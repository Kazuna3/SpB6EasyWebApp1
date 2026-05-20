package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloViewController {

	// 次のURLで、リクエストハンドラメソッドである helloView が呼び出される。
	// http://localhost:8080/hello/view
	@GetMapping("view")
	public String helloView() {

		// 戻り値は、ビュー名を返す。
		return "hello";

	}

}
