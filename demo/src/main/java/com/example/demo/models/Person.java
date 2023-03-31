// パッケージ名を定義する
package com.example.demo.models;

// クラスがエンティティであることを示す
import javax.persistence.Entity;

// IDを自動生成することを示す
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// バリデーション用のアノテーションをインポート
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// LombokのGetter/Setterアノテーションをインポート
import lombok.Getter;
import lombok.Setter;

// クラスにGetter/Setterを自動生成する
@Getter
@Setter

// クラスがエンティティであることを示す
@Entity

// Personクラスを定義する
public class Person {
    @Id // IDを自動生成することを示す
    @GeneratedValue // IDを自動生成することを示す
    private Long id; // ID

    @NotBlank // 空文字を許容しない
    @Size(max = 120) // 最大文字数を120文字に設定
    private String name; // 名前

    @NotNull // nullを許容しない
    @Min(0) // 最小値を0に設定
    @Max(120) // 最大値を120に設定
    private Integer age; // 年齢

    @NotBlank // 空文字を許容しない
    @Email // メールアドレスの形式であることを示す
    @Size(max = 254) // 最大文字数を254文字に設定
    private String email; // メールアドレス

    // lombokを使わないgetter,setterの書き方
    // @Getterアノテーション、@Setterアノテーションはそれぞれモデルのゲッター、セッターを自動で生成してくれる
    // public String getName(){
    // return this.name;
    // }

    // public Integer getAge(){
    // return this.age;
    // }

    // public String getEmail(){
    // return this.email;
    // }

    // public void setName(String name){
    // this.name = name;
    // }

    // public void setAge(Integer age){
    // this.age = age;
    // }

    // public void setEmail(String email){
    // this.email = email;
    // }
}
