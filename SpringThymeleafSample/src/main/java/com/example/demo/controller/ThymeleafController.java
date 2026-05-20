package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Member;

@Controller
public class ThymeleafController {

	// 次のURLで、リクエストハンドラメソッドである showView が呼び出される。
	// http://localhost:8080/show
	@GetMapping("show")
	public String showView(Model model) {

		//model.addAttribute("name", "太郎");
		model.addAttribute("name", "花子");

		Member member = new Member(1, "会員01");
		model.addAttribute("mb", member);

		// List を作成する。
		// List<String> directionList = new ArrayList<>();
		// directionList.add("東");
		// directionList.add("西");
		// directionList.add("南");
		// directionList.add("北");
		List<String> directionList = Arrays.asList("東", "西", "南", "北");
		model.addAttribute("list", directionList);

		// コレクション格納用：Member を作成する。
		Member member1 = new Member(10, "田中");
		Member member2 = new Member(20, "鈴木");

		// Map を作成し、Memberを格納する。
		// Map<String, Member> memberMap = new HashMap<>();
		// memberMap.put("tanaka", member1);
		// memberMap.put("suzuki", member2);
		Map<String, Member> memberMap = Map.of("tanaka", member1, "suzuki", member2);
		model.addAttribute("map", memberMap);

		// List を作成し、Member を格納する。
		// List<Member> memberList = new ArrayList<>();
		// memberList.add(member1);
		// memberList.add(member2);
		List<Member> memberList = Arrays.asList(member1, member2);
		model.addAttribute("members", memberList);

		// 「Model」にデータを格納する。
		// model.addAttribute("name", "太郎");
		// model.addAttribute("mb", member);
		// model.addAttribute("list", directionList);
		// model.addAttribute("map", memberMap);
		// model.addAttribute("members", memberList);

		// 戻り値は、ビュー名を返す。
		return "useThymeleaf";

	}

	// 次のURLで、リクエストハンドラメソッドである showA が呼び出される。
	// http://localhost:8080/a
	@GetMapping("a")
	public String showA() {

		return "pageA";

	}

	// 次のURLで、リクエストハンドラメソッドである showA が呼び出される。
	// http://localhost:8080/layout
	@GetMapping("layout")
	public String layout() {

		return "commons/layout";

	}

}
