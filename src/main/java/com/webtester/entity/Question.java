package com.webtester.entity;

public class Question {
    private int id;

    private String question;

    private String answer;

    private boolean isSelectable;

    public Question() {
    }

    public Question(int id, String question, String answer, boolean isSelectable) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.isSelectable = isSelectable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }
}
