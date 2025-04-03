package server.networking.socketHandler;

import server.model.Vinyl;
import server.model.VinylList;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class SocketHandler implements VinylStateListenerServer, Runnable
{
  private final Socket clientSocket;
  private BufferedReader in;
  private PrintWriter out;
  private VinylList vinylList;
  private Gson gson;
  private boolean running;

  public SocketHandler(Socket clientSocket)
  {
    this.clientSocket = clientSocket;
    this.vinylList = VinylList.getInstance();
    this.gson = new Gson();
  }

  public void run()
  {
    while(true){
    try {
      //initialize input streams and output streams
      this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      this.out = new PrintWriter(clientSocket.getOutputStream(), true);

      //registry as a listener
      for (Vinyl vinyl : vinylList.getVinylList()) {
        vinyl.addListener(this);
      }
    } catch (IOException e) {
      System.err.println("error in initializing socket streams: " + e.getMessage());
      closeConnection();
    }
    }
  }

  private void closeConnection()
  {
  }

  public void handleRequest(ObjectInputStream incomingData, ObjectOutputStream outgoingData)
  {


  }

  @Override public void update(Vinyl vinyl)
  {

  }
}
