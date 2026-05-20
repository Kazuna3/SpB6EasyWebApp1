package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.Form;

@Controller
public class RequestParamController {

	/** 入力画面を表示する */
	// 次のURLで、リクエストハンドラメソッドである showView が呼び出される。
	// http://localhost:8080/show
	@GetMapping("show")
	public String showView() {

		// 戻り値は「ビュー名」を返す
		return "entry";

	}

	//	/** 確認画面を表示する */
	//	@PostMapping("confirm")
	//	public String confirmView(
	//		Model model,
	//		@RequestParam String name,
	//		@RequestParam Integer age,
	//		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate birth
	//	) {
	//
	//		// Model に格納する 
	//		model.addAttribute("name", name);
	//		model.addAttribute("age", age);
	//		model.addAttribute("birth", birth);
	//
	//		// 戻り値は「ビュー名」を返す 
	//		return "confirm";
	//
	//	}

	@PostMapping("confirm")
	public String confirmView(Form form) {

		// 戻り値は「ビュー名」を返す 
		return "confirm2";

	}

}
