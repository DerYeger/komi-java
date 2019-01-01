package eu.yeger.komi.model;

import javafx.beans.property.*;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Group  
{

   public static final String PROPERTY_player = "player";

   private Player player = null;


   public Player getPlayer()
   {
      return this.player;
   }


   public Group setPlayer(Player value)
   {
      if (this.player != value)
      {
         Player oldValue = this.player;
         if (this.player != null)
         {
            this.player = null;
            oldValue.withoutGroups(this);
         }
         this.player = value;
         if (value != null)
         {
            value.withGroups(this);
         }
         firePropertyChange("player", oldValue, value);
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



   public Group withoutPawns(Pawn value)
   {
      this.getPawns().remove(value);
      return this;
   }


   public static final String PROPERTY_liberties = "liberties";

   private java.util.ArrayList<Slot> liberties = null;


   public java.util.ArrayList<Slot> getLiberties()
   {
      if (this.liberties == null)
      {
         return EMPTY_liberties;
      }

      return this.liberties;
   }



   public Group withoutLiberties(Slot value)
   {
      this.getLiberties().remove(value);
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
      this.setPlayer(null);

      this.withoutPawns(this.getPawns().clone());


      this.withoutLiberties(this.getLiberties().clone());


   }
















   public static final java.util.ArrayList<Pawn> EMPTY_pawns = new java.util.ArrayList<Pawn>()
   { @Override public boolean add(Pawn value){ throw new UnsupportedOperationException("No direct add! Use xy.withPawns(obj)"); }};


   public Group withPawns(Object... value)
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
               ((Pawn)item).setGroup(this);
               firePropertyChange("pawns", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }



   public Group withoutPawns(Object... value)
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
               ((Pawn)item).setGroup(null);
               firePropertyChange("pawns", item, null);
            }
         }
      }
      return this;
   }


   public static final java.util.ArrayList<Slot> EMPTY_liberties = new java.util.ArrayList<Slot>()
   { @Override public boolean add(Slot value){ throw new UnsupportedOperationException("No direct add! Use xy.withLiberties(obj)"); }};


   public Group withLiberties(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withLiberties(i);
            }
         }
         else if (item instanceof Slot)
         {
            if (this.liberties == null)
            {
               this.liberties = new java.util.ArrayList<Slot>();
            }
            if ( ! this.liberties.contains(item))
            {
               this.liberties.add((Slot)item);
               ((Slot)item).withGroups(this);
               firePropertyChange("liberties", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }



   public Group withoutLiberties(Object... value)
   {
      if (this.liberties == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutLiberties(i);
            }
         }
         else if (item instanceof Slot)
         {
            if (this.liberties.contains(item))
            {
               this.liberties.remove((Slot)item);
               ((Slot)item).withoutGroups(this);
               firePropertyChange("liberties", item, null);
            }
         }
      }
      return this;
   }




}