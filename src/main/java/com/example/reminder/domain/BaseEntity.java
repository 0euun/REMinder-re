package com.example.reminder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;
}
