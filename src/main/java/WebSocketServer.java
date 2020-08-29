import org.glassfish.tyrus.server.Server;
import java.util.concurrent.CountDownLatch;


public class WebSocketServer {

    public static void main(String[] args) {
        runServer();
    }



    public static void runServer() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
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