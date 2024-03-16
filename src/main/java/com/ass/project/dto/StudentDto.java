package com.ass.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer marks;
    private SubjectDto subjectDto;
}
