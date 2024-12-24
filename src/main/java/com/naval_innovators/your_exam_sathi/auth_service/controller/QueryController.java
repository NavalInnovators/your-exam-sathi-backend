package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.models.Query;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.QueryRequest;
import com.naval_innovators.your_exam_sathi.auth_service.service.QueryServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/queries")
@RequiredArgsConstructor
public class QueryController {

    private final QueryServices queryService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuery(@RequestBody @Valid QueryRequest queryRequest) {
        queryService.submitQuery(queryRequest);
        return new ResponseEntity<>("Query has been Successfully Submitted!!", HttpStatus.CREATED);
    }
}
