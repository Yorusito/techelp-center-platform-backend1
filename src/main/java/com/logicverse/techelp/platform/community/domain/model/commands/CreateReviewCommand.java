package com.logicverse.techelp.platform.community.domain.model.commands;


public record CreateReviewCommand(String comment, String assessment, Long technicalId) {

}
