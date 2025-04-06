package client.networking;

import java.io.IOException;
import java.net.Socket;

public class RunClient
{
  public static void main ( String[] args )
  {
    int port = 8080;
    
    try
    {
      while ( true ){
        Socket socket = new Socket("localhost", port);
        ClientHandler clientHandler = new ClientHandler(socket);
        new Thread(clientHandler).start();
      }
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
    
  }
}
