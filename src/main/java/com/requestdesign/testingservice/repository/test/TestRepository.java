package com.requestdesign.testingservice.repository.test;

import com.requestdesign.testingservice.dto.test.TaskBlockNumberDto;
import com.requestdesign.testingservice.dto.test.TestCreationDto;
import com.requestdesign.testingservice.dto.test.TestManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.phrase.PhraseToTestDto;
import com.requestdesign.testingservice.entity.phrase.TestPhrase;
import com.requestdesign.testingservice.entity.test.*;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.exceptions.phrase.PhraseNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TestNotFoundException;
import com.requestdesign.testingservice.repository.block.BlockRepository;
import com.requestdesign.testingservice.rowmapper.phrase.TestPhraseRowMapper;
import com.requestdesign.testingservice.rowmapper.test.SimpleTestRowMapper;
import com.requestdesign.testingservice.rowmapper.test.TestRowMapper;
import com.requestdesign.testingservice.rowmapper.test.question.QuestionBlockRowMapper;
import com.requestdesign.testingservice.rowmapper.test.task.TaskBlockInTestRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class TestRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final BlockRepository blockRepository;

    @Autowired
    public TestRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, BlockRepository blockRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.blockRepository = blockRepository;
    }

    public Test findById(Long id) throws TestNotFoundException, TaskBlockNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectByIdQuery = "select * from test where id = :id";
        parameterSource.addValue("id", id);
        Optional<Test> test = namedParameterJdbcTemplate.query(selectByIdQuery, parameterSource, new TestRowMapper()).stream().findFirst();
        if(test.isEmpty()) {
            throw new TestNotFoundException("Тест с таким id не существует");
        }
        String selectTaskBlocksQuery = "select * from test_to_block as ttb join task_block as tb on ttb.task_block_id = tb.id where test_id = :id";
        List<TaskBlock> taskBlocks = namedParameterJdbcTemplate.query(selectTaskBlocksQuery, parameterSource, new TaskBlockInTestRowMapper()).stream().toList().stream().toList();
        List<TaskBlock> tasks = new ArrayList<>();
        for(var block: taskBlocks) {
            block = blockRepository.findTaskBlockById(block.getId());
            tasks.add(block);
        }
        String selectQuestionBlocksQuery = "select * from test_to_question_block as ttqb join question_block as qb on qb.id = ttqb.question_block_id where ttqb.test_id = :id";
        List<QuestionBlock> questions = namedParameterJdbcTemplate.query(selectQuestionBlocksQuery, parameterSource, new QuestionBlockRowMapper()).stream().toList();
        List<QuestionBlock> questionBlocks = new ArrayList<>();
        for(var block: questions) {
            block = blockRepository.findQuestionBlockById(block.getId());
            questionBlocks.add(block);
        }

        test.get().setQuestionBlocks(new HashSet<>(questionBlocks));
        test.get().setTaskBlocks(new HashSet<>(tasks));

        return test.get();
    }

    public List<Test> findAllTests() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from test";
        return new ArrayList<>(namedParameterJdbcTemplate.query(selectAllQuery, mapSqlParameterSource, new SimpleTestRowMapper()).stream().toList());
    }

    @Transactional
    public Long saveTest(TestCreationDto test) {
        System.out.println(test);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("title", test.getTitle());
        mapSqlParameterSource.addValue("url", test.getUrl());
        mapSqlParameterSource.addValue("region", test.getRegion());
        mapSqlParameterSource.addValue("comment", test.getComment());
        String createTestQuery = "insert into test(title, url, region, comment) " +
                "values(:title, :url, :region, :comment);";
        KeyHolder holder = new GeneratedKeyHolder();
        String addQuestionBlockToTest = "insert into test_to_question_block(test_id, question_block_id)" +
                " values(:test_id, :question_block_id)";
        namedParameterJdbcTemplate.update(createTestQuery, mapSqlParameterSource, holder);
        Long id = (Long) holder.getKeys().get("id");
        mapSqlParameterSource.addValue("test_id", id);
        for(var question_block: test.getQuestionBlockIds()) {
            mapSqlParameterSource.addValue("question_block_id", question_block);
            namedParameterJdbcTemplate.update(addQuestionBlockToTest, mapSqlParameterSource, holder);
        }
        String addTaskBlockToTest = "insert into test_to_block(test_id, task_block_id, number)" +
                " values(:test_id, :task_block_id, :number)";
        for(var task_block: test.getTaskBlockNumberDtos()) {
            mapSqlParameterSource.addValue("task_block_id", task_block.getTaskBlockId());
            mapSqlParameterSource.addValue("number", task_block.getInteger());
            namedParameterJdbcTemplate.update(addTaskBlockToTest, mapSqlParameterSource, holder);
        }
        return id;
    }

    @Transactional
    public Long createTestManually(TestManuallyCreateDto test) {
        //TODO
        return null;
    }

    public void addTaskBlockToTestById(Long test, TaskBlockNumberDto taskBlockNumber) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String addTaskBlockQuery = "insert into test_to_block(test_id, task_block_id, number)" +
                "values(:test_id, :task_block_id, :number)";
        parameterSource.addValue("test_id", test);
        parameterSource.addValue("task_block_id", taskBlockNumber.getTaskBlockId());
        parameterSource.addValue("number", taskBlockNumber.getInteger());
        namedParameterJdbcTemplate.update(addTaskBlockQuery, parameterSource);
    }

    public void addTaskToTaskBlock(Long task, Long taskBlock) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String addTaskToBlock = "insert into task_block_to_task(task_block_id, task_id) "+
                "values(:task_block, :task_id)";
        parameterSource.addValue("task_block", taskBlock);
        parameterSource.addValue("task_id", task);
        namedParameterJdbcTemplate.update(addTaskToBlock, parameterSource);
    }

    public TestPhrase findPhraseFromTestById(Long testId, Long phraseId) throws PhraseNotFoundException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String findPhraseFromTest = "select * from test_phrase as tp where tp.test_id = :test_id and tp.phrase_id = :phrase_id";
        parameterSource.addValue("test_id", testId);
        parameterSource.addValue("phrase_id", phraseId);
        Optional<TestPhrase> testPhrase = namedParameterJdbcTemplate.query(findPhraseFromTest, parameterSource, new TestPhraseRowMapper()).stream().findFirst();
        if(testPhrase.isEmpty()) {
            throw new PhraseNotFoundException("Фараза с таким id не указана в тесте");
        } else {
            return testPhrase.get();
        }
    }

    public void addPhraseToTest(Long testId, PhraseToTestDto phraseAddToTestDto) {
        try {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            String addPhraseQuery = "insert into test_phrase(phrase_id, intensivity, test_id) "+
                    "values(:phrase_id, :intensivity, :test_id)";
            parameterSource.addValue("phrase_id", phraseAddToTestDto.getPhraseId());
            parameterSource.addValue("intensivity", phraseAddToTestDto.getIntensivity());
            parameterSource.addValue("test_id", testId);
            namedParameterJdbcTemplate.update(addPhraseQuery, parameterSource);
        } catch(Exception e) {
            System.out.println("Hello world");
        }
    }

    public List<TestPhrase> findAllTestPhrasesFromTest(Long testId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String selectAllQuery = "select * from test_phrase as tp where tp.test_id = :id";
        parameterSource.addValue("id", testId);
        List<TestPhrase> testPhrases = namedParameterJdbcTemplate.query(selectAllQuery, parameterSource, new TestPhraseRowMapper()).stream().toList();
        return testPhrases;
    }

    public void editPhraseInTest(Long testId, PhraseToTestDto phraseToTest) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String editPhraseQuery = "update test_phrase set intensivity = :intensivity where test_id = :test_id and phrase_id = :phrase_id";
        parameterSource.addValue("test_id", testId);
        parameterSource.addValue("phrase_id", phraseToTest.getPhraseId());
        parameterSource.addValue("intensivity", phraseToTest.getIntensivity());
        namedParameterJdbcTemplate.update(editPhraseQuery, parameterSource);
    }
}
