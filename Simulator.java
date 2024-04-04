/**
 * Filename:Simulator.java
 * Name: Tiancheng Ma
 * Login: cs8bwi20dp
 * Date: February.29.2020
 * Sources of Help: Piazza,tutor
 *
 * Description: This file contains methods users can use to play game
 */
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *This class contains methods allowing users to play game, like handle battle.
 */
public class Simulator {

    // Used if user selects bulbasaur as starter
    private static final int BULBASAUR = 2;

    // Used to parse the level of a Pokemon
    private static final int LEVEL_INDEX = 2;

    // Number of required arguments
    private static final int REQUIRED_ARGS = 2;
    private static final int REQUIRED_ARGS1 = 2;
    private static final int REQUIRED_ARGS2 = 5;
    private static final int REQUIRED_ARGS3 = 3;

    // Used to split pokemon file / user input
    private static final String SEPARATOR = ",";
    private static final String PROMPT_SEP = " ";

    // Used to index through user arguments
    private static final int BOX_INDEX = 1;
    private static final int POS_INDEX = 2;
    private static final int TO_BOX_INDEX = 3;
    private static final int TO_POS_INDEX = 4;

    // Different choices user can make
    private static final String OPTION_0 = "0";
    private static final String OPTION_1 = "1";
    private static final String OPTION_2 = "2";

    private static final String USER_PROMPT = "> ";

    private static final String USAGE_PROMPT =
        "\nUsage: java Simulator [0|1|2] [filename]\n" +
        "0 - Charmander, 1 - Squirtle, 2 - Bulbasaur\n\n";

    private static final String MAIN_PROMPT =
        "\nWhat would you like to do?\n" +
        "[0] - Go into the wild!\n" +
        "[1] - View your PC!\n\n";

    private static final String PC_PROMPT =
        "\nCurrently viewing someone's PC\n" +
        "[0] b           - View box b (specify a number)\n" +
        "[1] b1 p1 b2 p2 - Move Pokemon at box b1, pos p1 to box b2, p2\n" +
        "[2] b p         - Release Pokemon at box b, pos p\n\n";

    private static final String WILD_PROMPT =
        "\nYou have encountered a level %d %s!\n" +
        "[0] - Catch\n" +
        "[1] - Run\n\n";

    private static final String CAUGHT_PROMPT =
        "\nSuccessfully caught %s!\n";

    private static final String RUN_PROMPT =
        "\nPhew... ran away!\n";

    private static final String BATTLE_INTRO =
        "\nBattling against your rival!\n" +
        "Your rival sent out %s.\n" +
        "Go! %s!\n" +
        "--------------------------------------\n";

    private static final String BATTLE_MAIN =
        "Your rival has dealt %d damage!\n" +
        "You dealt %d damage!\n\n";

    private static final String BATTLE_WIN =
        "You won!\n";

    private static final String BATTLE_LOSE =
        "You lost! Smell you later!\n";

    private static final String EMPTY_WILD =
        "No more pokemon in the wild!\n";

    private static final String SUCCESSFUL_MOVE =
        "Successful move!\n";

    private static final String SUCCESSFUL_RELEASE =
        "Successful release!\n";

    private static final String UNRECOGNIZED_PROMPT =
        "Unrecognized command. Please try again.\n\n";

    private static final String FILE_NOT_FOUND =
        "File: %s could not be found!\n\n";

    // One storage, one scanner (reinitialize scanner as necessary)
    private static PokemonStorageSystem<Pokemon> storage;
    private static Scanner scanner;

    /**
     *This method operates battle and gives result.
     *@param starter our pokemon.
     *@param rival rival pokemon.
     */
    private static void handleBattle(Pokemon starter, Pokemon rival) {
        // Initial battle text
        System.out.printf(BATTLE_INTRO, rival.getName(), starter.getName());

        // Calculate damage done from each pokemon
        int starter_dmg = starter.attack();
        int rival_dmg = rival.attack();

        // Use System.out.printf with BATTLE_MAIN as format string
        System.out.printf(BATTLE_MAIN, rival_dmg, starter_dmg);

        // Battle logic -- you win if your damage > rival damage
        if(starter_dmg > rival_dmg){
          // System.out.printf BATTLE_WIN if you win
          System.out.print(BATTLE_WIN);
        }
        else{
          // else BATTLE_LOSE
          System.out.print(BATTLE_LOSE);
        }
    }

    /**
     *This method helps catch wild pokemon.
     *@param wild the pokemon in the wild.
     */
    private static void handleWild(Pokemon wild) {
        // Use the wild pokemon that was passed in
        System.out.printf(WILD_PROMPT, wild.getLevel(), wild.getName());

        // Parse user's next decision
        String line;

        boolean invalid = true;

        try {
            // Keep prompting user until a valid action has been made
            while(invalid) {
                System.out.print(USER_PROMPT);
                line = scanner.nextLine().toUpperCase().trim();

                switch(line) {
                    case OPTION_0:
                        invalid = false;
                        storage.deposit(wild);
                        System.out.printf(CAUGHT_PROMPT, wild.getName());
                        break;
                    case OPTION_1:
                        invalid = false;
                        System.out.printf(RUN_PROMPT);
                        break;
                    default:
                        System.out.printf(UNRECOGNIZED_PROMPT);
                        break;
                }
            }
        }
        catch(NoStorageSpaceException e){
          System.out.println(e);
        }
    }

    /**
     *This method holds interaction between user and terminal.
     */
    private static void handlePC() {
        System.out.printf(PC_PROMPT);

        String line;
        String[] splitLine;

        // Keep looping until we have a valid input
        boolean invalid = true;

        try {
            while(invalid) {
                System.out.print(USER_PROMPT);
                line = scanner.nextLine().trim();
                splitLine = line.split(PROMPT_SEP);

                // Check to ensure number of required args is correct
                // If so, then parse accordingly
                // Assumes that inputs are numbers; Not handling invalid cases
                switch(splitLine[0].toUpperCase()) {
                    case OPTION_0: {
                        if(splitLine.length != REQUIRED_ARGS1) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        //Pase argument and pass in getBox
                        int num = Integer.parseInt(splitLine[1]);
                        String result = storage.getBox(num);

                        //System.out.printf output of getBox
                        System.out.printf(result);

                        break;
                    }
                    case OPTION_1: {
                        if(splitLine.length != REQUIRED_ARGS2) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        //Parse arguments and pass into move
                        int boxF = Integer.parseInt(splitLine[BOX_INDEX]);
                        int posF = Integer.parseInt(splitLine[POS_INDEX]);
                        int boxT = Integer.parseInt(splitLine[TO_BOX_INDEX]);
                        int posT = Integer.parseInt(splitLine[TO_POS_INDEX]);
                        storage.move(boxF,posF,boxT,posT);

                        System.out.printf(SUCCESSFUL_MOVE);
                        break;
                    }
                    case OPTION_2: {
                        if(splitLine.length != REQUIRED_ARGS3) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        //Parse arguments and pass into release
                        int boxNum = Integer.parseInt(splitLine[BOX_INDEX]);
                        int posNum = Integer.parseInt(splitLine[POS_INDEX]);
                        storage.release(boxNum,posNum);

                        System.out.printf(SUCCESSFUL_RELEASE);
                        break;
                    }
                    default:
                        System.out.printf(UNRECOGNIZED_PROMPT);
                        break;
                }
            }
        }
        catch(OutOfBoundsException e){
          System.out.println(e);
        }
    }

    /**
     *This method gives List<Pokemon> based on given file.
     *@param filename the name of file containing pokemons.
     *@return List<Pokemon> based on file.
     */
    private static List<Pokemon> parsePokemonFile(String filename){
      //check if filename is null.
      if(filename.equals(null)){
        return null;
      }
      List<Pokemon> create = new ArrayList<Pokemon>();
      Scanner input = null;
      try{
        //read file by line
        File inputfile = new File(filename);
        input = new Scanner(inputfile);
        while(input.hasNext()){
          String line = input.nextLine();
          String[] split = line.split(SEPARATOR);
          //get id, name and level of pokemon.
          String id = split[0];
          String name = split[1];
          int level = Integer.parseInt(split[LEVEL_INDEX]);
          //create pokemon and put it in the list.
          Pokemon P = new Pokemon(id,name,level);
          create.add(P);
        }
      }
      catch(FileNotFoundException nofile){
        System.out.printf(FILE_NOT_FOUND,filename);
        return null;
      }
      catch(MaxLevelException e1){
        System.out.println(e1);
        return null;
      }
      catch(MinLevelException e2){
        System.out.println(e2);
        return null;
      }
      finally{
        if(input != null){
          input.close();
        }
      }
      return create;
    }

    /**
     *This method holds main action for pokemon game.
     *@param args command line.
     */
    public static void main(String[] args) {
        //check if args length is valid.
        if(args.length != REQUIRED_ARGS) {
            System.out.printf(USAGE_PROMPT);
            return;
        }

        //Initialize global pokemon storage
        storage = new PokemonStorageSystem<Pokemon>();

        // Choose your starter pokemon
        int choice = Integer.parseInt(args[0]);
        Pokemon starter = null;
        Pokemon rival = null;

        //Initialize the starter and rival variables accordingly
        try{
          if(choice == 0){
            starter = new Charmander();
            rival = new Squirtle();
          }
          else if(choice == 1){
            starter = new Squirtle();
            rival = new Bulbasaur();
          }
          else if(choice == BULBASAUR){
            starter = new Bulbasaur();
            rival = new Charmander();
          }
          else{
            return;
          }
        }
        catch(MaxLevelException e1){
          System.out.println(e1);
          return;
        }
        catch(MinLevelException e2){
          System.out.println(e2);
          return;
        }

        storage.setPartyMember(starter);

        //Start battle against the opposing rival pokemon
        handleBattle(starter,rival);

        // Retrieve the filename of all the Pokemon that can be attainable
        List<Pokemon> allPokemon = parsePokemonFile(args[1]);
        if(allPokemon == null) {
            return;
        }

        // Used to index through allPokemon
        int currIndex = 0;

        // Interactive mode
        System.out.printf(MAIN_PROMPT);
        System.out.print(USER_PROMPT);

        scanner = new Scanner(System.in);
        String line;

        // Keep looping until ctrl+D
        while(scanner.hasNextLine()) {
            // Decide whether to go into the wild or view PC
            line = scanner.nextLine().toUpperCase().trim();

            switch(line) {
                case OPTION_0:
                    if(currIndex == allPokemon.size()) {
                        System.out.printf(EMPTY_WILD);
                    } else {
                        handleWild(allPokemon.get(currIndex));
                        currIndex += 1;
                    }
                    break;
                case OPTION_1:
                    handlePC();
                    break;
                default:
                    System.out.printf(UNRECOGNIZED_PROMPT);
                    break;
            }

            System.out.printf(MAIN_PROMPT);
            System.out.print(USER_PROMPT);
        }
    }
}
