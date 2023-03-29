package com.example.domain;

public class User {
    private Integer id;
    private String name;
    private String email;

    // 空のコンストラクタ（デフォルトコンストラクタ）
    public User() {
    }

    // 全フィールドを引数に持つコンストラクタ
    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // 各フィールドのゲッターとセッター
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toStringメソッド（オプション）
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
