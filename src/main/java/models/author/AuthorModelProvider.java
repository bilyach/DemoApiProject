package models.author;

import util.RandomUtil;

import static util.RandomUtil.MIN_STRING_LENGTH;

public class AuthorModelProvider {

    public static AuthorModel getValidAuthorModel() {
        return AuthorModel.builder()
                .id(RandomUtil.getRandomNumber(MIN_STRING_LENGTH))
                .idBook(RandomUtil.getRandomNumber(MIN_STRING_LENGTH))
                .firstName(RandomUtil.getRandomString(MIN_STRING_LENGTH))
                .lastName(RandomUtil.getRandomString(MIN_STRING_LENGTH))
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

    public static AuthorModel getAuthorModelWithoutBookId() {
        AuthorModel validAuthorModelModel = getValidAuthorModel();
        validAuthorModelModel.setIdBook(null);
        return validAuthorModelModel;
    }

    public static AuthorModel getAuthorModelWithRequiredFields() {
        AuthorModel validAuthorModelModel = getValidAuthorModel();
        validAuthorModelModel.setFirstName(null);
        validAuthorModelModel.setLastName(null);
        return validAuthorModelModel;
    }
}
