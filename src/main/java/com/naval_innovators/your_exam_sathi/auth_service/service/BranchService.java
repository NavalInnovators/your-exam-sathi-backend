package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.BranchResponse;

import java.util.List;

public interface BranchService {
    List<BranchResponse> getAllBranches();
}

