import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;


public class WebSocketServer {

    public static void main(String[] args) {
        runServer();
    }



    public static void runServer() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        System.out.println("HELLO opened");
        Server server = new Server("localhost", 8080, "/ws", LunchBoxEndpoint.class);
        try {
            server.start();
            countDownLatch.await();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }

    }

}