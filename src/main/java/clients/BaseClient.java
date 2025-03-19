package clients;

import config.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {

    RequestSpecification buildSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(ConfigurationManager.getConfiguration().getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build().filter(new AllureRestAssured());
    }

}
