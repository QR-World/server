package com.example.demo.domain.qrcode.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.demo.domain.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "qrcodes")
public class QRCode {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public QRCode(String link, User user) {
        this.link = link;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.deleted = false;
    }
}