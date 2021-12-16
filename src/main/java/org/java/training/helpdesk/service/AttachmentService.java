package org.java.training.helpdesk.service;

import org.java.training.helpdesk.entity.Attachment;
import org.java.training.helpdesk.exception.FileStorageException;
import org.java.training.helpdesk.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
public class AttachmentService {
    private final static String STORAGE_EXCEPTION = "{service.AttachmentService.message}";

    private final AttachmentRepository attachmentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public Attachment create(MultipartFile file) {
        try {
            return attachmentRepository.save(new Attachment(file.getBytes(),
                    file.getOriginalFilename(), file.getContentType()));
        } catch (IOException e) {
            throw new FileStorageException(STORAGE_EXCEPTION); }

    }
}