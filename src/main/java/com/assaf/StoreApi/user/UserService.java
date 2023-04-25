package com.assaf.StoreApi.user;

import com.assaf.StoreApi.enums.Role;
import com.assaf.StoreApi.reset_password.ResetPasswordToken;
import com.assaf.StoreApi.reset_password.ResetPasswordTokenRepository;
import com.assaf.StoreApi.utils.Methods.UserMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private ResetPasswordTokenRepository resetPasswordTokenRepository;
    final private  UserMethods userMethods;

    private void checkAuthorities() throws AccessDeniedException {
        String userAuth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (!userAuth.contains(Role.SUPER_ADMIN.toString())) {
            throw new AccessDeniedException("Access Denied|الوصول مرفوض");
        }
    }

//    private void createPasswordResetTokenForUser(User user, String token) {
//        ResetPasswordToken myToken = new ResetPasswordToken(token, user);
//        resetPasswordTokenRepository.save(myToken);
//    }

    public ResponseEntity<Boolean> deleteUserById(Long userId) throws AccessDeniedException {
        checkAuthorities();
        try {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(true);
        } catch (Exception ignored) {
            return ResponseEntity.ok(false);
        }
    }

    public ResponseEntity<Boolean> resetPassword() throws AccessDeniedException {
        User user = userMethods.getCurrentUser();
        if(user == null){
            throw new AccessDeniedException("Access Denied|الوصول مرفوض");
        }
        String currentUsername = user.getEmail();
        Long currentUserId = user.getId();

        String token = UUID.randomUUID().toString();
//        createPasswordResetTokenForUser(user, token);

        return ResponseEntity.ok(true);
    }
}
