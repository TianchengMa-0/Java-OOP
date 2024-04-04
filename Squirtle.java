/**
 * Filename:Squirtle.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains specific information of one kind of pokemon
 */
import java.util.*;

/**
 *This class defines information of Squirtle and its attack damage.
 */
public class Squirtle extends Pokemon{
  //required variable
  private static final String NAME = "Squirtle";
  private static final String DEX_NUMBER = "007";
  private static final int INITIAL_LEVEL = 5;
  //extra variable needed
  private int THREE = 3;
  private int EIGHT = 8;
  private int LIMIT = 2;

  /**
   *This method helps initialize this pokemon
   */
  public Squirtle() throws MinLevelException, MaxLevelException{
    //call constructor of Pokemon.java
    super(DEX_NUMBER,NAME,INITIAL_LEVEL);
  }

  @Override
  public int attack(){
    Random rad_dmg_squ = this.getRandom();
    //put possible damage into the array
    int[] array = new int[2];
    array[0] = THREE;
    array[1] = EIGHT;
    int result_index = rad_dmg_squ.nextInt(LIMIT);
    return array[result_index];
  }
}
