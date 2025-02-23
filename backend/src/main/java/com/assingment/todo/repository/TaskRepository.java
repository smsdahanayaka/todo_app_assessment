package com.assingment.todo.repository;

import com.assingment.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// REPO - CLASS OF TASK REPO
public interface TaskRepository extends JpaRepository<Task,Long> {

    // get only pending task
    @Query("SELECT t FROM Task t WHERE t.status = 'NO' ORDER BY t.createAt DESC")
    List<Task> findPendingTasks();
}
