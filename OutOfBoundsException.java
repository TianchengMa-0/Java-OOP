/**
 * Filename:OutOfBoundsException.java
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
public class OutOfBoundsException extends Exception{
  private static final String EXCEPT_MSG = "Out of bounds: %s\n";
  private String errorLocation;

  /**
   *It calls constructor of Exception and set errorLocation to asked string
   *@param loc string version of index out of bound
   */
  public OutOfBoundsException(String loc){
    //call constructor of super class
    super(String.format(EXCEPT_MSG,loc));
    //set errorLocation
    errorLocation = loc;
  }

  @Override
  public String toString(){
    //check null
    if(this == null){
      return null;
    }
    //check instance
    if(!(this instanceof OutOfBoundsException)){
      return null;
    }
    //formatted string
    String Formatted = String.format(EXCEPT_MSG,errorLocation);
    return Formatted;
  }
}
