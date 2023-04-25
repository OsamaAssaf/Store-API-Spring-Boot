package com.assaf.StoreApi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    final private UserService userService;

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<Boolean> deleteUserById(
            @PathVariable(name = "user_id") Long userId
    ) throws AccessDeniedException {
        return userService.deleteUserById(userId);
    }

    @GetMapping("/reset_password")
    public ResponseEntity<Boolean> resetPassword() throws AccessDeniedException {
        return userService.resetPassword();
    }

}
