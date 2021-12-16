package org.java.training.helpdesk.service;

import org.java.training.helpdesk.entity.Likee;
import org.java.training.helpdesk.repository.LikeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeeService {

    private final LikeeRepository likeeRepository;
    private final AuthenticationService authenticationService;
    private final ChapterService chapterService;

    public LikeeService(LikeeRepository likeeRepository, AuthenticationService authenticationService,
                        ChapterService chapterService) {
        this.likeeRepository = likeeRepository;
        this.authenticationService = authenticationService;
        this.chapterService = chapterService;
    }

    public void service(Long chapterId) {
        Likee like = likeeRepository
                .findLikeeByChapterIdAndUserId(chapterId, authenticationService.getAuthenticateUser().getId());
        if (like != null) {
            likeeRepository.delete(like);
        } else {
            likeeRepository.save(new Likee(authenticationService.getAuthenticateUser(),
                    chapterService.getChapter(chapterId)));
        }
    }
}