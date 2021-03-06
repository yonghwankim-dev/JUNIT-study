/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import java.util.*;

// 어떤 사람이 회사 혹은 구직자에게 물어볼 수 있는 적절한 질문에 대한 답변을 담고 있음
public class Profile { 
   private Map<String,Answer> answers = new HashMap<>();	// key : 질문, value : 대답(Answer 객체)
   private int score;	// 점수
   private String name;	// 회사 혹은 구직자

   public Profile(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }

   // Answer 객체 : 질문에 대한 true 값을 갖는 객체
   // add() : Answer 객체를 Profile에 추가합니다.
   public void add(Answer answer) { 
	   // 질문에 대한 답을 저장한다.
      answers.put(answer.getQuestionText(), answer);
   }
   
   // Criteria : 다수의 Criterion 객체를 담는 컨테이너
   // Criterion : 고용주가 구직자를 찾거나 그 반대를 의미, Answer 객체와 그 질문에 대한 가중치를 의미하는 Weight 객체를 캡슐화합니다.
   // matches() : Criteria 객체를 인자로 받아 각 Criterion에
   // 대해 반복문을 실행하여 해당 기준이 프로파일에 있는 답변과 맞는지 결정합니다.
   // 기준이 절대적이지만 정답과 맞지 않는다면 matches() 메서드는 false를 
   // 반환합니다. 그리고 프로파일에 맞는 기준이 없다면 flase를 반환합니다.
   // 그 외의 경우에는 true를 반환합니다.
   public boolean matches(Criteria criteria) { 
      score = 0;
      
      boolean kill = false;
      boolean anyMatches = false;
      
      for (Criterion criterion: criteria) {
    	 // Answer 객체는 Question 객체를 참조하고 그 대답에 대한 적절한 값을 포합합니다.
         Answer answer = answers.get(
               criterion.getAnswer().getQuestionText());
         boolean match = 
               criterion.getWeight() == Weight.DontCare || 
               answer.match(criterion.getAnswer());

         if (!match && criterion.getWeight() == Weight.MustMatch) {  
            kill = true;
         }
         if (match) {         
            score += criterion.getWeight().getValue();
         }
         anyMatches |= match;  
      }
      if (kill)       
         return false;
      return anyMatches; 
   }

   public int score() {
      return score;
   }
}
