package com.example.demo.form;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalcForm {

	@NotNull(message = "左：数値が未入力です。")
	@Range(min = 1, max = 10, message = "左：{min}～{max}数値が未入力です。")
	private Integer leftNum;

	@NotNull(message = "右：数値が未入力です。")
	@Range(min = 1, max = 10, message = "右：{min}～{max}数値が未入力です。")
	private Integer rightNum;

}
