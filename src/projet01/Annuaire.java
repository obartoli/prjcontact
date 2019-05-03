package projet01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Annuaire {

	private ArrayList<Contact> annuaire;

	public Annuaire() {
		setAnnuaire(new ArrayList<Contact>());
	}

	public ArrayList<Contact> getAnnuaire() {
		return annuaire;
	}

	public void setAnnuaire(ArrayList<Contact> annuaire) {
		this.annuaire = annuaire;
	}

	public boolean ajouterContact(Contact c) {
		if(this.getAnnuaire().contains(c)) {
			return false;
		}
		return this.getAnnuaire().add(c);
	}

	public boolean supprimerContact(int id) {
		return this.getAnnuaire().remove(this.getAnnuaire().get(id));
	}

	public void recherche(String nom) {
		int cpt = 0;
		for (int i =0; i <this.getAnnuaire().size(); i++) {
			if(this.getAnnuaire().get(i).getNom().equalsIgnoreCase(nom)) {
				System.out.println(this.getAnnuaire().get(i) + "\nid : " + i);
				System.out.println();
				cpt++;
			}
		}
		if(cpt == 0) {
			System.out.println(nom + " absent du répertoire...");
			System.out.println();
		}
	}

	public void tri() {
		Collections.sort(this.getAnnuaire());
	}

	public void lister() {
		for (int i =0; i <this.getAnnuaire().size(); i++) {
			System.out.println("\n" + this.getAnnuaire().get(i) + "\nid : " + i);
		}
	}

	//Inutile finalement puisque la date n'est jamais défini directement avec LocalDate en amont ...
	public void modifier(int id, String nom, String prenom, char sexe, LocalDate date, String telephone, char situationF) throws MonException {

		this.modifierBase(id, nom, prenom, sexe);
		if(date != null)
			this.getAnnuaire().get(id).setDate(date);

		ContactPrive c = null;
		if(this.getAnnuaire().get(id) instanceof ContactPrive)
			c = (ContactPrive) this.getAnnuaire().get(id);

		if(telephone != null)
			c.setTelephone(telephone);

		if(situationF != ' ')
			c.setSituationF(situationF);
	}

	public void modifier(int id, String nom, String prenom, char sexe, int annee, int mois ,int jour, String telephone, char situationF) throws MonException{

		this.modifierBase(id, nom, prenom, sexe);
		if(annee != -1 && mois != -1 && jour != -1)
			this.getAnnuaire().get(id).setDate(annee, mois, jour);

		ContactPrive c = null;
		if(this.getAnnuaire().get(id) instanceof ContactPrive)
			c = (ContactPrive) this.getAnnuaire().get(id);

		if(telephone != null)
			c.setTelephone(telephone);

		if(situationF != ' ')
			c.setSituationF(situationF);
	}

	//Inutile finalement puisque la date n'est jamais défini directement avec LocalDate en amont ...
	public void modifier(int id, String nom, String prenom, char sexe, LocalDate date, String mail, String entreprise) throws MonException {

		this.modifierBase(id, nom, prenom, sexe);
		if(date != null)
			this.getAnnuaire().get(id).setDate(date);

		ContactPro c = null;
		if(this.getAnnuaire().get(id) instanceof ContactPro)
			c = (ContactPro) this.getAnnuaire().get(id);		

		if(mail != null)
			c.setMail(mail);

		if(entreprise != null)
			c.setEntreprise(entreprise);
	}

	public void modifier(int id, String nom, String prenom, char sexe, int annee, int mois, int jour, String mail, String entreprise) throws MonException{

		this.modifierBase(id, nom, prenom, sexe);
		if(annee != -1 && mois != -1 && jour != -1)
			this.getAnnuaire().get(id).setDate(annee, mois, jour);

		ContactPro c = null;
		if(this.getAnnuaire().get(id) instanceof ContactPro)
			c = (ContactPro) this.getAnnuaire().get(id);		

		if(mail != null)
			c.setMail(mail);

		if(entreprise != null)
			c.setEntreprise(entreprise);
	}


	public void modifierBase(int id, String nom, String prenom, char sexe) throws MonException {
		if(nom != null)
			this.getAnnuaire().get(id).setNom(nom);

		if(prenom != null)
			this.getAnnuaire().get(id).setPrenom(prenom);

		if(sexe != ' ')
			this.getAnnuaire().get(id).setSexe(sexe);
	}

	public void export() {

		try {
			File fichier = new File("C:\\Users\\Administrateur\\eclipse-workspace\\prj#014\\export.txt");
			if(!fichier.exists())
				fichier.createNewFile();

			FileWriter fw = new FileWriter(fichier);
			for (Contact contact : annuaire) {
				fw.write(contact + "");
				fw.write("\n");
			}
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void exportPro() {

		try {
			File fichier = new File("C:\\Users\\Administrateur\\eclipse-workspace\\prj#014\\exportPro.txt");
			if(!fichier.exists())
				fichier.createNewFile();

			FileWriter fw = new FileWriter(fichier);
			for (Contact contact : annuaire) {
				if(contact instanceof ContactPro) {
					fw.write(contact + "");
					fw.write("\n");
				}
			}
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exportPrive() {

		try {
			File fichier = new File("C:\\Users\\Administrateur\\eclipse-workspace\\prj#014\\exportPrive.txt");
			if(!fichier.exists())
				fichier.createNewFile();

			FileWriter fw = new FileWriter(fichier);
			for (Contact contact : annuaire) {
				if(contact instanceof ContactPrive) {
					fw.write(contact + "");
					fw.write("\n");
				}
			}
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
