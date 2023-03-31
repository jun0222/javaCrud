<!-- TOC -->

- [参考教材](#参考教材)
- [発展教材](#発展教材)
- [サーバー起動](#サーバー起動)
- [依存関係のビルド](#依存関係のビルド)
- [標準出力](#標準出力)
- [H2 データベースについて](#h2-データベースについて)
- [maven](#maven)
- [CRUD の流れ（コードベース）](#crud-の流れコードベース)
  - [CREATE](#create)
    - [demo/src/main/resources/templates/person/index.html](#demosrcmainresourcestemplatespersonindexhtml)
    - [demo/src/main/java/com/example/demo/controller/PersonController.java](#demosrcmainjavacomexampledemocontrollerpersoncontrollerjava)
      - [saveAndFlush()](#saveandflush)
    - [demo/src/main/java/com/example/demo/models/Person.java](#demosrcmainjavacomexampledemomodelspersonjava)
  - [READ](#read)
    - [demo/src/main/java/com/example/demo/controller/PersonController.java](#demosrcmainjavacomexampledemocontrollerpersoncontrollerjava-1)
    - [demo/src/main/resources/templates/person/index.html](#demosrcmainresourcestemplatespersonindexhtml-1)
  - [UPDATE](#update)
    - [demo/src/main/resources/templates/person/index.html](#demosrcmainresourcestemplatespersonindexhtml-2)
    - [demo/src/main/java/com/example/demo/controller/PersonController.java](#demosrcmainjavacomexampledemocontrollerpersoncontrollerjava-2)
    - [demo/src/main/resources/templates/person/edit.html](#demosrcmainresourcestemplatespersonedithtml)
    - [demo/src/main/java/com/example/demo/controller/PersonController.java](#demosrcmainjavacomexampledemocontrollerpersoncontrollerjava-3)
  - [DELETE](#delete)
    - [demo/src/main/resources/templates/person/index.html](#demosrcmainresourcestemplatespersonindexhtml-3)
    - [demo/src/main/java/com/example/demo/controller/PersonController.java](#demosrcmainjavacomexampledemocontrollerpersoncontrollerjava-4)
- [Rails との比較](#rails-との比較)

<!-- /TOC -->

# 参考教材

- [手を動かしながら学ぶ！Spring Boot 入門](https://www.techpit.jp/courses/224)

# 発展教材

- [実践 SpringBoot ~SpringBoot Advanced Tutorial~](https://www.techpit.jp/courses/232)
  ※未着手。必要に応じて。

# サーバー起動

http://localhost:8080 でみる

VSCode の拡張機能で、demo/src/main/java/com/example/demo/DemoApplication.java  
の main 関数を Run する。  
![picture 1](images/d5e644c576c4678342b5a797101ee2300b02d637cb136805240a5c4a48b3dc6c.png)

# 依存関係のビルド

`pom.xml`でコード内を右クリックして、`Add Starters`から必要な依存関係を追加する。  
その後`Proceed`をクリック。

# 標準出力

```java
@PostMapping("/create")
public String create(@Validated @ModelAttribute Person person, BindingResult result, Model model) {

    // 標準出力
    System.out.println(result);
    System.out.println(person.getAge());

    if (result.hasErrors()) {
        model.addAttribute("people", repository.findAll());
        return "person/index";
    }
    repository.saveAndFlush(person);
    return "redirect:/";
}
```

このようにログ出力できる。

# H2 データベースについて

sqlite ではなく Java ではこちらが一般的な簡易 DB。

# maven

maven を利用して setup している。

# CRUD の流れ（コードベース）

## CREATE

### demo/src/main/resources/templates/person/index.html

ここで controller の/create へ処理を飛ばす

```html
<form th:action="@{/create}" th:object="${person}" method="post">
  <!-- 名前の入力フォーム -->
  <label>名前</label>
  <input type="text" th:field="*{name}" th:errorclass="invalid" /><br />
  <!-- 名前に関するバリデーションエラーメッセージ -->
  <div class="red" th:errors="*{name}"></div>

  <!-- 年齢の入力フォーム -->
  <label>年齢</label>
  <input type="number" th:field="*{age}" th:errorclass="invalid" /><br />
  <!-- 年齢に関するバリデーションエラーメッセージ -->
  <div class="red" th:errors="*{age}"></div>

  <!-- メールアドレスの入力フォーム -->
  <label>メールアドレス</label>
  <input type="email" th:field="*{email}" th:errorclass="invalid" /><br />
  <!-- メールアドレスに関するバリデーションエラーメッセージ -->
  <div class="red" th:errors="*{email}"></div>

  <!-- 送信ボタン -->
  <button>送信</button>
</form>
```

### demo/src/main/java/com/example/demo/controller/PersonController.java

ここで DB への保存、返す template html を記述する。

```java
// データ作成用のルーティング
@PostMapping("/create")
public String create(@Validated @ModelAttribute Person person, BindingResult result, Model model) {

    // 標準出力
    System.out.println(result);
    System.out.println(person.getAge());

    // バリデーションエラーがある場合はindex.htmlを表示
    if (result.hasErrors()) {
        model.addAttribute("people", repository.findAll());
        return "person/index";
    }
    repository.saveAndFlush(person);
    return "redirect:/";
}
```

#### saveAndFlush()

Spring Data JPA で提供されているメソッドで、データを保存し、すぐに永続化するために使用されます。つまり、データベースに反映されるように、即座にデータを保存します。

### demo/src/main/java/com/example/demo/models/Person.java

モデル。バリデーションや getter,setter の定義などを行う

```java
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
}

```

## READ

### demo/src/main/java/com/example/demo/controller/PersonController.java

DB からデータを findAll()して、テンプレートを指定して view を返す

```java
// ページ表示用のルーティング
@GetMapping("/")
public String index(@ModelAttribute Person person, Model model) {
    // 一覧用データの用意
    model.addAttribute("people", repository.findAll());
    return "person/index";
}
```

### demo/src/main/resources/templates/person/index.html

Thymeleef の構文を使って、controller から受け取った DB のデータを each で回す

```html
<table>
  <thead>
    <tr>
      <td>ID</td>
      <td>name</td>
      <td>age</td>
      <td>email</td>
    </tr>
  </thead>
  <!-- リストの要素をループで回し、1件ずつテーブルの行に出力する -->
  <tr th:each="person : ${people}">
    <!-- IDを表示 -->
    <td>[[${person.id}]]</td>
    <!-- 名前を表示 -->
    <td>[[${person.name}]]</td>
    <!-- 年齢を表示 -->
    <td>[[${person.age}]]</td>
    <!-- メールアドレスを表示 -->
    <td>[[${person.email}]]</td>
    <!-- 編集ページへのリンクを表示 -->
    <td><a th:href="@{/edit/{id}(id=${person.id})}">編集</a></td>
    <!-- 削除ページへのリンクを表示 -->
    <td><a th:href="@{/delete/{id}(id=${person.id})}">削除</a></td>
  </tr>
</table>
```

## UPDATE

### demo/src/main/resources/templates/person/index.html

各データの編集用のビューに移動する

```html
<td><a th:href="@{/edit/{id}(id=${person.id})}">編集</a></td>
```

### demo/src/main/java/com/example/demo/controller/PersonController.java

各データの編集用のビューに移動する

```java
// データ編集用のルーティング
@GetMapping("/edit/{id}")
public String edit(@PathVariable long id, Model model) {
    model.addAttribute("person", repository.findById(id)); // 一覧用データの用意
    return "person/edit";
}
```

### demo/src/main/resources/templates/person/edit.html

CREATE と同様、controller の/update 処理へ form を送信する

```html
<!--更新処理を呼び出すURLを指定。で囲んだ部分には変数idが入る-->
<!--formにバインドするオブジェクトを指定-->
<form th:action="@{/update/{id}(id=${id})}" th:object="${person}" method="post">
  <label>名前</label>
  <input type="text" th:field="*{name}" th:errorclass="invalid" /><br />
  <!--テキストボックスを表示。nameプロパティにバインドする-->
  <div class="red" th:errors="*{name}"></div>
  <!--バリデーションエラーがあればメッセージを表示-->

  <label>年齢</label>
  <input type="number" th:field="*{age}" th:errorclass="invalid" /><br />
  <!--数値入力欄を表示。ageプロパティにバインドする-->
  <div class="red" th:errors="*{age}"></div>
  <!--バリデーションエラーがあればメッセージを表示-->

  <label>メールアドレス</label>
  <input type="email" th:field="*{email}" th:errorclass="invalid" /><br />
  <!--メールアドレス入力欄を表示。emailプロパティにバインドする-->
  <div class="red" th:errors="*{email}"></div>
  <!--バリデーションエラーがあればメッセージを表示-->

  <button>送信</button>
  <!--フォームを送信するためのボタンを表示-->
</form>
```

### demo/src/main/java/com/example/demo/controller/PersonController.java

CREATE 同様、DB 操作とテンプレートの指定をする

```java
// データ更新用のルーティング
@PostMapping("/update/{id}")
public String update(@PathVariable long id, @Validated @ModelAttribute Person person, BindingResult result) {
    if (result.hasErrors()) {
        return "person/edit";
    }
    repository.save(person);
    return "redirect:/";
}
```

## DELETE

### demo/src/main/resources/templates/person/index.html

各データの削除ボタンをクリックする

```html
<td><a th:href="@{/delete/{id}(id=${person.id})}">削除</a></td>
```

### demo/src/main/java/com/example/demo/controller/PersonController.java

DB からデータの削除、テンプレートの返却を実施する。  
※delete 処理なので、本来は GetMapping を使わない。

```java
// データ削除用のルーティング
@GetMapping("/delete/{id}")
public String remove(@PathVariable long id) {
    repository.deleteById(id);
    return "redirect:/";
}
```

# Rails との比較

Rails 近い思想、構成になっている。

- ルーティングは controller のみで設定する
- バリデーションや DB 周りは model で行う
- view は html ファイルに spring boot の構文が使える。ループして全部表示するものは each で回すのが基本。
- Gemfile のように pom.xml に依存関係を追加する
- terminal にエラーが出る
- log level を設定すると Rails 同様の粒度でアプリケーションエラーが表示される
