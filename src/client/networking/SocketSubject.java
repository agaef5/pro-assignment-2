package client.networking;

public interface SocketSubject
{
  void notifyListeners();
  void addListener(SocketSubject listener);
  void removeListener(SocketSubject listener);

}
