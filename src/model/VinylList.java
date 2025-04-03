package model;

import java.util.ArrayList;

public class VinylList
{
  private ArrayList<Vinyl> vinyls;
  private static VinylList instance;

  private VinylList()
  {
    vinyls = new ArrayList<Vinyl>();
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

public void reserveVinyl (Vinyl vinyl)
{
  for (int i = 0; i< vinyls.size();i++)
  {
    if (vinyls.get(i).equals(vinyl))
    {
      vinyls.get(i).reserve();
      return;
    }
  }
}

public void rentVinyl(Vinyl vinyl)
{
  for (int i = 0; i< vinyls.size();i++)
  {
    if (vinyls.get(i).equals(vinyl))
    {
      vinyls.get(i).rent();
      return;
    }
  }
}

public void returnVinyl(Vinyl vinyl)
{
  for (int i = 0; i< vinyls.size();i++)
  {
    if (vinyls.get(i).equals(vinyl))
    {
      vinyls.get(i).returnVinyl();
      return;
    }
  }
}

public void removeFlag(Vinyl vinyl)
{
  for (int i = 0; i< vinyls.size();i++)
  {
    if (vinyls.get(i).equals(vinyl))
    {
      vinyls.get(i).markForRemoval();
      return;
    }
  }
}

}
