package server.model;

import java.util.ArrayList;

public class VinylList implements VinylDataModel
{
  private ArrayList<Vinyl> vinyls;
  private static VinylList instance;

  private VinylList()
  {
    vinyls = new ArrayList<>();
  }

  public void addVinyl(Vinyl vinyl)
  {
    vinyls.add(vinyl);
  }

  public void removeVinyl(Vinyl vinyl)
  {
    vinyls.remove(vinyl);
    System.out.println("Vinyl was removed");
  }
  public ArrayList<Vinyl> getVinylList()
  {
    return vinyls;
  }
  public static VinylList getInstance() {
    if (instance == null) {
      instance = new VinylList();
    }
    return instance;
  }
}
