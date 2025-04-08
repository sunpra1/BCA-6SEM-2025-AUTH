package com.sunpra.auth.ui;

import static com.sunpra.auth.ui.RegisterViewModel.KEY_C_PASSWORD;
import static com.sunpra.auth.ui.RegisterViewModel.KEY_EMAIL;
import static com.sunpra.auth.ui.RegisterViewModel.KEY_FULL_NAME;
import static com.sunpra.auth.ui.RegisterViewModel.KEY_PASSWORD;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sunpra.auth.R;

import java.util.Map;
import java.util.Objects;


public class RegisterFragment extends Fragment {

    RegisterViewModel viewModel;

    TextInputLayout fullNameTil;
    TextInputEditText fullNameEt;

    TextInputLayout emailTil;
    TextInputEditText emailEt;

    TextInputLayout passwordTil;
    TextInputEditText passwordEt;

    TextInputLayout confirmPasswordTil;
    TextInputEditText confirmPasswordEt;

    ConstraintLayout progressLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeView(view);
        setupObservers();
    }

    private void initializeView(View view) {
        fullNameTil = view.findViewById(R.id.fullNameTil);
        fullNameEt = view.findViewById(R.id.fullName);

        emailTil = view.findViewById(R.id.emailTil);
        emailEt = view.findViewById(R.id.emailAddress);

        passwordTil = view.findViewById(R.id.passwordTil);
        passwordEt = view.findViewById(R.id.password);

        confirmPasswordTil = view.findViewById(R.id.confirmPasswordTil);
        confirmPasswordEt = view.findViewById(R.id.confirmPassword);

        progressLayout = view.findViewById(R.id.progressLayout);

        view.findViewById(R.id.registerBtn).setOnClickListener((registerBtnView) -> {
            viewModel.onRegisterClicked(
                    Objects.requireNonNull(fullNameEt.getText()).toString(),
                    emailEt.getText().toString(),
                    passwordEt.getText().toString(),
                    confirmPasswordEt.getText().toString()
            );
        });
    }

    private void setupObservers() {
        viewModel.formErrors.observe(requireActivity(), formErrors -> {
            handleFormErrors(formErrors);
        });
        viewModel.isLoading.observe(requireActivity(), this::handleIsLoading);
    }

    private void handleIsLoading(Boolean isLoading) {
        progressLayout.setVisibility(
                isLoading ? View.VISIBLE : View.GONE
        );
    }

    private void handleFormErrors(Map<String, String> formErrors) {
        String fullNameError = formErrors.get(KEY_FULL_NAME);
        String emailError = formErrors.get(KEY_EMAIL);
        String passwordError = formErrors.get(KEY_PASSWORD);
        String confirmPasswordError = formErrors.get(KEY_C_PASSWORD);

        fullNameTil.setError(fullNameError);
        fullNameTil.setErrorEnabled(fullNameError != null);

        if (emailError != null) {
            emailTil.setError(emailError);
            emailTil.setErrorEnabled(true);
        } else {
            emailTil.setError(null);
            emailTil.setErrorEnabled(false);
        }

        if (passwordError != null) {
            passwordTil.setError(passwordError);
            passwordTil.setErrorEnabled(true);
        } else {
            passwordTil.setError(null);
            passwordTil.setErrorEnabled(false);
        }

        if (confirmPasswordError != null) {
            confirmPasswordTil.setError(confirmPasswordError);
            confirmPasswordTil.setErrorEnabled(true);
        } else {
            confirmPasswordTil.setError(null);
            confirmPasswordTil.setErrorEnabled(false);
        }

    }
}