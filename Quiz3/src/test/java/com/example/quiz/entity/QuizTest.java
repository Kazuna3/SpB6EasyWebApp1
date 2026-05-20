package com.example.quiz.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuizTest {

	@Test
	@DisplayName("デフォルトコンストラクタとGetter Method の単体テスト")
	void noArgsConstructorAndGetterMethodTest() {

		Quiz quiz = new Quiz();

		assertEquals(quiz.getId(), null);
		assertEquals(quiz.getQuestion(), null);
		assertEquals(quiz.getAnswer(), null);
		assertEquals(quiz.getAuthor(), null);

	}

	@Test
	@DisplayName("オールコンストラクタとGetter Method の単体テスト")
	void allArgsConstructorAndGetterMethodTest() {

		Quiz quiz = new Quiz(4, "質問", false, "著者");

		assertEquals(quiz.getId(), 4);
		assertEquals(quiz.getQuestion(), "質問");
		assertEquals(quiz.getAnswer(), false);
		assertEquals(quiz.getAuthor(), "著者");

	}

	@Test
	@DisplayName("デフォルトコンストラクタとSetter Method の単体テスト")
	void noArgsConstructorAndSetterMethodTest() {

		Quiz quiz = new Quiz();

		quiz.setId(4);
		quiz.setQuestion("質問");
		quiz.setAnswer(false);
		quiz.setAuthor("著者");

		assertEquals(quiz.getId(), 4);
		assertEquals(quiz.getQuestion(), "質問");
		assertEquals(quiz.getAnswer(), false);
		assertEquals(quiz.getAuthor(), "著者");

	}

	@Test
	@DisplayName("equals Method の単体テスト")
	void equalsTest() {

		Quiz quiz1 = new Quiz();
		Quiz quiz2 = quiz1;

		quiz1.setId(4);
		quiz1.setQuestion("質問");
		quiz1.setAnswer(false);
		quiz1.setAuthor("著者");

		assertTrue(quiz1.equals(quiz2));
		assertEquals(quiz1, quiz2);

		Quiz quiz3 = new Quiz();

		assertFalse(quiz1.equals(quiz3));
		assertNotEquals(quiz1, quiz3);

	}

}
