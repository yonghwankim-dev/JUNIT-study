package practice;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class StudentScoreCalculatorTest {

	// 각각의 학생의 4과목에 점수를 입력후 4과목의 평균들의 평균을 구한다.
	@Test
	public void answerArithmeticMeanOfForNumbers() {
		StudentScoreCalculator cal = new StudentScoreCalculator();
		
		Student student1 = new Student("kim");
		student1.add(100);
		student1.add(100);
		student1.add(100);
		student1.add(100);
		
		Student student2 = new Student("lee");
		student2.add(90);
		student2.add(90);
		student2.add(90);
		student2.add(90);
		
		cal.add(student1);
		cal.add(student2);
		
		int actualResult = cal.arithmeticMean();
		
		assertThat(actualResult, equalTo(95));
	}

}
