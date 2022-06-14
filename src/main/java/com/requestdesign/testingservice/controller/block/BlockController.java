package com.requestdesign.testingservice.controller.block;

import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockManuallyCreateDto;
import com.requestdesign.testingservice.entity.test.QuestionBlock;
import com.requestdesign.testingservice.entity.test.TaskBlock;
import com.requestdesign.testingservice.service.block.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlockController implements BlockControllerInterface {
    private final BlockService blockService;

    @Autowired
    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }
    @Override
    public ResponseEntity getQuestionBlockById(Long blockId) {
        QuestionBlock questionBlock = blockService.getQuestionBlockById(blockId);
        return new ResponseEntity(questionBlock, HttpStatus.OK);
    }

    @Override
    public ResponseEntity editQuestionBlockById(Long blockId, QuestionBlockCreateDto questionBlockCreateDto) {
        blockService.editQuestionBlockById(blockId, questionBlockCreateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getTaskBlockById(Long blockId) {
        TaskBlock taskBlock = blockService.getTaskBlockById();
        return new ResponseEntity(taskBlock, HttpStatus.OK);
    }

    @Override
    public ResponseEntity editTaskBlockById(Long blockId, TaskBlockCreateDto taskBlockCreateDto) {
        blockService.editTaskBlockById(blockId, taskBlockCreateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity createQuestionBlock(QuestionBlockCreateDto questionBlockCreateDto) {
        Long id = blockService.createQuestionBlock(questionBlockCreateDto);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllQuestionBlock() {
        List<QuestionBlock> questionBlockList = blockService.getAllQuestionBlocks();
        return new ResponseEntity(questionBlockList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllTaskBlocks() {
        List<TaskBlock> taskBlocks = blockService.getAllTaskBlocks();
        return new ResponseEntity(taskBlocks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity createTaskBlock(TaskBlockCreateDto taskBlockCreateDto) {
        blockService.createTaskBlock(taskBlockCreateDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
