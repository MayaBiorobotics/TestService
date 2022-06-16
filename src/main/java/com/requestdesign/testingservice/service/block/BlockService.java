package com.requestdesign.testingservice.service.block;

import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.entity.test.Question;
import com.requestdesign.testingservice.entity.test.QuestionBlock;
import com.requestdesign.testingservice.entity.test.TaskBlock;
import com.requestdesign.testingservice.exceptions.block.TaskBlockNotFoundException;
import com.requestdesign.testingservice.repository.block.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {
    private final BlockRepository blockRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public QuestionBlock getQuestionBlockById(Long blockId) throws TaskBlockNotFoundException {
        QuestionBlock questionBlock = blockRepository.findQuestionBlockById(blockId);
        return questionBlock;
    }

    public void editQuestionBlockById(Long blockId, QuestionBlockCreateDto questionBlockCreateDto) {
        blockRepository.editQuestionBlockById(blockId, questionBlockCreateDto);
    }

    public TaskBlock getTaskBlockById(Long id) throws TaskBlockNotFoundException {
        TaskBlock taskBlock = blockRepository.findTaskBlockById(id);
        return taskBlock;
    }

    public void editTaskBlockById(Long blockId, TaskBlockCreateDto taskBlockCreateDto) {
        blockRepository.editTaskBlockById(blockId, taskBlockCreateDto);
    }

    public Long createQuestionBlock(QuestionBlockCreateDto questionBlockCreateDto) {
        Long id = blockRepository.createQuestionBlock(questionBlockCreateDto);
        return id;
    }

    public List<QuestionBlock> getAllQuestionBlocks() throws TaskBlockNotFoundException {
        List<QuestionBlock> questions = blockRepository.findAllQuestionBlocks();
        return questions;
    }

    public List<TaskBlock> getAllTaskBlocks() throws TaskBlockNotFoundException {
        List<TaskBlock> taskBlocks = blockRepository.findAllTaskBlocks();
        return taskBlocks;
    }

    public void createTaskBlock(TaskBlockCreateDto taskBlockCreateDto) {
        blockRepository.createTaskBlock(taskBlockCreateDto);
    }
}
