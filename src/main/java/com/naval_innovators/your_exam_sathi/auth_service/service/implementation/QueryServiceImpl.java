package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import java.time.LocalDate;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserQueryResponse;
import com.naval_innovators.your_exam_sathi.auth_service.models.UserQuery;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.QueryRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CourseRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.QueryServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryServices {

    private final QueryRepository queryRepository;
    private final CourseRepository courseRepository;
    private final ProfileRepository profileRepository;

    @Override
    public UserQuery submitQuery(QueryRequest queryRequest) {
        // Find course by courseId
        Course course = courseRepository.findById(queryRequest.getCourseId())
                        .orElseThrow(() -> new IllegalArgumentException("Course not found."));

        // Find profile by profileId
        Profile profile = profileRepository.findById(queryRequest.getProfileId())
                        .orElseThrow(() -> new IllegalArgumentException("Profile not found."));

        // Build and save the query
        UserQuery query = UserQuery.builder()
                        .query(queryRequest.getQuery())
                        .course(course)
                        .profile(profile)
                        .submissionDate(LocalDate.now())
                        .build();

        queryRepository.save(query);
        return query;
    }

    public List<UserQueryResponse> getUserQueries(Long profileId) {
        return queryRepository.findUserQueriesByProfileId(profileId);
    }
}
