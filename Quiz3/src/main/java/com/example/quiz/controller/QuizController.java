package com.example.quiz.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.component.UtilComponent;
import com.example.quiz.entity.Quiz;
import com.example.quiz.form.QuizForm;
import com.example.quiz.service.QuizService;

import lombok.RequiredArgsConstructor;

/** Quizコントローラー */
@Controller
@RequiredArgsConstructor
// @RequestMapping("/quiz")
public class QuizController {

	/** DI対象 */
	private final QuizService service;
	private final UtilComponent utilComponent;

	/** 「form-backing bean」の初期化 */
	/*
	 * 「バリデーション」を行う時は、「form-backingbean」の設定が必要になる。
	 * HTMLの「formタグ」にバインドする「Formクラス」インスタンスの事を
	 * 「form-backingbean」と呼び、「@ModelAttribute」アノテーションを使うことで
	 * 結びつけることができる。
	 * 「form-backingbean」の初期化は、「@ModelAttribute」アノテーションを
	 * 付与したメソッドで作成する。作成方法は「@ModelAttribute」アノテーションを
	 * 付与して、HTMLの「formタグ」にバインドしたい「Formクラス」を
	 * 初期化して戻り値で返す。「@ModelAttribute」アノテーションが
	 * 付与されたメソッドは、このクラスのリクエストハンドラメソッド実行前に呼ばれ、
	 * 「リクエストスコープ」で「Model」に格納される。
	 * 「Model」に格納される時、明示的に「名前」を付けなければ、格納する
	 * 「Formクラス」名の「ローワーキャメルケース」で「Model」に格納される。
	 */
	@ModelAttribute
	public QuizForm setUpForm() {

		QuizForm form = new QuizForm();
		// ラジオボタンのデフォルト値設定
		form.setAnswer(true);
		return form;

	}

	/** Quizの一覧を表示します */
	// 次のURLで、リクエストハンドラメソッドである showList が呼び出される。
	// http://localhost:8080/quiz
	@GetMapping("/quiz")
	public String showList(QuizForm quizForm, Model model) {

		//新規登録設定
		quizForm.setNewQuiz(true);
		//掲示板の一覧を取得する
		Iterable<Quiz> list = service.selectAll();
		// 表示用「Model」への格納
		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		return "crud";

	}

	/** Quizデータを1件挿入 */
	@PostMapping("/quiz/insert")
	public String insert(
		@Validated QuizForm quizForm,
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes
	) {

		// 入力チェック
		if (bindingResult.hasErrors()) {

			// エラーがある場合は、一覧表示処理を呼びます
			return showList(quizForm, model);

		} else {

			Quiz quiz = utilComponent.formToEntity(quizForm);

			service.insertQuiz(quiz);

			/*
			 * RedirectAttributes を、本メソッドの引数に設定し、
			 * redirectAttributes.addFlashAttribute("complete", "登録が完了しました。");
			 * と記述することで、リダイレクト先に渡したい値を設定している。
			 * これは「FlashScope」という「スコープ」になり、1回のリダイレクトでのみ有効な「スコープ」となる。
			 */
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました。");
			return "redirect:/quiz";

		}

	}

	/** Quizデータを１件取得し、フォーム内に表示する */
	@GetMapping("/quiz/{id}")
	public String showUpdate(
		@PathVariable Integer id,
		Model model,
		RedirectAttributes redirectAttributes
	) {

		// Quizを取得(Optionalでラップ)
		Optional<Quiz> quizOpt = service.selectOneById(id);
		// QuizFormへの詰め直し
		Optional<QuizForm> quizFormOpt = quizOpt.map(t -> utilComponent.entityToForm(t));

		// QuizFormがnullでなければ中身を取り出す
		if (quizFormOpt.isPresent()) {

			QuizForm quizForm = quizFormOpt.get();

			// 更新用のModelを作成する
			makeUpdateModel(quizForm, model);
			return "crud";

		} else {

			/*
			 * RedirectAttributes を、本メソッドの引数に設定し、
			 * redirectAttributes.addFlashAttribute("...","...");
			 * と記述することで、リダイレクト先に渡したい値を設定している。
			 * これは「FlashScope」という「スコープ」になり、1回のリダイレクトでのみ有効な「スコープ」となる。
			 */
			redirectAttributes.addFlashAttribute("complete", "更新対象のデータが無くなった！");
			return "redirect:/quiz";

		}

	}

	/** 更新用のModelを作成する */
	private void makeUpdateModel(QuizForm quizForm, Model model) {

		quizForm.setNewQuiz(false);
		model.addAttribute("quizForm", quizForm);
		model.addAttribute("id", quizForm.getId());
		model.addAttribute("title", "更新用フォーム");

	}

	/** idをKeyにしてデータを更新する */
	@PostMapping("/quiz/update")
	public String update(
		@Validated QuizForm quizForm,
		BindingResult result,
		Model model,
		RedirectAttributes redirectAttributes
	) {

		// 入力チェック
		if (result.hasErrors()) {

			// 更新用のModelを作成する
			makeUpdateModel(quizForm, model);
			return "crud";

		} else {

			//QuizFormからQuizに詰め直す
			Quiz quiz = utilComponent.formToEntity(quizForm);

			service.updateQuiz(quiz);

			//更新処理、フラッシュスコープの使用、リダイレクト（個々の編集ページ）

			/*
			 * RedirectAttributes を、本メソッドの引数に設定し、
			 * redirectAttributes.addFlashAttribute("complete","登録が完了しました");
			 * と記述することで、リダイレクト先に渡したい値を設定している。
			 * これは「FlashScope」という「スコープ」になり、1回のリダイレクトでのみ有効な「スコープ」となる。
			 */
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました。");
			// 更新画面を表示する
			return "redirect:/quiz/" + quiz.getId();

		}

	}

	/** idをKeyにしてデータを削除する */
	@PostMapping("/quiz/delete")
	public String delete(
		@RequestParam("id") String id,
		Model model,
		RedirectAttributes redirectAttributes
	) {

		//タスクを1件削除してリダイレクト
		service.deleteQuizById(Integer.parseInt(id));

		/*
		 * RedirectAttributes を、本メソッドの引数に設定し、
		 * redirectAttributes.addFlashAttribute("complete","登録が完了しました");
		 * と記述することで、リダイレクト先に渡したい値を設定している。
		 * これは「FlashScope」という「スコープ」になり、1回のリダイレクトでのみ有効な「スコープ」となる。
		 */
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました。");
		return "redirect:/quiz";

	}

	// ---------- 【以下はクイズで遊ぶ機能】 ----------
	/** Quizデータをランダムで１件取得し、画面に表示する */
	@GetMapping("/quiz/play")
	public String showQuiz(
		Model model
	) {

		// Quiz を取得( Optional でラップ)
		Optional<Quiz> quizOpt = service.selectOneRandomQuiz();

		// 値が入っているか判定する
		if (quizOpt.isPresent()) {

			// QuizFormへの詰め直し
			Optional<QuizForm> quizFormOpt = quizOpt.map(t -> utilComponent.entityToForm(t));

			QuizForm quizForm = quizFormOpt.get();

			// 表示用「Model」への格納
			model.addAttribute("quizForm", quizForm);

		} else {

			model.addAttribute("msg", "問題がありません・・・");

		}

		return "play";

	}

	/** クイズの正解 不正解を判定する */
	@PostMapping("/quiz/check")
	public String checkQuiz(
		QuizForm quizForm,
		@RequestParam Boolean answer,
		Model model
	) {

		if (service.checkQuiz(quizForm.getId(), answer)) {

			model.addAttribute("msg", "正解です！");

		} else {

			model.addAttribute("msg", "残念、不正解です・・・");

		}

		return "answer";

	}

}