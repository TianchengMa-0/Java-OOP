/**
 * Filename:Pokemon.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains basic information of a pokemon
 */
import java.util.*;

/**
 *This class defines basic information of pokemon and provides method to get it
 */
public class Pokemon{
  //required variables
  private static final int MAX_DAMAGE = 10;
  //can be 100 but can not be 101.
  private static final int MAX_LEVEL  = 100;
  private String dexNumber;
  private String name;
  private int level;
  private Random random;

  /**
   *this constructor initialize basic information of pokemon.
   *@param dexNumber id of pokemon.
   *@param name name of pokemon.
   *@param level level of pokemon.
   */
  public Pokemon(String dexNumber, String name, int level)
    throws MinLevelException, MaxLevelException{
    //check if given int variables are valid.
    if(dexNumber == null || name == null){
      return;
    }
    this.dexNumber = dexNumber;
    this.name = name;

    //check if level is valid and throw exceptions
    if(level < 1){
      MinLevelException min = new MinLevelException(name);
      throw min;
    }
    else if(level > MAX_LEVEL){
      MaxLevelException max = new MaxLevelException(name);
      throw max;
    }
    else{
      this.level = level;
    }

    //set random to Random object
    random = new Random();
  }

  /**
   *This method helps get name of pokemon
   *@return string version of name.
   */
  public String getName(){
    return name;
  }

  /**
   *This method helps get level of pokemon
   *@return level of this pokemon.
   */
  public int getLevel(){
    return level;
  }

  /**
   *This method helps get random object
   *@return random variable of this pokemon.
   */
  public Random getRandom(){
    return random;
  }

  @Override
  public String toString(){
    //check null
    if(this == null){
      return null;
    }
    //chek instance
    if(!(this instanceof Pokemon)){
      return null;
    }
    //return string version of id
    return dexNumber.toString();
  }

  /**
   *This method helps get damage of pokemon
   *@return random damage between given limit.
   */
  public int attack(){
    int rad_dmg = random.nextInt(MAX_DAMAGE);
    return rad_dmg;
  }
}
