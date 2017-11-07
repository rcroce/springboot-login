package com.architech.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.architech.model.User;
import com.architech.service.UserService;

@Component
public class UserValidator implements Validator {
    
	private static final String USERNAME_REGEXP = "^[a-zA-Z0-9]{5,32}$";
	private static final String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,32}$";
	
	private Pattern usrPattern = Pattern.compile(USERNAME_REGEXP);
	private Pattern pwdPattern = Pattern.compile(PASSWORD_REGEXP);
	private Matcher matcher;
	
	public boolean isValid(String text, Pattern pattern){
		  matcher = pattern.matcher(text);
		  return matcher.matches();
	}
	
	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (!isValid(user.getUsername(), usrPattern)) {
            errors.rejectValue("username", "Format.User.username");
        }
        else if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.user.username");
        }
        if (!isValid(user.getPassword(), pwdPattern)) {
            errors.rejectValue("password", "Format.User.password");
        }
    }
    
}
