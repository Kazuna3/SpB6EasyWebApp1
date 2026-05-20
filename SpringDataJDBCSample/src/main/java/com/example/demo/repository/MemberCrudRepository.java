package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Member;

/**
 * @author Kazunari
 * Member テーブル：リポジトリ
 *
 * 書籍「SpringFramework超入門～やさしくわかるWebアプリ開発～」では、本インターフェースを RepositoryImpl として認識している模様。
 * その理由は、P386 に次の記載がある為である。
 * 「10-2-2 Repository の作成」で作成した「インターフェース」に「SpringData」が提供する「CrudRepository」を継承し、「RepositoryImpl」を作成します。
 * ※技術評論社に、上の認識（本インターフェースを RepositoryImpl と認識している事）が誤りではないか？と確認したところ、その通り！との事であった。
 * 技術評論社の原田さんから、2024(R6).03.11に、その旨メールがあった。
 * 
 * CrudRepository の型引数には、保存対象のオブジェクトの型（Member）と、保存対象のオブジェクトの主キーの型（Integer）を、指定している。
 * これにより下の「メソッド」を「MemberCrudRepository」インターフェース内にメソッド記述を一切しなくても使えるようになる。
 * メソッドで使用されている「戻り値」や「引数」の「エンティティ」や「ID」は、<Member,Integer>で指定した型になる。
 * 
 * 取得したエンティティの数を返す。
 * long count();
 * 
 * 指定したエンティティを削除する。
 * void delete(T entity);
 * 
 * 指定したIDで特定されるエンティティを削除する。
 * void deleteById(ID id);
 * 
 * 指定した複数のエンティティを削除する。
 * void deleteAll(Iterable<? extends T> entities);
 * 
 * 指定した複数のIDで特定されるエンティティを削除する。
 * void deleteAllById(Iterable<? extends ID> ids);
 * 
 * 指定したIDのエンティティが存在すればTrueを、そうでなければfalseを返す。
 * boolean existsById(ID id);
 * 
 * 全てのエンティティを返す。
 * Iterable<T> findAll();
 * 
 * 指定したIDを持つ全てのエンティティを返す。
 * Iterable<T> findAllById(Iterable<ID> ids);
 * 
 * 指定したIDを持つエンティティを返す。
 * Optional<T> findById(ID id);
 * 
 * 指定したエンティティを保存する。
 * ＠Id アノテーションが付与されたフィールドが、Null の場合は Insert 文が、そうでなければ Update 文が実行される。
 * <S extends T> S save(S entity);
 * 
 * 指定した全てのエンティティを保存する。
 * <S extends T> Iterable<S> saveAll(Iterable<S> entities);
 */
public interface MemberCrudRepository extends CrudRepository<Member, Integer> {

}
