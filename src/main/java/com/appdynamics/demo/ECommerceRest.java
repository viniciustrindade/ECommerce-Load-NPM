package com.appdynamics.demo;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by swetha.ravichandran on 8/19/15.
 */
public class ECommerceRest {
    private Client client = null;
    static private final Random randomGen = new Random();
    static final Logger logger = Logger.getLogger(ECommerceRest.class.getName());

    public void generateLoad(String host, int port) {
        List<User> users = getUserInformation(host, port);
        User user = getUserInfo(users);
        if (user != null) {
            addItemsToCart(host, port, user);
            checkOut(host, port, user);
        }
    }

    private static List<User> getUserInformation(String host, int port) {
        List<User> users = new ArrayList<>();
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget =
                    client.target(UriBuilder.fromUri("http://" + host + ":" + port + "/appdynamicspilot/rest/json/user/all").build());
            String response = webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class);
            Gson gson = new Gson();
            TypeToken<List<User>> token = new TypeToken<List<User>>() {
            };
            users = gson.fromJson(response, token.getType());
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
        return users;
    }

    private User getUserInfo(List<User> userList) {
        logger.info("FaultInjection - userInfo Size : " + userList.size());
        if (userList != null && userList.size() > 0) {
            Random generator = new Random();
            int index = generator.nextInt(userList.size());
            index = (index == userList.size()) ? index - 1 : index;
            User user = userList.get(index);
            logger.info("User Name : " + user.getEmail());
            logger.info("Password : " + user.getPassword());
            return user;
        }
        return null;
    }

    private void addItemsToCart(String host, int port, User user) {
        try {
            int max = 5, min = 1;
            int angularNumBooks = randomGen.nextInt((max - min) + 1) + min;
            client = ClientBuilder.newClient();
            logger.info("Add Items to Cart: " + "http://" + host + ":" + port + "/appdynamicspilot/rest/json/cart/" + angularNumBooks);
            WebTarget webTarget =
                    client.target(UriBuilder.fromUri("http://" + host + ":" + port + "/appdynamicspilot/rest/json/cart/" + angularNumBooks).build());
            String response = webTarget.request().header("username", user.getEmail()).accept(MediaType.APPLICATION_JSON).get(String.class);
            try {
                Thread.currentThread().sleep(500);
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
            logger.info("Add Items to Cart: " + response);
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
    }

    private void checkOut(String host, int port, User user) {
        try {
            client = ClientBuilder.newClient();
            logger.info("Check Out: " +  "http://" + host + ":" + port + "/appdynamicspilot/rest/json/cart/co");
            WebTarget webTarget =
                    client.target(UriBuilder.fromUri("http://" + host + ":" + port + "/appdynamicspilot/rest/json/cart/co").build());
            String response = webTarget.request().header("username", user.getEmail()).accept(MediaType.TEXT_PLAIN).get(String.class);
            try {
                Thread.currentThread().sleep(500);
            } catch (Exception ex) {
                logger.warning(ex.getMessage());
            }
            logger.info("CheckOut response: " + response);
        } catch (Exception ex) {
            logger.warning(ex.getMessage());
        }
    }

}
