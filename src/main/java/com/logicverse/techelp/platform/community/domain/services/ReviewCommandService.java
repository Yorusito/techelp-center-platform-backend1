package com.logicverse.techelp.platform.community.domain.services;

import com.logicverse.techelp.platform.community.domain.model.commands.CreateReviewCommand;
public interface ReviewCommandService {
    Long handle(CreateReviewCommand command);
}
