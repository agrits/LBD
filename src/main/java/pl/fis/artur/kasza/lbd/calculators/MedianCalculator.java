package pl.fis.artur.kasza.lbd.calculators;

import java.util.ArrayList;
import java.util.HashMap;

import pl.fis.artur.kasza.lbd.models.Survey;
import pl.fis.artur.kasza.lbd.qualifiers.Median;

@Median
public class MedianCalculator implements Calculator {

	
	//TODO refactor from average to median :)
	public HashMap<String, ArrayList<Double>> calculate(ArrayList<Survey> surveys){
		HashMap<String, ArrayList<Integer>> scores = new HashMap<String, ArrayList<Integer>>();
		HashMap<String, ArrayList<Double>> medians = new HashMap<String, ArrayList<Double>>();
		for(Survey survey : surveys) {
			ArrayList<Integer> answers = survey.getAnswers();
			ArrayList<Integer> scoreList;
			if(scores.containsKey(survey.getUniversity())) {
				scoreList = scores.get(survey.getUniversity());
				for(int i = 0; i<3; i++) {
					scoreList.set(i, scoreList.get(i)+answers.get(i));
				}
				//3rd index holds total number of surveys for given university
				scoreList.set(3, scoreList.get(3)+1);
			}
			else {
				scoreList = new ArrayList<Integer>();
				for(int i = 0; i<3; i++) {
					scoreList.add(answers.get(i));
				}
				scoreList.add(1);
			}
			scores.put(survey.getUniversity(), scoreList);
		}
		for(String university : scores.keySet()) {
			ArrayList<Double> medianList = new ArrayList<Double>();
			double sum = 0;
			for(int i = 0; i<3; i++) {
				sum += scores.get(university).get(i)*1.0/scores.get(university).get(3);
				medianList.add(scores.get(university).get(i)*1.0/scores.get(university).get(3));
			}
			medianList.add(sum/3);
			medians.put(university, medianList);
		}
		return medians;
	}

}
