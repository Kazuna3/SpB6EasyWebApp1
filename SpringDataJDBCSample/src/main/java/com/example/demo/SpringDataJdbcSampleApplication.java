package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberCrudRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class SpringDataJdbcSampleApplication {

	// @Autowired
	// MemberCrudRepository memberCrudRepository;

	private final MemberCrudRepository memberCrudRepository;

	public static void main(String[] args) {

		//SpringApplication.run(SpringDataJdbcSampleApplication.class, args);

		ApplicationContext context = SpringApplication.run(SpringDataJdbcSampleApplication.class, args);
		SpringDataJdbcSampleApplication app = context.getBean(SpringDataJdbcSampleApplication.class);

		app.execute();

	}

	/**
	 * 「登録」と「全件取得」を実行する。
	 */
	private void execute() {

		// 「登録」を実行する。
		executeInsert();

		// 「全件取得」を実行する。
		executeSelectAll();

	}

	/**
	 * 登録
	 */
	private void executeInsert() {

		// エンティティを作成する。（id は自動採番の為 null を設定する）
		Member member = new Member(null, "花子");

		// リポジトリで登録処理を実行し、登録結果を取得する。
		member = memberCrudRepository.save(member);

		// 結果を表示する。
		System.out.println("登録したデータ：" + member);

	}

	/**
	 * 全件取得
	 */
	private void executeSelectAll() {

		System.out.println("--- 全件を取得します。 ---");

		// リポジトリで全件取得処理を実行し、結果を取得する。
		Iterable<Member> members = memberCrudRepository.findAll();

		// members.forEach(s -> System.out.println(s));
		members.forEach(System.out::println);

	}

}
