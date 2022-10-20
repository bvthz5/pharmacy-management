package com.example.pharmacy.form;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

    private MultipartFile profilePic;

    public MultipartFile getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(MultipartFile profilePic) {
        this.profilePic = profilePic;
    }
    
}
