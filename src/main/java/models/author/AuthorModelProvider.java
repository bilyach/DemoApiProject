package models.author;

import static util.RandomUtil.getRandomNumber;
import static util.RandomUtil.getRandomString;

public class AuthorModelProvider {

    public static AuthorModel getValidAuthorModel() {
        return AuthorModel.builder()
                .id(getRandomNumber())
                .idBook(getRandomNumber())
                .firstName(getRandomString())
                .lastName(getRandomString())
                .build();
    }

    public static AuthorModel getAuthorModelWithId(int id) {
        AuthorModel validAuthorModelModel = getValidAuthorModel();
        validAuthorModelModel.setId(id);
        return validAuthorModelModel;
    }

    public static AuthorModel getAuthorModelWithIds(int id, int bookId) {
        AuthorModel validAuthorModelModel = getValidAuthorModel();
        validAuthorModelModel.setId(id);
        validAuthorModelModel.setIdBook(bookId);
        return validAuthorModelModel;
    }

    public static AuthorModel getAuthorModelWithOnlyId() {
        return AuthorModel.builder().id(getRandomNumber()).build();
    }

    public static AuthorModel getAuthorModelWithOnlyBookId() {
        return AuthorModel.builder().idBook(getRandomNumber()).build();
    }

    public static AuthorModel getAuthorModelWithOnlyRequiredFields() {
        return AuthorModel.builder()
                .idBook(getRandomNumber())
                .id(getRandomNumber()).build();
    }

    public static AuthorModel getAuthorEmptyModel() {
        return AuthorModel.builder().build();
    }
}
