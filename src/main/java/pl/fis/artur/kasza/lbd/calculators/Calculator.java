package pl.fis.artur.kasza.lbd.calculators;

import java.util.ArrayList;
import java.util.HashMap;

import pl.fis.artur.kasza.lbd.models.Survey;

public interface Calculator {
	public HashMap<String, ArrayList<Double>> calculate(ArrayList<Survey> surveys);
}
