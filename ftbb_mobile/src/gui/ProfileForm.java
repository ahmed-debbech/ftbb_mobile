/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import entities.Client;
import services.ServiceClient;

/**
 *
 * @author Yassine
 */
public class ProfileForm extends Form {

    public ProfileForm() {
        this.setTitle("Profile");
        this.setLayout(BoxLayout.y());
        Client cl = new Client();
        cl = ServiceClient.getC();
        Label lname = new Label("Name: " + cl.getName());
        Label lsurname = new Label("Surname: " + cl.getSurname());
       
        Label lsex = new Label("Sexe: " + cl.getSex());

        Label lemail = new Label("Email: " + cl.getEmail());

        Label lnumber = new Label("Number: " + cl.getNumber());
        this.addAll(lname, lsurname, lemail, lnumber,lsex);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> new SigninForm().showBack());

    }
}
