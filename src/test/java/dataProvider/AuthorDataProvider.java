package dataProvider;

import clients.AuthorClient;
import models.author.AuthorModel;
import models.author.AuthorModelProvider;
import org.testng.annotations.DataProvider;
import util.RandomUtil;

import java.util.Arrays;

import static util.RandomUtil.ZERO_VAL;

public class AuthorDataProvider {

    @DataProvider(name = "getRandomAuthorModel")
    public static Object[][] getRandomAuthorModelProvider() {
        var authorModels = new AuthorClient().getAuthorsAsModels();
        AuthorModel authorModel = RandomUtil.getRandomElement(Arrays.asList(authorModels));
        return new Object[][]{
                {
                        authorModel
                },
        };
    }

    @DataProvider(name = "validAuthorModel")
    public static Object[][] getValidAuthorModelProvider() {
        return new Object[][]{
                {
                        AuthorModelProvider.getValidAuthorModel()
                },
        };
    }

    @DataProvider(name = "authorPostModel")
    public static Object[][] getAuthorPostProvider() {
        return new Object[][]{
                {
                        AuthorModelProvider.getValidAuthorModel()
                },
                {
                        AuthorModelProvider.getAuthorModelWithoutBookId()
                },
                {
                        AuthorModelProvider.getAuthorModelWithRequiredFields()
                },
        };
    }

    @DataProvider(name = "negativeAuthorPostModel")
    public static Object[][] getAuthorPostNegativeModelProvider() {
        return new Object[][]{
                {
                        AuthorModelProvider.getAuthorModelWithId(ZERO_VAL)
                },
                {
                        AuthorModelProvider.getAuthorModelWithIds(ZERO_VAL, ZERO_VAL)
                },
                {
                        AuthorModelProvider.getAuthorModelWithIds(Integer.MIN_VALUE, Integer.MIN_VALUE)
                },
                {
                        AuthorModelProvider.getAuthorModelWithIds(Integer.MAX_VALUE, Integer.MAX_VALUE)
                },
                {
                        AuthorModel.builder().build()
                },
        };
    }

    @DataProvider(name = "authorPutModel")
    public static Object[][] getUpdateAuthorProvider() {
        var authorModels = new AuthorClient().getAuthorsAsModels();
        AuthorModel authorModel = RandomUtil.getRandomElement(Arrays.asList(authorModels));
        return new Object[][]{
                {
                        authorModel.getId(),
                        AuthorModelProvider.getAuthorModelWithId(authorModel.getId())
                },
        };
    }

    @DataProvider(name = "negativePutAuthorModel")
    public static Object[][] getUpdateInvalidPutAuthorProviderProvider() {
        var authorModels = new AuthorClient().getAuthorsAsModels();
        AuthorModel authorModel = RandomUtil.getRandomElement(Arrays.asList(authorModels));
        return new Object[][]{
                {
                        authorModel.getId(),
                        AuthorModelProvider.getAuthorModelWithIds(Integer.MIN_VALUE, Integer.MIN_VALUE)
                },
        };
    }

    @DataProvider(name = "negativeAuthorIdProvider")
    public static Object[][] getNegativeAuthorIdProvider() {

        return new Object[][]{
                {ZERO_VAL},
                {Integer.MIN_VALUE},
                {Integer.MAX_VALUE},
        };
    }

}
