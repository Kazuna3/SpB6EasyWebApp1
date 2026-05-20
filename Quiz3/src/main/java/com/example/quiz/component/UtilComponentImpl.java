package com.example.quiz.component;

import org.springframework.stereotype.Component;

import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;

@Component
public class UtilComponentImpl implements UtilComponent {

	// Entity(DomainObject) を Form に詰め替える。
	// Refill Entity to Form.
	@Override
	public QuizForm entityToForm(Quiz quiz) {

		QuizForm quizForm = new QuizForm();
		quizForm.setId(quiz.getId());
		quizForm.setQuestion(quiz.getQuestion());
		quizForm.setAnswer(quiz.getAnswer());
		quizForm.setAuthor(quiz.getAuthor());

		return quizForm;

	}

	// Form を Entity(DomainObject) に詰め替える。
	// Repackage Form to Entity.
	@Override
	public Quiz formToEntity(QuizForm quizForm) {

		Quiz quiz = new Quiz(
		// @formatter:off
				quizForm.getId()
			,	quizForm.getQuestion()
			,	quizForm.getAnswer()
			,	quizForm.getAuthor());
		// @formatter:on
		return quiz;

	}

}