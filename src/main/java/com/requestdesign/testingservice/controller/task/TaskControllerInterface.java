package com.requestdesign.testingservice.controller.task;

import com.requestdesign.testingservice.dto.test.task.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TaskControllerInterface {
    @GetMapping("/{taskId}")
    public ResponseEntity getTaskById(@PathVariable Long taskId);

    @PutMapping("/{taskId}")
    public ResponseEntity editTaskById(@PathVariable Long taskId, @RequestBody TaskDto taskDto);

    @PostMapping("/")
    public ResponseEntity createTask(@RequestBody TaskDto taskDto);

    @GetMapping("/")
    public ResponseEntity getAllTasks();
}
