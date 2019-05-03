package projet01;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Menu

		Scanner scan = new Scanner(System.in);
		char reponse = ' ';
		Annuaire annuaire = new Annuaire();
		do {
			System.out.println("#############################################");
			System.out.println("'L' > Lister le repertoire");
			System.out.println("'A' > Ajouter contact // 'S' > Supprimer contact // 'M' > Modifier contact");
			System.out.println("'R' > Rechercher par nom");
			System.out.println("'T' > Trier les contacts (sur le nom puis sur les prénoms)");
			System.out.println("'E' > Exporter les contacts");
			System.out.println("'Q' > Pour quitter");
			System.out.println("#############################################");

			reponse = scan.next().charAt(0);

			try {
				switch (reponse) {
				case 'L':
				case 'l':
					annuaire.lister();
					break;
				case 'A':
				case 'a':
					ajouter(scan, annuaire);
					break;
				case 'R':
				case 'r':
					recherche(scan, annuaire);
					break;
				case 'S':
				case 's':
					supprimer(scan, annuaire);
					break;
				case 'M':
				case 'm':
					modifier(scan, annuaire);
					break;
				case 'T':
				case 't':
					annuaire.tri();
					annuaire.lister();
					break;
				case 'E':
				case 'e':
					exporter(scan, annuaire);
					break;
				default:
					break;
				}
			}catch(MonException e) {
				System.err.println(e.getMessage());
			}
		}while(reponse != 'Q' && reponse != 'q');

	}

	public static void ajouter(Scanner scan, Annuaire annuaire) throws MonException {
		System.out.println(">>Pro(p) ou Privé(m) ?");
		char rep = scan.next().charAt(0);
		boolean bool = false;
		
		switch(rep) {
		case 'P':
		case 'p':
			bool = ajouterPro(scan,annuaire);
			if(!bool) 
				System.out.println("Contact déjà existant !");
			break;
		case 'm':
		case 'M':
			bool = ajouterPrive(scan, annuaire);
			if(!bool) 
				System.out.println("Contact déjà existant !");
			break;
		default:
			System.out.println("mauvaise saisie .... 'p' ou 'm' !");
			break;
		}
	}

	public static boolean ajouterPro(Scanner scan, Annuaire annuaire) throws MonException {
		System.out.println("---- Nouveau contact ----");
		System.out.print("Nom [50 max] >> ");
		String nom = scan.next();
		System.out.print("Prenom [50 max] >> ");
		String prenom = scan.next();
		System.out.print("Sexe [F/M] >> ");
		char sexe = scan.next().charAt(0);
		System.out.print("Année d'embauche >> ");
		int annee = scan.nextInt();
		System.out.print("Mois d'embauche >> ");
		int mois = scan.nextInt();
		System.out.print("Jour d'embauche >> ");
		int jour = scan.nextInt();
		System.out.print("Mail >> ");
		String mail = scan.next();
		System.out.print("Entreprise [50 max] >> ");
		String entreprise = scan.next();

		return annuaire.ajouterContact(new ContactPro(nom, prenom, sexe, annee, mois, jour, mail, entreprise));
	}

	public static boolean ajouterPrive(Scanner scan, Annuaire annuaire) throws MonException {
		System.out.println("---- Nouveau contact ----");
		System.out.print("Nom [50 max] >> ");
		String nom = scan.next();
		System.out.print("Prenom [50 max] >> ");
		String prenom = scan.next();
		System.out.print("Sexe [F/M] >> ");
		char sexe = scan.next().charAt(0);
		System.out.print("Année de naissance >> ");
		int annee = scan.nextInt();
		System.out.print("Mois de naissance >> ");
		int mois = scan.nextInt();
		System.out.print("Jour de naissance >> ");
		int jour = scan.nextInt();
		System.out.print("Téléphone [00-00-00-00-00] >> ");
		String telephone = scan.next();
		System.out.print("Situation familiale [C/M/D/X] >> ");
		char situation = scan.next().charAt(0);

		return annuaire.ajouterContact(new ContactPrive(nom, prenom, sexe, annee, mois, jour, telephone, situation));
	}

	public static void recherche(Scanner scan, Annuaire annuaire) {
		System.out.print(">>Entrez un nom à rechercher : ");
		String nom = scan.next();
		annuaire.recherche(nom);

	}
	
	public static void supprimer(Scanner scan, Annuaire annuaire) {
		System.out.print(">>Id du contact à supprimer : ");
		int id = scan.nextInt();
		annuaire.supprimerContact(id);
	}
	
	public static void modifier(Scanner scan, Annuaire annuaire) throws MonException {
		System.out.println(">>Id du contact à modifier : ");
		int id = scan.nextInt();
		if(annuaire.getAnnuaire().get(id) instanceof ContactPrive) {
			modifierPrive(scan, annuaire, id);
		}else {
			modifierPro(scan, annuaire,id);
		}
	}
	
	public static void modifierPrive(Scanner scan, Annuaire annuaire, int id) throws MonException {
		String nom = null;
		String prenom = null;
		char sexe = ' ';
		int annee = -1;
		int mois = -1;
		int jour = -1;
		String telephone = null;
		char situation = ' ';
		char rep;
		
		ContactPrive c = (ContactPrive) annuaire.getAnnuaire().get(id);
		
		System.out.println("---- Modifier contact ----");
		System.out.println("modifier nom: "+c.getNom() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			nom = scan.next();
		System.out.println("modifier prenom: "+c.getPrenom() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			prenom = scan.next();
		System.out.println("modifier sexe: "+c.getSexe() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			sexe = scan.next().charAt(0);
		System.out.println("modifier date de Naissance: "+c.getDate() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o') {
			annee = scan.nextInt();
			mois = scan.nextInt();
			jour = scan.nextInt();
		}
		System.out.println("modifier téléphone: "+c.getTelephone() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			telephone = scan.next();
		System.out.println("modifier situation: "+c.getSituationF() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			situation = scan.next().charAt(0);
		
		annuaire.modifier(id, nom, prenom, sexe, annee,mois, jour, telephone, situation);
	}

	public static void modifierPro(Scanner scan, Annuaire annuaire, int id) throws MonException {
		String nom = null;
		String prenom = null;
		char sexe = ' ';
		int annee = -1;
		int mois = -1;
		int jour = -1;
		String mail = null;
		String entreprise = null;
		char rep;
		
		ContactPro c = (ContactPro) annuaire.getAnnuaire().get(id);
		
		System.out.println("---- Modifier contact ----");
		System.out.println("modifier nom: "+c.getNom() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			nom = scan.next();
		System.out.println("modifier prenom: "+c.getPrenom() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			prenom = scan.next();
		System.out.println("modifier sexe: "+c.getSexe() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			sexe = scan.next().charAt(0);
		System.out.println("modifier date de Naissance: "+c.getDate() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o') {
			annee = scan.nextInt();
			mois = scan.nextInt();
			jour = scan.nextInt();
		}
		System.out.println("modifier téléphone: "+c.getMail() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			mail = scan.next();
		System.out.println("modifier situation: "+c.getEntreprise() + " >> modifier [o/n] ");
		rep = scan.next().charAt(0);
		if(rep == 'o')
			entreprise = scan.next();
		
		annuaire.modifier(id, nom, prenom, sexe, annee,mois, jour, mail, entreprise);
	}
	
	public static void exporter(Scanner scan, Annuaire annuaire) {
		System.out.print("Exporter tous [a] / Exporter pro [p] / exporter privé [m] >>");
		char reponse = scan.next().charAt(0);
		
		switch(reponse) {
		case 'A':
		case 'a':
			annuaire.export();
			break;
		case 'P':
		case 'p':
			annuaire.exportPro();
			break;
		case 'm':
		case 'M':
			annuaire.exportPrive();
			break;
		default:
			System.out.println("mauvaise saisie....");
			break;
		}
	}
}
