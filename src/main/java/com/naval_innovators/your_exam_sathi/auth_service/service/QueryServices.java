package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.models.Query;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.models.Subject;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.QueryRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.SubjectRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class QueryServices {

    private final QueryRepository queryRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public QueryServices(QueryRepository queryRepository, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.queryRepository = queryRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public Query submitQuery(QueryRequest queryRequest) {
        Subject subject = subjectRepository.findById(queryRequest.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found."));

        User user = userRepository.findById(queryRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        
        Query query = Query.builder()
                .query(queryRequest.getQuery())
                .subject(subject)
                .user(user)
                .submissionDate(LocalDate.now())  
                .build();

        queryRepository.save(query);
        return query;
    }
}
