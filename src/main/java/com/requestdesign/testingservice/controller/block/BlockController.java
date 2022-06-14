package com.requestdesign.testingservice.controller.block;

import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockCreateDto;
import com.requestdesign.testingservice.entity.test.QuestionBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }

    @Override
    public ResponseEntity editTaskBlockById(Long blockId, TaskBlockCreateDto taskBlockCreateDto) {
        return null;
    }

    @Override
    public ResponseEntity createQuestionBlock(QuestionBlockCreateDto questionBlockCreateDto) {
        return null;
    }

    @Override
    public ResponseEntity getAllQuestionBlock() {
        return null;
    }

    @Override
    public ResponseEntity getAllTaskBlocks() {
        return null;
    }

    @Override
    public ResponseEntity createTaskBlock(TaskBlockCreateDto taskBlockCreateDto) {
        return null;
    }
}
