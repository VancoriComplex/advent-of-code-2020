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
        while (scanner.hasNext())
            builder.append(scanner.next()).append(" ");
        return builder.toString();
    }
}
