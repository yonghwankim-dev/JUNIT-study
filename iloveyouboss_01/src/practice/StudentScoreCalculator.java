package practice;

import java.util.ArrayList;
import java.util.List;

public class StudentScoreCalculator {
	private List<Student> students = new ArrayList<Student>();
	
	public void add(Student student)
	{
		students.add(student);
	}
	
	public int arithmeticMean()
	{
		int sum = 0;
		for(Student s : students)
		{
			List<Integer> scores = s.getScores();
			int total = scores.stream().reduce(0, Integer::sum);
			sum += total / scores.size();	// 각각의 학생 평균 누적
		}
		return sum / students.size();
	}
}
