package com.assingment.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

// ENTITY - CLASS OF TASK ENTITY
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @Column(length = 255)
    private String title; // title

    private String description; // Description

    @Enumerated(EnumType.STRING)
    private Status status = Status.NO; // Default status

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt; // Set creation time

    @CreationTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateBy; // Set update time

    public Task(long l, String old_title, String old_description, boolean b) {
    }

    public Task(long id, String title, String description, Task.Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Getter for get id
    public Long getId() { return id; }

    // Setter for set id
    public void setId(Long id) { this.id = id; }

    // Getter for Get title
    public String getTitle() { return title; }

    // Setter for Set title
    public void setTitle(String title) { this.title = title; }

    // Getter for Get description
    public String getDescription() { return description; }

    // Setter for Set description
    public void setDescription(String description) { this.description = description; }

    // Getter for get status
    public Status getStatus() { return status; }

    // Setter for set status
    public void setStatus(Status status) { this.status = status; }

    // Getter for get Create time
    public LocalDateTime getCreateAt() { return createAt; }

    // Setter for Get Create time
    public void setCreateAt(LocalDateTime createAt) { this.createAt = createAt; }

    // Getter for get Update time
    public LocalDateTime getUpdateBy() { return updateBy; }

    // Enum Status
    public enum Status { YES, NO; }
}
