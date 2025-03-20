package dataProvider;

import clients.AuthorClient;
import clients.BookClient;
import models.book.BookModel;
import models.book.BookModelProvider;
import org.testng.annotations.DataProvider;
import util.RandomUtil;

import java.util.Arrays;
import java.util.Objects;

import static util.RandomUtil.ZERO_VAL;

public class BookDataProvider {

    private static BookClient client = new BookClient();

    @DataProvider(name = "getRandomBookModel")
    public static Object[][] getRandomBookModelProvider() {
        BookModel bookModel = RandomUtil.getRandomElement(client.getModels());
        return new Object[][]{
                {
                        bookModel
                },
        };
    }

    @DataProvider(name = "validBookModel")
    public static Object[][] getValidAuthorModelProvider() {
        return new Object[][]{
                {
                        BookModelProvider.getValidBookModel()
                },
        };
    }

    @DataProvider(name = "negativeBookIdProvider")
    public static Object[][] getNegativeBookIdProvider() {

        return new Object[][]{
                {ZERO_VAL},
                {Integer.MIN_VALUE},
                {Integer.MAX_VALUE},
        };
    }

    @DataProvider(name = "postBookModel")
    public static Object[][] getCreateBookProvider() {
        var count = Objects.requireNonNull(client.getModels()).size();
        return new Object[][]{
                {
                        BookModelProvider.getValidBookModel(count++)
                },
                {
                        BookModelProvider.getBookModelWithoutTitle(count++)
                },
                {
                        BookModelProvider.getBookModelWithoutPageCount(count++)
                },
                {
                        BookModelProvider.getBookModelWithoutDate(++count)
                },
        };
    }

    @DataProvider(name = "negativePostBookModel")
    public static Object[][] getNewBookProvider() {
        return new Object[][]{
                {
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

    @DataProvider(name = "putBookModel")
    public static Object[][] getUpdateBookProvider() {
        BookModel bookModel = RandomUtil.getRandomElement(client.getModels());
        return new Object[][]{
                {
                        bookModel.getId(),
                        BookModelProvider.getValidBookModel()
                },
        };
    }

    @DataProvider(name = "negativePutBookModel")
    public static Object[][] getUpdateBookInvalidDataProvider() {
        BookModel bookModel = RandomUtil.getRandomElement(client.getModels());
        return new Object[][]{
                {
                        bookModel.getId(),
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

}
