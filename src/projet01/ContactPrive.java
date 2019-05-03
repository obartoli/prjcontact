package projet01;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactPrive extends Contact {

	private String telephone;
	private char situationF;
	private byte age;
	
	
	public ContactPrive(String nom, String prenom, char sexe, LocalDate date) throws MonException {
		super(nom, prenom, sexe, date);

	}
	
	public ContactPrive(String nom, String prenom, char sexe, LocalDate date, String telephone, char situationF) throws MonException {
		super(nom, prenom, sexe, date);

		this.setTelephone(telephone);
		this.setSituationF(situationF);
	}
	
	public ContactPrive(String nom, String prenom, char sexe, int annee, int mois ,int jour, String telephone, char situationF) throws MonException {
		super(nom, prenom, sexe, annee, mois, jour);

		this.setTelephone(telephone);
		this.setSituationF(situationF);
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) throws MonException {
		Pattern pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}$");
		Matcher matcher = pattern.matcher(telephone);
		if(!matcher.find()) {
            throw new MonException(Erreur.TELEPHONE, "telephone");
        }
		this.telephone = telephone;
	}

	public char getSituationF() {
		return situationF;
	}

	public void setSituationF(char situationF) throws MonException {
		switch(situationF) {
		case 'C':
		case 'c':
		case 'M':
		case 'm':
		case 'D':
		case 'd':
		case 'X':
		case 'x':
			break;
		default:
			throw new MonException(Erreur.SIUTATION_FAMILIALE, "situationFamiliale");
		}
		this.situationF = Character.toUpperCase(situationF);
	}
	
	public byte getAge() {
		LocalDate aujourdhui = LocalDate.now();
		
		age = (byte) (this.getDate().until(aujourdhui).toTotalMonths() / 12);
		return age;
	}
	
	@Override
	public String toString() {
		String str = "Contact Privé :\n";
		str += super.toString();
		str += "\nTéléphone : " + this.getTelephone();
		str += "\nSituation Familiale : ";
		
		switch(this.getSituationF()) {
		case 'C':
			str += "Célibataire";
			break;
		case 'M':
			str += "Marié(e)";
			break;
		case 'D':
			str += "Divorcé(e)";
			break;
		default:
			str += "Autre";
			break;
		}
		str += "\nAge : " + this.getAge() + " ans";
		str += ((LocalDate.now().getMonthValue() == super.getDate().getMonthValue() &&
                 LocalDate.now().getDayOfMonth() == super.getDate().getDayOfMonth())?" et Bon Anniversaire ! ":"" );
		
		return str;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ContactPrive) {
			ContactPrive c = (ContactPrive) o;
			return (super.equals(c) 
					&& (this.getTelephone().equals(c.getTelephone())) 
					&& (this.getSituationF() == c.getSituationF()));
		}
		return false;
	}

}
