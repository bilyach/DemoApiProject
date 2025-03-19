package models.book;

import util.RandomUtil;

import java.time.ZonedDateTime;

import static util.DateUtil.convertDateToString;
import static util.RandomUtil.MIN_STRING_LENGTH;

public class BookModelProvider {

    public static BookModel getValidBookModel() {
        return getValidBookModel(RandomUtil.getRandomNumber(MIN_STRING_LENGTH));
    }

    public static BookModel getValidBookModel(Integer id) {
        return BookModel.builder()
                .id(id)
                .title(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .excerpt(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .description(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .pageCount(id)
                .publishDate(convertDateToString(ZonedDateTime.now()))
                .build();
    }

    public static BookModel getBookModelWithoutTitle(Integer id) {
        BookModel validBookModel = getValidBookModel(id);
        validBookModel.setTitle(null);
        return validBookModel;
    }

    public static BookModel getBookModelWithoutPageCount(Integer id) {
        BookModel validBookModel = getValidBookModel(id);
        validBookModel.setPageCount(null);
        return validBookModel;
    }

    public static BookModel getBookModelWithoutDate(Integer id) {
        BookModel validBookModel = getValidBookModel(id);
        validBookModel.setPublishDate(null);
        return validBookModel;
    }

    public static BookModel getInvalidBookModel() {
        return BookModel.builder()
                .id(Integer.MIN_VALUE)
                .pageCount(Integer.MIN_VALUE)
                .build();
    }

}
