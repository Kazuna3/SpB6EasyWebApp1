package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PathVariableController {

	/** 画面を表示する */
	// 次のURLで、リクエストハンドラメソッドである showView が呼び出される。
	// http://localhost:8080/show
	@GetMapping("show")
	public String showView() {

		// 戻り値は「ビュー名」を返す
		return "show";

	}

	/** リンク処理 */
	@GetMapping("/function/{no}")
	public String selectFunction(@PathVariable Integer no) {

		// 「ビュー名」の初期化
		String view = null;

		switch (no) {

		case 1:
			view = "pathvariable/function1";
			break;

		case 2:
			view = "pathvariable/function2";
			break;

		case 3:
			view = "pathvariable/function3";
			break;

		}

		// 戻り値は「ビュー名」を返す
		return view;

	}

	/** 「ボタンA」押下処理 */
	@PostMapping(value = "send", params = "a")
	public String showAView() {

		// 戻り値は「ビュー名」を返す
		return "submit/a";

	}

	/** 「ボタンB」押下処理 */
	@PostMapping(value = "send", params = "b")
	public String showBView() {

		// 戻り値は「ビュー名」を返す
		return "submit/b";

	}

	/** 「ボタンC」押下処理 */
	@PostMapping(value = "send", params = "c")
	public String showCView() {

		// 戻り値は「ビュー名」を返す
		return "submit/c";

	}

}
