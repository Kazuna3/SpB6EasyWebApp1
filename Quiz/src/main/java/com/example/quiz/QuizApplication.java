package com.example.quiz;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;

@SpringBootApplication
public class QuizApplication {

	/** 起動メソッド */
	public static void main(String[] args) {

		// SpringApplication.run(QuizApplication.class, args);

		SpringApplication.run(QuizApplication.class, args)
				.getBean(QuizApplication.class).execute();

	}

	/** 注入 */
	@Autowired
	QuizRepository repository;

	/** 実行メソッド */
	private void execute() {

		// 登録処理
		// setup();

		// 全件取得
		showList();

		// １件取得
		// showOne();

		// 更新処理
		// updateQuiz();

		// 削除処理
		// deleteQuiz();

		// 全レコードの削除処理 
		// deleteAllQuiz();

	}

	/** === クイズを 2 件登録します === */
	@SuppressWarnings("unused")
	private void setup() {

		// エンティティ生成
		Quiz quiz1 = new Quiz(null, "「Spring」はフレームワークですか？", true, "登録 太郎");

		// 登録実行
		quiz1 = repository.save(quiz1);

		// 登録確認
		System.out.println("登録したデータは、" + quiz1 + "です。");

		// エンティティ生成
		Quiz quiz2 = new Quiz(null, "「Spring MVC」はバッチ処理機能を提供しますか？",
				false, "登録 太郎");

		// 登録実行
		quiz2 = repository.save(quiz2);

		// 登録確認
		System.out.println("登録したデータは、" + quiz2 + "です。");

	}

	/** === 全件取得 === */
	// @SuppressWarnings("unused")
	private void showList() {

		System.out.println("--- 全件取得開始 ---");
		// リポジトリを使用して全件取得を実施、結果を取得
		Iterable<Quiz> quizzes = repository.findAll();

		//		for (Quiz quiz : quizzes) {
		//
		//			System.out.println(quiz);
		//
		//		}

		// quizzes.forEach(s -> System.out.println(s));
		quizzes.forEach(System.out::println);

		System.out.println("--- 全件取得完了 ---");

	}

	/** === １件取得 === */
	@SuppressWarnings("unused")
	private void showOne() {

		System.out.println("--- １件取得開始 ---");

		// リポジトリを使用して１件取得を実施、結果を取得（戻り値は Optional）
		Optional<Quiz> quizOpt = repository.findById(1);

		// 値存在チェック
		if (quizOpt.isPresent()) {

			System.out.println(quizOpt.get());

		} else {

			System.out.println("該当する問題が存在しません・・・");

		}

		System.out.println("--- １件取得完了 ---");

	}

	/** === 更新処理 === */
	@SuppressWarnings("unused")
	private void updateQuiz() {

		System.out.println("--- 更新処理開始 ---");

		// 変更したいエンティティを生成する
		Quiz quiz1 = new Quiz(1, "「スプリング」はフレームワークですか？", true, "変更 タロウ");

		// 更新実行
		quiz1 = repository.save(quiz1);

		// 更新確認
		System.out.println("更新したデータは、" + quiz1 + "です。");
		System.out.println("--- 更新処理完了 ---");

	}

	/** === 削除処理 === */
	@SuppressWarnings("unused")
	private void deleteQuiz() {

		System.out.println("--- 削除処理開始 ---");
		// 削除実行
		repository.deleteById(2);
		System.out.println("--- 削除処理完了 ---");

	}

	/** === 全レコードの削除処理 === */
	@SuppressWarnings("unused")
	private void deleteAllQuiz() {

		System.out.println("--- 削除処理開始 ---");
		// 削除実行
		repository.deleteAll();
		System.out.println("--- 削除処理完了 ---");

	}

}