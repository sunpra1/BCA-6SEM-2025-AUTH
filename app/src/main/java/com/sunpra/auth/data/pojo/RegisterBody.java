package com.sunpra.auth.data.pojo;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("confirm_password")
    private String confirmPassword;

    public RegisterBody(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

//{
//    "token": "24|p6eHiE6oAbu8XDyCNvpf3fNfaGFvcmEZhIzndzrjca13b6e5",
//    "user": {
//        "name": "Sunil Prasai",
//        "email": "sunpra123@gmail.com",
//        "updated_at": "2025-03-31T04:19:58.000000Z",
//        "created_at": "2025-03-31T04:19:58.000000Z",
//        "id": 3
//    }
//}
