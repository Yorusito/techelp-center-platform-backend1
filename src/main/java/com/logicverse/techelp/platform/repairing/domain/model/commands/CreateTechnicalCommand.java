package com.logicverse.techelp.platform.repairing.domain.model.commands;

public record CreateTechnicalCommand(String name, String lastName, String phone, String address, String city,String experience, String photo, String email, String password,
                                     String description) {
}
