package util;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class HttpsReader {

    public static String getContent(URL input, String sessionKey) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) input.openConnection();
        connection.setRequestProperty("Cookie", sessionKey);
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine())
            builder.append(scanner.nextLine()).append("\n");
        System.out.println(builder.toString());
        return builder.toString();
    }
}
