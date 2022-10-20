package com.example.pharmacy.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.enitity.User;
import com.example.pharmacy.form.ChangePasswordForm;
import com.example.pharmacy.form.ImageForm;
import com.example.pharmacy.form.LoginForm;
import com.example.pharmacy.form.ResetpasswordForm;
import com.example.pharmacy.service.UserService;
import com.example.pharmacy.view.LoginView;
import com.example.pharmacy.view.UserView;

import net.bytebuddy.utility.RandomString;



@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public UserView currentUser() {
        return userService.currentUser();
    }

    @PostMapping
    public LoginView login(@Valid @RequestBody LoginForm form, Errors errors) {
        System.out.print("edfrfetwser");
        return userService.login(form, errors);
    }

    @PutMapping
    public LoginView refresh(@RequestBody String refreshToken) {
        return userService.refresh(refreshToken);
    }

	//  forgot password

	@PostMapping("/resetPswrd")
	public void validateEmail(@Valid @RequestBody String email) {
		String token = RandomString.make(45);
		userService.forgotPassword(token, email);
	}

// reset password

	@PostMapping("/forgotPswrd/{token}")

	public void resetPassword(@PathVariable("token") String token, @Valid @RequestBody ResetpasswordForm form) {
		userService.resetPasswrd(token, form.getPassword());
	}

// chnge pswrd

    @PutMapping("/changepswrd")
	public UserView changePassword(@Valid @RequestBody ChangePasswordForm form) {
		return userService.changePassword(form);
	}

    // pro pic

    @PutMapping("/profilePic")
    public User add(@ModelAttribute ImageForm form) throws Exception{
        System.out.println(form.getProfilePic().getOriginalFilename());
        return userService.uploadPic(form);
    }

    @GetMapping("/getPic")
	public HttpEntity<byte[]> getImg() {
		return userService.getImg();
	}

    @GetMapping("/count")
	public long userCount() {
		return userService.userCount();
	}

    @GetMapping("/detail")
	public UserView detailView() {
		return userService.detailView();
	}

}
