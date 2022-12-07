package com.sparta.poster.entity;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class Timestamped {
    @CreatedDate
    private LocalDateTime createdAt;

    public Timestamped() {
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

}