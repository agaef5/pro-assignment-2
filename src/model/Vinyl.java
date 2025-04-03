package model;

import java.util.ArrayList;
import java.util.List;
import view.VinylStateListener;

public class Vinyl
{
  private String title;
  private String artist;
  private int releaseYear;
  private VinylState currentState;
  private boolean removeFlag;
  private List<VinylStateListener> listeners = new ArrayList<>();


  public Vinyl(String title, String artist, int releaseYear)
  {
    this.title=title;
    this.artist=artist;
    this.releaseYear=releaseYear;
    this.currentState= new Available();
    this.removeFlag=false;
  }

  public void setState(VinylState state)
  {
    this.currentState=state;
    notifyListeners();
  }

  public void rent()
  {
    currentState.rent(this);
  }
  public void reserve()
  {
    currentState.reserve(this);
  }
  public void returnVinyl()
  {
    currentState.returnVinyl(this);
  }

  public void markForRemoval()
  {
    removeFlag=true;
    currentState.markForRemoval(this);
    notifyListeners();
  }

  public void addListener(VinylStateListener listener)
  {
    listeners.add(listener);
  }
  protected void removeListener(VinylStateListener listener)
  {
    listeners.remove(listener);
  }

  public void notifyListeners()
  {
    for (VinylStateListener listener : listeners)
      listener.update(this);
  }

  @Override
  public String toString() {
    String result = title + " by " + artist + " (" + releaseYear + ")"
        + " - (" + currentState.getClass().getSimpleName() + ")";

    if (removeFlag) {
      result += " (r)"; // Append "(r)" if marked for removal
    }

    return result;
  }

  public boolean isMarkedForRemoval()
  {
    return removeFlag;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Vinyl vinyl = (Vinyl) obj;
    return releaseYear == vinyl.releaseYear &&
        title.equals(vinyl.title) &&
        artist.equals(vinyl.artist);
  }


}

