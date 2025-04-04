package server.model;

import java.util.ArrayList;

public interface VinylDataModel
{
  void addVinyl(Vinyl vinyl);
  void removeVinyl(Vinyl vinyl);
  ArrayList<Vinyl> getVinylList();
  void reserveVinyl(Vinyl vinyl);
  void rentVinyl(Vinyl vinyl);
  void returnVinyl(Vinyl vinyl);
  void removeFlag(Vinyl vinyl);
}
