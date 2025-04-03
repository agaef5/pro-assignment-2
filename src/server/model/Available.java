package server.model;

public class Available implements VinylState
{
  @Override public void reserve(Vinyl vinyl)
  {
    System.out.println("Vinyl is reserved successfully.");
    vinyl.setState(new Reserved());
  }

  @Override public void rent(Vinyl vinyl)
  {
    System.out.println("Vinyl rented successfully.");
    vinyl.setState(new Rented());
  }

  @Override public void returnVinyl(Vinyl vinyl)
  {
    System.out.println("Vinyl is already available.");
  }

  @Override public void markForRemoval(Vinyl vinyl)
  {
    if (vinyl.isMarkedForRemoval()) {
      VinylList.getInstance().removeVinyl(vinyl); // Remove if flagged
    }
  }
}
