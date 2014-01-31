import java.util.ArrayList;


public class Person {
	
	private String firstName;
	private String lastName;
	private String areaCode;
	private String phoneNumber;
	private int title;
	
	private ArrayList<Specialization> specializations;
	
	public Person(String firstName, String lastName, String areaCode, String phoneNumber, int title, ArrayList<Specialization> specs) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
		this.title = title;
		this.specializations = specs;
	}
	
	public void printMe() {
		System.out.println(firstName + " " + lastName + " " + areaCode + "-" + phoneNumber);
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getAreaCode() {
		return this.areaCode;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getPhone() {
		String phone = this.areaCode + "-" + this.phoneNumber;
		return phone;
	}
	
	public int getTitle() {
		return this.title;
	}
	
	public ArrayList<Specialization> getSpecs() {
		return this.specializations;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setTitle(int title) {
		this.title = title;
	}
}
