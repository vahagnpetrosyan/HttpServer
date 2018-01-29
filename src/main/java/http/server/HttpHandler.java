package http.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpHandler implements Callable<Void>{
  private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

  private Socket socket;

  public HttpHandler(Socket socket) {
    this.socket = socket;
  }

  @Override
  public Void call() throws IOException {

    logger.info("Connected handler");

    try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream())){


      do {
        String line = reader.readLine();
        if (line.equals("")) break;
        logger.info(line);
      } while (true);

      System.out.println("- -- - --- ----- --- -- --- -- -- -");
      out.print("HTTP/1.1 200 OK");
      out.print("\r\n");
      out.println("Content-Type: text/html");
      out.print("\r\n");
      out.print("<p> Hello </p>");
      out.print("\r\n");
      out.flush();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    finally {
      socket.close();
    }
    return null;
  }

}
