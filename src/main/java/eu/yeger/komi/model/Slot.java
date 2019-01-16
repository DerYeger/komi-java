package eu.yeger.komi.model;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Slot  
{
   public static final String PROPERTY_pawn = "pawn";

   private Pawn pawn = null;

   public Pawn getPawn()
   {
      return this.pawn;
   }

   public Slot setPawn(Pawn value)
   {
      if (this.pawn != value)
      {
         Pawn oldValue = this.pawn;
         if (this.pawn != null)
         {
            this.pawn = null;
            oldValue.setSlot(null);
         }
         this.pawn = value;
         if (value != null)
         {
            value.setSlot(this);
         }
         firePropertyChange("pawn", oldValue, value);
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
      this.setBoard(null);
      this.setPawn(null);

      this.withoutNeighbors(this.getNeighbors().clone());


      this.withoutNeighbors(this.getNeighbors().clone());


   }

   public static final String PROPERTY_board = "board";

   private Board board = null;

   public Board getBoard()
   {
      return this.board;
   }

   public Slot setBoard(Board value)
   {
      if (this.board != value)
      {
         Board oldValue = this.board;
         if (this.board != null)
         {
            this.board = null;
            oldValue.withoutSlots(this);
         }
         this.board = value;
         if (value != null)
         {
            value.withSlots(this);
         }
         firePropertyChange("board", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_xPos = "xPos";

   private int xPos;

   public int getXPos()
   {
      return xPos;
   }

   public Slot setXPos(int value)
   {
      if (value != this.xPos)
      {
         int oldValue = this.xPos;
         this.xPos = value;
         firePropertyChange("xPos", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_yPos = "yPos";

   private int yPos;

   public int getYPos()
   {
      return yPos;
   }

   public Slot setYPos(int value)
   {
      if (value != this.yPos)
      {
         int oldValue = this.yPos;
         this.yPos = value;
         firePropertyChange("yPos", oldValue, value);
      }
      return this;
   }

   public static final java.util.ArrayList<Slot> EMPTY_neighbors = new java.util.ArrayList<Slot>()
   { @Override public boolean add(Slot value){ throw new UnsupportedOperationException("No direct add! Use xy.withNeighbors(obj)"); }};

   public static final String PROPERTY_neighbors = "neighbors";

   private java.util.ArrayList<Slot> neighbors = null;

   public java.util.ArrayList<Slot> getNeighbors()
   {
      if (this.neighbors == null)
      {
         return EMPTY_neighbors;
      }

      return this.neighbors;
   }

   public Slot withNeighbors(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withNeighbors(i);
            }
         }
         else if (item instanceof Slot)
         {
            if (this.neighbors == null)
            {
               this.neighbors = new java.util.ArrayList<Slot>();
            }
            if ( ! this.neighbors.contains(item))
            {
               this.neighbors.add((Slot)item);
               ((Slot)item).withNeighbors(this);
               firePropertyChange("neighbors", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }

   public Slot withoutNeighbors(Object... value)
   {
      if (this.neighbors == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutNeighbors(i);
            }
         }
         else if (item instanceof Slot)
         {
            if (this.neighbors.contains(item))
            {
               this.neighbors.remove((Slot)item);
               ((Slot)item).withoutNeighbors(this);
               firePropertyChange("neighbors", item, null);
            }
         }
      }
      return this;
   }












}