/**
 * Filename:MinLevelException.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains an exception case for later use
 */

/**
 *This class defines one exception may happen.
 */
public class MinLevelException extends Exception{
  private static final String EXCEPT_MSG = "%s can't be less than level 1!\n";
  private String pokemonName;

  /**
   *It calls constructor of Exception and sets pokemonName to asked string
   *@param name name of pokemon thrown
   */
  public MinLevelException(String name){
    //call constructor of super class.
    super(String.format(EXCEPT_MSG,name));
    //set pokemonName
    pokemonName = name;
  }

  @Override
  public String toString(){
    //check null
    if(this == null){
      return null;
    }
    //check instance
    if(!(this instanceof MinLevelException)){
      return null;
    }
    //formatted string
    String Formatted2 = String.format(EXCEPT_MSG,pokemonName);
    return Formatted2;
  }
}
