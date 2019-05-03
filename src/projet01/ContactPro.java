package projet01;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactPro extends Contact {

	
	private String mail, entreprise;
	
	public ContactPro(String nom, String prenom, char sexe, LocalDate dateEmbauche, String mail, String entreprise) throws MonException {
		super(nom, prenom, sexe, dateEmbauche);

		this.setMail(mail);
		this.setEntreprise(entreprise);
	}
	
	public ContactPro(String nom, String prenom, char sexe, int annee, int mois, int jour, String mail, String entreprise) throws MonException {
		super(nom, prenom, sexe, annee, mois, jour);

		this.setMail(mail);
		this.setEntreprise(entreprise);
	}
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) throws MonException {
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(mail);
		if(!matcher.find()) {
			throw new MonException(Erreur.MAIL, "mail");
		}
		this.mail = mail;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) throws MonException {
		if(entreprise.length() > 50) {
			throw new MonException(Erreur.TAILLE, "entreprise");
		}
		this.entreprise = entreprise.toUpperCase();
	}
	
	public String getAnciennete() {
		String anciennete="";
		
		LocalDate aujourdhui = LocalDate.now();
		
		anciennete = (this.getDate().until(aujourdhui).toTotalMonths() / 12) + " an(s)";
		long mod = this.getDate().until(aujourdhui).toTotalMonths() % 12;
		anciennete += (mod != 0?" et " + mod +" mois":"");
		
		return anciennete;
	}
	
	@Override
	public String toString() {
		String str = "Contact Pro :\n";
		str += super.toString();
		str += "\nMail : " + this.getMail();
		str += "\nEntré(e) dans la société " +this.getEntreprise() + " depuis " + this.getAnciennete();
		
		return str;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ContactPro) {
			ContactPro c = (ContactPro) o;
			return (super.equals(c) 
					&& (this.getMail().equals(c.getMail())) 
					&& (this.getEntreprise().equals(c.getEntreprise())));
		}
		return false;
	}

}
