package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

public interface SecurityService {
    public String findLoggedInUsername();
    public void autoLogin(String username, String password);
}
