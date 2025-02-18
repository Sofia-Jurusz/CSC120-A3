// importing necessary packages for printing, making lists, and randomization
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Conversation implements Chatbot {

  // Attributes 
  int rounds;
  ArrayList<String> transcript;
  
  /**
   * Constructor 
   */
 
  Conversation() { 
    this.rounds = 0;
    this.transcript = new ArrayList<String>(); //they said static array, look that up maybe....

  } 
  

  /**
   * Starts and runs the conversation with the user
   * everything the chatbot responds/says and user inputs will be recorded in the transcipt
   */
  public void chat() {
    
    Scanner my_scanner = new Scanner (System.in); //creates a scanner that will help print out values
    
    //Asks user how many rounds and prompts conversation
    System.out.println("How many rounds?");
    transcript.add("How many rounds?");

    String user_input_rounds = my_scanner.nextLine();
    this.rounds = Integer.parseInt(user_input_rounds);
    transcript.add(user_input_rounds);

    
    System.out.println("What's up with you?"); 
    transcript.add("What's up with you?");                  
    
    /** 
     * The user can input a response for the amount of rounds they request, and 
     * after each response the chatbot will give a response
     * */ 

    for (int i =0; i < this.rounds; i++) {
      String user_answer = my_scanner.nextLine();
      transcript.add(user_answer);
  
      // if chatbot is on the last round, it will say its last response
      if (i == this.rounds - 1) {
        String done_convo = "Okay bye bye!!";
        System.out.println(done_convo);
        transcript.add(done_convo);
      }
      //otherwise, the chatbot will continue the conversation by calling on the 'respond' method

      else {
        String keep_going = respond(user_answer);
        System.out.println(keep_going); 
        transcript.add(keep_going); }

      }
    my_scanner.close();

    

  
}

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println(" ");
    System.out.println("Conversation Transcript:");

    /**
     * the transcript is an array list, as defined in the constructor. 
     * The for loop interates through each element of the array,
     * so the transcipt is line by line.
     */
    for(int i = 0; i < transcript.size(); i++) {
      System.out.println(transcript.get(i));

    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    /**
     * First, three lists are made
     * one with the pronoun (or verb I am now realizing) the user inputs
     * two with placeholder strings
     * three with the new pronouns
     */
    String new_inputString = inputString.concat(" "); 
    //I add a space to the string so that later on, chatbot can pick up words at the end of sentances
    
    ArrayList<String> input_pronoun_list = new ArrayList<String>();
      input_pronoun_list.add("I");
      input_pronoun_list.add(" me ");
      input_pronoun_list.add(" am ");
      input_pronoun_list.add("you");
      input_pronoun_list.add("my ");
      input_pronoun_list.add("your ");
    
    ArrayList<String> placeholder_pronoun_list = new ArrayList<String>();
      placeholder_pronoun_list.add(" I_ph ");
      placeholder_pronoun_list.add(" me_ph ");
      placeholder_pronoun_list.add(" am_ph ");
      placeholder_pronoun_list.add(" you_ph ");
      placeholder_pronoun_list.add(" my_ph ");
      placeholder_pronoun_list.add(" your_ph ");
    

    ArrayList<String> output_pronoun_list = new ArrayList<String>();
      output_pronoun_list.add("You ");
      output_pronoun_list.add(" you ");
      output_pronoun_list.add("are ");
      output_pronoun_list.add("I ");
      output_pronoun_list.add("your ");
      output_pronoun_list.add("my ");

    
    /**
     * If a word in the sentance is the same as one of the words in the input_pronoun list, 
     * then we replace it with a placeholder string. 
     * This creates a new version of new_inputString
     */
    for(int i = 0; i < input_pronoun_list.size(); i++) {
      String pronoun = input_pronoun_list.get(i);
      String pronoun_placeholder = placeholder_pronoun_list.get(i);
      
      if (inputString.contains(pronoun) == true) {
          new_inputString = new_inputString.replace(pronoun, pronoun_placeholder );
      
  
      }
      
    }
    
    /**
     * If there are placeholder strings in the new_inputString,
     * then we need to change them to the correct words so chatbot can mirror the response
     */

    for(int i = 0; i < placeholder_pronoun_list.size(); i++) {
      String pronoun_placeholder = placeholder_pronoun_list.get(i);
      String output_pronoun = output_pronoun_list.get(i);
     
      if (new_inputString.contains(pronoun_placeholder) == true) {
          new_inputString = new_inputString.replace(pronoun_placeholder, output_pronoun);
      
  
      }
  
    }
    /*
     * if we edited the original string, then we print out the new mirrored string with a question mark at the end 
     * otherwise, chatbot responds with one of its super creative automated responses 
     */
    if (new_inputString.equals(inputString.concat(" ")) == false){
      if (new_inputString.contains(".") == true) {    
            String output_string = new_inputString.replace(".","?");
            return output_string;
          }
          else {    
            String output_string = new_inputString.concat("?");
            return output_string;
          }
      

    }
    else {
      ArrayList<String> general_responses = new ArrayList<String>();
        general_responses.add("That's so cool, tell me more!");
        general_responses.add("Really!?!? Then what happened?");
        general_responses.add("Very interesting, could you elaborate");
        general_responses.add("I didn't ask.");
        general_responses.add("I'm so sorry that happened to you.");
      
        // creates a random response generator. A random number in the range of the general_responses list becomes the index we use to retrieve a string from the list
      Random r = new Random();
      String returnString = general_responses.get(r.nextInt(general_responses.size())); 

      return returnString; 
    }
  }


  public static void main(String[] arguments) { 



    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
