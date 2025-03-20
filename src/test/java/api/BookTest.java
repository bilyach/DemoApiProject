package api;

import clients.BookClient;
import dataProvider.BookDataProvider;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.book.BookModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class BookTest extends BaseApiTest {

    private BookClient client = new BookClient();

    @Test
    public void verifyGetBooksResponseSchema() {
        verifySchema(client.getResponse(), "getBooks");
    }

    @Test(dataProvider = "getRandomBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyGetBooksResponseSchema(BookModel bookModel) {
        verifySchema(client.getResponse(bookModel.getId()), "getBookById");
    }

    @Test(dataProvider = "validBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPostBookResponseSchema(BookModel bookModel) {
        verifySchema(client.postResponse(bookModel), "postBooks");
    }

    @Test(dataProvider = "validBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPutBookResponseSchema(BookModel bookModel) {
        verifySchema(client.putResponse(bookModel.getId(), bookModel), "putBooks");
    }

    @Test
    public void verifyThatGetBooksEndpointIsIdempotent() {
        var bookModels1 = client.get();
        var bookModels2 = client.get();

        Assertions.assertThat(bookModels1).isEqualTo(bookModels2);
    }

    @Test(dataProvider = "getRandomBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyGetBookByIdEndpoint(BookModel bookModel) {
        var bookResponse = client.get(bookModel.getId());

        Assertions.assertThat(bookResponse.getId()).isEqualTo(bookModel.getId());
    }

    @Test(dataProvider = "postBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPostBooksEndpoint(BookModel bookModel) {
        var createdBook = client.post(bookModel);

        Assertions.assertThat(createdBook.getId()).isEqualTo(bookModel.getId());
    }

    @Test(dataProvider = "putBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPutBookByIdEndpoint(Integer id, BookModel bookModel) {
        var updatedBook = client.put(id, bookModel);

        Assertions.assertThat(updatedBook.getId()).isEqualTo(bookModel.getId());
    }

    @Test(dataProvider = "getRandomBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyDeleteBookByIdEndpoint(BookModel bookModel) {
        var createdBook = client.post(bookModel);
        var response = client.deleteResponse(createdBook.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);

        var deletedBook = client.getResponse(createdBook.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(deletedBook.getBody().asString().isEmpty()).isTrue();
    }


    @Test(dataProvider = "negativeBookIdProvider", dataProviderClass = BookDataProvider.class)
    public void verifyGetBookByIdEndpointNegative(Integer id) {
        var bookResponse = client.getResponse(id);

        Assertions.assertThat(bookResponse.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Test(dataProvider = "negativePostBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPostBookEndpointNegative(BookModel BookModel) {
        var createdBook = client.postResponse(BookModel);

        Assertions.assertThat(createdBook.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test(dataProvider = "negativePutBookModel", dataProviderClass = BookDataProvider.class)
    public void verifyPutBookByIdEndpointNegative(Integer id, BookModel BookModel) {
        var updatedBook = client.putResponse(id, BookModel);

        Assertions.assertThat(updatedBook.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

}
