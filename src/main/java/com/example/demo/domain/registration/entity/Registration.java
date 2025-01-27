package com.example.demo.domain.registration.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.demo.domain.qrcode.entity.QRCode;
import com.example.demo.domain.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "qrcode_id", nullable = false)
    private QRCode qrcode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Registration(QRCode qrcode, User user) {
        this.qrcode = qrcode;
        this.user = user;
        this.registeredAt = LocalDateTime.now();
    }
}