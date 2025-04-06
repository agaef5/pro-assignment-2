package client.networking;

import java.io.*;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import protocolWrappers.Request;
import protocolWrappers.Response;

public class Client
{
  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;
  private final Gson gson = new Gson();
  
  public Client (String host, int port){
    try
    {
      socket = new Socket(host, port);
      out = new PrintWriter(socket.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
  }
  
  public void sendMessage(String action, Object payload){
    Request request = new Request("client", action, payload);
    String json = gson.toJson(request);
    out.println(json);
  }
  private void listenToServer(){
    try
    {
      String serverResponse = in.readLine();
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }
  }
}
