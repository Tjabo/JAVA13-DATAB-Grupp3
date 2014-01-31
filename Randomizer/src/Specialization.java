
public class Specialization {
	
	private String name;
	private int index;
	
	public Specialization(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public void setName(String specialization) {
		this.name = specialization;
	}

	public void setIndex(int specializationIndex) {
		this.index = specializationIndex;
	}
}
