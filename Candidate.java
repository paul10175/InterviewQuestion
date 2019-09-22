
public class Candidate {
	
	//variable declaration
	private String word; 
	private Integer confidence;
	
	public Candidate(String word) {
		this.word = word;
		this.confidence = 1;
	}
	
	//this constructor is solely for testing purposes, so we can create dummy canidates to test against
	public Candidate(String word, int confidence) {
		this.word = word;
		this.confidence = confidence;
	}
	
	public String getWord() {
		return word;
	}
	
	public void addConfidence() {
		this.confidence++;
	}
	
	public Integer getConfidence() {
		return confidence;
	}
	
	public String toString () {
		return "\"" + this.word + "\" (" +confidence + ")";
	}

	//The sole purpose for this function is so we can use the contains method for arraylist. 
	public boolean equals(Object o1) {
		if (o1 == this)
			return true;
		
		if (!(o1 instanceof Candidate))
			return false;
		
		Candidate can = (Candidate) o1;
		
		if (can.getWord().equals(this.getWord()))
			return true;
		else 
			return false;
	}
}
