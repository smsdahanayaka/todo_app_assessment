package com.assingment.todo.service;

import com.assingment.todo.entity.Task;
import com.assingment.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// SERVICE - CLASS OF TASK SERVISE
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    // FETCH - FUNCTION OF FETCH ALL TASK
    public List<Task> getAlltask() {
        try{
            return taskRepository.findPendingTasks();
        }catch(Exception e){
            throw  new RuntimeException("Error Fetch tasks ",e);
        }
    }

    // INSERT - FUNCTION OF INSERT TASK
    public Task saveTask(Task task) {
        try{
            return taskRepository.save(task);
        }catch(Exception e){
            throw  new RuntimeException("Error saving task ",e);
        }

    }

    // GET - FUNCTION OF GET TASK BY ID
    public Optional<Task> getTaskById(Long id) {
        try{
            return taskRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException("Error find task by id ",e);
        }
    }

    //DELETE - FUNCTION OF DELETE TASK BY ID
    public void deleteTask(Long id) {
        try{
            taskRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error delete task id " + id);
        }
    }
}
