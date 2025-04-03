package server.model;

public class ReservedRented implements VinylState
{
  @Override public void reserve(Vinyl vinyl)
  {
    System.out.println("Vinyl is already reserved");
  }

  @Override public void rent(Vinyl vinyl)
  {
    System.out.println("Vinyl is already rented and reserved");

  }

  @Override public void returnVinyl(Vinyl vinyl)
  {
    System.out.println("Vinyl returned but still reserved");
    vinyl.setState(new Reserved());
  }
  @Override public void markForRemoval(Vinyl vinyl)
  {

  }
}
