package server.networking;

import server.networking.socketHandler.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private int port;           // The port to listen on
  private ServerSocket serverSocket;  // The ServerSocket to accept connections
  private boolean running;    // Flag to control server status (running or not)

  public Server(int port) {
    this.port = port;
    this.running = false;
  }

  public void start() {
    try {
      // Create the ServerSocket to listen for incoming connections
      serverSocket = new ServerSocket(port);
      running = true;
      System.out.println("Server started on port: " + port);

      // Continuously listen for incoming connections
      while (running) {
        System.out.println("Waiting for new connections...");
        Socket clientSocket = serverSocket.accept();  // Block and wait for a client to connect
        System.out.println("New client connected: " + clientSocket.getInetAddress());

        // Create a new SocketHandler for the client and start it in a new thread
        SocketHandler socketHandler = new SocketHandler(clientSocket);
        new Thread(socketHandler).start();  // Start the SocketHandler in a new thread
      }
    } catch (IOException e) {
      System.err.println("Error starting the server: " + e.getMessage());
    }
  }

  public void stop() {
    try {
      running = false;
      if (serverSocket != null && !serverSocket.isClosed()) {
        serverSocket.close();  // Close the server socket to stop accepting connections
      }
      System.out.println("Server stopped.");
    } catch (IOException e) {
      System.err.println("Error stopping the server: " + e.getMessage());
    }
  }
}
