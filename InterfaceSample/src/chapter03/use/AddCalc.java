package chapter03.use;

import chapter03.used.Calculator;

/**
 * Calculator 実装クラス
 * 加算を行う。
 * @author Kazunari
 */
public class AddCalc implements Calculator {

	@Override
	public Integer calc(Integer x, Integer y) {

		return x + y;

	}

}
