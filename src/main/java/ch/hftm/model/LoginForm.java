package ch.hftm.model;

import javax.ws.rs.FormParam;

public class LoginForm {
    public @FormParam("username") String username;
    public @FormParam("password") String password;
    public String errorMessage;
}