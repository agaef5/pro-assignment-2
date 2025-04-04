package server.networking.socketHandler;

import server.model.*;
import protocolWrappers.serverResponses.ErrorResponse;
import protocolWrappers.serverResponses.ListOfVinyls;
import protocolWrappers.Request;
import protocolWrappers.Response;
import protocolWrappers.userRequests.ChangeVinylState;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

/*class responsible for dealing communication between client and server.
* It reads the data from the client, processes it and sends a response back.
* It updates the vinyl state via VinylStateListenerServer inferface, notifying
* the client of state changes
*/
public class SocketHandler implements VinylStateListenerServer, Runnable {
  private final Socket clientSocket;
  private BufferedReader in;
  private PrintWriter out;
  private VinylList vinylList;
  private Gson gson;
  private boolean running;

  public SocketHandler(Socket clientSocket) {
    this.clientSocket = clientSocket;
    this.vinylList = VinylList.getInstance();
    this.gson = new Gson();
  }

  @Override
  public void run() {
    try {
      // Initialize input streams en output streams
      //in = incoming data from client. Client sends JSON-formatted message (action + payload)
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);

      // Register SocketHandler as listener for changes in vinyls
      for (Vinyl vinyl : vinylList.getVinylList()) {
        vinyl.addListener(this);
      }

      //Waiting for incoming messages from client
      //Once message is recieved, processMEssage method is executed.
      String message;
      while ((message = in.readLine()) != null) {
        processMessage(message);
      }
    } catch (IOException e) {
      System.err.println("Error in socket communication: " + e.getMessage());
    } finally {
      closeConnection();
    }
  }

  private void closeConnection() {
    try {
      in.close();
      out.close();
      clientSocket.close();
    } catch (IOException e) {
      System.err.println("Error in closing down connection: " + e.getMessage());
    }
  }

  private void processMessage(String message) {
    try {
      //Recieved message from client is parsed into Request object using GSON
      Request request = gson.fromJson(message, Request.class);

      //process action based on request
      switch (request.action()) {
        case "getVinyls":
          sendVinylList();
          break;
        case "reserve":
          handleReserve(request.payload());
          break;
        case "rent":
          handleRent(request.payload());
          break;
        case "return":
          handleReturn(request.payload());
          break;
        case "changeState":
          handleChangeVinylState(request.payload());
          break;
        default:
          sendError("Not allowed here");
      }
    } catch (Exception e) {
      sendError("Error in processing request " + e.getMessage());
    }
  }

  private void sendVinylList() {
    // Send list to client in JSON-format
    ListOfVinyls response = new ListOfVinyls(vinylList);
    sendResponse("success", response);
  }

  private void handleReserve(Object payload) {
    Vinyl vinyl = gson.fromJson(payload.toString(), Vinyl.class);
    vinylList.reserveVinyl(vinyl);
    sendResponse("success", "Vinyl reserved: " + vinyl.toString());
  }

  private void handleRent(Object payload) {
    Vinyl vinyl = gson.fromJson(payload.toString(), Vinyl.class);
    vinylList.rentVinyl(vinyl);
    sendResponse("success", "Vinyl rented: " + vinyl.toString());
  }

  private void handleReturn(Object payload) {
    Vinyl vinyl = gson.fromJson(payload.toString(), Vinyl.class);
    vinylList.returnVinyl(vinyl);
    sendResponse("success", "Vinyl returned: " + vinyl.toString());
  }

  private void handleChangeVinylState(Object payload) {
    ChangeVinylState changeRequest = gson.fromJson(payload.toString(), ChangeVinylState.class);
    Vinyl vinyl = changeRequest.vinyl();
    String state = changeRequest.state();

    // Dependent on given state, change state
    switch (state) {
      case "available":
        vinyl.setState(new Available());
        break;
      case "reserved":
        vinyl.setState(new Reserved());
        break;
      case "rented":
        vinyl.setState(new Rented());
        break;
      default:
        sendError("Wrong state");
        return;
    }
    sendResponse("success", "Vinyl state changed to: " + state);
  }

  private void sendResponse(String status, Object payload) {
    // Make a response with status and payload
    Response response = new Response(status, payload);
    out.println(gson.toJson(response));
  }

  private void sendError(String errorMessage) {
    // dens error message to client
    ErrorResponse errorResponse = new ErrorResponse(errorMessage);
    out.println(gson.toJson(errorResponse));
  }

  @Override
  public void update(Vinyl vinyl) {
    // when vinyl changes state, send message to cleint
    sendResponse("update", vinyl);
  }
}
