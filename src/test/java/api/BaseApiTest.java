package api;

import java.io.InputStream;

public class BaseApiTest {

    InputStream schema(String name) {
        return getClass().getClassLoader()
                .getResourceAsStream(String.format("schema/%s.json", name));
    }

}
