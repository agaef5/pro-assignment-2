package server.model;

public class Rented implements VinylState
{
  @Override public void reserve(Vinyl vinyl)
  {
    System.out.println("Vinyl reserved while rented");
    vinyl.setState(new ReservedRented());
  }

  @Override public void rent(Vinyl vinyl)
  {
    System.out.println("Vinyl is already rented");
  }

  @Override public void returnVinyl(Vinyl vinyl)
  {
    System.out.println("Vinyl returned and now available");
    vinyl.setState(new Available());
    if (vinyl.isMarkedForRemoval()) {
      VinylList.getInstance().removeVinyl(vinyl);
      vinyl.notifyListeners();
    }
  }
  @Override public void markForRemoval(Vinyl vinyl)
  {

  }
}
