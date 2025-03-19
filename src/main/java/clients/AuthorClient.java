package clients;

import io.restassured.response.Response;
import models.author.AuthorModel;

import static io.restassured.RestAssured.given;

public class AuthorClient extends BaseClient {

    public Response getAuthors() {
        return given()
                .spec(buildSpec())
                .get("/api/v1/authors");
    }

    public Response getAuthorById(Integer id) {
        return given()
                .spec(buildSpec())
                .get("/api/v1/authors/" + id);
    }

    public Response postAuthors(AuthorModel body) {
        return given()
                .spec(buildSpec())
                .and()
                .body(body)
                .when()
                .post("/api/v1/authors");
    }

    public Response putAuthorsById(Integer id, AuthorModel body) {
        return given()
                .spec(buildSpec())
                .and()
                .body(body)
                .when()
                .put("/api/v1/authors/" + id);
    }

    public Response deleteAuthorById(Integer id) {
        return given()
                .spec(buildSpec())
                .when()
                .delete("/api/v1/authors/" + id);
    }

    public AuthorModel[] getAuthorsAsModels() {
        return getAuthors()
                .then()
                .extract().response().as(AuthorModel[].class);
    }

    public AuthorModel getAuthorByIdAsModel(Integer id) {
        return getAuthorById(id)
                .then()
                .extract().response().as(AuthorModel.class);
    }

    public AuthorModel postAuthorsAsModel(AuthorModel body) {
        return postAuthors(body)
                .then()
                .extract().response().as(AuthorModel.class);
    }

    public AuthorModel putAuthorsByIdAsModel(Integer id, AuthorModel body) {
        return putAuthorsById(id, body)
                .then()
                .extract().response().as(AuthorModel.class);
    }


}
