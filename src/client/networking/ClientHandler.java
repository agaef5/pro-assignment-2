package client.networking;

import com.google.gson.Gson;
import protocolWrappers.Request;
import server.model.Vinyl;
import server.model.VinylList;
import server.networking.socketHandler.VinylStateListenerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements VinylClient, SocketSubject, Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  
  public ClientHandler ( Socket socket ) throws IOException
  {
    this.socket = socket;
  }
  private void processMessage(String message) {
    switch (message) {
      case "getVinyls":
        System.out.println("Here are the vinyls...");
        break;
      case "reserve":
        System.out.println("Vinyl reserved.");
        break;
      case "rent":
        System.out.println("Vinyl rented.");
        break;
      case "return":
        System.out.println("Vinyl returned.");
        break;
      case "changeState":
        System.out.println("Vinyl state changed.");
        break;
      default:
        System.out.println("Invalid action.");
    }
  }
  @Override public void run ()
  {
    gson = new Gson();
    try
    {
      in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      
      String message;
      while ( (message = in.readLine()) != null ){
        processMessage(message);
        }
      }
    catch ( IOException ex )
    {
      throw new RuntimeException(ex);
    }
  }
}
