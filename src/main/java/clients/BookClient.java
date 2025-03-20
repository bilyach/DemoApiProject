package clients;

import io.restassured.response.Response;
import models.author.AuthorModel;
import models.book.BookModel;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BookClient extends BaseClient<BookModel> {

    private static final String BOOKS_ENDPOINT = "/api/v1/books/";

    public Response getResponse() {
        return getResponse(BOOKS_ENDPOINT);
    }

    public Response getResponse(Integer id) {
        return getResponse(BOOKS_ENDPOINT + id);
    }

    public Response postResponse(BookModel body) {
        return postResponse(BOOKS_ENDPOINT, body);
    }

    public Response putResponse(Integer id, BookModel body) {
        return putResponse(BOOKS_ENDPOINT + id, body);
    }

    public Response deleteResponse(Integer id) {
        return deleteResponse(BOOKS_ENDPOINT + id);
    }

    public List<BookModel> get() {
        return Arrays.asList(getResponse().as(BookModel[].class));
    }

    public BookModel get(Integer id) {
        return get(BOOKS_ENDPOINT + id, BookModel.class);
    }

    public BookModel post(BookModel body) {
        return post(BOOKS_ENDPOINT, body, BookModel.class);
    }

    public BookModel put(Integer id, BookModel body) {
        return put(BOOKS_ENDPOINT + id, body, BookModel.class);
    }

    public BookModel delete(Integer id) {
        return delete(BOOKS_ENDPOINT + id, BookModel.class);
    }

}
