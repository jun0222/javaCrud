package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotNull
    @Min(0)
    @Max(120)
    private Integer age;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

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
