package com.assingment.todo.controller;

import com.assingment.todo.entity.Task;
import com.assingment.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//CONTROLLER - CLASS OF TASK CONTROLLER
@RestController
@CrossOrigin
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET - FUNCTION OF GET ALL TASK LIST
    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllList(){
        try{

            // call to service class for get all task
            List<Task> tasks=taskService.getAlltask();

            // if empty
            if(tasks.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(tasks);
            }
            return ResponseEntity.ok(tasks);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    // POST - FUNCTION OF INSERT TASK
    @PostMapping("/setTask")
    public ResponseEntity<Object> setTask(@RequestBody Task task){
        try{

            // pass data to service for save task
            Task saveTask=taskService.saveTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveTask);
        }catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT - FUNCTION OF UPDATE TASK
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody Task task){
        try{

            // check if the task with the given id
            Optional<Task> exsisting=taskService.getTaskById(id);

            if(exsisting.isPresent()){
                Task upTask= exsisting.get();
                upTask.setTitle(task.getTitle());
                upTask.setDescription(task.getDescription());
                upTask.setStatus(task.getStatus());

                // update if the task with given id
                Task updateTask=taskService.saveTask(upTask);

                return ResponseEntity.status(HttpStatus.OK).body(updateTask);

            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not find with id "+ id);
            }

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error update task"+id);
        }
    }

    // DELETE - FUNCTION OF DELETE TASK
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        try{

            // check if the task with the given id
            Optional<Task> exsisting=taskService.getTaskById(id);

            if(exsisting.isPresent()){

                // delete if the task with given id
                taskService.deleteTask(id);
                return ResponseEntity.status(HttpStatus.OK).body("Task "+id+" delete successfully");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task not find with id "+ id);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error delete task "+ id) ;
        }
    }


}
