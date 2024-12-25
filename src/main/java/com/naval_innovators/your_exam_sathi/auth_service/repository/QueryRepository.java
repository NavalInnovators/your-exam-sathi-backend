package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserQueryResponse;
import com.naval_innovators.your_exam_sathi.auth_service.models.UserQuery;

import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<UserQuery, Long> {
    @Query("SELECT new com.naval_innovators.your_exam_sathi.auth_service.dtos.UserQueryResponse(q.query, q.answer, q.submissionDate) " +
           "FROM UserQuery q WHERE q.user.Id = :userId")
    List<UserQueryResponse> findUserQueriesByUserId(@Param("userId") Long userId);
}
