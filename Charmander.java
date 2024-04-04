/**
 * Filename:Charmander.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains specific information of one kind of pokemon
 */

/**
 *This class defines information of Charmander and its attack damage.
 */
public class Charmander extends Pokemon{
  //required variable
  private static final String NAME = "Charmander";
  private static final String DEX_NUMBER = "004";
  private static final int INITIAL_LEVEL = 5;
  //extra variable
  private int FIVE = 5;

  /**
   *This method helps initialize this pokemon
   */
  public Charmander() throws MinLevelException, MaxLevelException{
    //call constructor of Pokemon.java
    super(DEX_NUMBER,NAME,INITIAL_LEVEL);
  }

  @Override
  public int attack(){
    return FIVE;
  }
}
