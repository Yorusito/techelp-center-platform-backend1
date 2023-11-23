package com.logicverse.techelp.platform.monitoring.application.internal.commandservices;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.CreateComponentItemCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.commands.UpdateComponentItemCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.entities.ComponentItem;
import com.logicverse.techelp.platform.monitoring.domain.model.entities.DashBoard;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.monitoring.domain.services.ComponentItemCommandService;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.ComponentItemRepository;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class ComponentItemCommandServiceImpl implements ComponentItemCommandService {
    private ComponentItemRepository componentItemRepository;
    private DashboardRepository dashboardRepository;

    public ComponentItemCommandServiceImpl(ComponentItemRepository componentItemRepository, DashboardRepository dashboardRepository) {
        this.componentItemRepository = componentItemRepository;
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Long handle(CreateComponentItemCommand command) {
        var dashboard = dashboardRepository.findByTechnicianId(new TechnicianId(command.TechnicianId()));
        if (dashboard.isEmpty()) dashboardRepository.save(new DashBoard(command.TechnicianId()));
            //throw new IllegalArgumentException("Buy our membership to access inventory");
        var component = new ComponentItem(command.name(), command.quantity(),command.price(),dashboard.get());
        componentItemRepository.save(component);
        return component.getId();
    }

    @Override
    public void handle(UpdateComponentItemCommand command) {
        var dashboard = dashboardRepository.findByTechnicianId(new TechnicianId(command.technicianId()));
        this.componentItemRepository.deleteByDashBoard(dashboard.get());
    }
}
