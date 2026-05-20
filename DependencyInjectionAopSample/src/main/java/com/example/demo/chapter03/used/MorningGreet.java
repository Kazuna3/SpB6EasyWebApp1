package com.example.demo.chapter03.used;

import org.springframework.stereotype.Component;

/**
 * Greet 実装クラス<br>
 * 朝の挨拶を行う。
 * @author Kazunari
 */
@Component
public class MorningGreet implements Greet {

	@Override
	// public void greeting() {
	public String greeting() {

		System.out.println("--------------");
		System.out.println("おはようございます！");
		System.out.println("--------------");

		return "文字列型の戻り値：MorningGreet";

	}

}
