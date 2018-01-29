package http.config;

import java.util.ResourceBundle;

public enum Configuration {
  Instance;

  private final static String CONFIGURATION = "config";
  private final static String BASE_IP = "baseIP";
  private final static String PORT = "port";

  private String baseIP;
  private int port;

  public String getBaseIP() {
    return baseIP;
  }

  public int getPort() {
    return port;
  }

  Configuration() {
    try {
      final ResourceBundle bundle = ResourceBundle.getBundle(CONFIGURATION);

      if(bundle.containsKey(BASE_IP)){
        baseIP = bundle.getString(BASE_IP);
      }
      if(bundle.containsKey(PORT)){
        port = Integer.parseInt(bundle.getString(PORT));
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }

  }
}
