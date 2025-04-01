package client.networking;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketVinylClient implements VinylClient, SocketSubject
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
}
