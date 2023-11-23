package com.logicverse.techelp.platform.community.interfaces.rest.transform;

import com.logicverse.techelp.platform.community.domain.model.commands.CreateReviewCommand;
import com.logicverse.techelp.platform.community.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewFromResourceAssembler {
    public static CreateReviewCommand toCommandFrom(CreateReviewResource resource){
        return new CreateReviewCommand(resource.comment(),resource.assessment(),resource.technicalId());
    }
}
