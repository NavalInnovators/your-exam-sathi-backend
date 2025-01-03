package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.BranchResponse;
import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.repository.BranchRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchResponse> getAllBranches() {
        // Fetch only branch ID and branch name
        return branchRepository.findAll().stream()
                .map(branch -> new BranchResponse(branch.getId(), branch.getName()))
                .collect(Collectors.toList());
    }
}

