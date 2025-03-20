package api;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.InputStream;

public class BaseApiTest {

    private InputStream schema(String name) {
        return getClass().getClassLoader()
                .getResourceAsStream(String.format("schema/%s.json", name));
    }

    public void verifySchema(Response response, String schemaName) {
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schema(schemaName)));
    }

}
