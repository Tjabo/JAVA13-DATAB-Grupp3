// firstnames.txt sourced at: http://www.svenskaakademien.se/almanackan/akademialmanackan/5493f2dd-4c6b-4e56-90ba-c701edeffb02
// lastnames.txt sourced at: http://www.scb.se/sv_/Hitta-statistik/Statistik-efter-amne/Befolkning/Amnesovergripande-statistik/Namnstatistik/30898/30905/Samtliga-folkbokforda--Efternamn-topplistor/Efternamn-topp-100/
// areacodes.txt sourced at: http://sv.wikipedia.org/wiki/Lista_%C3%B6ver_svenska_riktnummer

/**
 * @author Calle
 * 
 * A small program to randomise names, phone numbers and job titles to populate our Hospital database with dummy-data.
 * 
 * NUMBER_TO_GENERATE constant in Randomizer.java can be set to generate varying amounts of employees.
 *
 */
public class Main {
	public static void main(String[] args) {
		Randomizer rmz = new Randomizer();
		
		System.out.println("Loading input resources files...");
		//Ladda namn och riktnummer från textfiler
		rmz.loadFirstNames();
		rmz.loadLastNames();
		rmz.loadAreaCodes();		
		
		//Ladda namn på avdelningar och mottagningar
		rmz.loadWorkplaceNames();
		
		// Ladda namn på specialinriktningar
		rmz.loadSpecializations();
		
		System.out.println("Creating objects...");
		
		// Skapa objekt av specialiseringar
		rmz.createSpecializations();
		
		//Skapa resterande telefonnummer som random
		rmz.createNumbers();
		
		// Skapa arbetsplatserna och arbetstagarna
		rmz.createWorkplaces();		
		rmz.createPersons();
		
		System.out.println("Exporting SQL query...");
		
		// Konvertera till SQL och exportera till .sql fil.
		rmz.convertToSQL();
		rmz.exportInsertStatement();
	}
}
