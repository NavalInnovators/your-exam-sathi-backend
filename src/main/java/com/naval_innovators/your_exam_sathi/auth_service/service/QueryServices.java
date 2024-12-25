package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.models.UserQuery;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserQueryResponse;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface QueryServices {

    public UserQuery submitQuery(QueryRequest queryRequest);
    public List<UserQueryResponse> getUserQueries(Long profileId);
}