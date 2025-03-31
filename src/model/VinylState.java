package model;

public interface VinylState
{
  public void reserve(Vinyl vinyl);
  public void rent(Vinyl vinyl);
  public void returnVinyl(Vinyl vinyl);
  public void markForRemoval(Vinyl vinyl);
}
