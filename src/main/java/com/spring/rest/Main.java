package com.spring.rest;

import com.spring.rest.client.RestClient;
import com.spring.rest.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RestClient client = new RestClient();
        RestClient.Session session = new RestClient.Session();


        String users = client.getUsers(session);
        System.out.println(users);


        User user = new User(3L, "James", "Brown", (byte) 25);
        String addResponse = client.addUser(user, session);
        System.out.println(addResponse);


        user.setName("Thomas");
        user.setLastName("Shelby");
        String updateResponse = client.updateUser(user, session);
        System.out.println(updateResponse);


        String deleteResponse = client.deleteUser(3L, session);
        System.out.println(deleteResponse);


        String finalCode = addResponse + updateResponse + deleteResponse;
        System.out.println("Код проверки: " + finalCode);
    }
}