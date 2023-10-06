package in.reqres;

import in.reqres.config.ApiConfigProvider;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static final ApiConfigProvider provider = new ApiConfigProvider();

    @BeforeAll
    static void beforeAll() {
        provider.setUp();
    }
}
