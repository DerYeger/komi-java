package eu.yeger.komi.model;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Player  
{
   public static final String PROPERTY_currentGame = "currentGame";

   private Game currentGame = null;

   public Game getCurrentGame()
   {
      return this.currentGame;
   }

   public Player setCurrentGame(Game value)
   {
      if (this.currentGame != value)
      {
         Game oldValue = this.currentGame;
         if (this.currentGame != null)
         {
            this.currentGame = null;
            oldValue.setCurrentPlayer(null);
         }
         this.currentGame = value;
         if (value != null)
         {
            value.setCurrentPlayer(this);
         }
         firePropertyChange("currentGame", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_pawns = "pawns";

   private java.util.ArrayList<Pawn> pawns = null;


   public java.util.ArrayList<Pawn> getPawns()
   {
      if (this.pawns == null)
      {
         return EMPTY_pawns;
      }

      return this.pawns;
   }

   public Player withoutPawns(Pawn value)
   {
      this.getPawns().remove(value);
      return this;
   }

   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null)
      {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }

   public void removeYou()
   {
      this.setGame(null);
      this.setCurrentGame(null);

      this.withoutPawns(this.getPawns().clone());
   }

   public static final String PROPERTY_game = "game";

   private Game game = null;

   public Game getGame()
   {
      return this.game;
   }


   public Player setGame(Game value)
   {
      if (this.game != value)
      {
         Game oldValue = this.game;
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutPlayers(this);
         }
         this.game = value;
         if (value != null)
         {
            value.withPlayers(this);
         }
         firePropertyChange("game", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_score = "score";

   private int score;

   public int getScore()
   {
      return score;
   }

   public Player setScore(int value)
   {
      if (value != this.score)
      {
         int oldValue = this.score;
         this.score = value;
         firePropertyChange("score", oldValue, value);
      }
      return this;
   }

   public static final java.util.ArrayList<Pawn> EMPTY_pawns = new java.util.ArrayList<Pawn>()
   { @Override public boolean add(Pawn value){ throw new UnsupportedOperationException("No direct add! Use xy.withPawns(obj)"); }};

   public Player withPawns(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withPawns(i);
            }
         }
         else if (item instanceof Pawn)
         {
            if (this.pawns == null)
            {
               this.pawns = new java.util.ArrayList<Pawn>();
            }
            if ( ! this.pawns.contains(item))
            {
               this.pawns.add((Pawn)item);
               ((Pawn)item).setPlayer(this);
               firePropertyChange("pawns", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }

   public Player withoutPawns(Object... value)
   {
      if (this.pawns == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutPawns(i);
            }
         }
         else if (item instanceof Pawn)
         {
            if (this.pawns.contains(item))
            {
               this.pawns.remove((Pawn)item);
               ((Pawn)item).setPlayer(null);
               firePropertyChange("pawns", item, null);
            }
         }
      }
      return this;
   }

   public static final String PROPERTY_roundsWon = "roundsWon";

   private int roundsWon;

   public int getRoundsWon()
   {
      return roundsWon;
   }

   public Player setRoundsWon(int value)
   {
      if (value != this.roundsWon)
      {
         int oldValue = this.roundsWon;
         this.roundsWon = value;
         firePropertyChange("roundsWon", oldValue, value);
      }
      return this;
   }
}