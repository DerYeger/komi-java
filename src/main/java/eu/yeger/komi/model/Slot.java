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




















}