
public class Workplace {
	
	private int workplaceid;
	private String workplaceName;
	private int workplaceType; // 0 = Ward, 1 = Clinic
	
	public Workplace(int workplaceid, String workplaceName, int workplaceType) {
		this.workplaceid = workplaceid;
		this.workplaceName = workplaceName;
		this.workplaceType = workplaceType;
	}
	
	public void printMe() {
		System.out.println("Name:" + workplaceName + "\t" + "ID: " + workplaceid +"\t" + "Type: " + workplaceType);
	}
	
	public int getWorkplaceid() {
		return this.workplaceid;
	}
	
	public String getWorkplaceName() {
		return this.workplaceName;
	}
	
	public int getWorkplaceType() {
		return this.workplaceType;
	}
	
	public void setWorkplaceid(int workplaceid) {
		this.workplaceid = workplaceid;
	}
	
	public void setWorkplacename(String name) {
		this.workplaceName = name;
	}
	
	public void setWorkplaceType(int type) {
		this.workplaceType = type;
	}

}
