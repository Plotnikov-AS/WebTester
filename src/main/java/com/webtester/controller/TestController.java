package com.webtester.controller;

import com.webtester.entity.AnswerOptionsDAO;
import com.webtester.entity.Question;
import com.webtester.entity.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {
    private QuestionDAO questionDAO;

    private AnswerOptionsDAO answerOptionsDAO;

    @Autowired
    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Autowired
    public void setAnswerOptionsDAO(AnswerOptionsDAO answerOptionsDAO) {
        this.answerOptionsDAO = answerOptionsDAO;
    }


    @GetMapping("{questionId}")
    public String showQuestion(@PathVariable String questionId, Model model) {
        Question question = questionDAO.findQuestion(Integer.parseInt(questionId));
        model.addAttribute("question", question);
        if (question.isSelectable()) {
            model.addAttribute("answerOptions", answerOptionsDAO.getQuestionAnswerOptions(question));
        }
        return "test";
    }

    @PostMapping("{questionId}")
    public String sendAnswer(
            @PathVariable String questionId,
            @RequestParam String answer,
            Model model) {
        String correctAnswer = questionDAO.findQuestion(Integer.parseInt(questionId)).getAnswer();
        answer = answer.replaceAll(",", "");//no idea why freemarker add comma in values...

        int nextQuestionId = Integer.parseInt(questionId) + 1;
        if (questionDAO.findQuestion(nextQuestionId) != null) {
            model.addAttribute("nextQuestion", questionDAO.findQuestion(nextQuestionId));
        }
        if (answer.equals(correctAnswer)) {
            return "correctAnswer";
        }
        return "badAnswer";
    }
}
