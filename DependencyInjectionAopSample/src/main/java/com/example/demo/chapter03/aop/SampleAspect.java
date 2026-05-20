package com.example.demo.chapter03.aop;

import java.text.SimpleDateFormat;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {

	/*
	 * 【Memo】
	 * ジョイントポイント（JoinPoint）：@Before, @AfterReturning, @AfterThrowing, @After, @Around
	 * 
	 * 上のジョイントポイントのアノテーションの引数に、Pointcut 式の一つである execution で、
	 * ポイントカット（PointCut）を指定する。アノテーションの引数の実装例は次の通り。
	 * 【実装例】
	 * ("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
	 * 
	 * 書式は次の通り。
	 * 【書式】
	 * execution(戻り値の型 パッケージ.クラス.メソッド(引数))
	 * 
	 * @Before("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
	 * 上の実装例では、パッケージ「任意の引数型 com.example.demo.chapter03.used」クラス名が Greet」で終わる
	 * クラスの全メソッドに対して「日付、メソッド名を表示するアドバイス」を作成します。
	 * 
	 */

	// @Before("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {

		// 分かり易いように表示する。
		System.out.println("===== Before Advice =====");

		// 日付を表示する。
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date()));

		// メソッド名を表示する。
		System.out.println(String.format("メソッド：%s", joinPoint.getSignature().getName()));

	}

	// @After("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {

		// 分かり易いように表示する。
		System.out.println("===== After Advice =====");

		// 日付を表示する。
		System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date()));

		// メソッド名を表示する。
		System.out.println(String.format("メソッド：%s", joinPoint.getSignature().getName()));

	}

	@Around("execution(* com.example.demo.chapter03.used.*Greet.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// 分かり易いように表示する。
		System.out.println("===== Around Advice =====");
		System.out.println("▼▼▼ Around Advice ▼▼▼");

		// 指定したクラスの指定したメソッドを実行
		Object result = proceedingJoinPoint.proceed();

		System.out.println("result:" + result);

		System.out.println("▲▲▲ Around Advice ▲▲▲");

		// 戻り値を返す必要がある場合は Object 型の戻り値で返す。

		return result;

	}

}
