package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.models.Query;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.QueryRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface QueryServices {

    public Query submitQuery(QueryRequest queryRequest);
}
