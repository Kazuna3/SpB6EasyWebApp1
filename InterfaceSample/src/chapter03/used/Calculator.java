package chapter03.used;

/**
 * 計算処理
 * @author Kazunari
 */
@FunctionalInterface
public interface Calculator {

	/**
	 * 計算処理を実行する。
	 * @param x
	 * @param y
	 * @return
	 */
	Integer calc(Integer x, Integer y);

}
