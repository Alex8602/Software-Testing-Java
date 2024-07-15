import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class CentruComercial {
	private final String denumire;
	private final int suprafataCentru;
	private final ArrayList<Magazin> magazine;
	
	public CentruComercial(String denumire, int suprafataCentru, ArrayList<Magazin> magazine) {
		this.denumire = denumire;
		this.suprafataCentru = suprafataCentru;
		this.magazine = magazine;
	}
	
	public String getCelMaiMareMagazin(ETipMagazin tipMagazin) throws ExceptieMagazin {
		// cod nou:
		Optional<Magazin> celMaiMareMagazin = magazine.stream()
                .filter(m -> m.getTipMagazin() == tipMagazin)
				.max(Comparator.comparing(Magazin::getSuprafataMp));

        if (celMaiMareMagazin.isEmpty()) {
            throw new ExceptieMagazin();
        } else {
            return celMaiMareMagazin.get().getDenumire();
        }
        // cod vechi:
		/*
		Magazin result = magazine.get(0);
		for(Magazin m: magazine) {
			if(m.getTipMagazin() == tipMagazin && m.getSuprafataMp()>result.getSuprafataMp())
				result = m;
		}
		return result.getDenumire();
		 */
	}
	
	public float calculTarifAsigurare(IAutoritate autoritate) throws ExceptieCalculAsigurare {
		// cod nou:
		if (autoritate == null) {
			throw new ExceptieCalculAsigurare();
		}
		if (autoritate.esteAsigurat(this)) {
			float taxa = autoritate.tarifAsigurareMp();
			int suprafata = 0;
			for (Magazin magazin : magazine) {
				suprafata += magazin.getSuprafataMp();
				if (suprafata > this.suprafataCentru) {
					throw new ExceptieCalculAsigurare();
				}
			}
			double tarif = (double) taxa * suprafata;
			if (tarif > Float.MAX_VALUE) {
				throw new ExceptieCalculAsigurare();
			}
			else {
				return (float) tarif;
			}
		}
		else {
			throw new ExceptieCalculAsigurare();
		}

		// cod vechi:
		/*
		if(autoritate.esteAsigurat(this)){
			float taxa = autoritate.tarifAsigurareMp();
			int suprafata = 0;
			for(Magazin m: magazine) {
				suprafata += m.getSuprafataMp();
			}
			return taxa * suprafata;
		}
		else
			throw new ExceptieCalculAsigurare();
		 */
	}


}
