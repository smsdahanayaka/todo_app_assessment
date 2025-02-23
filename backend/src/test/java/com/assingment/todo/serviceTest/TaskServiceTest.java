package com.assingment.todo.serviceTest;

import com.assingment.todo.entity.Task;
import com.assingment.todo.repository.TaskRepository;
import com.assingment.todo.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task(1L, "Test Task", "This is a test task", Task.Status.NO);
    }

    // TEST - TEST FUNCTION FOR SAVE TASK
    @Test
    void testSaveTask() {

        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task);

        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
        assertEquals(Task.Status.NO, savedTask.getStatus());

        verify(taskRepository, times(1)).save(task);
    }

    // TEST - TEST FUNCTION FOR GET TASK BY ID
    @Test
    void testGetTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> fetchedTask = taskService.getTaskById(1L);

        assertTrue(fetchedTask.isPresent());
        assertEquals("Test Task", fetchedTask.get().getTitle());
        assertEquals(Task.Status.NO, fetchedTask.get().getStatus());

        verify(taskRepository, times(1)).findById(1L);
    }

    // TEST - TEST FUNCTION FOR DELETE TASK
    @Test
    void testDeleteTask() {
        // Act
        taskService.deleteTask(1L);

        // Assert
        verify(taskRepository, times(1)).deleteById(1L);
    }

    // TEST - TEST FUNCTION FOR GET ALL TASK
    @Test
    void testGetAllTasks() {
        // Arrange
        when(taskRepository.findPendingTasks()).thenReturn(List.of(task));

        // Act
        List<Task> tasks = taskService.getAlltask();

        // Assert
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals("Test Task", tasks.get(0).getTitle());

        // Verify repository interaction
        verify(taskRepository, times(1)).findPendingTasks();
    }
}
