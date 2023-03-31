package com.example.demo.controller;

import javax.annotation.PostConstruct; // Java Beanの初期化メソッドを表すアノテーション

import org.springframework.stereotype.Controller; // コントローラークラスであることを表すアノテーション
import org.springframework.validation.BindingResult; // バリデーション結果の格納クラス
import org.springframework.validation.annotation.Validated; // バリデーション実行クラス
import org.springframework.ui.Model; // ビューにデータを渡すためのクラス
import org.springframework.web.bind.annotation.GetMapping; // GETリクエストを受け付けるためのアノテーション
import org.springframework.web.bind.annotation.PostMapping; // POSTリクエストを受け付けるためのアノテーション
import org.springframework.web.bind.annotation.ModelAttribute; // モデル属性を設定するためのアノテーション
import org.springframework.web.bind.annotation.PathVariable; // パス変数を受け取るためのアノテーション

import com.example.demo.models.Person; // Personクラスのインポート
import com.example.demo.repository.PersonRepository; // PersonRepositoryクラスのインポート

@Controller
public class PersonController {

    // PersonRepositoryクラスのフィールドをfinalにする。
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    // ページ表示用のルーティング
    @GetMapping("/")
    public String index(@ModelAttribute Person person, Model model) {
        // 一覧用データの用意
        model.addAttribute("people", repository.findAll());
        return "person/index";
    }

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

    // データ削除用のルーティング
    @GetMapping("/delete/{id}")
    public String remove(@PathVariable long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    // データ編集用のルーティング
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("person", repository.findById(id)); // 一覧用データの用意
        return "person/edit";
    }

    // データ更新用のルーティング
    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @Validated @ModelAttribute Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person/edit";
        }
        repository.save(person);
        return "redirect:/";
    }

    // 初期データの投入
    @PostConstruct
    public void dataInit() {
        Person suzuki = new Person();
        suzuki.setName("鈴木");
        suzuki.setAge(23);
        suzuki.setEmail("suzuki@email.com");
        repository.saveAndFlush(suzuki);

        Person sato = new Person();
        sato.setName("佐藤");
        sato.setAge(20);
        sato.setEmail("sato@email.com");
        repository.saveAndFlush(sato);
    }
}