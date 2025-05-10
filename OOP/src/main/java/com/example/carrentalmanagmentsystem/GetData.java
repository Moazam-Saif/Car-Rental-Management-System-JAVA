package com.example.carrentalmanagmentsystem;

public class GetData {

    public static String username="MyUser";
    public static String path;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        GetData.username = username;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        GetData.path = path;
    }
}