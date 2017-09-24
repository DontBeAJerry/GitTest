package partie;

import java.util.Scanner;

import dictionnaire.Dictionnaire;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dictionnaire d = new Dictionnaire();
		Scanner sc = new Scanner(System.in);
		String mot = new String();

		System.out.println("Veuillez entrez le mot à chercher");
		mot = sc.nextLine();

		if (d.rchMot(mot)) {
			System.out.println("Le mot \"" + mot + "\" est dans le dictionnaire");
			System.out.println("Le scrabble c\'est facile à coder");
		} else {
			System.out.println("Le mot \"" + mot + "\" n\'est pas dans le dictionnaire");
			System.out.println("Le Scrabble c\'est pas facile à coder");
		}
		
		sc.close();
	}

}
