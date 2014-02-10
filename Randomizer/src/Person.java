import java.sql.Date;
import java.util.ArrayList;


public class Person {
	
	private String firstName;
	private String lastName;
	private String areaCode;
	private String phoneNumber;
	private int title;
	private Date doe;
	
	private ArrayList<Specialization> specializations;
	private Workplace wp;
	
	public Person(String firstName, String lastName, String areaCode, String phoneNumber, int title, Workplace wp, ArrayList<Specialization> specs, Date doe) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
		this.title = title;
		this.specializations = specs;
		this.wp = wp;
		this.doe = doe;
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
	
	public Workplace getWorkplace() {
		return this.wp;
	}
	
	public Date getDOE() {
		return this.doe;
	}
	
	public void setWorkplace(Workplace wp) {
		this.wp = wp;
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
	
	public void setDOE(Date doe) {
		this.doe = doe;
	}
}
