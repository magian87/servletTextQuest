package ru.servlettextquest_.settings;

public class QuestionSettings {
    private int num;
    private String question;
    private String acceptText;
    private int accept;
    private String rejectText;
    private int reject;
    private String commentCurrentStep;

    public QuestionSettings() {
    }

    public QuestionSettings(int num, String question, String acceptText, int accept, String rejectText, int reject, String commentCurrentStep) {
        this.num = num;
        this.question = question;
        this.acceptText = acceptText;
        this.accept = accept;
        this.rejectText = rejectText;
        this.reject = reject;
        this.commentCurrentStep = commentCurrentStep;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAcceptText() {
        return acceptText;
    }

    public void setAcceptText(String acceptText) {
        this.acceptText = acceptText;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public String getRejectText() {
        return rejectText;
    }

    public void setRejectText(String rejectText) {
        this.rejectText = rejectText;
    }

    public int getReject() {
        return reject;
    }

    public void setReject(int reject) {
        this.reject = reject;
    }

    public String getCommentCurrentStep() {
        return commentCurrentStep;
    }

    public void setCommentCurrentStep(String commentCurrentStep) {
        this.commentCurrentStep = commentCurrentStep;
    }

    @Override
    public String toString() {
        return "QuestionSettings{" +
                "num=" + num +
                ", question='" + question + '\'' +
                ", acceptText='" + acceptText + '\'' +
                ", accept=" + accept +
                ", rejectText='" + rejectText + '\'' +
                ", reject=" + reject +
                ", commentCurrentStep='" + commentCurrentStep + '\'' +
                '}';
    }
}
