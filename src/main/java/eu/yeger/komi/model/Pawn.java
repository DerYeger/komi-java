package eu.yeger.komi.model;

import javafx.beans.property.*;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Pawn  
{


   public static final String PROPERTY_player = "player";

   private Player player = null;


   public Player getPlayer()
   {
      return this.player;
   }


   public Pawn setPlayer(Player value)
   {
      if (this.player != value)
      {
         Player oldValue = this.player;
         if (this.player != null)
         {
            this.player = null;
            oldValue.withoutPawns(this);
         }
         this.player = value;
         if (value != null)
         {
            value.withPawns(this);
         }
         firePropertyChange("player", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_slot = "slot";

   private Slot slot = null;


   public Slot getSlot()
   {
      return this.slot;
   }


   public Pawn setSlot(Slot value)
   {
      if (this.slot != value)
      {
         Slot oldValue = this.slot;
         if (this.slot != null)
         {
            this.slot = null;
            oldValue.setPawn(null);
         }
         this.slot = value;
         if (value != null)
         {
            value.setPawn(this);
         }
         firePropertyChange("slot", oldValue, value);
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
      this.setPlayer(null);
      this.setSlot(null);

   }


















   public static final String PROPERTY_hasLiberties = "hasLiberties";

   private boolean hasLiberties;

   public boolean getHasLiberties()
   {
      return hasLiberties;
   }

   public Pawn setHasLiberties(boolean value)
   {
      if (value != this.hasLiberties)
      {
         boolean oldValue = this.hasLiberties;
         this.hasLiberties = value;
         firePropertyChange("hasLiberties", oldValue, value);
      }
      return this;
   }




   public static final String PROPERTY_hasBeenChecked = "hasBeenChecked";

   private boolean hasBeenChecked;

   public boolean getHasBeenChecked()
   {
      return hasBeenChecked;
   }

   public Pawn setHasBeenChecked(boolean value)
   {
      if (value != this.hasBeenChecked)
      {
         boolean oldValue = this.hasBeenChecked;
         this.hasBeenChecked = value;
         firePropertyChange("hasBeenChecked", oldValue, value);
      }
      return this;
   }




}