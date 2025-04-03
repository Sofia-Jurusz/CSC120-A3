// importing necessary packages for printing, making lists, and randomization
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Conversation implements Chatbot {

  // Attributes 
  int rounds;
  ArrayList<String> transcript;
  ArrayList<String> inputPronounList;
  ArrayList<String> placeholderPronounList;
  ArrayList<String> outputPronounList;
  ArrayList<String> generalResponses;
  String outputString;

  
  
  /**
   * Constructor 
   */
 
  Conversation() { 
    this.rounds = 0;
    this.transcript = new ArrayList<String>(); 
    this.inputPronounList = new ArrayList<String>();
    this.placeholderPronounList = new ArrayList<String>();
    this.outputPronounList = new ArrayList<String>();
    this.generalResponses = new ArrayList<String>();
    this.outputString = "";

    // inputProunList
    inputPronounList.add("i");
    inputPronounList.add("me");
    inputPronounList.add("am");
    inputPronounList.add("you");
    inputPronounList.add("my");
    inputPronounList.add("your");
      
    // placeholderPronounList
    placeholderPronounList.add("I_ph");
    placeholderPronounList.add("me_ph");
    placeholderPronounList.add("am_ph");
    placeholderPronounList.add("you_ph");
    placeholderPronounList.add("my_ph");
    placeholderPronounList.add("your_ph");
      
  
    // outputPronounList
    outputPronounList.add("you");
    outputPronounList.add("you");
    outputPronounList.add("are");
    outputPronounList.add("I");
    outputPronounList.add("your");
    outputPronounList.add("my");

    // generalResponses
    generalResponses.add("That's so cool, tell me more!");
    generalResponses.add("Really!?!? Then what happened?");
    generalResponses.add("Very interesting, could you elaborate");
    generalResponses.add("I didn't ask.");
    generalResponses.add("I'm so sorry that happened to you.");

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

    
    //The user can input a response for the amount of rounds they request, and 
    //after each response the chatbot will give a response
    for (int i =0; i < this.rounds; i++) {
      String user_answer = my_scanner.nextLine();
      transcript.add(user_answer);

    // if chatbot is on the last round, it will say its last response
      if (i == this.rounds-1) { 
        String lastResponse = respond(user_answer);
        transcript.add(lastResponse);
        System.out.println(lastResponse);

        String done_convo = "Okay bye bye!!";
        System.out.println(done_convo);
        transcript.add(done_convo);

      } else { //otherwise, the chatbot will continue the conversation by calling on the 'respond' method
        String keep_going = respond(user_answer);
        System.out.println(keep_going); 
        transcript.add(keep_going); 
      }
  

    
    }
    my_scanner.close();
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println(" ");
    System.out.println("Conversation Transcript:");
    for(int i = 0; i < transcript.size(); i++) {
      System.out.println(transcript.get(i));

    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return outputString mirrored or canned response to user input  
   */

  public String respond(String inputString) {

    String lowercase_inputString = inputString.toLowerCase();
    String[] input_list = lowercase_inputString.split(" ");
    ArrayList<String> placeholder_list = new ArrayList<String>();
    int counter = 0;

    for (int i =0; i < input_list.length; i++) {
      String word = input_list[i];
      if(this.inputPronounList.contains(word)){
        String placeholderWord = placeholderPronounList.get(this.inputPronounList.indexOf(word));
        placeholder_list.add(placeholderWord);
      } else { 
        placeholder_list.add(word);
        counter = counter+1;
      }
      
    }
    
    if (counter == input_list.length){
      // random response generator. 
      Random r = new Random();
      String returnString = generalResponses.get(r.nextInt(generalResponses.size())); 
      return returnString; 

    } else { //mirroed response generator
      ArrayList<String> output_list = new ArrayList<String>();
      for (int i = 0; i < placeholder_list.size(); i++){
        String word = placeholder_list.get(i);
        if (placeholderPronounList.contains(word)){
          String outputWord = outputPronounList.get(this.placeholderPronounList.indexOf(word)); 
          output_list.add(outputWord);
        } else {
          output_list.add(word);
        }
      }

      this.outputString = output_list.toString();
      this.outputString = this.outputString.replaceAll("\\[","");
      this.outputString = outputString.replaceAll("\\]","?");
      this.outputString = this.outputString.replaceAll(",","");
      return this.outputString;
    }
  }
  public static void main(String[] arguments) { 
    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
