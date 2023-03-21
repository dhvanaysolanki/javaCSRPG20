import java.util.Random;
import java.util.Scanner;


class GameControl{

    // scanner and random initialization and declaration

    private final static Scanner scanInput = new Scanner(System.in);
    private final static Random rand = new Random();


    /* Damien
       This method turns all string input into proper grammatical format, it turns the first letter of a word into upper case while leaving everything else lower case.
       This method is methodized as it is needed for some text output that is used multiple times. The only parameter is string.

     */

    public static String capitalCase(String inputL){
        return inputL.substring(0, 1).toUpperCase() + inputL.substring(1).toLowerCase();

    }

    public static String strInputValidation(String[] userInputOp, String inputPrompt) {
        System.out.print(inputPrompt);
        String userInput = scanInput.nextLine().strip().toLowerCase();
        for (String posInput : userInputOp) {
            if (posInput.toLowerCase().strip().equals(userInput) || (userInputOp[0].equals(""))) {
                return userInput;

                /* Dilshaan
                    This method validates the user input, and if the users input does not match the options provided we asked them again until we get a valid input.
                    This is methodized as it is used in every scenario where the user is asked to input, it makes sure the input of the user is valid and usable by the following code.
                    We use a String array list to input the possible options for the user then we match the users input to see if it matches with what is in the array list.
                    In order to properly match the userInputOp with the inputPromt we use strip and LowerCase to standardize all text input.
                 */
            }
        }

        System.out.println("\nPlease enter valid input, the choices are: " + String.join(", ", userInputOp));
        return GameControl.strInputValidation(userInputOp, inputPrompt);
    }


    /* The code below was kept for future use

    public static int intInputValidation(int[] userInputOp, String inputPrompt) {

        System.out.println(inputPrompt);
        int userInput;

        try {
            userInput = scanInput.nextInt();
            for (int posInput : userInputOp) {
                if (posInput == userInput) {
                    scanInput.nextLine();
                    return userInput;
                }
            }
        } catch (InputMismatchException e) {
            scanInput.nextLine();
        }

        System.out.println("\nPlease enter a number, from the range of, " + userInputOp[0] + "-" + userInputOp[userInputOp.length - 1] + ":");
        return GameControl.intInputValidation(userInputOp, inputPrompt);

    }
     */




    public static int randomNum(int min, int max) {
        return rand.nextInt(max - min) + min;
    }


}
