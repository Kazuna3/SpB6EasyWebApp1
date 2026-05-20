package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.form.CalcForm;

@Component
public class CalcValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		// 引数で渡された Form が入力チェックの対象かを論理値で返します。
		return CalcForm.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {

		// 対象の Form を取得する
		CalcForm form = (CalcForm) target;

		// 値が入っているかの判定
		if (form.getLeftNum() != null && form.getRightNum() != null) {

			// (左側の入力項目が「奇数」かつ右側の入力項目が「偶数」)でない
			if (!((form.getLeftNum() % 2 == 1) && (form.getRightNum() % 2 == 0))) {

				// エラーの場合は、引数 Errors インターフェースの
				// 「reject」メソッドにエラーメッセージのキーを指定する。
				errors.reject("com.example.demo.validator.CalcValidator.message");

			}

		}

	}

}
