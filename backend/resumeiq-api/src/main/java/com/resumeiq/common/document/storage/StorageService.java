package com.resumeiq.common.document.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	StoredFile store(MultipartFile file);
	void delete(String storagePath);

}
