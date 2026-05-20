package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloModelController {

	// 次のURLで、リクエストハンドラメソッドである helloView が呼び出される。
	// http://localhost:8080/hello/model
	@GetMapping("model")
	public String helloView(Model model) {

		// Model にデータを格納する。
		model.addAttribute("msg", "タイムリーフ！！！");

		return "helloThymeleaf";

	}

}
