package com.example.quiz.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.Quiz;

/*
 * 書籍「SpringFramework超入門～やさしくわかるWebアプリ開発～」では、本インターフェースを RepositoryImpl として認識している模様。
 * その理由は、P386 に次の記載がある為である。
 * 「10-2-2 Repository の作成」で作成した「インターフェース」に「SpringData」が提供する「CrudRepository」を継承し、「RepositoryImpl」を作成します。
 * ※技術評論社に、上の認識（本インターフェースを RepositoryImpl と認識している事）が誤りではないか？と確認したところ、その通り！との事であった。
 * 技術評論社の原田さんから、2024(R6).03.11に、その旨メールがあった。
 */

/** Quiz テーブル：RepositoryImpl */
public interface QuizRepository extends CrudRepository<Quiz, Integer> {

}
