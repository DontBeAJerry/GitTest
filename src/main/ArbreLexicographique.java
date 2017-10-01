package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArbreLexicographique {

	private Noeud courant;
	private Noeud tete;
	private char lettre;

	public ArbreLexicographique() {
		Noeud[] fils = new Noeud[30];
		this.tete = new Noeud('\u0000', false, 1, fils);
		this.courant = tete;
		this.remplirArbre();
	}

	private void ajouterMot(String mot) {
		int i, nbFils;
		//System.out.println(mot);

		if (mot.equals("")) {
			courant.setMot(true);
			this.courant = tete;
		} else {
			lettre = mot.charAt(0);
		}

		if (possedeFils(this.courant.getFils(), this.lettre)) {
			i = chercherIndiceFils(this.courant.getFils(), this.lettre);
			this.courant = this.courant.getFils()[i];
			//System.out.println(mot);

			if (mot.equals("")) {
				courant.setMot(true);
				this.courant = tete;
			} else {
				mot = mot.substring(1, mot.length());
				ajouterMot(mot);
			}
		} else {
			nbFils = nbFils(this.courant.getFils());
			Noeud[] fils;
			this.courant.getFils()[nbFils] = new Noeud(lettre, false, this.courant.getNiveau() + 1,
					fils = new Noeud[30]);
			i = chercherIndiceFils(this.courant.getFils(), lettre);
			this.courant = this.courant.getFils()[i];

			if (mot.equals("")) {
				courant.setMot(true);
				this.courant = tete;
			} else {
				mot = mot.substring(1, mot.length());
				ajouterMot(mot);
			}
		}
	}

	protected boolean existe(String mot) {
		if (mot.equals("")) {
			System.out.println("Le mot de base est vide");
			return false;
		}else return this.existe2(mot, this.tete);
	}
	
	protected boolean existe2(String mot, Noeud courant) {
					
		if(mot.equals("") && courant.isMot()) {
			//System.out.println("On est à la fin du mot : true");
			return true;
		}else{
			
			if(courant.getFils()!=null) {
				for(int i=0; i<courant.getFils().length; i++) {
					if(courant.getFils()[i] == null) continue;
					if(courant.getFils()[i].getLettre() == mot.charAt(0)) {
						//courant = courant.getFils()[i];
						this.existe2(mot.substring(1,mot.length()), courant.getFils()[i]);
						return true;
					}
				}
			}
		}
		//System.out.println("On a pas trouvé : false");
		return false;
	}

	private boolean possedeFils(Noeud[] fils, char c) {
		int i;
		boolean bool = false;

		for (i = 0; i < fils.length; i++) {
			if (fils[i] == null) {
				continue;
			} else if (fils[i].getLettre() == c) {
				bool = true;

			}
		}
		return bool;
	}

	private int chercherIndiceFils(Noeud[] fils, char c) {
		int i, j;
		j = 0;

		for (i = 0; i < fils.length; i++) {
			if (fils[i] == null) {
				continue;
			} else if (fils[i].getLettre() == c) {
				j = i;
			}
		}
		return j;
	}

	private int nbFils(Noeud[] fils) {
		int i, cpt;
		cpt = 0;

		for (i = 0; i < fils.length; i++) {
			if (fils[i] != null) {
				cpt++;
			}
		}
		return cpt;
	}

	private void remplirArbre() {
		String fichier = "/home/newbie/eclipse-workspace/Scrabble/dico";

		try {

			InputStream ips = new FileInputStream(fichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			int i = 0;

			while ((ligne = br.readLine()) != null) {
				this.ajouterMot(ligne);
				i++;
				//System.out.println(i);
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Noeud getCourant() {
		return courant;
	}

	public void setCourant(Noeud courant) {
		this.courant = courant;
	}

	public Noeud getTete() {
		return tete;
	}

	public void setTete(Noeud tete) {
		this.tete = tete;
	}

	public char getLettre() {
		return lettre;
	}

	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

}
