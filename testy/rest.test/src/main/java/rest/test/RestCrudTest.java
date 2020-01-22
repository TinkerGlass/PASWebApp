package rest.test;

import io.restassured.response.Response;
import org.junit.Test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class RestCrudTest {

    public static String url = "https://localhost:8443/pas/api/items";

//    @Test public void getItemList() throws JSONException {
//        Response response = RestAssured.given().
//                relaxedHTTPSValidation().
//                auth().
//                basic("Superuser", "admin").
//                when().
//                get(url);
//        Assertions.assertEquals(new JSONArray(response.asString()).length(), 21);
//    }

//    @Test public void getItemSingle() throws JSONException {
//        Response response = RestAssured.given().
//                relaxedHTTPSValidation().
//                auth().
//                basic("Superuser", "admin").
//                when().
//                get(url + "/0");
//        JSONObject body = new JSONObject(response.asString());
//        Assertions.assertEquals(body.get("id"), 0);
//    }


//    @Test public void deleteItem() throws JSONException {
//        RestAssured.given().
//                relaxedHTTPSValidation().
//                auth().
//                basic("Superuser", "admin").
//                when().
//                post(url).
//                body();
//        Response response = RestAssured.given().
//                relaxedHTTPSValidation().
//                auth().
//                basic("Superuser", "admin").
//                when().
//                delete(url);
//    }
//
//
//    @Test public void putItem() throws JSONException {
//        Response response = RestAssured.given().
//                relaxedHTTPSValidation().
//                auth().
//                basic("Superuser", "admin").
//                when().
//                put(url);
//    }

    @Test public void postItem() throws JSONException {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        JSONObject body = new JSONObject()
                .put("type", "album")
                .put("Sticker", new JSONObject()
                        .put("stickerContent", "content")
                        .put("stickerName", "name")
                        .put("stickerPoints", 100))
                .put("available", true)
                .put("price", 12.21)
                .put("releaseDate", 869233)
                .put("author", "autor")
                .put("genre", "POP")
                .put("tracks", 20);
        Response responsePost = RestAssured.given().
                relaxedHTTPSValidation().
                headers(headers).
                auth().
                basic("Superuser", "admin").
                body(body.toString()).
                when().
                post(url);
        Response response = RestAssured.given().
                relaxedHTTPSValidation().
                auth().
                basic("Superuser", "admin").
                when().
                get(url);
        Assertions.assertEquals(responsePost.getStatusCode(), 400);
        Assertions.assertEquals(response.getStatusCode(), 200);
        Assertions.assertEquals(new JSONArray(response.asString()).length(), 22);
    }
}
