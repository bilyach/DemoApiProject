package util;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    public final static int MIN_STRING_LENGTH = 10;
    public final static int ZERO_VAL = 0;

    public static String getRandomString(int length) {
        return RandomStringUtils.insecure().nextAlphabetic(length);
    }

    public static <T> T getRandomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static int getRandomNumber(int length) {
        return ThreadLocalRandom.current().nextInt(length);
    }
}
