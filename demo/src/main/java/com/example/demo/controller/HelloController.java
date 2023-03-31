// ControllerアノテーションでクラスがControllerであることを示す
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
// GetMappingアノテーションでHTTP GETリクエストを処理するメソッドを示す
import org.springframework.web.bind.annotation.GetMapping;
// ResponseBodyアノテーションでHTTP応答本文を返すことを示す
import org.springframework.web.bind.annotation.ResponseBody;
// Modelを使用するために必要なimport文
import org.springframework.ui.Model;

// クラスにControllerアノテーションをつけてControllerであることを示す
@Controller
public class HelloController {
  // GetMappingアノテーションで/helloにGETリクエストを受け付ける
  // ResponseBodyアノテーションで、HTTP応答本文を返すことを示す
  @GetMapping("/hello")
  @ResponseBody
  public String helloWorld() {
    // 応答本文として"Hello,World"を返す
    return "Hello,World";
  }

  // GetMappingアノテーションで/greetにGETリクエストを受け付ける
  // ResponseBodyアノテーションで、HTTP応答本文を返すことを示す
  @GetMapping("/greet")
  @ResponseBody
  public String greet() {
    // 応答本文として"こんにちは"を返す
    return "こんにちは";
  }

  // GetMappingアノテーションで/sayHelloにGETリクエストを受け付ける
  @GetMapping("/sayHello")
  public String sayHello() {
    // View名として"hello"を返す
    return "hello";
  }

  // GetMappingアノテーションで/greetModelにGETリクエストを受け付ける
  // Modelを使用するために引数としてModelを取る
  @GetMapping("/greetModel")
  public String greetModel(Model model) {
    // モデルに"message"というキーで値を設定する
    String word = "Hello,World！（model）";
    model.addAttribute("message", word);
    // View名として"greetmodel"を返す
    return "greetmodel";
  }
}