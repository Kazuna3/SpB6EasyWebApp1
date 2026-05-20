package com.example.demo.chapter03.used;

import org.springframework.stereotype.Component;

/**
 * Greet 実装クラス<br>
 * 夕方の挨拶を行う。
 * @author Kazunari
 */
@Component
public class EveningGreet implements Greet {

	@Override
	public void greeting() {

		System.out.println("--------------");
		System.out.println("こんばんは。");
		System.out.println("--------------");

	}

}
