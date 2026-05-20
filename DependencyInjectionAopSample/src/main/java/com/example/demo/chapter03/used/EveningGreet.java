package com.example.demo.chapter03.used;

/**
 * Greet 実装クラス<br>
 * 夕方の挨拶を行う。
 * @author Kazunari
 */
// @Component
public class EveningGreet implements Greet {

	@Override
	// public void greeting() {
	public String greeting() {

		System.out.println("--------------");
		System.out.println("こんばんは。");
		System.out.println("--------------");

		return "文字列型の戻り値：EveningGreet";

	}

}
