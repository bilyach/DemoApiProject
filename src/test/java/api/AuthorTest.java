package api;

import clients.AuthorClient;
import dataProvider.AuthorDataProvider;
import io.restassured.module.jsv.JsonSchemaValidator;
import models.author.AuthorModel;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

public class AuthorTest extends BaseApiTest {

    private AuthorClient authorClient = new AuthorClient();

    @Test
    public void verifyGetAuthorsResponseSchema() {
        authorClient.getAuthors()
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("getAuthors")));
    }

    @Test(dataProvider = "getRandomAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyGetAuthorResponseSchema(AuthorModel authorModel) {
        authorClient.getAuthorById(authorModel.getId())
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("getAuthorById")));
    }

    @Test(dataProvider = "validAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPostAuthorResponseSchema(AuthorModel authorModel) {
        authorClient.postAuthors(authorModel)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("postAuthors")));
    }

    @Test(dataProvider = "validAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyPutAuthorResponseSchema(AuthorModel authorModel) {
        authorClient.putAuthorsById(authorModel.getId(), authorModel)
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema("putAuthors")));
    }

    @Test
    public void verifyThatGetAuthorsEndpointIsIdempotent() {
        var authorModels1 = authorClient.getAuthorsAsModels();
        var authorModels2 = authorClient.getAuthorsAsModels();

        Assertions.assertThat(authorModels1).isEqualTo(authorModels2);
    }

    @Test(dataProvider = "getRandomAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyGetAuthorByIdEndpoint(AuthorModel authorModel) {
        var authorResponse = authorClient.getAuthorByIdAsModel(authorModel.getId());

        Assertions.assertThat(authorResponse.getId()).isEqualTo(authorModel.getId());
    }

    @Test(dataProvider = "authorPostProvider", dataProviderClass = AuthorDataProvider.class)
    public void verifyPostAuthorsEndpoint(AuthorModel authorModel) {
        var createdAuthor = authorClient.postAuthorsAsModel(authorModel);

        Assertions.assertThat(createdAuthor.getId()).isEqualTo(authorModel.getId());
    }

    @Test(dataProvider = "updateAuthorProvider", dataProviderClass = AuthorDataProvider.class)
    public void verifyPutAuthorByIdEndpoint(Integer id, AuthorModel authorModel) {
        var updatedAuthor = authorClient.putAuthorsByIdAsModel(id, authorModel);

        Assertions.assertThat(updatedAuthor.getId()).isEqualTo(authorModel.getId());
    }

    @Test(dataProvider = "getRandomAuthorModel", dataProviderClass = AuthorDataProvider.class)
    public void verifyDeleteAuthorByIdEndpoint(AuthorModel authorModel) {
        var createdAuthor = authorClient.postAuthorsAsModel(authorModel);
        var response = authorClient.deleteAuthorById(createdAuthor.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);

        var deletedAuthor = authorClient.getAuthorById(createdAuthor.getId())
                .then().extract().response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        Assertions.assertThat(deletedAuthor.getBody().asString().isEmpty()).isTrue();
    }


    @Test(dataProvider = "negativeAuthorIdProvider", dataProviderClass = AuthorDataProvider.class)
    public void verifyGetAuthorByIdEndpointNegative(Integer id) {
        var authorResponse = authorClient.getAuthorById(id)
                .then().extract().response();

        Assertions.assertThat(authorResponse.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Test(dataProvider = "authorPostNegativeModelProvider", dataProviderClass = AuthorDataProvider.class)
    public void verifyPostAuthorEndpointNegative(AuthorModel authorModel) {
        var createdAuthor = authorClient.postAuthors(authorModel)
                .then().extract().response();

        Assertions.assertThat(createdAuthor.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Test(dataProvider = "updateInvalidPutAuthorProvider", dataProviderClass = AuthorDataProvider.class)
    public void verifyPutAuthorByIdEndpointNegative(Integer id, AuthorModel authorModel) {
        var updatedAuthor = authorClient.putAuthorsById(id, authorModel)
                .then().extract().response();

        Assertions.assertThat(updatedAuthor.getStatusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
    }

}
