package rest.test;

import io.restassured.response.Response;
import org.junit.Test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RestCrudTest {

    public static String url = "https://localhost:8443/pas/api/items";
    public int size = 0;

    @Test public void getItemList() throws JSONException {
        Response response = RestAssured.given().
                relaxedHTTPSValidation().
                auth().
                basic("Superuser", "admin").
                when().
                get(url);
        size = new JSONArray(response.asString()).length();
        Assertions.assertNotEquals(size, 0);
    }

    @Test public void getItemSingle() throws JSONException {
        Response response = RestAssured.given().
                relaxedHTTPSValidation().
                auth().
                basic("Superuser", "admin").
                when().
                get(url + "/0");
        JSONObject body = new JSONObject(response.asString());
        Assertions.assertEquals(body.get("id"), 0);
    }


//    @Test public void putItem() throws JSONException {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//
//    }

    @Test public void post_deleteItem() throws JSONException {
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
                .put("releaseDate", 4359)
                .put("title", "title")
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
        Assertions.assertEquals(responsePost.getStatusCode(), 200);
        Assertions.assertEquals(response.getStatusCode(), 200);
        Assertions.assertEquals(new JSONArray(response.asString()).length(), size+1);
        Response responseDelete = RestAssured.given().
                relaxedHTTPSValidation().
                auth().
                basic("Superuser", "admin").
                when().
                delete(url + "/" + (new JSONArray(response.asString()).length() - 1));
        Assertions.assertEquals(responseDelete.getStatusCode(), 204);
        response = RestAssured.given().
                relaxedHTTPSValidation().
                auth().
                basic("Superuser", "admin").
                when().
                get(url);
        Assertions.assertEquals(new JSONArray(response.asString()).length(), size);
    }
}
