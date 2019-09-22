import java.util.List;

public class TestFile {

	public TestFile () {
		testAll();
	}
	
	
	//runs all the test 
	public void testAll() {
		testBasicContains();
		testPunctuation();
		testLongerStringAndOrder();
		testOddCases();
	}
	/*
	 * This basic test starts off by seeing if the program is counting, and storing the correct number of canidates. It does
	 * this by creating Canidate arrays that hold the expected answers. We then loop through and see if our answer even contains 
	 * the correct solutions. This does not check order
	 */
	public boolean testBasicContains() {
		AutoCompleteProvider provider = new AutoCompleteProvider();
		String train = "The third thing that I need to tell you is that this thing does not think thoroughly.";
		Candidate[] testOne = {new Candidate("thing", 2), new Candidate("think", 1), new Candidate("third", 1), new Candidate("this", 1)}, 
				   testTwo = {new Candidate("need", 1)}, 
				   testThr = {new Candidate("that", 2), new Candidate("thing", 2), new Candidate("think", 1), 
						      new Candidate("this", 1), new Candidate("third", 1), new Candidate("the", 1), new Candidate("thoroughly", 1)};
		
		provider.train(train);
		
		List<Candidate> list = provider.getWords("thi");
		for (int i = 0; i < testOne.length; i++) {
			if (!list.contains(testOne[i])) {
				System.out.println("You failed test one on the element " + testOne[i].toString());
				return false;
			}
		}
		
		list = provider.getWords("nee");
		for (int i = 0; i < testTwo.length; i++) {
			if (!list.contains(testTwo[i])) {
				System.out.println("You failed test two on the element " + testTwo[i].toString());
				return false; 
			}
		}
		
		list = provider.getWords("th");
		for (int i = 0; i < testThr.length; i++) {
			if (!list.contains(testThr[i])) {
				System.out.println("You failed test three on the element " + testThr[i].toString());
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * This tests the same string as the previous test, however, it adds punctuation between each word 
	 * to make sure that it excludes them properly
	 */
	public boolean testPunctuation() {
		AutoCompleteProvider provider = new AutoCompleteProvider();
		String train = "The. third, thing, that, I need. to! tell. you. is? that? this, thing, does, not, think, thoroughly.";
		Candidate[] testOne = {new Candidate("thing", 2), new Candidate("think", 1), new Candidate("third", 1), new Candidate("this", 1)}, 
				   testTwo = {new Candidate("need", 1)}, 
				   testThr = {new Candidate("that", 2), new Candidate("thing", 2), new Candidate("think", 1), 
						      new Candidate("this", 1), new Candidate("third", 1), new Candidate("the", 1), new Candidate("thoroughly", 1)};
		
		provider.train(train);
		
		List<Candidate> list = provider.getWords("thi");
		for (int i = 0; i < testOne.length; i++) {
			if (!list.contains(testOne[i])) {
				System.out.println("You failed test one on the element " + testOne[i].toString());
				return false;
			}
		}
		
		list = provider.getWords("nee");
		for (int i = 0; i < testTwo.length; i++) {
			if (!list.contains(testTwo[i])) {
				System.out.println("You failed test two on the element " + testTwo[i].toString());
				return false; 
			}
		}
		
		list = provider.getWords("th");
		for (int i = 0; i < testThr.length; i++) {
			if (!list.contains(testThr[i])) {
				System.out.println("You failed test three on the element " + testThr[i].toString());
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * This test a longer string and makes sure that it contains all the canidates and they are in the correct order.
	 */
	public boolean testLongerStringAndOrder() {
		AutoCompleteProvider provider = new AutoCompleteProvider();
		String train = "this tis ta this tis ta this tis a this tis ta this tis ta this this this this this ta ta ta " 
				+ " them thor repeat this,  ? them thor repeat this, them thor repeat this, them thor thor thor" 
				+ " hello hello hello how are you thor training just training thor training thor training!";
		Candidate[] test = {new Candidate("this", 13), new Candidate("thor", 9), new Candidate("ta", 7), new Candidate("tis", 5), new Candidate("them", 4),
						   new Candidate("training", 4)};
		provider.train(train);
		
		List<Candidate> list = provider.getWords("t");
		for (int i = 0; i < test.length; i++) {
			if (!test[i].equals(list.get(i))) {
				System.out.println("Failed test four. You should have had " + test[i].toString() + " but you had " + list.get(i).toString());
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * 1. Just make sure no exceptions are thrown on the empty string. 2. Make sure it handles phone number
	 */
	public boolean testOddCases() {
		AutoCompleteProvider provider = new AutoCompleteProvider();
		Candidate[] test = {new Candidate("410-818-7085", 2), new Candidate("410-897-0987", 1), new Candidate("410", 1)};
		
		provider.train("");
		List<Candidate> list = provider.getWords("");
		
		provider.train("410-818-7085, 410-897-0987, 410-818-7085, 410");
		list = provider.getWords("4");
		
		for (int i = 0; i < test.length; i++) {
			if (!list.contains(test[i])) {
				System.out.println("You failed odd cases. You should have had " + test[i]);
				return false;
			}
				
		}
		return true;
	}
}
