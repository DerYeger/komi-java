package eu.yeger.komi.model;

import javafx.beans.property.*;

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


   public static final String PROPERTY_groups = "groups";

   private java.util.ArrayList<Group> groups = null;


   public java.util.ArrayList<Group> getGroups()
   {
      if (this.groups == null)
      {
         return EMPTY_groups;
      }

      return this.groups;
   }



   public Slot withoutGroups(Group value)
   {
      this.getGroups().remove(value);
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

      this.withoutGroups(this.getGroups().clone());


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


   public static final java.util.ArrayList<Group> EMPTY_groups = new java.util.ArrayList<Group>()
   { @Override public boolean add(Group value){ throw new UnsupportedOperationException("No direct add! Use xy.withGroups(obj)"); }};


   public Slot withGroups(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withGroups(i);
            }
         }
         else if (item instanceof Group)
         {
            if (this.groups == null)
            {
               this.groups = new java.util.ArrayList<Group>();
            }
            if ( ! this.groups.contains(item))
            {
               this.groups.add((Group)item);
               ((Group)item).withLiberties(this);
               firePropertyChange("groups", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }



   public Slot withoutGroups(Object... value)
   {
      if (this.groups == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutGroups(i);
            }
         }
         else if (item instanceof Group)
         {
            if (this.groups.contains(item))
            {
               this.groups.remove((Group)item);
               ((Group)item).withoutLiberties(this);
               firePropertyChange("groups", item, null);
            }
         }
      }
      return this;
   }




}