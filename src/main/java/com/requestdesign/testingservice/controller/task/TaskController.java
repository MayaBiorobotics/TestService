package com.requestdesign.testingservice.controller.task;

import com.requestdesign.testingservice.dto.test.task.TaskDto;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.exceptions.test.TaskNotFoundException;
import com.requestdesign.testingservice.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.ReadPendingException;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController implements TaskControllerInterface{
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskRepository) {
        this.taskService = taskRepository;
    }
    @Override
    public ResponseEntity getTaskById(Long taskId) {
        Task task = null;
        try {
            task = taskService.findTaskById(taskId);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @Override
    public ResponseEntity editTaskById(Long taskId, TaskDto taskDto) {
        taskService.editTaskById(taskId, taskDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity createTask(TaskDto taskDto) {
        taskService.createTask(taskDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }
}
