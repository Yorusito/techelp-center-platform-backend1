package com.logicverse.techelp.platform.monitoring.domain.services;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.CreateComponentItemCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.commands.UpdateComponentItemCommand;

public interface ComponentItemCommandService {
    Long handle(CreateComponentItemCommand command);

    void handle(UpdateComponentItemCommand command);
}
