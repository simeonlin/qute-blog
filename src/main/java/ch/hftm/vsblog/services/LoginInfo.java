package ch.hftm.vsblog.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.AuthenticationFailedException;

@RequestScoped
@Named("loginInfo")
public class LoginInfo {
    @Inject
    JsonWebToken token;

    public boolean isLoggedIn() {
        try {
            if(token.getGroups() != null && token.getGroups().size() > 0) {
                return true;
            }
            return false;
        } catch (AuthenticationFailedException e) {
            // Exception not catched during injection from qute-template
            return false;
        }
        
    }
}