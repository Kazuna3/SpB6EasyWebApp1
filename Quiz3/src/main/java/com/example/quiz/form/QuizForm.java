package com.example.quiz.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuizForm {

	/** 識別ID */
	private Integer id;

	/** クイズの内容 */
	@NotBlank
	private String question;

	private Boolean answer;

	/** 作成者 */
	@NotBlank
	private String author;

	/** 「登録」or「変更」判定用 */
	private Boolean newQuiz;

}
