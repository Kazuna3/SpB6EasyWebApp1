package com.example.quiz.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.quiz.component.UtilComponent;
import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;

// 最重要。テスト対象のサーバを起動して、Controller の
// テストを行えるようにするアノテーション。
@SpringBootTest
// @RequiredArgsConstructor
public class QuizControllerTest {

	// QuizController で QuizService に Mock Object を DI する。
	@MockBean
	private QuizService quizService;

	private MockMvc mockMvc;

	@Autowired
	UtilComponent utilComponent;

	@Autowired
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void setup() {

		// @AutoConfigureMockMvc というアノテーションを使うと、この初期化は不要だが、
		// 問題が起きることもあるので、手動で初期化している。
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		System.out.println("■初期セットアップ処理完了！");

	}

	@Test
	@DisplayName("get method で /quiz がリクエストされた場合")
	void showListTest() throws Exception {

		QuizForm quizForm = new QuizForm();

		// setUpForm method で設定されている値を再現する。
		quizForm.setAnswer(true);

		// showList method で設定されている値を再現する。
		quizForm.setNewQuiz(true);

		Iterable<Quiz> iterableQuiz = new ArrayList<Quiz>() {

			{

				add(new Quiz(1, "質問の内容1", true, "質問に対する回答1"));
				add(new Quiz(2, "質問の内容2", false, "質問に対する回答2"));
				add(new Quiz(3, "質問の内容3", true, "質問に対する回答3"));

			}

		};

		when(quizService.selectAll()).thenReturn(iterableQuiz);
		// doReturn(iterableQuiz).when(quizService).selectAll();

		// get method で /quiz がリクエストされた場合のテストを行う
		this.mockMvc.perform(get("/quiz"))

				// setUpForm method に @ModelAttribute が付与されている為、次の確認を行う。
				// model に quizForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("quizForm"))

				// model の quizForm の値が、quizForm と同一か確認する。
				.andExpect(model().attribute("quizForm", quizForm))

				// model に list という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("list"))

				// model の list の値が、iterableQuiz と同一か確認する。
				.andExpect(model().attribute("list", iterableQuiz))

				// model に title という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("title"))

				// model の title の値が、"登録用フォーム" と同一か確認する。
				.andExpect(model().attribute("title", "登録用フォーム"))

				// メソッドの戻り値が、crud か確認する。
				.andExpect(view().name("crud"));

		verify(quizService, times(1)).selectAll();

	}

	@Test
	@DisplayName("post method で /quiz/insert がリクエストされた場合")
	void insertTest() throws Exception {

		/////////////////////////////////////////////
		// ■テストケース１
		QuizForm quizForm = new QuizForm();
		quizForm.setId(1);
		quizForm.setQuestion("5 × 3 は、20 です。");
		quizForm.setAnswer(false);
		quizForm.setAuthor("鈴木　一郎");
		quizForm.setNewQuiz(null);

		this.mockMvc.perform(
				post("/quiz/insert")
						/*
						.param("id", "1")
						.param("question", "5 × 3 は、20 です。")
						.param("answer", "false")
						.param("author", "鈴木　一郎"))
						 */

						// 次行は上のコメントブロック部より、厳密な設定ができる実装方法である。 
						.flashAttr("quizForm", quizForm))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/quiz"))
				.andExpect(flash().attribute("complete", "登録が完了しました。"))
				.andExpect(view().name("redirect:/quiz"));

		Quiz quiz = utilComponent.formToEntity(quizForm);

		verify(quizService, times(1)).insertQuiz(quiz);

		/////////////////////////////////////////////
		// ■テストケース２
		quizForm = new QuizForm();
		quizForm.setId(null);
		quizForm.setQuestion("5 × 3 は、20 です。");
		quizForm.setAnswer(false);
		quizForm.setAuthor("鈴木　一郎");
		quizForm.setNewQuiz(null);

		this.mockMvc.perform(
				post("/quiz/insert")
						/*
						.param("id", "")
						.param("question", "5 × 3 は、20 です。")
						.param("answer", "false")
						.param("author", "鈴木　一郎"))
						 */

						// 次行は上のコメントブロック部より、厳密な設定ができる実装方法である。 
						.flashAttr("quizForm", quizForm))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/quiz"))
				.andExpect(flash().attribute("complete", "登録が完了しました。"))
				.andExpect(view().name("redirect:/quiz"));

		quiz = utilComponent.formToEntity(quizForm);

		verify(quizService, times(1)).insertQuiz(quiz);

		/////////////////////////////////////////////
		// ■テストケース３
		quizForm = new QuizForm();
		quizForm.setId(null);
		quizForm.setQuestion("");
		quizForm.setAnswer(false);
		quizForm.setAuthor("鈴木　一郎");
		quizForm.setNewQuiz(null);

		this.mockMvc.perform(
				post("/quiz/insert")
						/*
						.param("id", "")
						.param("question", "")
						.param("answer", "false")
						.param("author", "鈴木　一郎"))
						 */

						// 次行は上のコメントブロック部より、厳密な設定ができる実装方法である。 
						.flashAttr("quizForm", quizForm))
				.andExpect(status().isOk())
				.andExpect(view().name("crud"));

		quiz = utilComponent.formToEntity(quizForm);

		verify(quizService, times(0)).insertQuiz(quiz);

	}

	@Test
	@DisplayName("get method で /quiz/{id} がリクエストされた場合")
	void showUpdateTest() throws Exception {

		/////////////////////////////////////////////
		// ■テストケース１
		// @PathVariable の id を持つレコードが存在しなかった場合のテスト。

		Optional<Quiz> optQuiz = Optional.empty();

		when(quizService.selectOneById(100)).thenReturn(optQuiz);
		// doReturn(optQuiz).when(quizService).selectOneById(100);

		this.mockMvc.perform(
				get("/quiz/100")
						.param("id", "100"))
				.andExpect(flash().attribute("complete", "更新対象のデータが無くなった！"))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/quiz"));

		verify(quizService, times(1)).selectOneById(100);

		/////////////////////////////////////////////
		// ■テストケース２
		// @PathVariable の id を持つレコードが存在した場合のテスト。

		Quiz quiz = new Quiz(3, "英語で３月は、March です。", true, "木村美穂");
		optQuiz = Optional.of(quiz);

		when(quizService.selectOneById(3)).thenReturn(optQuiz);
		// doReturn(optQuiz).when(quizService).selectOneById(3);

		QuizForm quizForm = utilComponent.entityToForm(quiz);
		quizForm.setNewQuiz(false);

		this.mockMvc.perform(
				get("/quiz/3")
						.param("id", "3"))
				.andExpect(model().attribute("quizForm", quizForm))
				.andExpect(model().attribute("id", quizForm.getId()))
				.andExpect(model().attribute("title", "更新用フォーム"))
				.andExpect(status().isOk())
				.andExpect(view().name("crud"));

		verify(quizService, times(1)).selectOneById(3);

	}

	@Test
	@DisplayName("画面「更新用フォーム」で、クイズのデータを編集し、ボタン「送信」が押下された場合")
	void updateTest() throws Exception {

		/////////////////////////////////////////////
		// ■テストケース１
		// 入力項目の単項目チェックでエラーがなかった場合。

		QuizForm quizForm = new QuizForm();
		quizForm.setId(6);
		quizForm.setQuestion("5 × 3 は、20 です。");
		quizForm.setAnswer(false);
		quizForm.setAuthor("岡田さやか");
		quizForm.setNewQuiz(null);

		Quiz quiz = utilComponent.formToEntity(quizForm);

		// when(quizService.updateQuiz(quiz)).thenReturn(void);
		// doReturn(void).when(quizService).updateQuiz(quiz);
		doNothing().when(quizService).updateQuiz(quiz);

		this.mockMvc.perform(
				post("/quiz/update")
						/*
						 * コメントブロックでコメントアウトした書き方の場合、①でnullをセットしたいが失敗する。
						 * しかし、②の書き方であれば成功する。
						.param("id", "6")
						.param("question", "5 × 3 は、20 です。")
						.param("answer", "false")
						.param("author", "岡田さやか")
						.param("newQuiz", "null"))	// ①
						 */

						.flashAttr("quizForm", quizForm)) // ②
				.andExpect(status().isFound())
				.andExpect(flash().attribute("complete", "更新が完了しました。"))
				.andExpect(redirectedUrl("/quiz/" + quiz.getId()))
				.andExpect(view().name("redirect:/quiz/" + quiz.getId()));

		verify(quizService, times(1)).updateQuiz(quiz);

		/////////////////////////////////////////////
		// ■テストケース２
		// 入力項目の単項目チェックでエラーがあった場合。
		quizForm = new QuizForm();
		quizForm.setId(6);
		quizForm.setQuestion("5 × 3 は、20 です。");
		quizForm.setAnswer(false);
		quizForm.setAuthor("");
		quizForm.setNewQuiz(false);

		quiz = utilComponent.formToEntity(quizForm);

		this.mockMvc.perform(
				post("/quiz/update")
						.flashAttr("quizForm", quizForm))
				.andExpect(status().isOk())
				.andExpect(model().attribute("quizForm", quizForm))
				.andExpect(model().attribute("id", quizForm.getId()))
				.andExpect(model().attribute("title", "更新用フォーム"))
				.andExpect(view().name("crud"));

	}

	@Test
	@DisplayName("ボタン「削除」の押下で起動する、クイズデータの削除処理のテスト")
	void deleteTest() throws Exception {

		doNothing().when(quizService).deleteQuizById(5);

		this.mockMvc.perform(
				post("/quiz/delete")
						.param("id", "5"))
				.andExpect(status().isFound())
				.andExpect(flash().attribute("delcomplete", "削除が完了しました。"))
				.andExpect(redirectedUrl("/quiz"))
				.andExpect(view().name("redirect:/quiz"));

		verify(quizService, times(1)).deleteQuizById(5);

	}

	@Test
	@DisplayName("Quiz データをランダムで１件取得し、プレイ用画面に表示するロジックのテスト")
	void showQuizTest() throws Exception {

		/////////////////////////////////////////////
		// ■テストケース１
		// 主題するクイズの抽出に成功した場合のテスト

		Quiz quiz = new Quiz(3, "英語で３月は、March です。", true, "木村美穂");
		Optional<Quiz> optQuiz = Optional.of(quiz);

		QuizForm quizForm = utilComponent.entityToForm(quiz);

		when(quizService.selectOneRandomQuiz()).thenReturn(optQuiz);
		// doReturn(optQuiz).when(quizService).selectOneRandomQuiz();

		this.mockMvc.perform(
				get("/quiz/play"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("quizForm", quizForm))
				.andExpect(view().name("play"));

		verify(quizService, times(1)).selectOneRandomQuiz();

		/////////////////////////////////////////////
		// ■テストケース２
		// 主題するクイズの抽出に失敗した場合のテスト

		optQuiz = Optional.empty();

		when(quizService.selectOneRandomQuiz()).thenReturn(optQuiz);
		// doReturn(optQuiz).when(quizService).selectOneRandomQuiz();

		this.mockMvc.perform(
				get("/quiz/play"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("msg", "問題がありません・・・"))
				.andExpect(view().name("play"));

		verify(quizService, times(2)).selectOneRandomQuiz();

	}

	@Test
	@DisplayName("クイズのプレイ画面のテスト")
	void checkQuizTest() throws Exception {

		/////////////////////////////////////////////
		// ■テストケース１
		// クイズに正解した場合のテスト。
		QuizForm quizForm = new QuizForm();
		quizForm.setId(100);
		quizForm.setQuestion("英語で１月は、January です。");
		quizForm.setAnswer(true);
		quizForm.setAuthor("加藤　智子");
		quizForm.setNewQuiz(null);

		when(quizService.checkQuiz(100, true)).thenReturn(true);
		// doReturn(true).when(quizService).checkQuiz(100, true);

		mockMvc.perform(
				post("/quiz/check")
						.flashAttr("quizForm", quizForm)
						.param("answer", "true"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("msg", "正解です！"))
				.andExpect(view().name("answer"));

		verify(quizService, times(1)).checkQuiz(100, true);

		/////////////////////////////////////////////
		// ■テストケース２
		// クイズに不正解した場合のテスト。
		when(quizService.checkQuiz(100, true)).thenReturn(false);
		// doReturn(false).when(quizService).checkQuiz(100, true);

		mockMvc.perform(
				post("/quiz/check")
						.flashAttr("quizForm", quizForm)
						.param("answer", "false"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("msg", "残念、不正解です・・・"))
				.andExpect(view().name("answer"));

		verify(quizService, times(1)).checkQuiz(100, true);

	}

}
