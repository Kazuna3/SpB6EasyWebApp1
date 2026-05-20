package com.example.demo.form;

import java.util.Objects;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalcForm {

	@NotNull
	@Range(min = 1, max = 10)
	private Integer leftNum;

	@NotNull
	@Range(min = 1, max = 10)
	private Integer rightNum;

	// 相関チェックを利用するために追加したフィールドである。
	// これが無いと、html 側で leftOddAndRightEven フィールドが無い！と怒られる。
	// 型は何でも良い。Objectにするとなぜか、未使用フィールドであるとの警告が出る。
	private Integer eftOddAndRightEven;

	/**
	 * 相関チェック用メソッド
	 * 
	 * 次のURLを参考資料とした。
	 * https://penguinlabo.hatenablog.com/entry/springboot/web/7_correlationvalidation
	 * 
	 * @return 左側の値が奇数で、右側の値が偶数なら、正常と判断し true を、
	 *          それ以外の場合は異常と判断し false を返す。
	 */
	@AssertTrue(message = "左側は「奇数」を、右側は「偶数」を入力して下さい！")
	public Boolean getLeftOddAndRightEven() {

		if (Objects.nonNull(leftNum) && Objects.nonNull(rightNum)) {

			if (isOdd(leftNum) && isEven(rightNum)) {

				return true;

			} else {

				return false;

			}

		} else {

			return true;

		}

	}

	private Boolean isEven(Integer i) {

		if (0 == i % 2)
			return true;

		return false;

	}

	private Boolean isOdd(Integer i) {

		if (isEven(i))
			return false;

		return true;

	}

}
