package com.resumeiq.rewrite.dto.response;

import lombok.Data;

@Data
public class RewriteResumeResponse {

    /**
     * AI generated content.
     */
    private String rewrittenContent;

    /**
     * Why the AI rewrote it this way.
     */
    private String explanation;

}