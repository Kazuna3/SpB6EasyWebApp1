package com.example.quiz.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;

public class QuizServiceImplTest {

	QuizRepository mockQuizRepository;
	QuizService quizService;

	@BeforeEach
	void setup() {

		// モックを作成
		mockQuizRepository = mock(QuizRepository.class);
		quizService = new QuizServiceImpl(mockQuizRepository);

	}

	@Test
	@DisplayName("selectAllメソッドの単体テスト")
	void selectAllTest() {

		// Iterable<Quiz>の要素が無い場合のテスト
		Iterable<Quiz> iterableQuiz1 = new ArrayList<>();

		when(mockQuizRepository.findAll()).thenReturn(iterableQuiz1);
		// doReturn(iterableQuiz1).when(mockQuizRepository).findAll();

		Iterable<Quiz> iterableQuiz2 = quizService.selectAll();

		assertEquals(iterableQuiz1, iterableQuiz2);
		verify(mockQuizRepository, times(1)).findAll();

		/////////////////////////////////////////////
		// Iterable<Quiz>の要素が３つの場合のテスト
		iterableQuiz1 = new ArrayList<>() {

			{

				add(new Quiz(1, "質問の内容1", true, "質問に対する回答1"));
				add(new Quiz(2, "質問の内容2", false, "質問に対する回答2"));
				add(new Quiz(3, "質問の内容3", true, "質問に対する回答3"));

			}

		};

		// 次行をコメントアウトするとテストが失敗する。理由は iterableQuiz が、空っぽのままになっている為である。
		when(mockQuizRepository.findAll()).thenReturn(iterableQuiz1);
		// doReturn(iterableQuiz1).when(mockQuizRepository).findAll();

		iterableQuiz1.forEach(q -> System.out.println(q));

		iterableQuiz2 = quizService.selectAll();

		iterableQuiz2.forEach(q -> System.out.println(q));

		assertEquals(iterableQuiz1, iterableQuiz2);
		verify(mockQuizRepository, times(2)).findAll();

	}

	@Test
	@DisplayName("selectOneByIdメソッドの単体テスト")
	void selectOneByIdTest() {

		// Optional<Quiz>の要素が、empty の場合のテスト
		Optional<Quiz> optQuiz1 = Optional.empty();

		when(mockQuizRepository.findById(123)).thenReturn(optQuiz1);
		// doReturn(optQuiz1).when(mockQuizRepository).findById(123);

		Optional<Quiz> optQuiz2 = quizService.selectOneById(123);

		assertEquals(optQuiz1, optQuiz2);
		verify(mockQuizRepository, times(1)).findById(123);

		/////////////////////////////////////////////
		// Optional<Quiz>の要素が、not empty の場合のテスト
		optQuiz1 = Optional.of(new Quiz(123, "質問の内容", true, "質問に対する回答"));

		when(mockQuizRepository.findById(123)).thenReturn(optQuiz1);
		// doReturn(optQuiz1).when(mockQuizRepository).findById(123);

		optQuiz2 = quizService.selectOneById(123);

		assertEquals(optQuiz1, optQuiz2);
		verify(mockQuizRepository, times(2)).findById(123);

	}

	@Test
	@DisplayName("selectOneRandomQuizメソッドの単体テスト")
	void selectOneRandomQuizTest() {

		// Optional<Quiz>の要素が、empty の場合のテスト
		Optional<Quiz> optQuiz1 = Optional.empty();

		when(mockQuizRepository.getRandomId()).thenReturn(null);
		// doReturn(null).when(mockQuizRepository).getRandomId();

		Optional<Quiz> optQuiz2 = quizService.selectOneRandomQuiz();

		assertEquals(optQuiz1, optQuiz2);
		verify(mockQuizRepository, times(1)).getRandomId();

		/////////////////////////////////////////////
		// Optional<Quiz>の要素が、not empty の場合のテスト
		optQuiz1 = Optional.of(new Quiz(123, "質問の内容", true, "質問に対する回答"));

		when(mockQuizRepository.getRandomId()).thenReturn(123);
		// doReturn(123).when(mockQuizRepository).getRandomId();

		when(mockQuizRepository.findById(123)).thenReturn(optQuiz1);
		// doReturn(optQuiz1).when(mockQuizRepository).findById(123);

		optQuiz2 = quizService.selectOneRandomQuiz();

		assertEquals(optQuiz1, optQuiz2);
		verify(mockQuizRepository, times(1)).findById(123);
		verify(mockQuizRepository, times(2)).getRandomId();

	}

	@Test
	@DisplayName("checkQuizメソッドの単体テスト")
	void checkQuizTest() {

		// Optional<Quiz>の要素が、empty の場合のテスト
		Optional<Quiz> optQuiz1 = Optional.empty();
		// quizService.checkQuiz メソッドの戻り値の想定値。
		Boolean bool1 = false;

		when(mockQuizRepository.findById(123)).thenReturn(optQuiz1);
		// doReturn(optQuiz1).when(mockQuizRepository).findById(123);

		Boolean bool2 = quizService.checkQuiz(123, true);

		assertEquals(bool1, bool2);
		verify(mockQuizRepository, times(1)).findById(123);

		/////////////////////////////////////////////
		// Optional<Quiz>の要素が、not empty で、checkQuiz の戻り値が true の場合のテスト
		optQuiz1 = Optional.of(new Quiz(123, "質問の内容", true, "質問に対する回答"));
		bool1 = true;

		when(mockQuizRepository.findById(123)).thenReturn(optQuiz1);
		// doReturn(optQuiz1).when(mockQuizRepository).findById(123);

		bool2 = quizService.checkQuiz(123, true);

		assertEquals(bool1, bool2);
		verify(mockQuizRepository, times(2)).findById(123);

		/////////////////////////////////////////////
		// Optional<Quiz>の要素が、not empty で、checkQuiz の戻り値が false の場合のテスト
		optQuiz1 = Optional.of(new Quiz(123, "質問の内容", true, "質問に対する回答"));
		bool1 = false;

		when(mockQuizRepository.findById(123)).thenReturn(optQuiz1);
		// doReturn(optQuiz1).when(mockQuizRepository).findById(123);

		bool2 = quizService.checkQuiz(123, false);

		assertEquals(bool1, bool2);
		verify(mockQuizRepository, times(3)).findById(123);

	}

	@Test
	@DisplayName("insertQuizメソッドの単体テスト")
	void insertQuizTest() {

		Quiz quiz = new Quiz(123, "質問の内容", true, "質問に対する回答");
		quizService.insertQuiz(quiz);

		verify(mockQuizRepository, times(1)).save(quiz);

	}

	@Test
	@DisplayName("updateQuizメソッドの単体テスト")
	void updateQuizTest() {

		Quiz quiz = new Quiz(123, "質問の内容", true, "質問に対する回答");
		quizService.updateQuiz(quiz);

		verify(mockQuizRepository, times(1)).save(quiz);

	}

	@Test
	@DisplayName("deleteQuizByIdメソッドの単体テスト")
	void deleteQuizByIdTest() {

		quizService.deleteQuizById(123);

		verify(mockQuizRepository, times(1)).deleteById(123);

	}

}
