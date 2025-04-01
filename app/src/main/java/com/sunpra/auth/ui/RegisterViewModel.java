package com.sunpra.auth.ui;

import android.util.Log;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModel;

import com.sunpra.auth.data.pojo.RegisterBody;
import com.sunpra.auth.utility.ServiceProvider;

import java.util.concurrent.Executors;

public class RegisterViewModel extends ViewModel {

    public void registerUser(RegisterBody registerBody){
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
