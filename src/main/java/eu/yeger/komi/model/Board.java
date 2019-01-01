package eu.yeger.komi.model;

import javafx.beans.property.*;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Board  
{


   public static final String PROPERTY_game = "game";

   private Game game = null;


   public Game getGame()
   {
      return this.game;
   }


   public Board setGame(Game value)
   {
      if (this.game != value)
      {
         Game oldValue = this.game;
         if (this.game != null)
         {
            this.game = null;
            oldValue.setBoard(null);
         }
         this.game = value;
         if (value != null)
         {
            value.setBoard(this);
         }
         firePropertyChange("game", oldValue, value);
      }
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

      this.withoutSlots(this.getSlots().clone());


   }




   public static final String PROPERTY_slots = "slots";

   private java.util.ArrayList<Slot> slots = null;


   public java.util.ArrayList<Slot> getSlots()
   {
      if (this.slots == null)
      {
         return EMPTY_slots;
      }

      return this.slots;
   }



   public Board withoutSlots(Slot value)
   {
      this.getSlots().remove(value);
      return this;
   }












   public static final String PROPERTY_size = "size";

   private int size;

   public int getSize()
   {
      return size;
   }

   public Board setSize(int value)
   {
      if (value != this.size)
      {
         int oldValue = this.size;
         this.size = value;
         firePropertyChange("size", oldValue, value);
      }
      return this;
   }


   public static final java.util.ArrayList<Slot> EMPTY_slots = new java.util.ArrayList<Slot>()
   { @Override public boolean add(Slot value){ throw new UnsupportedOperationException("No direct add! Use xy.withSlots(obj)"); }};


   public Board withSlots(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withSlots(i);
            }
         }
         else if (item instanceof Slot)
         {
            if (this.slots == null)
            {
               this.slots = new java.util.ArrayList<Slot>();
            }
            if ( ! this.slots.contains(item))
            {
               this.slots.add((Slot)item);
               ((Slot)item).setBoard(this);
               firePropertyChange("slots", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }



   public Board withoutSlots(Object... value)
   {
      if (this.slots == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutSlots(i);
            }
         }
         else if (item instanceof Slot)
         {
            if (this.slots.contains(item))
            {
               this.slots.remove((Slot)item);
               ((Slot)item).setBoard(null);
               firePropertyChange("slots", item, null);
            }
         }
      }
      return this;
   }




}