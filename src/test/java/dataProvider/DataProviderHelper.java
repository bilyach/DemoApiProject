package dataProvider;

import clients.BookClient;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class DataProviderHelper {

    public static Integer getBookRandomId() {
        int count = Objects.requireNonNull(new BookClient().getBooksAsModels()).length;
        return ThreadLocalRandom.current().nextInt(count);
    }
}
