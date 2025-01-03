package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CourseResponse;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.responseDTOs.AppResponse;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.service.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService;

    @PostMapping("/add")
    public ResponseEntity<AppResponse> addCourse(@RequestBody Course course) {
        coursesService.addCourse(course);
       AppResponse appResponse = new AppResponse();
        appResponse.setResponse(true);
        return new ResponseEntity<>(appResponse, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<CourseResponse> courses = coursesService.getAllCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courses);
    }

}
