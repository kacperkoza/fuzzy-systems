import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

class LinesReader {

    List<String> readLinesFromFile(String filename) {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
        return reader.lines().collect(Collectors.toList());
    }

}
