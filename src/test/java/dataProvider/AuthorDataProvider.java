package dataProvider;

import clients.AuthorClient;
import org.testng.annotations.DataProvider;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static models.author.AuthorModelProvider.*;
import static util.RandomUtil.getRandomElement;

public class AuthorDataProvider {

    private static AuthorClient client = new AuthorClient();

    @DataProvider(name = "getRandomAuthorModel")
    public static Object[][] getRandomAuthorModel() {
        return new Object[][]{
                {
                        getRandomElement(client.getModels())
                }
        };
    }

    @DataProvider(name = "getValidAuthorModel")
    public static Object[][] getValidAuthorModelProvider() {
        return new Object[][]{
                {
                        getAuthorModelWithId(getRandomElement(client.getModels()).getId())
                }
        };
    }

    @DataProvider(name = "authorPostModel")
    public static Object[][] authorPostModel() {
        return new Object[][]{
                {
                        getValidAuthorModel()
                },
                {
                        getAuthorModelWithOnlyId()
                },
                {
                        getAuthorModelWithOnlyRequiredFields()
                }
        };
    }

    @DataProvider(name = "authorPutModel")
    public static Object[][] authorPutModel() {
        return new Object[][]{
                {
                        getValidAuthorModel()
                }
        };
    }

    @DataProvider(name = "negativeGetAuthorModel")
    public static Object[][] negativeGetAuthorModel() {
        return new Object[][]{
                {0},
                {MIN_VALUE},
                {MAX_VALUE}
        };
    }

    @DataProvider(name = "negativePostAuthorModel")
    public static Object[][] negativePostAuthorModel() {
        return new Object[][]{
                {
                        getAuthorEmptyModel()
                },
                {
                        getAuthorModelWithOnlyBookId()
                },
                {
                        getAuthorModelWithIds(0, 0)
                },
                {
                        getAuthorModelWithIds(MIN_VALUE, MIN_VALUE)
                },
                {
                        getAuthorModelWithIds(MAX_VALUE, MAX_VALUE)
                }
        };
    }

    @DataProvider(name = "negativePutAuthorModel")
    public static Object[][] negativePutAuthorModel() {
        return new Object[][]{
                {
                        getRandomElement(client.getModels()).getId(),
                        getAuthorModelWithIds(MIN_VALUE, MIN_VALUE)
                },
                {
                        getRandomElement(client.getModels()).getId(),
                        getAuthorModelWithId(getRandomElement(client.getModels()).getId())
                }
        };
    }

}
