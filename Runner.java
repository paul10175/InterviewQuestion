
import java.util.List;
import java.util.Scanner;

public class Runner{

	public static void main(String[] args) {
		
		//initialize all the variables we will need
		String option = "", train = "", word = "", displayDirections = "";
		Scanner scan = new Scanner(System.in);
		AutoCompleteProvider autoComplete = new AutoCompleteProvider();
		TestFile test = new TestFile();
		
		System.out.println("Welcome to our Mobile Device Keyboard. \n"
				+ "Type 1 for instructions or type any other key to continue");
		displayDirections = scan.nextLine().trim();
		
		if (displayDirections.equals("1")) {
			System.out.println("When the application starts you will have the option to enter words or phrases into the training data or type word fragements and then see auto suggestions. \n"
					+ "To enter in training data simply type train when prompted, and then enter whatever you like. However, if you would like to enter a word fragement and see suggestions enter type when prompted.\n" 
					+ "Thank you for using our service and we hope you enjoy your experience \n");
		}
	
		while (!option.equals("exit")) {
			System.out.println("Enter train to input training data, or enter type to type a word, or exit to exit");
			option = scan.nextLine().trim();
			
			while (!option.equals("train") && !option.equals("type") && !option.equals("exit")) {
				System.out.println("Haha please enter a valid input");
				option = scan.nextLine().trim();
			}
			
			if (option.equals("train")) {
				System.out.println("Ok now enter the training data.");
				train = scan.nextLine().trim();
				autoComplete.train(train);
			} else if (option.equals("type")){
				System.out.println("Enter a word");
				word = scan.nextLine().trim();
				if (autoComplete.getWords(word) == null)
					System.out.println("We currently do not have any suggestions for this word. Please enter more training data so we can make better predictions");
				else 
					System.out.println("\"" + word + "\" --> " + autoComplete.getWords(word));
			}
		}
		
		scan.close();
	}
}
