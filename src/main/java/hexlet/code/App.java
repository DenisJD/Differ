package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "picocli 4.6.3",
        description = "Compares two configuration files and shows a difference.")
class App {

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            defaultValue = "stylish",
            paramLabel = "format")
    String format;

    @Parameters (paramLabel = "filepath1", description = "path to first file")
    File filepath1;

    @Parameters (paramLabel = "filepath2", description = "path to second file")
    File filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
