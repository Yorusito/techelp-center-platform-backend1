package com.logicverse.techelp.platform.iam.infrastructure.hashing.bcrypt;
import com.logicverse.techelp.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
