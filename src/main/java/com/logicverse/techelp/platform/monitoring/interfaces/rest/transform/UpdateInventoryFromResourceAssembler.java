package com.logicverse.techelp.platform.monitoring.interfaces.rest.transform;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.CreateComponentItemCommand;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.UpdateInventoryResource;

import java.util.ArrayList;
import java.util.List;

public class UpdateInventoryFromResourceAssembler {
    public static List<CreateComponentItemCommand> toCommandFrom(UpdateInventoryResource resource, Long technicianId){
        List<CreateComponentItemCommand> listCommands = new ArrayList<>();
        var items = resource.items();
        for(int i=0; i < resource.items().size(); i++){
            listCommands.add(new CreateComponentItemCommand(items.get(i).name(),items.get(i).quantity(),
                    items.get(i).price(),technicianId));
        }
        return listCommands;
    }
}
