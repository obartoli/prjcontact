package projet01;

import java.time.DateTimeException;
import java.time.LocalDate;

public abstract class Contact implements Comparable<Contact>{

	private String nom, prenom;
	private char sexe;
	private LocalDate date;

	public Contact(String nom, String prenom, char sexe, LocalDate date) throws MonException {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setSexe(sexe);
		this.setDate(date);
	}

	public Contact(String nom, String prenom, char sexe, int annee, int mois, int jour) throws MonException {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setSexe(sexe);
		this.setDate(annee, mois, jour);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws MonException {
		if(nom.length() > 50) { 
			throw new MonException(Erreur.TAILLE, "nom");
		}
		this.nom = nom.toUpperCase();
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) throws MonException {
		if(prenom.length() > 50) { 
			throw new MonException(Erreur.TAILLE, "prenom");
		}
		this.prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) throws MonException {
		switch(sexe) {
		case 'M':
		case 'm':
		case 'F':
		case 'f':
			break;
		default:
			throw new MonException(Erreur.SEXE, "sexe");
		}

		this.sexe = Character.toUpperCase(sexe);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) throws MonException {
		try {
			this.date = date;
		}catch(DateTimeException e) {
			throw new MonException(Erreur.DATE, "date");
		}
	}

	public void setDate(int annee, int mois ,int jour) throws MonException {
		try {
			this.date = LocalDate.of(annee, mois, jour);
		}catch(DateTimeException e) {
			throw new MonException(Erreur.DATE, "date");
		}
	}

	@Override
	public String toString() {
		String str = (this.getSexe()=='M'?"M. ":"Mme ") + this.getNom() + " " + this.getPrenom();

		return str;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Contact) {
			Contact c = (Contact) o;
			return ((this.getNom().equals(c.getNom())) 
					&& (this.getPrenom().equals(c.getPrenom()))
					&& (this.getDate().equals(c.getDate())));
		}
		return false;
	}

	public int compareTo(Contact c) {
		int res = 0;
		res = this.getNom().compareTo(c.getNom());
		if(res == 0) {
			res = this.getPrenom().compareTo(c.getPrenom());
		}
		return res;
	}
}
