/**
 * Filename:Box.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains methods to change stuff at one box
 */
import java.util.*;

/**
 *This class helps initialize box,deposit pokemon and get position.
 */
public class Box<T>{
  //required variable
  private static final String BORDER     = "---------------------";
  private static final String DIVIDER    = "|";
  private static final String NEW_LINE   = "\n";
  private static final String EMPTY_POS  = "   ";
  private static final int MAX_ELEM_LINE = 5;
  private static final String OUT_OF_BOUNDS_EXCEPTION = "Index: %s";

  private List<Position<T>> boxElements;
  private int maxSize;

  /**
   *This method helps initialize a box containing required number of positions
   *@param maxSize the size of the box
   */
  public Box(int maxSize){
    this.maxSize = maxSize;
    this.boxElements = new ArrayList<Position<T>>();
    for(int i = 0; i < maxSize; i++){
      //add maxSize number of Position<T>(null) object.
      this.boxElements.add(new Position<T>(null));
    }
  }

  @Override
  public String toString() {
    int counter = 0;

    StringBuilder boxPrintout = new StringBuilder();
    boxPrintout.append(BORDER);

    // Iterate through each element, print 5 at most on a line
    for(Position<T> element : boxElements) {
      if(counter == 0) {
        boxPrintout.append(NEW_LINE);
        boxPrintout.append(DIVIDER);
      }

      // Print EMPTY_POS if the spot is free (null)
      T pokemon = element.getPokemon();
      if(element.isOpen()) {
          boxPrintout.append(EMPTY_POS);
      } else {
          boxPrintout.append(pokemon.toString());
      }
      boxPrintout.append(DIVIDER);

      counter++;

      // Used so we only have 5 elements at most on a line
      if(counter == MAX_ELEM_LINE) {
        boxPrintout.append(NEW_LINE);
        boxPrintout.append(BORDER);
        counter = 0;
      }
    }
    boxPrintout.append(NEW_LINE);

    return boxPrintout.toString();
  }

  /**
   *This method helps deposit one pokemon at an empty position at one time
   *@param newPokemon the pokemon we want to deposit.
   *@return true if successfully deposit a pokemon and false otherwise
   */
  public boolean deposit(T newPokemon){
    boolean is_open = false;
    for(Position<T> iter:this.boxElements){
      //check if this position is empty
      is_open = iter.isOpen();
      if(is_open == true){
        //deposit pokemon at this position if position is open
        iter.setPokemon(newPokemon);
        return true;
      }
    }
    //there's no space for this box and return false
    return false;
  }

  /**
   *This method helps get position<T> object based on given index.
   *@param index the index of position we want
   *@return Position<T> object
   */
  public Position<T> getPositionAtIndex(int index) throws OutOfBoundsException{
    //check if index is valid.
    if(index < 0 || index >= maxSize){
      String LOC = String.format(OUT_OF_BOUNDS_EXCEPTION,index);
      //create new exception and throw.
      OutOfBoundsException invalid = new OutOfBoundsException(LOC);
      throw invalid;
    }
    return boxElements.get(index);
  }
}
