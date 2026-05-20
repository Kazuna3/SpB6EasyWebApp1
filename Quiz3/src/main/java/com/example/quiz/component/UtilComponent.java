package com.example.quiz.component;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;

public interface UtilComponent {

	// Entity を Form に詰め替える。
	// Refill Entity to Form.
	public QuizForm entityToForm(Quiz quiz);

	// Form を Entity に詰め替える。
	// Repackage Form to Entity.
	public Quiz formToEntity(QuizForm quizForm);

}
