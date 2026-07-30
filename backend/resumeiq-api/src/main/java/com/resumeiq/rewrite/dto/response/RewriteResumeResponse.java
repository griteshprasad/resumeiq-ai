package com.resumeiq.rewrite.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class RewriteResumeResponse {

    private List<RewrittenSection> rewrittenSections;

}