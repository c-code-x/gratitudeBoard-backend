package com.Studentlifr.GratitudeBoard.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "messages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "mid", updatable = false, nullable = false)
    @UuidGenerator
    private UUID mid;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserDAO user;

    @Size(max = 5000)
    @Column(name = "message", nullable = false)
    private String message;

    @Builder.Default
    @Column(name = "posted_on", nullable = false)
    private String postedOn = LocalDate.now().toString();


    @Column(name = "is_anonymous", nullable = false)
    @Builder.Default
    private Boolean isAnonymous = false;
}
