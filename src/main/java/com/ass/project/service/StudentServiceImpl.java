package com.ass.project.service;

import com.ass.project.dto.StudentDto;
import com.ass.project.dto.SubjectDto;
import com.ass.project.entities.Student;
import com.ass.project.entities.Subject;
import com.ass.project.repository.StudentRepository;
import com.ass.project.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        SubjectDto subjectDto = studentDTO.getSubjectDto();
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);
        student.setSubject(savedSubject);
        Student savedStudent = studentRepository.save(student);
        StudentDto savedStudentDto = modelMapper.map(savedStudent, StudentDto.class);
        savedStudentDto.setSubjectDto(modelMapper.map(savedStudent.getSubject(), SubjectDto.class));

        return savedStudentDto;
    }

    @Override
    public List<StudentDto> getStudentsWithMarksGreaterThan50() {
        List<Student> studentsWithMarksGreaterThan50 = studentRepository.findAll().stream()
                .filter(student -> Integer.parseInt(String.valueOf(student.getMarks())) > 50)
                .collect(Collectors.toList());

        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : studentsWithMarksGreaterThan50) {
            StudentDto studentDto = modelMapper.map(student, StudentDto.class);
            SubjectDto subjectDto = modelMapper.map(student.getSubject(), SubjectDto.class);
            studentDto.setSubjectDto(subjectDto);
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }
}
