import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Randomizer {

	private String[] firstNames;
	private String[] lastNames;
	private String[] areaCodes;
	private ArrayList<String> phoneNums;
	
	private ArrayList<Person> persons;
	private ArrayList<Workplace> workplaces;
	private String[] clinics;
	private String[] wards;
	private String[] specializations;
	private ArrayList<Specialization> specList;
	
	private ArrayList<String> sqlString;

	private FileWrite fw;
	private FileRead fr;
	
	private int NUMBER_TO_GEN = 2000;
	
	private Random rand;

	public Randomizer() {
		rand = new Random();
	}

	public void loadFirstNames() {
		
		try {
			fr = new FileRead("res/firstnames.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}		
		
		String tempString = fr.readFromFile();		
		
		firstNames = tempString.split("\t");
	}
	
	public void loadLastNames() {
		
		try {
			fr = new FileRead("res/lastnames.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}		
		
		String tempString = fr.readFromFile();		
		
		lastNames = tempString.split(",");
	}
	
	public void loadAreaCodes() {
		
		try {
			fr = new FileRead("res/areacodes.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}		
		
		String tempString = fr.readFromFile();		
		areaCodes = tempString.split("\t");
	}
	
	public void loadSpecializations() {
		try {
			fr = new FileRead("res/specializations.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}		
		String tempString = fr.readFromFile();
		specializations = tempString.split("\t");
		fr.close();
	}
	
	public void loadWorkplaceNames() {
		try {
			fr = new FileRead("res/wards.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}		
		String tempString = fr.readFromFile();
		wards = tempString.split("\t");
		fr.close();
		
		try {
			fr = new FileRead("res/clinics.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Shit happens! " + e.getMessage());
			e.printStackTrace();
		}		
		String tempStringTwo = fr.readFromFile();
		clinics = tempStringTwo.split("\t");
		fr.close();		
	}
	
	public void createNumbers() {
		
		phoneNums = new ArrayList<String>();
		
		for (int i = 0; i < NUMBER_TO_GEN; i++) {
			
			String tempString = "";
			
			while (tempString.length() < 6) {
				tempString += Math.abs(rand.nextInt(9));
			}			
			phoneNums.add(tempString);			
		}
	}

	public void createPersons() {
		persons = new ArrayList<Person>();
		
		for (int i = 0; i < NUMBER_TO_GEN; i++) {
			String firstName = firstNames[Math.abs(rand.nextInt(firstNames.length))];
			String lastName = lastNames[Math.abs(rand.nextInt(lastNames.length))];
			String area = areaCodes[Math.abs(rand.nextInt(areaCodes.length))];
			String phone = phoneNums.get(Math.abs(rand.nextInt(phoneNums.size())));
			int title = rand.nextInt(3);
			
			ArrayList<Specialization> specs = new ArrayList<Specialization>();	
			
			for (int j = 0; j < rand.nextInt(5); j++) {
				int specIndex = rand.nextInt(16) + 1;
				String specName = specializations[specIndex];
				Specialization spec = new Specialization(specName, specIndex);	
				specs.add(spec);
			}			
			Person p = new Person(firstName, lastName, area, phone, title, specs);
			persons.add(p);			
		}
	}
	
	public void createWorkplaces() {
		
		workplaces = new ArrayList<Workplace>();
		
		int index = 1;
		
		for (String clinic : clinics) {
			Workplace workplace = new Workplace(index, clinic, 1);
			workplaces.add(workplace);
			index++;
		}
		for (String ward : wards) {
			Workplace workplace = new Workplace(index, ward, 0);
			workplaces.add(workplace);
			index++;
		}
	}
	
	public void createSpecializations() {
		specList = new ArrayList<Specialization>();
		
		int index = 1;
		
		for (String s : specializations) {
			Specialization spec = new Specialization(s, index);
			specList.add(spec);
			index++;
		}
	}
	
	public void convertToSQL() {
		
		sqlString = new ArrayList<String>();
		
		sqlString.add("START TRANSACTION;");
		
		for (Workplace w : workplaces){
			String workplace = 
					"INSERT INTO workplace (workplaceid, workplace_name)" +
							"VALUES (" 
							+ w.getWorkplaceid() + ", "
							+ "\"" + w.getWorkplaceName() + "\""	
							+");";
			sqlString.add(workplace);
			if (w.getWorkplaceType() == 0) {
				String ward = "INSERT INTO ward (workplaceid)"+"VALUES (" + w.getWorkplaceid() + ");";
				sqlString.add(ward);
			}
			else {
				String clinic = "INSERT INTO clinic (workplaceid)"+"VALUES (" + w.getWorkplaceid() + ");";
				sqlString.add(clinic);
			}
		}
		
		for (Specialization s : specList) {
			String spec = 
					"INSERT INTO specialization (specializationid, specialization_name)" +
							"VALUES ("
							+ s.getIndex() + ", "
							+ "\"" + s.getName() + "\""							
							+");";
			sqlString.add(spec);
		}

		int employeeIndex = 1;
		
		for (Person p : persons) {
			
			String employee = 
					"INSERT INTO employee (employeeid, first_name, last_name, telephone_number, date_of_employment)" +
							"VALUES (" 
							+ employeeIndex + ", "
							+ "\"" + p.getFirstName() + "\"" + ", " 
							+ "\"" + p.getLastName() + "\"" + ", " 
							+ "\"" + p.getPhone() + "\"" + ", "
							+ "NOW()"							
							+");";

			sqlString.add(employee);
			
			String title = "";
			String doc_spec = "";
			String nurse_spec = "";
			
			if (p.getTitle() == 0) {
				title = "INSERT INTO doctor (employeeid) VALUES (" + employeeIndex + ");";
				sqlString.add(title);
				
				for (Specialization s : p.getSpecs()) {
					doc_spec = "INSERT INTO doctor_specialization(employeeid, specializationid) VALUES (" + employeeIndex + ", " + s.getIndex() + ");";					
				}
				sqlString.add(doc_spec);				
			}
			else if (p.getTitle() == 1) {
				title = "INSERT INTO nurse (employeeid) VALUES (" + employeeIndex + ");";
				sqlString.add(title);

				for (Specialization s : p.getSpecs()) {
					nurse_spec = "INSERT INTO nurse_specialization(employee_id, specialization_id) VALUES (" + employeeIndex + ", " + s.getIndex() + ");";
				}
				sqlString.add(nurse_spec);
			}
			else {
				title = "INSERT INTO assistant_nurse (employeeid) VALUES (" + employeeIndex + ");";
				sqlString.add(title);
			}
			
			employeeIndex++;			
		}		
		sqlString.add("COMMIT;");		
	}
	
	public void exportInsertStatement() {
		
		try {
			fw = new FileWrite("res/export_employees.sql");
		} catch (IOException e) {
			System.out.println("Shit happans! " + e.getMessage());
			e.printStackTrace();
		}
		
		for (String str : sqlString) {
			fw.writeToFile(str);			
		}
		
		System.out.println("Export finished!");
		
		fw.closeFile();
	}
}
