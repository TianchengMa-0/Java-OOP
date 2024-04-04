/**
 * Filename:Position.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains methods to change stuff at one position
 */
import java.util.*;

/**
 *This class helps initialize,check and change pokemon at one position.
 */
public class Position<T>{
  private T pokemon;

  /**
   *This method puts given pokemon into the position.
   *@param pokemon the pokemon we want to put into this position
   */
  public Position(T pokemon){
    //check null
    if(pokemon == null){
      this.pokemon = null;
    }
    this.pokemon = pokemon;
  }

  /**
   *This method helps get pokemon at this position
   */
  public T getPokemon(){
    return this.pokemon;
  }

  /**
   *This method helps set pokemon to this position.
   *@param newPokemon the pokemon we want to set to this position
   */
  public void setPokemon(T newPokemon){
    this.pokemon = newPokemon;
  }

  /**
   *This method helps check if current position contains pokemon.
   *@return true if there's no pokemon at this position, and false otherwise
   */
  public boolean isOpen(){
    //check if there's pokemon
    if(this.pokemon == null){
      return true;
    }
    else{
      return false;
    }
  }
}
