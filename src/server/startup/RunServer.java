package server.startup;

import server.networking.Server;

import java.util.Scanner;

public class RunServer {
  public static void main(String[] args) {
    int port = 8080;  // Set the port on which the server will listen
    Server server = new Server(port);

    // Start the server in a new thread to handle multiple clients
    new Thread(() -> server.start()).start();


  }
}
