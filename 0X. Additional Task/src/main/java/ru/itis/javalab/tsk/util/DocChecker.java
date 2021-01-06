package ru.itis.javalab.tsk.util;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itis.javalab.tsk.repositories.DocRepository;
import ru.itis.javalab.tsk.services.interfaces.DocService;
import ru.itis.javalab.tsk.services.interfaces.LastIterationService;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Component
@AllArgsConstructor
class DocChecker {

    private final DocService docService;
    private final DocRepository docRepository;
    private final LastIterationService lastIterationService;

    @Transactional
    @Scheduled(cron = "${doc.checker.cron}")
    public void checkDocs() {
        var then = lastIterationService.getTimestamp();
        var now = ZonedDateTime.now();

        docRepository.findAllByDeadlineBetween(then, now).forEach(docService::failDeadline);
        lastIterationService.setLastIteration(now);
    }
}
