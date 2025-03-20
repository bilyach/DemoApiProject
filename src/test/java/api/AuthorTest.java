package api;

import clients.AuthorClient;
import dataProvider.AuthorDataProvider;
import models.author.AuthorModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;

public class AuthorTest extends BaseApiTest {

    public AuthorClient client = new AuthorClient();

    @Test
    public void verifyGetAuthorsResponseSchema() {
        verifySchema(client.getResponse(), "getAuthors");
    }

    @Test(dataProvider = "getRandomAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyGetAuthorResponseSchema(AuthorModel authorModel) {
        verifySchema(client.getResponse(authorModel.getId()), "getAuthorById");
    }

    @Test(dataProvider = "getValidAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPostAuthorResponseSchema(AuthorModel authorModel) {
        verifySchema(client.postResponse(authorModel), "postAuthors");
    }

    @Test(dataProvider = "getValidAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPutAuthorResponseSchema(AuthorModel authorModel) {
        verifySchema(client.putResponse(authorModel.getId(), authorModel), "putAuthors");
    }

    @Test(dataProvider = "getRandomAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyGetAuthorByIdEndpoint(AuthorModel authorModel) {
        var authorResponse = client.get(authorModel.getId());

        Assertions.assertThat(authorResponse.getId()).isEqualTo(authorModel.getId());
    }

    @Test(dataProvider = "authorPostModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPostAuthorsEndpoint(AuthorModel authorModel) {
        var createdAuthor = client.post(authorModel);

        Assertions.assertThat(createdAuthor.getId()).isEqualTo(authorModel.getId());
    }

    @Test(dataProvider = "authorPutModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPutAuthorByIdEndpoint(AuthorModel authorModel) {
        var updatedAuthor = client.put(authorModel.getId(), authorModel);

        Assertions.assertThat(updatedAuthor.getId()).isEqualTo(authorModel.getId());
    }

    @Test(dataProvider = "getRandomAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyDeleteAuthorByIdEndpoint(AuthorModel authorModel) {
        var createdAuthor = client.post(authorModel);
        var response = client.deleteResponse(createdAuthor.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HTTP_OK);

        var deletedAuthor = client.getResponse(createdAuthor.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HTTP_NOT_FOUND);
        Assertions.assertThat(deletedAuthor.getBody().asString().isEmpty()).isTrue();
    }

    @Test(dataProvider = "negativeGetAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyGetAuthorByIdEndpointNegative(Integer id) {
        var authorResponse = client.getResponse(id);

        Assertions.assertThat(authorResponse.getStatusCode()).isEqualTo(HTTP_NOT_FOUND);
    }

    @Test(dataProvider = "negativePostAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPostAuthorEndpointNegative(AuthorModel authorModel) {
        var createdAuthor = client.postResponse(authorModel);

        Assertions.assertThat(createdAuthor.getStatusCode()).isEqualTo(HTTP_BAD_REQUEST);
    }

    @Test(dataProvider = "negativePutAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPutAuthorByIdEndpointNegative(Integer id, AuthorModel authorModel) {
        var updatedAuthor = client.putResponse(id, authorModel);

        Assertions.assertThat(updatedAuthor.getStatusCode()).isEqualTo(HTTP_BAD_REQUEST);
    }

}
