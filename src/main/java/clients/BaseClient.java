package clients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public abstract class BaseClient<T> {

    RequestSpecification buildSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(ConfigurationManager.getConfiguration().getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build().filter(new AllureRestAssured());
    }

    public T get(String endpoint, Class<T> responseType) {
        return mapResponse(getResponse(endpoint), responseType);
    }

    public T post(String endpoint, Object body, Class<T> responseType) {
        return mapResponse(postResponse(endpoint, body), responseType);
    }

    public T put(String endpoint, Object body, Class<T> responseType) {
        return mapResponse(putResponse(endpoint, body), responseType);
    }

    public T delete(String endpoint, Class<T> responseType) {
        return mapResponse(deleteResponse(endpoint), responseType);
    }

    public Response getResponse(String endpoint) {
        return given()
                .spec(buildSpec())
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response postResponse(String endpoint, Object body) {
        return given()
                .spec(buildSpec())
                .and()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response putResponse(String endpoint, Object body) {
        return given()
                .spec(buildSpec())
                .and()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response deleteResponse(String endpoint) {
        return given()
                .spec(buildSpec())
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    T mapResponse(Response response, Class<T> responseType) {
        return response.as(responseType);
    }

}
