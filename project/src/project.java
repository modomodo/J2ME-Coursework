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
public class project extends MIDlet implements ActionListener {

    Form current, frmLogin, frmRegister1, frmRegister2, frmMenu;
    Command cmdExit, cmdNext, cmdBack, cmdRegister;
    TextField txtRegNu, txtpwd, txtFirstName, txtLastName, txtIDNo, txtEmailAddr, txtPhoneNo, txtPwd, txtRetypePwd;
    Button btnLogin, btnRegister, btnNext, btnSummary, btnDL, btnInsuaranceDetails, btnOffences, btnUpdates, btnRegisterR;

    public void startApp() {
        Display.init(this);
        try {
            //access the theme created in the resource editor
            Resources res = Resources.open("/project.res");
            UIManager.getInstance().setThemeProps(res.getTheme("project"));
        } catch (IOException ex) {
            System.out.println("Unable to get theme" + ex.getMessage());
        }
        //form Login
        frmLogin = new Form("Login");
        cmdExit = new Command("Exit", 0);
        frmLogin.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        txtpwd = new TextField("", 20);
        txtRegNu = new TextField("", 20);
        btnLogin = new Button("Login");
        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowMenu();
            }
        });
        btnRegister = new Button("Register");
        btnRegister.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowRegister1();

            }
        });
        frmLogin.addComponent(new Label("Registration Number"));
        frmLogin.addComponent(txtRegNu);
        frmLogin.addComponent(new Label("Password"));
        frmLogin.addComponent(txtpwd);
        frmLogin.addComponent(btnLogin);
        frmLogin.addComponent(btnRegister);
        frmLogin.addCommand(cmdExit);
        frmLogin.addCommandListener(this);
        frmLogin.show();
    }
    //register 1

    private void ShowRegister1() {
        frmRegister1 = new Form("Register");
        frmRegister1.setLayout(new BorderLayout());
        txtIDNo = new TextField("", 20);
        txtLastName = new TextField("", 20);
        txtFirstName = new TextField("", 20);
        btnNext = new Button("Next");
        btnNext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowRegister2();
            }
        });
        Container conRegister = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        conRegister.addComponent(new Label("First Name"));
        conRegister.addComponent(txtFirstName);
        conRegister.addComponent(new Label("Last Name"));
        conRegister.addComponent(txtLastName);
        conRegister.addComponent(new Label("ID NUMBER"));
        conRegister.addComponent(txtIDNo);
        conRegister.addComponent(btnNext);
        //add the container to the form
        frmRegister1.addComponent(BorderLayout.CENTER, conRegister);
        cmdBack = new Command("Back", 0);
        frmRegister1.addCommand(cmdBack);
        frmRegister1.addCommandListener(this);
        frmRegister1.show();

    }

    //register 2
    private void ShowRegister2() {

        frmRegister2 = new Form("Register");
        frmRegister2.setLayout(new BorderLayout());
        txtEmailAddr = new TextField("", 20);
        txtPhoneNo = new TextField("", 20);
        txtPwd = new TextField("", 20);
        txtRetypePwd = new TextField("", 20);
        btnRegisterR = new Button("Register");
        btnRegisterR.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                frmLogin.show();
            }
        });
        Container conRegister = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        conRegister.addComponent(new Label("Email Address"));
        conRegister.addComponent(txtEmailAddr);
        conRegister.addComponent(new Label("Phone Number"));
        conRegister.addComponent(txtPhoneNo);
        conRegister.addComponent(new Label("Password"));
        conRegister.addComponent(txtPwd);
        conRegister.addComponent(new Label("Retype Password"));
        conRegister.addComponent(txtRetypePwd);
        conRegister.addComponent(btnRegisterR);
        //add the container to the form
        frmRegister2.addComponent(BorderLayout.CENTER, conRegister);
        cmdBack = new Command("Back", 0);
        frmRegister2.addCommand(cmdBack);
        frmRegister2.addCommandListener(this);
        frmRegister2.show();

    }

    //menu
    private void ShowMenu() {
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
                ShowInsuaranceDetails();
            }
        });
        btnOffences = new Button("Offences");
        btnOffences.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowOffences();
            }
        });
        btnUpdates = new Button("Updates");
        btnUpdates.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowUpdates();
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

    private void ShowSummary() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ShowDL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ShowInsuaranceDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ShowOffences() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ShowUpdates() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent ae) {
        current = Display.getInstance().getCurrent();
        if (ae.getSource() == cmdExit) {
            destroyApp(true);
            notifyDestroyed();
        }
        if (ae.getSource() == cmdBack && current == frmRegister1) {
            frmLogin.show();
        }
        if (ae.getSource() == btnNext && current == frmRegister1) {
            ShowRegister2();
        }
        if (ae.getSource() == cmdRegister && current == frmRegister2) {
            frmLogin.show();
        }
        if (ae.getSource() == btnLogin && current == frmLogin) {
            frmMenu.show();
        }
        if (ae.getSource() == cmdBack && current == frmMenu) {
            frmLogin.show();
        }

    }
}
