package com.webtester.controller;

import com.webtester.entity.Question;
import com.webtester.entity.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private QuestionDAO questionDAO;

    @Autowired
    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "index";
    }

    @GetMapping("/main")
    public String listAll(Model model) {
        List<Question> questions = questionDAO.listAllQuestions();
        model.addAttribute("questions", questions);
        return "main";
    }
}
