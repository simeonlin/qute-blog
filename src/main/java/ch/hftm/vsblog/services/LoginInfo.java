package ch.hftm.vsblog.services;

import io.quarkus.security.AuthenticationFailedException;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("loginInfo")
public class LoginInfo {
    @Inject
    JsonWebToken token;

    public boolean isLoggedIn() {
        try {
            return token.getGroups() != null && token.getGroups().size() > 0;
        } catch (AuthenticationFailedException e) {
            // Exception not caught during injection from qute-template
            return false;
        }
        
    }
}
