package dataProvider;

import clients.BookClient;
import models.book.BookModel;
import models.book.BookModelProvider;
import org.testng.annotations.DataProvider;
import util.RandomUtil;

import java.util.Arrays;
import java.util.Objects;

import static util.RandomUtil.ZERO_VAL;

public class BookDataProvider {

    @DataProvider(name = "getRandomBookModel")
    public static Object[][] getRandomBookModelProvider() {
        var bookModels = new BookClient().getBooksAsModels();
        BookModel bookModel = RandomUtil.getRandomElement(Arrays.asList(bookModels));
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
        var count = Objects.requireNonNull(new BookClient().getBooksAsModels()).length;
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
        var bookModels = new BookClient().getBooksAsModels();
        BookModel bookModel = RandomUtil.getRandomElement(Arrays.asList(bookModels));
        return new Object[][]{
                {
                        bookModel.getId(),
                        BookModelProvider.getValidBookModel()
                },
        };
    }

    @DataProvider(name = "negativePutBookModel")
    public static Object[][] getUpdateBookInvalidDataProvider() {
        var bookModels = new BookClient().getBooksAsModels();
        BookModel bookModel = RandomUtil.getRandomElement(Arrays.asList(bookModels));
        return new Object[][]{
                {
                        bookModel.getId(),
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

}
