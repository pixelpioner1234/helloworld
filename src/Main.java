import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Utwórz serwer na porcie 8081
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        // Ustaw handler na ścieżce "/"
        server.createContext("/", new MyHandler());

        // Uruchom serwer
        server.setExecutor(null); // domyślny executor
        server.start();

        System.out.println("Serwer uruchomiony na porcie 8081");
    }

    // Handler odpowiadający na żądania HTTP
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello World";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
