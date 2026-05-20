package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kazunari
 * Member テーブル：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	/**
	 * 番号
	 */
	@Id
	private Integer id;

	/**
	 * 氏名
	 */
	private String name;

}
