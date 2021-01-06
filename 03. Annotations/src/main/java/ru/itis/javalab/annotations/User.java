package ru.itis.javalab.annotations;

@HtmlForm(method = "post", action = "/users")
public class User {
    @FormFiled(type = "text", name = "usr_name", placeholder = "Логин")
    private String username;
    @FormFiled(type = "password", name = "usr_pswd", placeholder = "Пароль")
    private String password;
}
