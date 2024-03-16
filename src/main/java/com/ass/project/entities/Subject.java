package com.ass.project.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`subject`")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject")
    private String subject;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
//    private Student student;
}
