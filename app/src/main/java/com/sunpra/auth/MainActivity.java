package com.sunpra.auth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.sunpra.auth.model.Address;
import com.sunpra.auth.model.User;
import com.sunpra.auth.ui.PracticeFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Adding default toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try (InputStream stream = getResources().openRawResource(R.raw.user)) {
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            String json = new String(bytes);
            Log.d("SUNPRA", "User Json: " + json);

            //region Using JSON
            ArrayList<User> users = new ArrayList<>();
            try {
                JSONArray userJSONArray = new JSONArray(json);
                for (int i = 0; i < userJSONArray.length(); i++){
                    User theUser = getUserFromJSON(userJSONArray.getJSONObject(i));
                    users.add(theUser);
                }
            }catch (Exception e){

            }
            Log.d("SUNPRA", "Users Created: " + users.size());
            //endregion
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PracticeFragment())
                .commit();
    }

    private User getUserFromJSON(JSONObject jsonObject) {
        try {
            String firstName = jsonObject.getString("first_name");
            String lastName = jsonObject.getString("last_name");
            String email = jsonObject.getString("email");

            JSONObject addressJson = jsonObject.getJSONObject("address");
            String country = addressJson.getString("country");
            String region = addressJson.getString("region");
            String district = addressJson.getString("district");
            String addressLine = addressJson.getString("address_line");
            boolean isInsideRingRoad = addressJson.getBoolean("isInsideRingroad");
            int houseNumber = addressJson.getInt("house_no");

            Address address = new Address(
                    country,
                    region,
                    district,
                    addressLine,
                    isInsideRingRoad,
                    houseNumber
            );

            User theUser = new User(
                    firstName,
                    lastName,
                    email,
                    address
            );

            Log.d("SUNPRA", "Full Name: " + theUser.getFirstName() + " " + theUser.getLastName());
            return theUser;
        } catch (Exception e) {
            return null;
        }
    }
}