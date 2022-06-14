package com.requestdesign.testingservice.service.task;

import com.requestdesign.testingservice.dto.test.task.TaskDto;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.exceptions.test.TaskNotFoundException;
import com.requestdesign.testingservice.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findTaskById(Long taskId) throws TaskNotFoundException {
        Task task = taskRepository.findTaskById(taskId);
        return task;
    }

    public void editTaskById(Long taskId, TaskDto taskDto) {
        taskRepository.editTaskById(taskId, taskDto);
    }

    public void createTask(TaskDto taskDto) {
        taskRepository.createTask(taskDto);
    }

    public List<Task> findAllTasks() {
        List<Task> tasks = taskRepository.findAllTasks();
        return tasks;
    }
}
