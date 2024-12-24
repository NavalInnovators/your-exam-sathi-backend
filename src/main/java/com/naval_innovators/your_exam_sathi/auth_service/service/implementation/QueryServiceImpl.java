package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import java.time.LocalDate;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.models.Query;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.QueryRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CourseRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.QueryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryServices {

    private final QueryRepository queryRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public Query submitQuery(QueryRequest queryRequest) {
        // Find course by courseId
        Course course = courseRepository.findById(queryRequest.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        // Find user by userId
        User user = userRepository.findById(queryRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        // Build and save the query
        Query query = Query.builder()
                .query(queryRequest.getQuery())
                .course(course)
                .user(user)
                .submissionDate(LocalDate.now())
                .build();

        queryRepository.save(query);
        return query;
    }
}
