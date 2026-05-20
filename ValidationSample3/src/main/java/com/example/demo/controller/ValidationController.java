package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.CalcForm;
import com.example.demo.validator.CalcValidator;

@Controller
public class ValidationController {

	/** インジェクション */
	@Autowired
	CalcValidator calcValidator;

	/** 相関チェック登録 */
	/*
	 * 「@InitBinder」アノテーションを付与したメソッドで「相関チェック」を登録します。
	 * 「@InitBinder」アノテーションには「チェック対象Form」クラスの「Model」での「識別名」を指定します。
	 * 「識別名」を指定しない場合、「Model」に格納されているすべての「オブジェクト」に対して適用され、対象外の場合例外になります。
	 * 「WebDataBinder」インターフェースの「addValidators」メソッドで「相関チェック」を登録することで「SpringMVC」から利用できます。
	 */
	@InitBinder("calcForm") // 左記のアノテーションの引数で、チェック対象の Form を指定する。
	public void initBinder(WebDataBinder webDataBinder) {

		// 次行の addValidators で、calcValidator と言う相関チェック処理を登録している。
		webDataBinder.addValidators(calcValidator);

	}

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
	public CalcForm setUpForm() {

		return new CalcForm();

	}

	// 次のURLで、リクエストハンドラメソッドである showView が呼び出される。
	// http://localhost:8080/show
	@GetMapping("show")
	public String showView() {

		// 戻り値は「ビュー名」を返す。
		return "entry";

	}

	/** 確認画面を表示する：Form クラス使用 */
	@PostMapping("calc")
	public String confirmView(
		@Validated CalcForm form,
		BindingResult bindingResult,
		Model model
	) {

		// 入力チェックされた場合
		if (bindingResult.hasErrors()) {

			// 入力画面へ
			return "entry";

		}

		// 加算実行
		Integer result = form.getLeftNum() + form.getRightNum();

		// Model に格納する
		model.addAttribute("result", result);

		// 確認画面へ
		return "confirm";

	}

}
