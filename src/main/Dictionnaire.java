package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionnaire {

	protected File dico = new File("dico");

	public Dictionnaire() {

	}

	public boolean rchMot(String mot) {

		boolean find = false;

		// System.out.print(d.dico.getAbsolutePath());

		try {

			FileReader fr = new FileReader(this.dico);
			BufferedReader br = new BufferedReader(fr);

			try {
				String line = br.readLine();

				long c1 = System.currentTimeMillis();

				while (line != null && !find) {
					if (line.equalsIgnoreCase(mot)) {
						find = true;
					}
					line = br.readLine();
				}

				long c2 = System.currentTimeMillis();

				System.out.println("Le temps d'execution est de : " + (c2 - c1) + "ms");

				br.close();
				fr.close();

				return find;

			} catch (IOException e) {
				System.out.println("Erreur lors de la lecture : " + e.getMessage());
			}

		} catch (FileNotFoundException e) {
			System.out.println("Erreur lors de la lecture : " + e.getMessage());
		}
		return find;

	}

}
