package practice;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private List<Integer> scores;
	
	public Student(String name){
		this.name = name;
		scores = new ArrayList<Integer>();
	}

	public void add(int score)
	{
		scores.add(score);	
	}

	public String getName() {
		return name;
	}

	public List<Integer> getScores() {
		return scores;
	}

}
