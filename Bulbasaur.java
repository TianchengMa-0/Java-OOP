/**
 * Filename:Bulbasaur.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains specific information of one kind of pokemon
 */
import java.util.*;

/**
 *This class defines information of Bulbasaur and its attack damage.
 */
public class Bulbasaur extends Pokemon{
  //required variables
  private static final String NAME = "Bulbasaur";
  private static final String DEX_NUMBER = "001";
  private static final int INITIAL_LEVEL = 5;
  //extra variabels needed
  private int ZERO = 0;
  private int SIX = 6;
  private int TEN = 10;
  private int LIMIT = 3;

  /**
   *This method helps initialize this pokemon
   */
  public Bulbasaur() throws MinLevelException, MaxLevelException{
    //call constructor of Pokemon.java
    super(DEX_NUMBER,NAME,INITIAL_LEVEL);
  }

  @Override
  public int attack(){
    Random rad_dmg_bul = getRandom();
    //put in possible damage into the array
    int[] array = new int[3];
    array[0] = ZERO;
    array[1] = SIX;
    array[2] = TEN;
    int result_index = rad_dmg_bul.nextInt(LIMIT);
    return array[result_index];
  }
}
