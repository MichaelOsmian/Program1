
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2016 
//PROJECT:          p1
//FILE:             GradeEstimator.java
//
//Authors: Sid Smith | sbsmith5@wisc.edu | sbsmith5 | 001
//Author1: (Michael Osmian,Osmian@wisc.edu,osmian,001)
//Author2: (name2,email2,netID2,lecture number2)
//
//---------------- OTHER ASSISTANCE CREDITS 
//Persons: Identify persons by name, relationship to you, and email. 
//Describe in detail the the ideas and help they provided. 
//
//Online sources: avoid web searches to solve your problems, but if you do 
//search, be sure to include Web URLs and description of 
//of any information you find. 
////////////////////////////80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeEstimator {

	private ScoreList scores = new ScoreList();
	private ScoreIterator itr1 = new ScoreIterator(scores);
	private ScoreIterator itr2 = new ScoreIterator(scores);
	private ArrayList<String> letters = new ArrayList<String>();
	private ArrayList<Double> thresholds = new ArrayList<Double>();
	private ArrayList<String> assignmentNames = new ArrayList<String>();
	private ArrayList<Double> assignmentWeight = new ArrayList<Double>();

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out.println(Config.USAGE_MESSAGE);
		}

		// Testing with the config file

		args[0] = Config.GRADE_INFO_FILE_FORMAT_EXAMPLE;

		try {

			System.out.print(createGradeEstimatorFromFile(args[0]).getEstimateReport());

			// getEstimateReport();

		} catch (GradeFileFormatException e) {
			System.out.println("GradeFileFormatException ");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} finally {

		}

	}
	
	private void addLetters(GradeEstimator g1,Scanner scnr) throws GradeFileFormatException {
String storedLine;
		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.letters.add(storedLine.substring(0, storedLine.indexOf(' ')));

				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.letters.add(storedLine.substring(0));
				storedLine = "";
			}
		} while (storedLine.length() > 0);

	}

	
	private void addThresholds(GradeEstimator g1, Scanner scnr) throws GradeFileFormatException{
		String storedLine;
		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.thresholds.add((Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')))));
				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.thresholds.add(Double.parseDouble(storedLine.substring(0)));
				storedLine = "";
			}
		} while (storedLine.length() > 0);


	}
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {

		GradeEstimator g1 = new GradeEstimator();
		Scanner scnr;
		scnr = new Scanner(gradeInfo);

		String storedLine;
		g1.addLetters(g1, scnr);
		g1.addThresholds(g1, scnr);
	
//
//		storedLine = scnr.nextLine();
//
//		if (storedLine.contains("#")) {
//			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
//		}
//		storedLine = storedLine.trim();
//
//		do {
//			if (storedLine.contains(" ")) {
//				g1.letters.add(storedLine.substring(0, storedLine.indexOf(' ')));
//
//				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
//
//				if (storedLine.charAt(0) == ' ')
//					throw new GradeFileFormatException();
//
//			} else {
//				g1.letters.add(storedLine.substring(0));
//				storedLine = "";
//			}
//		} while (storedLine.length() > 0);

//		storedLine = scnr.nextLine();
//
//		if (storedLine.contains("#")) {
//			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
//		}
//		storedLine = storedLine.trim();
//
//		do {
//			if (storedLine.contains(" ")) {
//				g1.thresholds.add((Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')))));
//				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
//
//				if (storedLine.charAt(0) == ' ')
//					throw new GradeFileFormatException();
//
//			} else {
//				g1.thresholds.add(Double.parseDouble(storedLine.substring(0)));
//				storedLine = "";
//			}
//		} while (storedLine.length() > 0);
//

		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.assignmentNames.add(storedLine.substring(0, storedLine.indexOf(' ')));

				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.assignmentNames.add(storedLine.substring(0));
				storedLine = "";
			}
		} while (storedLine.length() > 0);


		storedLine = scnr.nextLine();

		if (storedLine.contains("#")) {
			storedLine = storedLine.substring(0, storedLine.indexOf('#'));
		}
		storedLine = storedLine.trim();

		do {
			if (storedLine.contains(" ")) {
				g1.assignmentWeight.add((Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')))));

				storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);

				if (storedLine.charAt(0) == ' ')
					throw new GradeFileFormatException();

			} else {
				g1.assignmentWeight.add(Double.parseDouble(storedLine.substring(0)));
				storedLine = "";
			}
		} while (storedLine.length() > 0);


		while (scnr.hasNextLine()) {
			storedLine = scnr.nextLine();
			String n = storedLine.substring(0, storedLine.indexOf(' '));
			storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
			double pE = Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')));
			storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
			double pP = Double.parseDouble(storedLine.substring(0, storedLine.indexOf(' ')));
			storedLine = storedLine.substring(storedLine.indexOf(' ') + 1);
			Score a = new Score(n, pE, pP);
			g1.scores.add(a);
		}

		return g1;

	}

	public String getEstimateReport() {
		String estimateReport = null;
		String temp;
		double weightedAverageScore = 0;

		for (int i = 0; i < scores.size(); i++) {
			if (itr2.next().getCategory().equals(Character.toString(itr1.next().getName().charAt(0)))) {
				temp = (scores.get(i).getName() + "   " + String.format("%.2f", scores.get(i).getPercent()));
				if (estimateReport == null) {
					estimateReport = temp;
				} else {
					estimateReport = estimateReport + "\n" + temp;
				}
			}

		}
		estimateReport = estimateReport + "\n" + "Grade estimate is based on " + scores.size() + " scores";
		
		
//		for(int j=0;j<scores.size();j++){
//			if (itr2.next().getCategory().equals(Character.toString(itr1.next().getName().charAt(0)))){
//				System.out.println("eyyrwg");
//			}
//			
//			
//		}

		// ScoreIterator itr1 = new ScoreIterator(scores);
		// ScoreIterator itr2 = new ScoreIterator(scores);
		// ScoreIterator itr3 = new ScoreIterator(scores);
		// for (int j = 0; j < assignmentWeight.size(); j++) {
		//
		// while(itr1.hasNext() && itr2.hasNext()) {
		// while(itr2.next().getCategory()==(Character.toString(itr1.next().getName().charAt(0))))
		// {
		//
		// weightedAverageScore = (Double.parseDouble(String.format("%7.2f",
		// itr3.next().getPercent())));
		// }
		//
		//
		//
		// estimateReport = estimateReport +"\n" + weightedAverageScore;
		//
		// }
		//
		// }

		return estimateReport;

	}

}
