package com.logicverse.techelp.platform.community.interfaces.rest.transform;

import com.logicverse.techelp.platform.community.domain.model.entities.Review;
import com.logicverse.techelp.platform.community.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {

    public static ReviewResource toResourceFromEntity (Review entity){
        return new ReviewResource(entity.getComment(), entity.getAssessment(), entity.getTechnical().getId());
    }
}
