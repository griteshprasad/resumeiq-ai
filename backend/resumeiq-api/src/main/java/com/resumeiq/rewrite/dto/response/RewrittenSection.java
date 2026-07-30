package com.resumeiq.rewrite.dto.response;

import com.resumeiq.rewrite.dto.request.ResumeSectionType;

import lombok.Data;

@Data
public class RewrittenSection {

    private ResumeSectionType section;

    private String rewrittenContent;

    private String explanation;

}