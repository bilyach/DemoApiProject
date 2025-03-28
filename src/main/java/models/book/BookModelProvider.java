package models.book;


import static java.time.ZonedDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static util.RandomUtil.getRandomNumber;
import static util.RandomUtil.getRandomString;

public class BookModelProvider {

    public static BookModel getValidBookModel() {
        return BookModel.builder()
                .id(getRandomNumber())
                .title(getRandomString())
                .excerpt(getRandomString())
                .description(getRandomString())
                .pageCount(getRandomNumber())
                .publishDate(now().format(ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")))
                .build();
    }

    public static BookModel getBookModelWithOnlyRequiredFields() {
        return BookModel.builder()
                .id(getRandomNumber()).build();
    }

    public static BookModel getInvalidBookModel() {
        return BookModel.builder()
                .id(Integer.MIN_VALUE)
                .pageCount(Integer.MIN_VALUE)
                .build();
    }

    public static BookModel getEmptyBookModel() {
        return BookModel.builder().build();
    }

}
