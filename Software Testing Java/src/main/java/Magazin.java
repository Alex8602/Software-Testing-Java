public class Magazin {
	private String denumire;
	private int nrAngajati;
	private ETipMagazin tipMagazin;
	private int suprafataMp;
	
	public Magazin(String denumire, int nrAngajati, ETipMagazin tipMagazin, int suprafataMp) throws ExceptieMagazin {
		// cod adaugat:
		if (denumire.length() < 5 || denumire.length() > 100) {
			throw new ExceptieMagazin();
		}
		if (nrAngajati <= 0 || nrAngajati > 20) {
			throw new ExceptieMagazin();
		}
		if(suprafataMp < 10 || suprafataMp > 250) {
			throw new ExceptieMagazin();
		}

		// cod vechi:
		this.denumire = denumire;
		this.nrAngajati = nrAngajati;
		this.tipMagazin = tipMagazin;
		this.suprafataMp = suprafataMp;
	}

	public String getDenumire() { return denumire; }

	public void setDenumire(String denumire) throws ExceptieMagazin {
		// cod adaugat:
		if (denumire.length() < 5 || denumire.length() > 100) {
			throw new ExceptieMagazin();
		}

		// cod vechi:
		this.denumire = denumire;
	}

	public int getNrAngajati() {
		return nrAngajati;
	}

	public void setNrAngajati(int nrAngajati) throws ExceptieMagazin {
		// cod adaugat:
		if (nrAngajati <= 0 || nrAngajati > 20) {
			throw new ExceptieMagazin();
		}

		// cod vechi:
		this.nrAngajati = nrAngajati;
	}

	public ETipMagazin getTipMagazin() {
		return tipMagazin;
	}

	public void setTipMagazin(ETipMagazin tipMagazin) {
		this.tipMagazin = tipMagazin;
	}

	public int getSuprafataMp() {
		return suprafataMp;
	}

	public void setSuprafataMp(int suprafataMp) throws ExceptieMagazin {
		// cod adaugat:
		if(suprafataMp < 10 || suprafataMp > 250) {
			throw new ExceptieMagazin();
		}

		// cod vechi:
		this.suprafataMp = suprafataMp;
	}
}
