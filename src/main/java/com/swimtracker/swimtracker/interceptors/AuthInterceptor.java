package com.swimtracker.swimtracker.interceptors;

import com.swimtracker.swimtracker.infra.security.PasswordService;
import com.swimtracker.swimtracker.repository.UsersRepository;
import com.swimtracker.swimtracker.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String subject = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails user = usersRepository.findByLogin(subject);
        if (passwordService.verifyPassword(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Altere sua senha para acessar o sistema.");
            return false;

        }

        return true;

    }

}
