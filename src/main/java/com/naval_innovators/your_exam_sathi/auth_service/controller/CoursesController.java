package com.naval_innovators.your_exam_sathi.auth_service.controller;

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
    public ResponseEntity<Map<String,Boolean>> addCourse(@RequestBody Course course) {
        coursesService.addCourse(course);
        Map<String,Boolean> response = new HashMap<>();
        response.put("success",true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = coursesService.findAllCourses();
        if(courses.isEmpty()){
            return ResponseEntity.noContent().build(); // response code - 204(No Content)
        }
        return ResponseEntity.ok(courses);
    }


}
