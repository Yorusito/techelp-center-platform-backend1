package com.logicverse.techelp.platform.iam.interfaces.rest;

import com.logicverse.techelp.platform.iam.domain.services.UserCommandService;
import com.logicverse.techelp.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.logicverse.techelp.platform.iam.interfaces.rest.resources.SignInResource;
import com.logicverse.techelp.platform.iam.interfaces.rest.resources.SignUpResource;
import com.logicverse.techelp.platform.iam.interfaces.rest.resources.UserResource;
import com.logicverse.techelp.platform.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.logicverse.techelp.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.logicverse.techelp.platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.logicverse.techelp.platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByIdQuery;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalCommandService;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalQueryService;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.CreateTechnicalResource;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.TechnicalResource;
import com.logicverse.techelp.platform.repairing.interfaces.rest.transform.CreateTechnicalFromResourceAssembler;
import com.logicverse.techelp.platform.repairing.interfaces.rest.transform.TechnicalResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Management Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final TechnicalQueryService technicalQueryService;
    private final TechnicalCommandService technicalCommandService;

    public AuthenticationController(UserCommandService userCommandService,TechnicalQueryService technicalQueryService, TechnicalCommandService technicalCommandService) {
        this.userCommandService = userCommandService;
        this.technicalQueryService = technicalQueryService;
        this.technicalCommandService = technicalCommandService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticatedUserResource);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @PostMapping("/sign-up/technical")
    public ResponseEntity<TechnicalResource> createTechnical(@RequestBody CreateTechnicalResource resource) {
        var command = CreateTechnicalFromResourceAssembler.toCommandFrom(resource);
        var technicalId = technicalCommandService.handle(command);

        var query = new GetTechnicalByIdQuery(technicalId);
        var technicalByQuery = technicalQueryService.handle(query);

        var technicalResource = TechnicalResourceFromEntityAssembler.toResourceFromEntity(technicalByQuery.get());
        return new ResponseEntity<>(technicalResource, HttpStatus.CREATED);
    }

}
