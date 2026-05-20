package com.example.demo.chapter03.used;

/**
 * Greet 実装クラス<br>
 * 朝の挨拶を行う。
 * @author Kazunari
 */
// @Component
public class MorningGreet implements Greet {

	@Override
	public void greeting() {

		System.out.println("--------------");
		System.out.println("おはようございます！");
		System.out.println("--------------");

	}

}
