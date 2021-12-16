package org.java.training.helpdesk.repository;

import org.java.training.helpdesk.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}