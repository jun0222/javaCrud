<!-- TOC -->

- [参考記事](#参考記事)
- [setup](#setup)
- [CRUD](#crud)
- [コマンド](#コマンド)
  - [パッケージインストール](#パッケージインストール)
  - [サーバー起動](#サーバー起動)
- [パッケージ管理](#パッケージ管理)
- [プロジェクトの root ディレクトリ](#プロジェクトの-root-ディレクトリ)
- [エラー確認](#エラー確認)
- [エラー](#エラー)
  - [\[INFO\] BUILD FAILURE](#info-build-failure)
  - [class、interface または enum がありません](#classinterface-または-enum-がありません)
  - [シンボルを見つけられません JavaCrudApplication](#シンボルを見つけられません-javacrudapplication)
  - [シンボルを見つけられません Test](#シンボルを見つけられません-test)
  - [Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required](#property-sqlsessionfactory-or-sqlsessiontemplate-are-required)
  - [](#)

<!-- /TOC -->

# 参考記事

- [SpringBoot で ToDo アプリを作ってみよう【誰でも作れます・初心者向け】](https://qiita.com/toki_dev/items/f9fcdf7d65f3a8ab0f23)
- [SpringBoot を基づいての環境構築、及びユーザ登録機能実装（第一章）](https://qiita.com/rigenkichi/items/e033bc4aac724804c58e)

# setup

1. [Spring Initializr](https://start.spring.io/)でプロジェクトを作成
   1. Project Type を "Maven Project" にし、Packaging を "Jar" に選択します。
   2. Language を "Java" に選択します。
   3. Group と Artifact に適切な名前を入力します。
   4. Dependencies に必要なものを選択します。例えば、ウェブアプリケーションを作成する場合は "Web" を選択します。
   5. "Generate" ボタンをクリックしてプロジェクトをダウンロードし、展開します。
2. mvn をインストール
   1. `brew install mvn`
3. パッケージをインストール
   1. `mvn clean install` （pom.xml があるディレクトリで行う package インストールコマンド。）
4. サーバーを起動
   1. `mvn spring-boot:run`

# CRUD

# コマンド

### パッケージインストール

```
mvn clean install
```

### サーバー起動

```
mvn spring-boot:run
```

# パッケージ管理

`pom.xml`で行う。使うものの dependency を記載する。

# プロジェクトの root ディレクトリ

`pom.xml`がある場所。sqlite のデータを残すファイルもここに置く。  
コマンドは基本ここで叩く。

# エラー確認

サーバー起動したままコードを修正した場合、そのまま terminal のエラーを見るのではなく、一旦サーバーを落として build したときに出たエラーを見る。

# エラー

### [INFO] BUILD FAILURE

```
[ERROR] The goal you specified requires a project to execute but there is no POM in this directory (prodject の path). Please verify you invoked Maven from the correct directory. -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MissingProjectException
```

`mvn clean install` コマンドを実行するディレクトリの間違い。  
`pom.xml`があるディレクトリでコマンドを実行するとよい。

### class、interface または enum がありません

```
tempCodeRunnerFile.java:1: エラー: class、interfaceまたはenumがありません
rg.springframework.boot
```

code-runner でサーバーを起動しようとしていたため。  
`mvn spring-boot:run` でサーバー起動すれば良い。

### シンボルを見つけられません JavaCrudApplication

```
（フルパスなのでこれ以前は省略）javaCrud/src/test/java/com/example/javaCrud/JavaCrudApplicationTests.java:[4,28] シンボルを見つけられません
[ERROR]   シンボル:   クラス JavaCrudApplication
```

`src/main/java/com/example/javaCrud/JavaCrudApplication.java`
の  
`package com.example.javaCrud;` とすべき箇所が、  
`package com.example.demo;`だったので直して OK
（test のファイルにはもともと正しい方を記載していた。）

### シンボルを見つけられません Test

```
シンボルを見つけられません
[ERROR]   シンボル:   クラス Test
```

`junit`が import できていなかったため。

`pom.xml`

```xml
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.13.2</version>
	<scope>test</scope>
</dependency>
```

`src/test/java/com/example/javaCrud/JavaCrudApplicationTests.java`

```java
import org.junit.Test;
```

を記載して、build 成功。

### Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required

```
Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'userMapper' defined in file [〜省略〜/javaCrud/target/classes/com/example/demo/mapper/UserMapper.class]: Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required
```

`src/main/java/com/example/javaCrud/JavaCrudApplication.java`
に

```java
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.example.config"})
```

を記載して OK。

###

```
Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [com/example/config/MyBatisConfig.class]: Failed to instantiate [org.apache.ibatis.session.SqlSessionFactory]: Factory method 'sqlSessionFactory' threw exception with message: org/springframework/core/NestedIOException
```
