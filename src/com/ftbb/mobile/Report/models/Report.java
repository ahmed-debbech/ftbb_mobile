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
public class Report {
    private int reportId;
    private int clientId;
    private int commandId;
    private String email;
    private String reportDate;
    private String description;

    public Report() {
    }

    public Report(int reportId, int clientId, int commandId, String email, String reportDate, String description) {
        this.reportId = reportId;
        this.clientId = clientId;
        this.commandId = commandId;
        this.email = email;
        this.reportDate = reportDate;
        this.description = description;
    }

    public Report(int command_id, String email, String description) {
        this.commandId = commandId;
        this.email = email;
        this.description = description;
    }

    public int getReport_id() {
        return reportId;
    }

    public void setReport_id(int reportId) {
        this.reportId = reportId;
    }

    public int getClient_id() {
        return clientId;
    }

    public void setClient_id(int client_id) {
        this.clientId = clientId;
    }

    public int getCommand_id() {
        return commandId;
    }

    public void setCommand_id(int commandId) {
        this.commandId = commandId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReport_date() {
        return reportDate;
    }

    public void setReport_date(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Report{" + "report_id=" + reportId + ", client_id=" + clientId + ", command_id=" + commandId + ", email=" + email + ", report_date=" + reportDate + ", description=" + description + '}';
    }
    
    
    
    
}
