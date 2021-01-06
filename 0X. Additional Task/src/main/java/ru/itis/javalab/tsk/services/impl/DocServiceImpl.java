package ru.itis.javalab.tsk.services.impl;

import ru.itis.javalab.tsk.dto.DocDto;
import ru.itis.javalab.tsk.models.Doc;
import ru.itis.javalab.tsk.repositories.DocRepository;
import ru.itis.javalab.tsk.services.interfaces.DocService;
import ru.itis.javalab.tsk.services.interfaces.EmailService;
import ru.itis.javalab.tsk.services.interfaces.LastIterationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class DocServiceImpl implements DocService {

    private final DocRepository docRepository;
    private final LastIterationService lastIterationService;
    private final EmailService emailService;

    @Override
    public int create(DocDto dto) {
        var doc = docRepository.save(Doc.builder()
                .email(dto.getEmail())
                .deadline(dto.getDeadline())
                .isPast(false)
                .build());

        if (dto.getDeadline().compareTo(lastIterationService.getTimestamp()) <= 0) {
            failDeadline(doc);
        }

        return doc.getId();
    }

    @Override
    public void failDeadline(Doc doc) {
        doc.setPast(true);
        docRepository.save(doc);
        emailService.sendMessage(doc.getEmail(), "doc_past_deadline", doc);
    }
}
