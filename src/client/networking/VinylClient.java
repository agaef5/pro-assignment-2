package client.networking;

public interface VinylClient
{
  void notifyListeners();
  void addListener(SocketSubject listener);
  void removeListener(SocketSubject listener);

}
