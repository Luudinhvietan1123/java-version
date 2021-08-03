package Java11.NewFileMethod;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

public class NewFileMethodExample {
    public static void main(String[] args) {
        Path filePath = Paths.get("C:/", "temp", "test.txt");

        /*try
        {
            //Write content to file
            Files.writeString(filePath, "Hello World !!", StandardOpenOption.APPEND);

            //Verify file content
            String content = Files.readString(filePath);

            System.out.println(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
    }
}
