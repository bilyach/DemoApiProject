package clients;

import io.restassured.response.Response;
import models.author.AuthorModel;

import java.util.Arrays;
import java.util.List;

public class AuthorClient extends BaseClient<AuthorModel> {

    private static final String AUTHORS_ENDPOINT = "/api/v1/authors/";

    public Response getResponse() {
        return getResponse(AUTHORS_ENDPOINT);
    }

    public Response getResponse(Integer id) {
        return getResponse(AUTHORS_ENDPOINT + id);
    }

    public Response postResponse(AuthorModel body) {
        return postResponse(AUTHORS_ENDPOINT, body);
    }

    public Response putResponse(Integer id, AuthorModel body) {
        return putResponse(AUTHORS_ENDPOINT + id, body);
    }

    public Response deleteResponse(Integer id) {
        return deleteResponse(AUTHORS_ENDPOINT + id);
    }

    public List<AuthorModel> get() {
        return Arrays.asList(getResponse().as(AuthorModel[].class));
    }

    public AuthorModel get(Integer id) {
        return get(AUTHORS_ENDPOINT + id, AuthorModel.class);
    }

    public AuthorModel post(AuthorModel body) {
        return post(AUTHORS_ENDPOINT, body, AuthorModel.class);
    }

    public AuthorModel put(Integer id, AuthorModel body) {
        return put(AUTHORS_ENDPOINT + id, body, AuthorModel.class);
    }

    public AuthorModel delete(Integer id) {
        return delete(AUTHORS_ENDPOINT + id, AuthorModel.class);
    }


}
