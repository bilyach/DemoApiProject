package api;

import clients.BookClient;
import dataProvider.BookDataProvider;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.book.BookModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class BookTest extends BaseApiTest {

    private BookClient bookClient = new BookClient();

    @Test
    public void verifyGetBooksResponseSchema() {
        bookClient.getBooks()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("getBooks")));
    }

    @Test(dataProvider = "getRandomBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyGetBooksResponseSchema(BookModel bookModel) {
        bookClient.getBookById(bookModel.getId())
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("getBookById")));
    }

    @Test(dataProvider = "validBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPostBookResponseSchema(BookModel bookModel) {
        bookClient.postBooks(bookModel)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("postBooks")));
    }

    @Test(dataProvider = "validBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPutBookResponseSchema(BookModel BookModel) {
        bookClient.putBooksById(BookModel.getId(), BookModel)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("putBooks")));
    }

    @Test
    public void verifyThatGetBooksEndpointIsIdempotent() {
        var bookModels1 = bookClient.getBooksAsModels();
        var bookModels2 = bookClient.getBooksAsModels();

        Assertions.assertThat(bookModels1).isEqualTo(bookModels2);
    }

    @Test(dataProvider = "getRandomBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyGetBookByIdEndpoint(BookModel BookModel) {
        var BookResponse = bookClient.getBookByIdAsModel(BookModel.getId());

        Assertions.assertThat(BookResponse.getId()).isEqualTo(BookModel.getId());
    }

    @Test(dataProvider = "bookPostProvider", dataProviderClass = BookDataProvider.class)
    public void verifyPostBooksEndpoint(BookModel BookModel) {
        var createdBook = bookClient.postBooksAsModel(BookModel);

        Assertions.assertThat(createdBook.getId()).isEqualTo(BookModel.getId());
    }

    @Test(dataProvider = "updateBookProvider", dataProviderClass = BookDataProvider.class)
    public void verifyPutBookByIdEndpoint(Integer id, BookModel BookModel) {
        var updatedBook = bookClient.putBooksByIdAsModel(id, BookModel);

        Assertions.assertThat(updatedBook.getId()).isEqualTo(BookModel.getId());
    }

    @Test(dataProvider = "getRandomBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyDeleteBookByIdEndpoint(BookModel BookModel) {
        var createdBook = bookClient.postBooksAsModel(BookModel);
        var response = bookClient.deleteBookById(createdBook.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);

        var deletedBook = bookClient.getBookById(createdBook.getId())
                .then().extract().response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(deletedBook.getBody().asString().isEmpty()).isTrue();
    }


    @Test(dataProvider = "negativeBookIdProvider", dataProviderClass = BookDataProvider.class)
    public void verifyGetBookByIdEndpointNegative(Integer id) {
        var BookResponse = bookClient.getBookById(id)
                .then().extract().response();

        Assertions.assertThat(BookResponse.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Test(dataProvider = "bookPostNegativeModelProvider", dataProviderClass = BookDataProvider.class)
    public void verifyPostBookEndpointNegative(BookModel BookModel) {
        var createdBook = bookClient.postBooks(BookModel)
                .then().extract().response();

        Assertions.assertThat(createdBook.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test(dataProvider = "updateInvalidPutBookProvider", dataProviderClass = BookDataProvider.class)
    public void verifyPutBookByIdEndpointNegative(Integer id, BookModel BookModel) {
        var updatedBook = bookClient.putBooksById(id, BookModel)
                .then().extract().response();

        Assertions.assertThat(updatedBook.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

}
