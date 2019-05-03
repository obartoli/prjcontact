package projet01;

public class MonException extends Exception {

	private Erreur codeErr;
	private String message, champ;
	
	public MonException(Erreur codeErr) {
		this.codeErr = codeErr;
	}
	
	public MonException(Erreur codeErr, String champ) {
		this.codeErr = codeErr;
		this.champ = champ;
	}
	
	@Override
	public String getMessage() {
		
		switch(this.codeErr) {
		case TAILLE:
			this.message = (this.champ == null?"":this.champ+" : ") + "longueur maximum 50 !";
			break;
		case SEXE:
			this.message = (this.champ == null?"":this.champ+" : ") + "valeurs possible >> 'M' ou 'F'";
			break;
		case TELEPHONE:
			this.message = (this.champ == null?"":this.champ+" : ") + "format >> 00-00-00-00-00";
			break;
		case SIUTATION_FAMILIALE:
			this.message = (this.champ == null?"":this.champ+" : ") + "valeurs possible >> 'C' / 'M' / 'D' / 'X'";
			break;
		case AGE:
			this.message = (this.champ == null?"":this.champ+" : ") + " invalide";
			break;
		case MAIL:
			this.message = (this.champ == null?"":this.champ+" : ") + " invalide";
			break;
		case ANCIENNETE:
			this.message = (this.champ == null?"":this.champ+" : ") + "doit être >= 12";
			break;
		case DATE:
			this.message = (this.champ == null?"":this.champ+" : ") + "invalie";
			break;
		default:
			this.message = (this.champ == null?"":this.champ+" : ") + "Autre type d'erreur !";
			break;
		}
		
		return this.message;
	}
}
