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
public class Galerie {
        private int galerieId,adminId;
        private String photoUrl,photoTitle,description;

    public Galerie() {
        
    }

    public Galerie(int galerieId, int adminId, String photoUrl, String photoTitle, String description) {
        this.galerieId = galerieId;
        this.adminId = adminId;
        this.photoUrl = photoUrl;
        this.photoTitle = photoTitle;
        this.description = description;
    }

    public int getGalerieId() {
        return galerieId;
    }

    public void setGalerieId(int galerieId) {
        this.galerieId = galerieId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
        
        
                
                
             
}
