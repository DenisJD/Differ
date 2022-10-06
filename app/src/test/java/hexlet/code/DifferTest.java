package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static hexlet.code.Differ.generate;

public class DifferTest {
    private final String expected =
            """
                    {
                        val1: abcd
                      - val2: 50
                      + val2: 80
                      - val3: 1234
                      - val4: false
                      + val4: true
                      + val5: yes
                    }""";

    @Test
    public void differTest() throws Exception {
        String actual = generate("src/test/resources/file1Test.json", "src/test/resources/file2Test.json");
        Assertions.assertEquals(expected, actual);
    }
}
