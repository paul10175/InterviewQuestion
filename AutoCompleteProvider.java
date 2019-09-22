import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AutoCompleteProvider {
	private HashMap<String, ArrayList<Candidate>> map;

	public AutoCompleteProvider() {
		map = new HashMap<>();
	}
	
	public List<Candidate> getWords(String fragement) {
		return map.get(fragement);
	}
	
	//iterates through the passage and updates the map. First checks to make sure that it is not the empty string 
	public void train(String passage) {
		if (passage.equals(""))
			System.out.println("This is not valid please enter something else.");
		else {
			String[] splitPassage = (passage.toLowerCase()).split("\\s+");
			
			for (int i = 0; i < splitPassage.length; i++) {
				addToMap(splitPassage[i]);
			}
		}
	}
	
	//this is where the work is done for train(). This goes through each of the substrings (0, i) and maps them to the given word
	public void addToMap(String splitSubString) {
		char lastCharacterOfString = splitSubString.charAt(splitSubString.length() - 1);
		
		if (lastCharacterOfString == '.' || lastCharacterOfString == ',' || lastCharacterOfString == '!' || lastCharacterOfString == '?')
			splitSubString = splitSubString.substring(0, splitSubString.length() - 1);
		
		for (int i = 1; i < splitSubString.length(); i++) {
			String keyString = splitSubString.substring(0, i);
			Candidate newCanidate = new Candidate(splitSubString);
			
			if (map.containsKey(keyString)) {
				if (map.get(keyString).contains(newCanidate))
					findAndUpdateConfidence(map.get(keyString), newCanidate);
				else 
					map.get(keyString).add(newCanidate);
			} else {
				map.put(keyString, new ArrayList<Candidate>());
				map.get(keyString).add(newCanidate);
			}
		}
	}
	
	//Now we know that the map pair is already in the map, so now we need to increment the confidence and then place it in the correct position
	public void findAndUpdateConfidence(ArrayList<Candidate> listOfCanidates, Candidate canidate) {
		for (int i = 0; i < listOfCanidates.size(); i++) {
			if (listOfCanidates.get(i).getWord().equals(canidate.getWord())) {
				listOfCanidates.get(i).addConfidence();
				if (listOfCanidates.size() > 1 && i > 0) //check if there is only one canidate or if its already at the front of the list
					swapCanidateIntoCorrectPosition(listOfCanidates, i);
			}
		}
	}
	
	//this function simply swaps the canidate into the correct position in the list. Since we know we can only update the confidence by one we only need to swap up one confidence rank
	public void swapCanidateIntoCorrectPosition(ArrayList<Candidate> listOfCanidates, int currentIndex) {
		int confidenceOfCurrent = listOfCanidates.get(currentIndex).getConfidence(),
			   confidenceOfNext = listOfCanidates.get(currentIndex - 1).getConfidence();
		
		//keep swapping with the next element until we place the canidate in the correct position
		while (currentIndex > 0 && confidenceOfNext < confidenceOfCurrent) {
			Candidate temp = listOfCanidates.get(currentIndex);
			listOfCanidates.set(currentIndex, listOfCanidates.get(currentIndex - 1));
			listOfCanidates.set(currentIndex - 1, temp);
			currentIndex--;
			
			if (currentIndex - 1 >= 0)
				confidenceOfNext = listOfCanidates.get(currentIndex - 1).getConfidence();
		}
	}
}
