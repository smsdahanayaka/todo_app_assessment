package com.assingment.todo.controllerTest;

import com.assingment.todo.controller.TaskController;
import com.assingment.todo.entity.Task;
import com.assingment.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    // TEST - TEST FUNCTION FOR GET ALL TASK
    @Test
    void testGetAllList() {
        Task task1 = new Task(1L, "Task 1", "Description 1", false);
        Task task2 = new Task(2L, "Task 2", "Description 2", false);

        when(taskService.getAlltask()).thenReturn(Arrays.asList(task1, task2));

        ResponseEntity<List<Task>> response = taskController.getAllList();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(taskService, times(1)).getAlltask();
    }

    // TEST - TEST FUNCTION FOR CREATE TASK
    @Test
    void testSetTask() {
        Task newTask = new Task(3L, "Task 3", "Description 3", false);

        when(taskService.saveTask(any(Task.class))).thenReturn(newTask);

        ResponseEntity<Object> response = taskController.setTask(newTask);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(newTask, response.getBody());
        verify(taskService, times(1)).saveTask(newTask);
    }

    // TEST - TEST FUNCTION FOR UPDATE TASK
    @Test
    void testUpdateTask() {
        Task existingTask = new Task(1L, "Old Title", "Old Description", false);
        Task updatedTask = new Task(1L, "Updated Title", "Updated Description", true);

        when(taskService.getTaskById(1L)).thenReturn(Optional.of(existingTask));
        when(taskService.saveTask(any(Task.class))).thenReturn(updatedTask);

        ResponseEntity<Object> response = taskController.updateTask(1L, updatedTask);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedTask, response.getBody());
        verify(taskService, times(1)).saveTask(any(Task.class));
    }

    // TEST - TEST FUNCTION FOR DELETE TASK
    @Test
    void testDeleteTask() {
        Task existingTask = new Task(1L, "Task 1", "Description 1", false);

        when(taskService.getTaskById(1L)).thenReturn(Optional.of(existingTask));
        doNothing().when(taskService).deleteTask(1L);

        ResponseEntity<Object> response = taskController.deleteTask(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Task 1 delete successfully", response.getBody());
        verify(taskService, times(1)).deleteTask(1L);
    }

    // TEST - TEST FUNCTION FOR NOT FOUND TASK
    @Test
    void testDeleteTaskNotFound() {
        when(taskService.getTaskById(100L)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = taskController.deleteTask(100L);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("task not find with id 100", response.getBody());
        verify(taskService, never()).deleteTask(100L);
    }
}