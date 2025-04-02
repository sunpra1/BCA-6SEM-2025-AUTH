package com.sunpra.auth.ui;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.sunpra.auth.data.pojo.RegisterBody;
import com.sunpra.auth.utility.ServiceProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {

    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_C_PASSWORD = "confirm_password";

    Map<String, String> validateFullName(String fullName) {
        HashMap<String, String> fullNameErrors = new HashMap<>();

        if (fullName.isEmpty()) {
            fullNameErrors.put(KEY_FULL_NAME, "Full name is required");
        }

        return fullNameErrors;
    }

    Map<String, String> validateEmail(String email) {
        HashMap<String, String> emailErrors = new HashMap<>();

        if (email.isEmpty()) {
            emailErrors.put(KEY_EMAIL, "Email is required");
        } else if (!Pattern.compile("").matcher(email).matches()) {
            emailErrors.put(KEY_EMAIL, "Email is invalid.");
        }
        return emailErrors;
    }

    Map<String, String> validatePassword(String password) {
        HashMap<String, String> passwordErrors = new HashMap<>();

        if (password.isEmpty()) {
            passwordErrors.put(KEY_PASSWORD, "Password is required");
        }
        return passwordErrors;
    }

    Map<String, String> validateConfirmPassword(String password, String confirmPassword) {
        HashMap<String, String> confirmPasswordErrors = new HashMap<>();

        if (confirmPassword.isEmpty()) {
            confirmPasswordErrors.put(KEY_C_PASSWORD, "Password is required");
        }else if(validatePassword(password).isEmpty() && !password.equals(confirmPassword)){
            confirmPasswordErrors.put(KEY_C_PASSWORD, "Confirm password doesn't match with password.");
        }
        return confirmPasswordErrors;
    }

    public void registerUser(RegisterBody registerBody) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                ServiceProvider.getService().registerUser(registerBody).execute();
            } catch (Exception e) {
                // If IO Exception and UnknownHostException there is no Internet.
                Log.e("API_FAILED", "registerUser: ", e);
            }
        });
    }
}
