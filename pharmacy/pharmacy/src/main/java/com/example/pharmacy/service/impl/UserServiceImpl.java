package com.example.pharmacy.service.impl;

import static com.example.pharmacy.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Date;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.example.pharmacy.enitity.User;
import com.example.pharmacy.exception.BadRequestException;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.extra.EmailService;
import com.example.pharmacy.form.ChangePasswordForm;
import com.example.pharmacy.form.ImageForm;
import com.example.pharmacy.form.LoginForm;
import com.example.pharmacy.form.UserForm;
import com.example.pharmacy.repository.UserRepository;
import com.example.pharmacy.security.config.SecurityConfig;
import com.example.pharmacy.security.util.InvalidTokenException;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.security.util.TokenExpiredException;
import com.example.pharmacy.security.util.TokenGenerator;
import com.example.pharmacy.security.util.TokenGenerator.Status;
import com.example.pharmacy.security.util.TokenGenerator.Token;
import com.example.pharmacy.service.UserService;
import com.example.pharmacy.view.LoginView;
import com.example.pharmacy.view.UserView;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {
    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
	private EmailService emailService;

	private TextEncryptor textEncryptor;

    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::badRequestException);
        if (!form.getPassword().equals(user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(),
                securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    @Override
    public LoginView refresh(String refreshToken) throws BadRequestException {
        Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_REFRESH_TOKEN, refreshToken);
        } catch (InvalidTokenException e) {
            throw new BadRequestException("Invalid token", e);
        } catch (TokenExpiredException e) {
            throw new BadRequestException("Token expired", e);
        }

        int userId;
        try {
            userId = Integer.parseInt(status.data.substring(0, 10));
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid token", e);
        }

        String password = status.data.substring(10);

        User user = userRepository.findByUserIdAndPassword(userId, password)
                .orElseThrow(UserServiceImpl::badRequestException);

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        return new LoginView(
                user,
                new LoginView.TokenView(accessToken.value, accessToken.expiry),
                new LoginView.TokenView(refreshToken, status.expiry));
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }

    @Override
    public UserView currentUser() {

        return new UserView(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new));
    }

// -----------------------------------------forgot Password--------------------------------------------------------------------

	@Override
	public void forgotPassword(String token, String email) {
		User user = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
		if (user != null) {

			long t = System.currentTimeMillis();
			Duration passwordResetTokenExpiry = Duration.ofMinutes(10);
			textEncryptor = Encryptors.text("7C481ADD4AF55AB8", "374195D5E3080DC1");
			String tokenBfrEncript = token + "#" + t + "#" + passwordResetTokenExpiry.toMillis();
			String finalToken = textEncryptor.encrypt(tokenBfrEncript);
			user.setResetPasswrdToken(token);
			userRepository.save(user);

			String url = "http://localhost:4200/resetPswrd/" + finalToken;
			String emailId = user.getEmail();
			String subject = "Reset your password";
			String content = "Please click the link below to change password:<br>"
					+ "<h3><a href=\"[[URL]]\" target=\"_self\">forgot password</a></h3>" + "Thank you,<br>";
			content = content.replace("[[URL]]", url);
			try {
				emailService.sendEmail(emailId, subject, content);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}

		}

	}
// -----------------------------------reset pswrd-------------------------------------------------------------------
    @Override
	public void resetPasswrd(String token, String password) {
		textEncryptor = Encryptors.text("7C481ADD4AF55AB8", "374195D5E3080DC1");
		String decryptedToken = textEncryptor.decrypt(token);
		String[] parts = decryptedToken.split("#");
		String tokenFromUrl = parts[0];
		long createTime = Long.parseLong(parts[1]);
		long tokenExpiry = Long.parseLong(parts[2]);
		long currentTime = System.currentTimeMillis();
		long timeDiff = currentTime - createTime;

		if (timeDiff >= tokenExpiry) {
			throw new TokenExpiredException("Reset link expired");
		} else {

			User user = userRepository.findByResetPasswrdToken(tokenFromUrl).orElseThrow(NotFoundException::new);
			user.setPassword(password);
			
			user.setResetPasswrdToken("null");
			userRepository.save(user);
		}

	}

    // -----------------------------------------chnge--pswrd---------------------------------------------------------------

    @Override
	public UserView changePassword(ChangePasswordForm form) throws NotFoundException {
		User user = userRepository.findByUserId(SecurityUtil.getCurrentUserId());

		if (!form.getCurrentPassword().equals(user.getPassword())) {
			throw new BadRequestException("Password Mismatch");
		}
		user.setPassword(form.getNewPassword());
		
		return new UserView(userRepository.save(user));
	}

	
	
// ---------------------------------pro pic--------------------------------------------------------------
@Override
    public UserView uploadPic(ImageForm form) throws IOException {
        String uploadDir = "files/";
        String fileName;
        String randStr = RandomString.make(20);
        fileName = new Date().getTime() + "_" + randStr + "."
                + FilenameUtils.getExtension(form.getProfilePic().getOriginalFilename());

        Path uploadPath = Paths.get("src/main/resources/Images" + uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        try (InputStream inputStream = form.getProfilePic().getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) { 
            throw new IOException("Could not save file");
        }

        return new UserView(userRepository.save(userRepository.findByUserId((SecurityUtil.getCurrentUserId())).get().update(SecurityUtil.getCurrentUserId(), fileName)));
                                               
                                                                                                           
        
    }

	// ------------get img---------------

	@Override
    public byte[] getFileData() {
        String url = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new).getImage();

        try {
            return Files.readAllBytes(Paths.get("src/main/resources/Imagesfiles/"+url));
        } catch (IOException e) {
            throw new BadRequestException("File Not Found");
        }

    }


	@Override
	public long userCount() {
		return userRepository.countUsers();
	}

	@Override
	public UserView detailView() {
		return new UserView(
				userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(NotFoundException::new));
						
	}

    @Override
    public UserView updateDetails(UserForm form) {
        Integer uid = SecurityUtil.getCurrentUserId();
        User user = userRepository.findByUserId(uid).get();
        return new UserView(userRepository.save(user.updates(form, uid)));
    }
}
