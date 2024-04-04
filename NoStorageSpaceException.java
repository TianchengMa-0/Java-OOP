/**
 * Filename:NoStorageSpaceException.java
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
public class NoStorageSpaceException extends Exception{
  private static final String EXCEPT_MSG = "No storage left\n";

  /**
   *It calls constructor of Exception
   */
  public NoStorageSpaceException(){
    super(EXCEPT_MSG);
  }

  @Override
  public String toString(){
    //check null
    if(this == null){
      return null;
    }
    //chcek instance
    if(!(this instanceof NoStorageSpaceException)){
      return null;
    }
    return EXCEPT_MSG;
  }
}
