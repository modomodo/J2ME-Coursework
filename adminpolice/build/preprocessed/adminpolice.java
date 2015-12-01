/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.midlet.*;

/**
 * @author RODGEE
 */
public class adminpolice extends MIDlet implements ActionListener {
    Form current, frmLogin, frmMenu, frmRegister, frmUpdatesR;
    TextField txtRegNo, txtRegNoR, txtFirstNameR, txtLastNameR, txtIDNoR;
    TextArea txtaUpdates;
    Button btnLogin, btnRegister, btnSummary, btnDL, btnInsuaranceDetails, btnOffences, btnUpdates, btnRegisterR, btnUpdatesR;
    Command cmdExit, cmdBack;
    public void startApp() {
        Display.init(this);
        try{
            //access the theme created in the resource editor
            Resources res = Resources.open("/adminpolice.res");
            UIManager.getInstance().setThemeProps(res.getTheme("adminpolice"));
        }catch (IOException ex){
            System.out.println("Unable to get theme" + ex.getMessage());
        }
        
        frmLogin = new Form("Login");
        cmdExit = new Command("Exit", 0);
        frmLogin.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        txtRegNo = new TextField("", 20);
        
        btnLogin = new Button("Login");
        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowMenu ();
               
            }
        });
        btnRegister = new Button("Register");
        btnRegister.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowRegister();
               
            }
        });
        btnUpdatesR = new Button("Updates");
        btnUpdatesR.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowUpdatesR ();
               
            }
        });
        frmLogin.addComponent(new Label("Registration Number"));
        frmLogin.addComponent(txtRegNo);
        
        frmLogin.addComponent(btnLogin);
        frmLogin.addComponent(btnRegister);
        frmLogin.addComponent(btnUpdatesR);
        frmLogin.addCommand(cmdExit);
        frmLogin.addCommandListener(this);
        frmLogin.show();
    }
    public void ShowMenu (){
        frmMenu = new Form("Welcome");
        frmMenu.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        btnSummary = new Button("Summary");
        btnSummary.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                 ShowSummary();
             }
         });
        btnDL = new Button("Driving License");
        btnDL.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                 ShowDL();
             }
         });
         btnInsuaranceDetails = new Button("Insuarance Details");
         btnInsuaranceDetails.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                 ShowInsuaranceDetails ();
             }
         });
         btnOffences = new Button("Offences");
         btnOffences.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                ShowOffences ();
             }
         });
         btnUpdates = new Button("Updates");
         btnUpdates.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                ShowUpdates ();
             }
         });
         frmMenu.addComponent(btnSummary);
         frmMenu.addComponent(btnDL);
         frmMenu.addComponent(btnInsuaranceDetails);
         frmMenu.addComponent(btnOffences);
         frmMenu.addComponent(btnUpdates);
         cmdBack = new Command("Back", 0);
         frmMenu.addCommand(cmdBack);
         frmMenu.addCommandListener(this);
         frmMenu.show();
    }
     private void ShowRegister(){
         frmRegister = new Form("Register");
         frmRegister.setLayout(new BorderLayout());
         txtRegNoR = new TextField("", 20);
         txtFirstNameR = new TextField("", 20);
         txtLastNameR = new TextField("", 20);
         btnRegisterR = new Button("Register");
         btnRegisterR.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                 frmLogin.show();
             }
         });
         Container conRegister = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         conRegister.addComponent(new Label("Registration Number"));
         conRegister.addComponent(txtRegNoR);
         conRegister.addComponent(new Label("First Name"));
         conRegister.addComponent(txtFirstNameR);
         conRegister.addComponent(new Label("Last Name"));
         conRegister.addComponent(txtLastNameR);
         conRegister.addComponent(btnRegisterR);
         //add the container to the form
         frmRegister.addComponent(BorderLayout.CENTER, conRegister);
         cmdBack = new Command("Back", 0);
         frmRegister.addCommand(cmdBack);
         frmRegister.addCommandListener(this);
         frmRegister.show();
         
     }
     private void ShowUpdatesR (){
        frmUpdatesR = new Form("Register Updates");
        frmUpdatesR.setLayout(new BorderLayout());
        //txtaUpdates = new TextArea("", );
     }
    
     private void ShowSummary(){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     
     private void ShowDL(){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     
     private void ShowInsuaranceDetails(){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     private void ShowOffences(){
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
     private void ShowUpdates(){
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent ae) {
        current = Display.getInstance().getCurrent();
        if(ae.getSource() == cmdExit){
            destroyApp(true);
            notifyDestroyed();
        }else if(ae.getSource() == btnLogin && current == frmLogin){
            frmLogin.show();
        }else if(ae.getSource() == btnRegister && current == frmLogin){
            frmRegister.show();
        }else if(ae.getSource() == cmdBack && current == frmMenu){
            frmLogin.show();
        }else if(ae.getSource() == cmdBack && current == frmRegister){
            frmLogin.show();
        }
    }
}
