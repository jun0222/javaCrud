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
