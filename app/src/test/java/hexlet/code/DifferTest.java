package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static hexlet.code.Differ.generate;

public class DifferTest {

    @Test
    public void differTestJsonStylish() throws Exception {
        final String expectedJsonStylish =
                """
                        {
                            chars1: [a, b, c]
                          - chars2: [d, e, f]
                          + chars2: false
                          - checked: false
                          + checked: true
                          - default: null
                          + default: [value1, value2]
                          - id: 45
                          + id: null
                          - key1: value1
                          + key2: value2
                            numbers1: [1, 2, 3, 4]
                          - numbers2: [2, 3, 4, 5]
                          + numbers2: [22, 33, 44, 55]
                          - numbers3: [3, 4, 5]
                          + numbers4: [4, 5, 6]
                          + obj1: {nestedKey=value, isNested=true}
                          - setting1: Some value
                          + setting1: Another value
                          - setting2: 200
                          + setting2: 300
                          - setting3: true
                          + setting3: none
                        }""";
        String actual = generate("src/test/resources/file1Test.json", "src/test/resources/file2Test.json");
        Assertions.assertEquals(expectedJsonStylish, actual);
    }

    @Test
    public void differTestJsonPlain() throws Exception {
        final String expectedJsonPlain =
                """
                        Property 'chars2' was updated. From [complex value] to false
                        Property 'checked' was updated. From false to true
                        Property 'default' was updated. From null to [complex value]
                        Property 'id' was updated. From 45 to null
                        Property 'key1' was removed
                        Property 'key2' was added with value: 'value2'
                        Property 'numbers2' was updated. From [complex value] to [complex value]
                        Property 'numbers3' was removed
                        Property 'numbers4' was added with value: [complex value]
                        Property 'obj1' was added with value: [complex value]
                        Property 'setting1' was updated. From 'Some value' to 'Another value'
                        Property 'setting2' was updated. From 200 to 300
                        Property 'setting3' was updated. From true to 'none'
                        """;
        String actual = generate("src/test/resources/file1Test.json", "src/test/resources/file2Test.json", "plain");
        Assertions.assertEquals(expectedJsonPlain, actual);
    }

    @Test
    public void differTestJsonToJson() throws Exception {
        final String expectedJsonToJson = "{\"- follow\":false,"
                + "\"  host\":\"hexlet.io\","
                + "\"- proxy\":\"123.234.53.22\","
                + "\"- timeout\":50,"
                + "\"+ timeout\":20,"
                + "\"+ verbose\":true}";
        String actual = generate("src/test/resources/file3Test.json", "src/test/resources/file4Test.json", "json");
        Assertions.assertEquals(expectedJsonToJson, actual);
    }

    @Test
    public void differTestYamlStylish() throws Exception {
        final String expectedYamlStylish =
                """
                        {
                          - age: 18
                          + age: 21
                          - dogs: [dog1, dog2]
                          + dogs: [dog1, dog2, dog3]
                          - email: alex@mail.com
                          + email: alex_iv@mail.com
                            name: Alex
                          - surname: Petrov
                          + surname: Ivanov
                          - value: true
                          + value: false
                            value_true: true
                        }""";
        String actual = generate("src/test/resources/file1Test.yaml", "src/test/resources/file2Test.yaml");
        Assertions.assertEquals(expectedYamlStylish, actual);
    }

    @Test
    public void differTestYamlPlain() throws Exception {
        final String expectedYamlPlain =
                """
                        Property 'age' was updated. From 18 to 21
                        Property 'dogs' was updated. From [complex value] to [complex value]
                        Property 'email' was updated. From 'alex@mail.com' to 'alex_iv@mail.com'
                        Property 'surname' was updated. From 'Petrov' to 'Ivanov'
                        Property 'value' was updated. From true to false
                        """;
        String actual = generate("src/test/resources/file1Test.yaml", "src/test/resources/file2Test.yaml", "plain");
        Assertions.assertEquals(expectedYamlPlain, actual);
    }

    @Test
    public void differTestYamlToJson() throws Exception {
        final String expectedYamlToJson = "{\"- age\":18,"
                + "\"+ age\":21,"
                + "\"- dogs\":[\"dog1\",\"dog2\"],"
                + "\"+ dogs\":[\"dog1\",\"dog2\",\"dog3\"],"
                + "\"- email\":\"alex@mail.com\","
                + "\"+ email\":\"alex_iv@mail.com\","
                + "\"  name\":\"Alex\","
                + "\"- surname\":\"Petrov\","
                + "\"+ surname\":\"Ivanov\","
                + "\"- value\":true,"
                + "\"+ value\":false,"
                + "\"  value_true\":true}";
        String actual = generate("src/test/resources/file1Test.yaml", "src/test/resources/file2Test.yaml", "json");
        Assertions.assertEquals(expectedYamlToJson, actual);
    }
}
