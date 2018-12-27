package sample.usecase.security;

import javax.validation.Valid;
import javax.validation.constraints.*;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.security.authentication.*;
import io.micronaut.security.endpoints.LoginController;
import io.micronaut.security.handlers.LoginHandler;
import io.reactivex.Single;
import lombok.Data;
import sample.context.Dto;

/**
 * ユーザ名とパスワードのキー変更拡張用途
 */
@Replaces(LoginController.class)
public class ApiLoginController extends LoginController {

    public ApiLoginController(
            Authenticator authenticator,
            LoginHandler loginHandler,
            ApplicationEventPublisher eventPublisher) {
        super(authenticator, loginHandler, eventPublisher);
    }

    @SuppressWarnings("rawtypes")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Post
    public Single<HttpResponse> login(@Valid @Body LoginParams params, HttpRequest<?> request) {        
        return this.login(params.credentials(), request);
    }
    
    @Override
    @SuppressWarnings("rawtypes")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    @Post("/origin")
    public Single<HttpResponse> login(@Valid @Body UsernamePasswordCredentials usernamePasswordCredentials, HttpRequest<?> request) {
        
        return super.login(usernamePasswordCredentials, request);
    }
    
    @Data
    public static class LoginParams implements Dto {
        private static final long serialVersionUID = 1L;

        @NotBlank
        @NotNull
        private String loginId;
        @NotBlank
        @NotNull
        private String password;
        
        public UsernamePasswordCredentials credentials() {
            return new UsernamePasswordCredentials(loginId, password);
        }
        
    }

}
