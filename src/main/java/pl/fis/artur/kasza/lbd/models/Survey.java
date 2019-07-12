package pl.fis.artur.kasza.lbd.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Survey {
	private String firstName, lastName, university, faculty, degree;
	private ArrayList<Integer> answers = new ArrayList<Integer>();
	
	public Survey(String firstName, String lastName, String university, String faculty, String degree,
			ArrayList<String> answers) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.university = university;
		this.faculty = faculty;
		this.degree = degree;
		for(String answer : answers) {
			this.answers.add(Integer.parseInt(answer));
		}
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public ArrayList<Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Integer> answers) {
		this.answers = answers;
	}
	
	
	
	
	
}
