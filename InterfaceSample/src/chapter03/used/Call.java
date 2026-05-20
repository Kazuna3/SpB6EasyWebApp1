package chapter03.used;

import chapter03.use.AddCalc;

/**
 * インターフェース依存を確認する。
 * @author Kazunari
 */
public class Call {

	public static void main(String[] args) {

		Calculator calculator = new AddCalc();
		// Calculator calculator = new SubCalc();

		Integer result = calculator.calc(10, 5);
		System.out.println("計算結果は「" + result + "」です。");

	}

}
