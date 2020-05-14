package ch.hftm.vsblog.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
@Named("loginInfo")
public class LoginInfo {
    @Inject
    JsonWebToken token;

    public boolean isLoggedIn() {
        if(token.getGroups() != null && token.getGroups().size() > 0) {
            return true;
        }
        return false;
    }
}