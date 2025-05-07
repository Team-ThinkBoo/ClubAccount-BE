package com.ClubAccount_BE.user.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface UploadProfileImagePort {
    String uploadProfileImage(Long userId, MultipartFile image);
}
