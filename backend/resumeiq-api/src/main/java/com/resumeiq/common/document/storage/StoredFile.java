package com.resumeiq.common.document.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoredFile {

    private String storedFileName;

    private String storagePath;

}