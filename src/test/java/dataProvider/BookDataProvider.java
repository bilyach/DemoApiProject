package dataProvider;

import clients.BookClient;
import org.testng.annotations.DataProvider;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static models.book.BookModelProvider.*;
import static util.RandomUtil.getRandomElement;

public class BookDataProvider {

    private static BookClient client = new BookClient();

    @DataProvider(name = "getRandomBookModel")
    public static Object[][] getRandomBookModel() {
        return new Object[][]{
                {
                        getRandomElement(client.getModels())
                }
        };
    }

    @DataProvider(name = "getValidBookModel")
    public static Object[][] getValidBookModelProvider() {
        return new Object[][]{
                {
                        getValidBookModel()
                }
        };
    }

    @DataProvider(name = "bookPostModel")
    public static Object[][] bookPostModel() {
        return new Object[][]{
                {
                        getValidBookModel()
                },
                {
                        getBookModelWithOnlyRequiredFields()
                }
        };
    }

    @DataProvider(name = "bookPutModel")
    public static Object[][] bookPutModel() {
        return new Object[][]{
                {
                        getValidBookModel()
                }
        };
    }

    @DataProvider(name = "negativeGetBookProvider")
    public static Object[][] negativeGetBookProvider() {
        return new Object[][]{
                {0},
                {MIN_VALUE},
                {MAX_VALUE}
        };
    }

    @DataProvider(name = "negativePostBookModel")
    public static Object[][] negativePostBookModel() {
        return new Object[][]{
                {
                        getInvalidBookModel()
                },
                {
                        getEmptyBookModel()
                }
        };
    }


    @DataProvider(name = "negativePutBookModel")
    public static Object[][] negativePutBookModel() {
        var id = getRandomElement(client.getModels()).getId();
        return new Object[][]{
                {
                        id,
                        getInvalidBookModel()
                },
                {
                        id,
                        getEmptyBookModel()
                }
        };
    }

}
