package util;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpsReader {

    public static List<String> getContent(URL input,
                                          String sessionKey)
            throws IOException
    {
        HttpsURLConnection connection = (HttpsURLConnection) input.openConnection();
        connection.setRequestProperty("Cookie", sessionKey);
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        List<String> content = new ArrayList<>();
        while (scanner.hasNextLine())
            content.add(scanner.nextLine());
        return content;
    }
}
