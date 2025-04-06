package client.networking;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements VinylClient, SocketSubject, Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private List<SocketSubject> listeners = new ArrayList<>();
  
  public ClientHandler ( Socket socket ) throws IOException
  {
    this.socket = socket;
    this.gson=new Gson();
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
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
    try {
      String message;
      while ((message = in.readLine()) != null) {
        processMessage(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    }

  @Override public void notifyListeners()
  {
    for (SocketSubject listener : listeners){
      listener.notifyListeners();
    }
  }

  @Override public void addListener(SocketSubject listener)
  {
    listener.addListener(listener);
  }

  @Override public void removeListener(SocketSubject listener)
  {
    listener.removeListener(listener);
  }


}
