//package com.example.demo.controller;
//
//import com.example.demo.global.GlobalData;
//import com.example.demo.model.Product;
//import com.example.demo.service.ProductService;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api")
//public class RecipeController {
//
//    @Autowired
//    ProductService productService;
//
//    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
//
//    private static final String APP_ID = "15d25c7f";
//    private static final String APP_KEY = "e255a95a0d85e1db857626f096331aab";
//    private static final String API_URL = "https://api.edamam.com/search";
//
//    @GetMapping("/getRecipes")
//    public Map<String, List<String>> getRecipes() {
//        List<Product> cartItems = GlobalData.cart;
//        List<String> ingredients = cartItems.stream()
//                .map(Product::getName)
//                .collect(Collectors.toList());
//
//        logger.info("Ingredients for recipes: " + ingredients);
//        Map<String, List<String>> result = fetchRecipesFromEdamam(ingredients);
//        logger.info("Recipes received: " + result.get("recipes"));
//        return result;
//    }
//
//    private Map<String, List<String>> fetchRecipesFromEdamam(List<String> ingredients) {
//        Map<String, List<String>> result = new HashMap<>();
//        List<String> recipes = new ArrayList<>();
//        List<String> images = new ArrayList<>();
//
//        try {
//            String query = String.join(",", ingredients);
//            String urlString = API_URL + "?q=" + query + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = in.readLine()) != null) {
//                response.append(line);
//            }
//            in.close();
//
//            JsonElement jsonElement = JsonParser.parseString(response.toString());
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//            JsonArray hitsArray = jsonObject.getAsJsonArray("hits");
//
//            for (JsonElement hitElement : hitsArray) {
//                JsonObject recipeObject = hitElement.getAsJsonObject().getAsJsonObject("recipe");
//                recipes.add(recipeObject.get("label").getAsString());
//                images.add(recipeObject.get("image").getAsString());
//            }
//
//            result.put("recipes", recipes);
//            result.put("images", images);
//
//        } catch (Exception e) {
//            logger.error("Error fetching recipes from Edamam API", e);
//        }
//        return result;
//    }
//}

//
//package com.example.demo.controller;
//
//import com.example.demo.model.Product;
//import com.example.demo.global.GlobalData;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api")
//public class RecipeController {
//
//    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
//
//    private static final String API_URL = "https://api.edamam.com/search";
//    private static final String APP_ID = "15d25c7f";
//    private static final String APP_KEY = "e255a95a0d85e1db857626f096331aab";
//
//    @GetMapping("/getRecipes")
//    public Map<String, List<Map<String, String>>> getRecipes() {
//        List<Product> cartItems = GlobalData.cart;
//        List<String> ingredients = cartItems.stream()
//                .map(Product::getName)
//                .collect(Collectors.toList());
//
//        logger.info("Ingredients for recipes: " + ingredients);
//        Map<String, List<Map<String, String>>> result = fetchRecipesFromEdamam(ingredients);
//        logger.info("Recipes received: " + result);
//        return result;
//    }
//
//    private Map<String, List<Map<String, String>>> fetchRecipesFromEdamam(List<String> ingredients) {
//        Map<String, List<Map<String, String>>> result = new HashMap<>();
//        List<Map<String, String>> recipes = new ArrayList<>();
//
//        try {
//            String query = String.join(",", ingredients);
//            String urlString = API_URL + "?q=" + query + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
//            URL url = new URL(urlString);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            logger.info("Fetching recipes from: " + urlString);
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = in.readLine()) != null) {
//                response.append(line);
//            }
//            in.close();
//            logger.info("Response from Edamam API: " + response.toString());
//
//            JsonElement jsonElement = JsonParser.parseString(response.toString());
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            JsonArray hitsArray = jsonObject.getAsJsonArray("hits");
//
//            for (JsonElement hitElement : hitsArray) {
//                JsonObject recipeObject = hitElement.getAsJsonObject().getAsJsonObject("recipe");
//                Map<String, String> recipe = new HashMap<>();
//                recipe.put("label", recipeObject.get("label").getAsString());
//                recipe.put("image", recipeObject.get("image").getAsString());
//                recipe.put("url", recipeObject.get("url").getAsString());
//                recipes.add(recipe);
//            }
//
//            result.put("recipes", recipes);
//
//        } catch (Exception e) {
//            logger.error("Error fetching recipes from Edamam API", e);
//        }
//        return result;
//    }
//}


package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.global.GlobalData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecipeController {

    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    private static final String API_URL = "https://api.edamam.com/search";
    private static final String APP_ID = "15d25c7f";
    private static final String APP_KEY = "e255a95a0d85e1db857626f096331aab";

    @GetMapping("/getRecipes")
    public Map<String, List<Map<String, String>>> getRecipes() {
        List<Product> cartItems = GlobalData.cart;
        List<String> ingredients = cartItems.stream()
                .map(Product::getName)
                .collect(Collectors.toList());

        logger.info("Ingredients for recipes: " + ingredients);
        Map<String, List<Map<String, String>>> result = fetchRecipesFromEdamam(ingredients);
        logger.info("Recipes received: " + result);
        return result;
    }

    private Map<String, List<Map<String, String>>> fetchRecipesFromEdamam(List<String> ingredients) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        List<Map<String, String>> recipes = new ArrayList<>();

        try {
            String query = URLEncoder.encode(String.join(",", ingredients), StandardCharsets.UTF_8.toString());
            String urlString = API_URL + "?q=" + query + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            logger.info("Fetching recipes from: " + urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            logger.info("Response from Edamam API: " + response.toString());

            JsonElement jsonElement = JsonParser.parseString(response.toString());
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray hitsArray = jsonObject.getAsJsonArray("hits");

            for (JsonElement hitElement : hitsArray) {
                JsonObject recipeObject = hitElement.getAsJsonObject().getAsJsonObject("recipe");
                Map<String, String> recipe = new HashMap<>();
                recipe.put("label", recipeObject.get("label").getAsString());
                recipe.put("image", recipeObject.get("image").getAsString());
                recipe.put("url", recipeObject.get("url").getAsString());
                recipes.add(recipe);
            }

            result.put("recipes", recipes);

        } catch (Exception e) {
            logger.error("Error fetching recipes from Edamam API", e);
        }
        return result;
    }
}
