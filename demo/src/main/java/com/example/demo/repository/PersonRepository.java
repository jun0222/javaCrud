package com.example.demo.repository; // パッケージ名を定義する

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepositoryをインポートする

import com.example.demo.models.Person; // Personクラスをインポートする

// PersonRepositoryインターフェースを定義する
public interface PersonRepository extends JpaRepository<Person, Long> {
}
