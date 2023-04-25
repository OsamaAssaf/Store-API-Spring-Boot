package com.assaf.StoreApi.utils.Methods;

import com.assaf.StoreApi.hendlers.custom.EmailNotFoundException;
import com.assaf.StoreApi.user.User;
import com.assaf.StoreApi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMethods {

    final private UserRepository userRepository;


    public User getCurrentUser()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return userRepository.findByEmail(currentUsername)
                    .orElseThrow(() -> new EmailNotFoundException("Email Not Found|البريد الالكتروني غير مسجل"));
        }
        return null;
    }
    public Long getCurrentUserId()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            User user = userRepository.findByEmail(currentUsername)
                    .orElseThrow(() -> new EmailNotFoundException("Email Not Found|البريد الالكتروني غير مسجل"));
            return user.getId();
        }
        return null;
    }

    public String getCurrentUsername()  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
