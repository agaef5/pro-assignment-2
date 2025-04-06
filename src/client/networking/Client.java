package client.networking;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import protocolWrappers.Request;
import protocolWrappers.Response;
import protocolWrappers.serverResponses.ErrorResponse;
import protocolWrappers.serverResponses.ListOfVinyls;
import protocolWrappers.userRequests.ChangeVinylState;
import server.model.Vinyl;

public class Client implements VinylClient, SocketSubject
{
  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;
  private final Gson gson = new Gson();
  private List<SocketSubject> listeners = new ArrayList<>();

  public Client(String host, int port)
  {
    try
    {
      socket = new Socket(host, port);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /    // Send a message to the server
  public void sendMessage(String action, Object payload) {
    // Use Request wrapper to create a request object
    Request request = new Request("client", action, payload);
    String json = gson.toJson(request);
    out.println(json);

    // After sending the message, start listening for a server response
    listenToServer();
  }

  // Listen for messages from the server
  private void listenToServer() {
    new Thread(() -> {
      try {
        String serverResponse = in.readLine();
        if (serverResponse != null) {
          processServerResponse(serverResponse);
        }
        else{System.err.println("No server response");}
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  // Process the server's response
  private void processServerResponse(String response) {
    Response serverResponse = gson.fromJson(response, Response.class);

    if ("success".equals(serverResponse.status())) {
      // Handle success response
      Object payload = serverResponse.payload();
      if (payload instanceof ListOfVinyls) {
        // Handle the list of vinyls (display them, etc.)
        ListOfVinyls list = (ListOfVinyls) payload;
        System.out.println("Vinyls received: " + list.vinylList());
      }
    } else {
      // Handle error response
      ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
      System.err.println("Error: " + errorResponse.errorMessage());
    }
  }

  // SocketSubject interface methods for managing listeners
  @Override public void notifyListeners()
  {
    for (SocketSubject listener : listeners)
    {
      listener.notifyListeners();
    }
  }

  @Override public void addListener(SocketSubject listener)
  {
    listeners.add(listener);
  }

  @Override public void removeListener(SocketSubject listener)
  {
    listeners.remove(listener);
  }
}
