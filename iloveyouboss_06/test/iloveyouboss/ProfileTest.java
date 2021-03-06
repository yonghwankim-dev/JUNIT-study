package iloveyouboss;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import iloveyouboss.Answer;
import iloveyouboss.Bool;
import iloveyouboss.BooleanQuestion;
import iloveyouboss.Criteria;
import iloveyouboss.Criterion;
import iloveyouboss.Profile;
import iloveyouboss.Question;
import iloveyouboss.Weight;

public class ProfileTest {
	private Profile profile;
	private BooleanQuestion question;
	private Criteria criteria;
	
	// @Befroe 애노테이션 적용
	// @Test 애노테이션이 설정된 테스트 메서드들의 공통된 로직
	@Before
	public void create() {
		profile = new Profile("Bull Hockey, Inc.");
		question = new BooleanQuestion(1, "Got bonuses?");
		criteria = new Criteria();
	}
	
//	테스트 코드 1, @Before 애노테이션 적용 및 지역변수 인라인 적용 after
	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		profile.add(new Answer(question, Bool.FALSE));
		
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));
		
		boolean matches = profile.matches(criteria);
		
		assertFalse(matches);
	}
	
//	테스트 코드 2, @Before 애노테이션 적용 및 지역변수 인라인 적용 after
	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));	
		
		// 실행
		boolean matches = profile.matches(criteria);
		
		// 단언
		assertTrue(matches);	
	}
	
	
//	테스트 코드 1, @Before 애노테이션 적용 및 지역변수 인라인 적용 before
//	@Test
//	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
//		Profile profile = new Profile("Bull Hockey, Inc.");
//		Question question = new BooleanQuestion(1, "Got bonuses?");
//		Criteria criteria = new Criteria();
//		
//		Answer profileAnswer = new Answer(question, Bool.FALSE);
//		profile.add(profileAnswer);
//		
//		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
//		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
//		criteria.add(criterion);
//		
//		boolean matches = profile.matches(criteria);
//		
//		assertFalse(matches);
//	}
	
//	테스트 코드 2, @Before 애노테이션 적용 및 지역변수 인라인 적용 before
//	@Test
//	public void matchAnswersTrueForAnyDontCareCriteria() {
//		// 준비
//		Profile profile = new Profile("Bull Hockey, Inc.");
//		Question question = new BooleanQuestion(1, "Got milk?");
//		Criteria criteria = new Criteria();
//		Answer profileAnswer = new Answer(question, Bool.FALSE);
//		
//		profile.add(profileAnswer);	
//		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
//		Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
//		criteria.add(criterion);	
//		
//		// 실행
//		boolean matches = profile.matches(criteria);
//		
//		// 단언
//		assertTrue(matches);	
//	}
	
//	테스트 코드3, Criteria 인스턴스가 Criterion 객체를 포함하지 않을때
	@Test
	public void notContainCriterionForCriteria(){
		// 준비
		// 없음
		
		// 실행 : Criterion 객체가 포함되지 않고 매치 수행
		boolean matches = profile.matches(criteria);
		
		// 단언 : false
		assertFalse(matches);	
	}
	
//	테스트 코드4, answer.get()에서 반환된 Answer 객체가 null일 때
	@Test(expected = NullPointerException.class)
	public void answerIsNullWhenAnswerGet(){
		// 준비
		Question question2 = new BooleanQuestion(2, "not Got bonuses?");
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(question2, Bool.TRUE), Weight.MustMatch));
		
		// 실행 : profile에 존재하지 않은 criterion 객체 설정후 수행
		// 단언 : NullPointerException 
		boolean matche = profile.matches(criteria);
		
	}
	
//	테스트 코드5, criterion.getAnswer()의 반환값이 null일때
	@Test(expected = NullPointerException.class)
	public void criterionGetAnswerIsNull(){
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(null, Weight.MustMatch));
		
		// 실행 : criterion.getAnswer()의 반환값이 null이 되도록 수행
		// 단언 : NullPointerException 
		boolean matche = profile.matches(criteria);
	}
	
//	테스트 코드6, criterion.getAnswer().getQuestionText()의 반환값이 null일때
	@Test(expected = NullPointerException.class)
	public void criterionGetAnswerGetQuestionTextIsNull(){
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(null,Bool.TRUE), Weight.MustMatch));
		
		// 실행 : criterion.getAnswer().getQuestionText()의 반환값이 null일때 수행
		// 단언 : NullPointerException 
		boolean matche = profile.matches(criteria);
	}

//	테스트 코드7, criterion.getWeight()의 반환값이 Weight.DontCare여서 match 변수가 true일때
	@Test
	public void criterionGetWeightIsDontCare(){
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(question,Bool.TRUE), Weight.DontCare));
		
		// 실행 : profile 대답은 false, criterion 대답은 true, 하지만 가중치(Weight)는 DontCare, 매치수행
		boolean matche = profile.matches(criteria);
		
		// 단언 : true
		assertTrue(matche);
	}
	
	
//	테스트 코드8, 두 조건문이 모두 false여서 결과적으로 match 변수가 false가 될때
	@Test
	public void twoConditionIsAllFalse(){
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(question,Bool.TRUE), Weight.MustMatch));
		
		// 실행 : profile 대답은 false, criterion 대답은 true, 무조건 일치일때 매치 수행
		boolean matche = profile.matches(criteria);
		
		// 단언 : false
		assertFalse(matche);
	}
	
//	테스트 코드9, match 변수가 false이고 criterion.getWeight()가 Weight.MustMatch여서 kill 변수가 true일 때
	@Test
	public void matchIsFalseAndCriterionGetWeightIsMustMatch(){
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(question,Bool.TRUE), Weight.MustMatch));
		
		// 실행 : profile 대답은 false, criterion 대답은 true, 무조건 일치일때 매치 수행
		boolean matche = profile.matches(criteria);
		
		// 단언 : false
		assertFalse(matche);
	}
	
//	테스트 코드10, match 변수가 true이기 때문에 kill 변수가 변하지 않을때
	@Test
	public void matchIsTrue(){
		// 준비
		profile.add(new Answer(question, Bool.TRUE));	
		criteria.add(new Criterion(new Answer(question,Bool.TRUE), Weight.MustMatch));
		
		// 실행 : profile 대답은 true, criterion 대답은 true, 무조건 일치일때 매치 수행
		boolean matche = profile.matches(criteria);
		
		// 단언 : true
		assertTrue(matche);
	}
	
//	테스트 코드11, criterion.getWeight()가 Weight.MustMatch가 아니기 때문에 kill 변수가 변하지 않을때
	@Test
	public void matchIsTrueAndCriterionGetWeightIsMustMatch(){
		// 준비
		profile.add(new Answer(question, Bool.FALSE));	
		criteria.add(new Criterion(new Answer(question,Bool.TRUE), Weight.DontCare));
		
		// 실행 : profile 대답은 false, criterion 대답은 true, MustMatch가 아닐때 수행
		boolean matche = profile.matches(criteria);
		
		// 단언 : true
		assertTrue(matche);
	}
	
	
}
