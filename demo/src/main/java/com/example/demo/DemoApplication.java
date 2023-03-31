package com.example.demo; // パッケージ名を定義する

import org.springframework.boot.SpringApplication; // SpringApplicationクラスをインポートする
import org.springframework.boot.autoconfigure.SpringBootApplication; // SpringBootApplicationアノテーションをインポートする

@SpringBootApplication // SpringBootApplicationアノテーションを付与する

// DemoApplicationクラスを定義する
public class DemoApplication {

	// mainメソッドを定義する
	// このコードを実行するとSpringApplicationクラスのrunメソッドが呼び出される（サーバー起動）
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
