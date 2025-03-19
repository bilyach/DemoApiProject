package dataProvider;

import clients.AuthorClient;
import clients.BookClient;
import models.author.AuthorModel;
import models.author.AuthorModelProvider;
import models.book.BookModel;
import models.book.BookModelProvider;
import org.testng.annotations.DataProvider;
import util.RandomUtil;

import java.util.Arrays;
import java.util.Objects;

import static dataProvider.DataProviderHelper.getBookRandomId;
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
                        BookModelProvider.getValidBookModel(getBookRandomId())
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

    @DataProvider(name = "bookPostProvider")
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

    @DataProvider(name = "bookPostNegativeModelProvider")
    public static Object[][] getNewBookProvider() {
        return new Object[][]{
                {
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

    @DataProvider(name = "updateBookProvider")
    public static Object[][] getUpdateBookProvider() {
        var id = getBookRandomId();
        return new Object[][]{
                {
                        id,
                        BookModelProvider.getValidBookModel(id)
                },
        };
    }

    @DataProvider(name = "updateInvalidPutBookProvider")
    public static Object[][] getUpdateBookInvalidDataProvider() {
        return new Object[][]{
                {
                        getBookRandomId(),
                        BookModelProvider.getInvalidBookModel()
                },
        };
    }

    @DataProvider(name = "datePatternProvider")
    public static Object[][] getDatePatternProvider() {
        return new Object[][]{
                {
                        getBookRandomId()
                },
        };
    }
}
