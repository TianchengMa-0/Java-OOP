/**
 * Filename:PokemonStorageSystem.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains methods to do action on one pokemon
 */
import java.util.*;

/**
 *This class initializes storage system,do action on pokemon and get box.
 */
public class PokemonStorageSystem<T>{
  //required variables
  private static final int MAX_BOXES = 8;
  private static final int MAX_ITEMS = 30;
  private static final String OUT_OF_BOUNDS_EXCEPTION = "Box: %d, Pos: %d";

  private List<Box<T>> storage;
  private T partyMember;

  /**
   *This constructor initialize one storage system of required box and position
   */
  public PokemonStorageSystem(){
    this.storage = new ArrayList<Box<T>>();
    //insert required number of boxes and positions.
    for(int i = 0; i < MAX_BOXES; i++){
      this.storage.add(new Box<T>(MAX_ITEMS));
    }
  }

  /**
   *This method helps set party member
   *@param partyMember the object we want to set
   */
  public void setPartyMember(T partyMember){
    //check null
    if(partyMember == null){
      this.partyMember = null;
    }
    this.partyMember = partyMember;
  }

  /**
   *This method helps deposit pokemon.
   *@param newPokemon the pokemon we want to deposit.
   */
  public void deposit(T newPokemon) throws NoStorageSpaceException{
    //check null
    if(newPokemon == null){
      return;
    }
    for(Box<T> iter:this.storage){
      //run deposit method in Box.java and check if deposit is successful.
      if(iter.deposit(newPokemon) == false){
        //create new exception and throw.
        NoStorageSpaceException invalid = new NoStorageSpaceException();
        throw invalid;
      }
      else{
        //do nothing here if successfully deposit since we already run it.
        return;
      }
    }
  }

  /**
   *This method helps release pokemon
   *@param box box number of pokemon want to release
   *@param pos position number of pokemon want to release
   *@return generic type released
   */
  public T release(int box, int pos) throws OutOfBoundsException{
    boolean out = false;
    //check if box and pos are valid.
    if(box < 0 || box > MAX_BOXES){
      out = true;
    }
    if(pos < 0 || pos > MAX_ITEMS){
      out = true;
    }
    //if not valid, throw exception
    if(out == true){
      String LOC = String.format(OUT_OF_BOUNDS_EXCEPTION,box,pos);
      OutOfBoundsException invalid = new OutOfBoundsException(LOC);
      throw invalid;
    }

    //if valid, release and return pokemon and set null at the position.
    Box<T> BOX = this.storage.get(box);
    Position<T> POS = BOX.getPositionAtIndex(pos);
    T pokemon_release = POS.getPokemon();
    POS.setPokemon(null);
    return pokemon_release;
  }

  /**
   *This method helps move pokemon
   *@param boxFrom box number of pokemon want to move from
   *@param posFrom position number of pokemon want to move from
   *@param boxTo box number of pokemon want to move to
   *@param posTo position number of pokemon want to move to
   */
  public void move(int boxFrom, int posFrom, int boxTo, int posTo)
  throws OutOfBoundsException{
    boolean out1 = false;
    //check if boxFrom and posFrom are valid
    if(boxFrom < 0 || boxFrom > MAX_BOXES){
      out1 = true;
    }
    if(posFrom < 0 || posFrom > MAX_ITEMS){
      out1 = true;
    }
    if(out1 == true){
      //create and throw exception
      String LOC1 = String.format(OUT_OF_BOUNDS_EXCEPTION,boxFrom,posFrom);
      OutOfBoundsException invalid1 = new OutOfBoundsException(LOC1);
      throw invalid1;
    }

    boolean out2 = false;
    //check if boxTo and posTo are valid.
    if(boxTo < 0 || boxTo > MAX_BOXES){
      out2 = true;
    }
    if(posTo < 0 || posTo > MAX_ITEMS){
      out2 = true;
    }
    if(out2 == true){
      //create and throw exception.
      String LOC2 = String.format(OUT_OF_BOUNDS_EXCEPTION,boxTo,posTo);
      OutOfBoundsException invalid2 = new OutOfBoundsException(LOC2);
      throw invalid2;
    }

    //get pokemon we want to move from
    Box<T> BOXFROM = this.storage.get(boxFrom);
    Position<T> POSFROM = BOXFROM.getPositionAtIndex(posFrom);
    T pokemonFrom = POSFROM.getPokemon();

    //get pokemon we want to move to
    Box<T> BOXTO = this.storage.get(boxTo);
    Position<T> POSTO = BOXTO.getPositionAtIndex(posTo);
    T pokemonTo = POSTO.getPokemon();

    //swap pokemons
    POSFROM.setPokemon(pokemonTo);
    POSTO.setPokemon(pokemonFrom);
  }

  /**
   *This method helps get box based on given index number.
   *@param boxNumber index of box we want to get.
   *@return string version of box
   */
  public String getBox(int boxNumber) throws OutOfBoundsException{
    //check if boxNumber is valid.
    if(boxNumber < 0 || boxNumber > MAX_BOXES){
      //create and throw exception.
      String LOC = String.format(OUT_OF_BOUNDS_EXCEPTION,boxNumber,0);
      OutOfBoundsException invalid = new OutOfBoundsException(LOC);
      throw invalid;
    }
    //get box and return string version of box.
    Box<T> BOX = storage.get(boxNumber);
    return BOX.toString();
  }
}
