package clients;

import io.restassured.response.Response;
import models.author.AuthorModel;
import models.book.BookModel;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BookClient extends BaseClient<BookModel> {

    private static final String BOOKS_ENDPOINT = "/api/v1/books/";

    public BookClient() {
        super(BOOKS_ENDPOINT);
    }

    public Response getResponse(Integer id) {
        return getResponse(BOOKS_ENDPOINT + id);
    }

    public Response putResponse(Integer id, BookModel body) {
        return putResponse(BOOKS_ENDPOINT + id, body);
    }

    public List<BookModel> getModels() {
        return Arrays.asList(getResponse().as(BookModel[].class));
    }

}
