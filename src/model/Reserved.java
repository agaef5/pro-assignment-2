package model;

public class Reserved implements VinylState
{
  @Override public void reserve(Vinyl vinyl)
  {
    System.out.println("Vinyl is already reserved");
  }

  @Override public void rent(Vinyl vinyl)
  {
    System.out.println("Vinyl rented (was reserved)");
    vinyl.setState(new Rented());
  }

  @Override public void returnVinyl(Vinyl vinyl)
  {
    System.out.println("returning a reserved vinyl is not allowed");
  }
  @Override public void markForRemoval(Vinyl vinyl)
  {

  }
}
