package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<String> {

    @Parameters(paramLabel = "filepath1",
            description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2",
            description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            description = "output format: stylish/plain/json; [default: stylish]",
            defaultValue = "stylish",
            paramLabel = "format")
    private String format;

    @Override
    public String call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return "success";
    }

    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
