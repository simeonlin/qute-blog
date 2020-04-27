package ch.hftm.model;

import javax.ws.rs.FormParam;

public class PostForm {
    public @FormParam("title") String title;
    public @FormParam("content") String content;
    public String errorMessage;
}