package api;

import java.io.InputStream;

public class BaseApiTest {

    public InputStream schema(String name) {
        return getClass().getClassLoader()
                .getResourceAsStream(String.format("schema/%s.json", name));
    }

}
