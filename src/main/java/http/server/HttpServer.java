package http.server;

import http.config.Configuration;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

  private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

  private static String baseIP;
  private static int port;

  static {
    baseIP = Configuration.Instance.getBaseIP();
    port = Configuration.Instance.getPort();
  }

  public static void startUpServer() throws InterruptedException, IOException {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      logger.error("Could not listen on port: " + port);
      System.exit(1);
    }
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

    while (true) {

      Socket clientSocket = null;

      clientSocket = serverSocket.accept();

      if (clientSocket != null) {
        executor.submit(new HttpHandler(clientSocket));

      }

    }
  }
}
