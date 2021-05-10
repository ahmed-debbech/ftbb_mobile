/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.models;

/**
 *
 * @author narug
 */
public class Feedback {
    
    private int feedbackId,clientId;
    private String feedbackDate,email,topic,text,type;

    public Feedback() {
    }

    public Feedback(int feedbackId, int clientId, String feedbackDate, String email, String topic, String text, String type) {
        this.feedbackId = feedbackId;
        this.clientId = clientId;
        this.feedbackDate = feedbackDate;
        this.email = email;
        this.topic = topic;
        this.text = text;
        this.type = type;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feedbackId=" + feedbackId + ", clientId=" + clientId + ", feedbackDate=" + feedbackDate + ", email=" + email + ", topic=" + topic + ", text=" + text + ", type=" + type + '}';
    }
    
    
            
    
    
}
