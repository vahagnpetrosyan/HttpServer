import http.server.HttpServer;
import java.io.IOException;
import org.junit.Test;

public class HttpServerTest {

  @Test
  public void startUpServerTest() throws IOException, InterruptedException {
    HttpServer.startUpServer();
  }
}
