package com.resumeiq.resume.service.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoredFile {

    private String storedFileName;

    private String storagePath;

}