package org.java.training.helpdesk.converter;

import org.java.training.helpdesk.dto.ChapterDto;
import org.java.training.helpdesk.dto.ChapterWrapperDto;
import org.java.training.helpdesk.entity.Chapter;
import org.java.training.helpdesk.utils.CopyUtils;;
import org.springframework.stereotype.Component;

@Component
public class ChapterConverter {

    public ChapterDto toDto(final Chapter entity) {
        return new ChapterDto(entity.getId(), entity.getNumber(), entity.getName(), entity.getContent(),
                entity.getLikes().size(), entity.getAttachment());
    }

    public Chapter fromDto(final ChapterWrapperDto dto) {
        Chapter chapter = new Chapter();
        CopyUtils.copyNonNullProperties(dto, chapter);
        return chapter;
    }
}