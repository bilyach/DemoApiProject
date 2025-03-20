package clients;

import io.restassured.response.Response;
import models.author.AuthorModel;

import java.util.Arrays;
import java.util.List;

public class AuthorClient extends BaseClient<AuthorModel> {

    private static final String AUTHORS_ENDPOINT = "/api/v1/authors/";

    public AuthorClient() {
        super(AUTHORS_ENDPOINT);
    }

    public Response getResponse(Integer id) {
        return getResponse(AUTHORS_ENDPOINT + id);
    }

    public Response putResponse(Integer id, AuthorModel body) {
        return putResponse(AUTHORS_ENDPOINT + id, body);
    }

    public List<AuthorModel> getModels() {
        return Arrays.asList(getResponse().as(AuthorModel[].class));
    }


}
