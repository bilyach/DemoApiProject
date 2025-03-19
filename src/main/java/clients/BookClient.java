package clients;

import io.restassured.response.Response;
import models.book.BookModel;

import static io.restassured.RestAssured.given;

public class BookClient extends BaseClient {

    public Response getBooks() {
        return given()
                .spec(buildSpec())
                .get("/api/v1/books");
    }

    public Response getBookById(Integer id) {
        return given()
                .spec(buildSpec())
                .get("/api/v1/books/" + id);
    }

    public Response postBooks(BookModel body) {
        return given()
                .spec(buildSpec())
                .and()
                .body(body)
                .when()
                .post("/api/v1/books");
    }

    public Response putBooksById(Integer id, BookModel body) {
        return given()
                .spec(buildSpec())
                .and()
                .body(body)
                .when()
                .put("/api/v1/books/" + id);
    }

    public Response deleteBookById(Integer id) {
        return given()
                .spec(buildSpec())
                .when()
                .delete("/api/v1/books/" + id);
    }

    public BookModel[] getBooksAsModels() {
        return getBooks()
                .then()
                .extract().response().as(BookModel[].class);
    }

    public BookModel getBookByIdAsModel(Integer id) {
        return getBookById(id)
                .then()
                .extract().response().as(BookModel.class);
    }

    public BookModel postBooksAsModel(BookModel body) {
        return postBooks(body)
                .then()
                .extract().response().as(BookModel.class);
    }

    public BookModel putBooksByIdAsModel(Integer id, BookModel body) {
        return putBooksById(id, body)
                .then()
                .extract().response().as(BookModel.class);
    }


}
