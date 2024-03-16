package com.ass.project.service;

import com.ass.project.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudentsWithMarksGreaterThan50();
    StudentDto createStudent(StudentDto studentDTO);
}
