package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.chapter03.used.Greet;

/**
 * SpringBoot起動クラス
 * @author Kazunari
 */
@SpringBootApplication
public class DependencyInjectionAopSampleApplication {

	/**
	 * 注入される個所（インターフェース）
	 */
	@Autowired
	Greet greet;

	public static void main(String[] args) {

		// SpringApplication.run(DependencyInjectionAopSampleApplication.class, args);

		// SpringApplication.run(DependencyInjectionAopSampleApplication.class, args)
		// .getBean(DependencyInjectionAopSampleApplication.class).execute();

		ApplicationContext context = SpringApplication.run(DependencyInjectionAopSampleApplication.class, args);
		DependencyInjectionAopSampleApplication app = context.getBean(DependencyInjectionAopSampleApplication.class);

		app.execute();

	}

	/**
	 * 実行メソッド。
	 */
	private void execute() {

		greet.greeting();

	}

}
