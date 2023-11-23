package com.logicverse.techelp.platform.community.interfaces.rest.resources;

public record ReviewResource(
        String comment,

        String assessment,
        Long technicalId
) {
}
