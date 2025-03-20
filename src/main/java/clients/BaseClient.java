package clients;

import config.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public abstract class BaseClient<T> {

    private final String endpoint;

    private Class<T> responseType = getGenericType();

    public BaseClient(String endpoint) {
        this.endpoint = endpoint;
    }

    RequestSpecification buildSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(ConfigurationManager.getConfiguration().getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build().filter(new AllureRestAssured());
    }

    public T get() {
        return mapResponse(getResponse());
    }

    public T get(Integer id) {
        return mapResponse(getResponse(endpoint + id));
    }

    public T post(Object body) {
        return mapResponse(postResponse(body));
    }

    public T put(Object body) {
        return mapResponse(putResponse(body));
    }

    public T put(Integer id, Object body) {
        return mapResponse(putResponse(endpoint + id, body));
    }

    public T delete(Integer id) {
        return mapResponse(deleteResponse(id));
    }

    public Response getResponse() {
        return given()
                .spec(buildSpec())
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    Response getResponse(String endpoint) {
        return given()
                .spec(buildSpec())
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response postResponse(Object body) {
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

    public Response putResponse(Object body) {
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

    public Response deleteResponse(Integer id) {
        return given()
                .spec(buildSpec())
                .when()
                .delete(endpoint + id)
                .then()
                .extract()
                .response();
    }

    T mapResponse(Response response) {
        return response.as(responseType);
    }

    private Class<T> getGenericType() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type type = parameterizedType.getActualTypeArguments()[0];
            return (Class<T>) type;  // Cast and return the generic type
        }
        throw new IllegalStateException("Generic type not found");
    }

}
