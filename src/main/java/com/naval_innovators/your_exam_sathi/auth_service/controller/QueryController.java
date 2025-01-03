package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserQueryResponse;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.QuerySubmitResponse;
import com.naval_innovators.your_exam_sathi.auth_service.service.QueryServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/queries")
@RequiredArgsConstructor
public class QueryController {

    private final QueryServices queryService;

    @PostMapping("/submit")
    public ResponseEntity<QuerySubmitResponse> submitQuery(@RequestBody @Valid QueryRequest queryRequest) {
        QuerySubmitResponse response = new QuerySubmitResponse();
        try {
            queryService.submitQuery(queryRequest);
            response.setMessage("Query has been Successfully Submitted!!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.setMessage("Failed to submit the query.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile/{profileId}")
    public List<UserQueryResponse> getUserQueries(@PathVariable Long profileId) {
        List<UserQueryResponse> userQueries = queryService.getUserQueries(profileId);

        if (userQueries.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No queries found for the given profile ID.");
        }

        return userQueries;
    }
}