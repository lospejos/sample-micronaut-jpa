package sample.usecase.security;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.*;
import io.micronaut.security.session.*;

@Singleton
@Replaces(SessionLogoutHandler.class)
public class ApiLogoutHandler extends SessionLogoutHandler {

    public ApiLogoutHandler(SecuritySessionConfiguration securitySessionConfiguration) {
        super(securitySessionConfiguration);
    }
    
    @Override
    public HttpResponse<?> logout(HttpRequest<?> request) {
        super.logout(request);
        return HttpResponse.ok();
    }
    
}
