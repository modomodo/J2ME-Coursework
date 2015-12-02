/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Dialog;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;

/**
 * @author RODGEE
 */
public class project extends MIDlet implements ActionListener {

    Form current, frmLogin, frmRegister1, frmRegister2, frmMenu, frmSummary, frmDl, frmInsurance, frmOffences, frmUpdates;
    Command cmdExit, cmdNext, cmdBack, cmdRegister, cmdOk;
    TextField txtRegNu, txtpwd, txtFirstName, txtLastName, txtIDNo, txtEmailAddr, txtPhoneNo, txtPwdR, txtRetypePwd;
    Button btnLogin, btnRegister, btnNext, btnSummary, btnDL, btnInsuaranceDetails, btnOffences, btnUpdates, btnRegisterR;
    String first_name, last_name, id_no, email_address, password, phone_number, RetypePwd;
    Dialog success, validate, error1, error2;
    TextArea txtaValidate, txtasuccess, txtaerror1, txtaerror2, txtaSummary, txtaDl, txtaInsurance, txtaOffences, txtaUpdates;

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
        cmdOk = new Command("OK", 0);
        txtaValidate = new TextArea();
        txtaValidate.setEditable(false);
        txtasuccess = new TextArea();
        txtasuccess.setEditable(false);
        txtaerror1 = new TextArea();
        txtaerror1.setEditable(false);
        txtaerror2 = new TextArea();
        txtaerror2.setEditable(false);

        validate = new Dialog("validate");
        validate.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        validate.addComponent(txtaValidate);
        validate.addCommand(cmdOk);
        success = new Dialog("success");
        success.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        success.addComponent(txtasuccess);
        error1 = new Dialog("success");
        error1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        error1.addComponent(txtaerror1);
        error2 = new Dialog("success");
        error2.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        error2.addComponent(txtaerror2);

        frmLogin.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        txtpwd = new TextField("", 20);
        txtRegNu = new TextField("", 20);
        btnLogin = new Button("Login");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                txtasuccess.setText("you've logged in successfully");
                success.setTimeout(1000);
                success.show(70,70,10,10,true);
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
                //collect values
                first_name = txtFirstName.getText().trim();
                last_name = txtLastName.getText().trim();
                id_no = txtIDNo.getText().trim();
                //check if the values are empty
                if (first_name.equals("") || last_name.equals("") || id_no.equals("")) {
                      txtaValidate.setText("Please fill all fields");
                      validate.setTimeout(1000);
                      validate.show(70,70,10,10,true);
                    
                } else {
                    String RegisterURL = "http://localhost/project/register1.php?first_name="
                            + first_name + "&last_name=" + last_name + "&id_no=" + id_no;
                    // System.out.println(" first "+first_name+"  last "+last_name+" id "+id_no);
                    String registerResponse = dbfunction(RegisterURL);
                    int resp = Integer.parseInt(registerResponse);
                    if (resp == 1) {
                      
                        ShowRegister2();
                    } else {
                        txtaerror1.setText("wrong details");
                      error1.setTimeout(1000);
                      error1.show(70,70,10,10,true);

                    }
                }
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
        txtPwdR = new TextField("", 20);
        txtRetypePwd = new TextField("", 20);
        btnRegisterR = new Button("Register");
        btnRegisterR.addActionListener(new ActionListener() {

            //iuiuiuiu
            public void actionPerformed(ActionEvent ae) {
                //get the values entered
                email_address = txtEmailAddr.getText().trim();
                phone_number = txtPhoneNo.getText().trim();
                password = txtPwdR.getText().trim();
                RetypePwd = txtRetypePwd.getText().trim();
                if (email_address.equals("") || phone_number.equals("") || password.equals("") || RetypePwd.equals("")) {
                      txtaerror2.setText("Please fill all fields");
                      error2.setTimeout(1000);
                      error2.show(70,70,10,10,true);
                //check if password match
                }else if (password.equals(RetypePwd)) {
                    //send to database
                    String registerURL = "http://localhost/project/register2.php?email_address="+
                            email_address+"&password="+password+"&phone_number="+phone_number+"&id_no="+id_no;
                    //call the dbfunction method
                    String registerResponse = dbfunction(registerURL);
                    int resp = Integer.parseInt(registerResponse);
                    if (resp == 1) {
                        //send the user to login form
                      txtaValidate.setText("you've registered successfully");
                      validate.setTimeout(1000);
                      validate.show(70,70,10,10,true);
                      frmLogin.show();
                    } else {
                        txtaerror2.setText("Kindly try later");
                      error2.setTimeout(1000);
                      error2.show(70,70,10,10,true);

                    }

            }
            }
        });
        Container conRegister = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        conRegister.addComponent(new Label("Email Address"));
        conRegister.addComponent(txtEmailAddr);
        conRegister.addComponent(new Label("Phone Number"));
        conRegister.addComponent(txtPhoneNo);
        conRegister.addComponent(new Label("Password"));
        conRegister.addComponent(txtPwdR);
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
        frmMenu = new Form("MENU");
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
        frmSummary = new Form("Summary");
        txtaSummary = new TextArea(10, 20, TextArea.UNEDITABLE);
        txtaSummary.setEditable(false);
        frmSummary.addComponent(txtaSummary);
        cmdBack = new Command("Back");
        frmSummary.addCommand(cmdBack);
        frmSummary.addCommandListener(this);
        frmSummary.show();
    }

    private void ShowDL() {
        frmDl = new Form("Driving License");
        txtaDl = new TextArea(10, 20, TextArea.UNEDITABLE);
        txtaDl.setEditable(false);
        frmDl.addComponent(txtaDl);
        cmdBack = new Command("Back");
        frmDl.addCommand(cmdBack);
        frmDl.addCommandListener(this);
        frmDl.show();
    }

    private void ShowInsuaranceDetails() {
        frmInsurance = new Form("Insurance Details");
        txtaInsurance = new TextArea(10, 20, TextArea.UNEDITABLE);
        txtaInsurance.setEditable(false);
        frmInsurance.addComponent(txtaInsurance);
        cmdBack = new Command("Back");
        frmInsurance.addCommand(cmdBack);
        frmInsurance.addCommandListener(this);
        frmInsurance.show();
    }

    private void ShowOffences() {
        frmOffences = new Form("Offences");
        txtaOffences = new TextArea(10, 20, TextArea.UNEDITABLE);
        txtaOffences.setEditable(false);
        frmOffences.addComponent(txtaOffences);
        cmdBack = new Command("Back");
        frmOffences.addCommand(cmdBack);
        frmOffences.addCommandListener(this);
        frmOffences.show();
    }

    private void ShowUpdates() {
        frmUpdates = new Form("Updates");
        txtaUpdates = new TextArea(10, 20, TextArea.UNEDITABLE);
        txtaUpdates.setEditable(false);
        frmUpdates.addComponent(txtaUpdates);
        cmdBack = new Command("Back");
        frmUpdates.addCommand(cmdBack);
        frmUpdates.addCommandListener(this);
        frmUpdates.show();
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

            }if (ae.getSource() == btnLogin && current == frmLogin) {
                frmMenu.show();
            }else if (ae.getSource() == cmdBack && current == frmMenu) {
            frmLogin.show();
            }
            if (ae.getSource() == cmdBack && current == frmRegister2) {
                frmRegister1.show();
            }else if (ae.getSource() == cmdBack && current == frmDl) {
            frmMenu.show();
        } else if (ae.getSource() == cmdBack && current == frmSummary) {
            frmMenu.show();
        } else if (ae.getSource() == cmdBack && current == frmInsurance) {
            frmMenu.show();
        }else if (ae.getSource() == cmdBack && current == frmUpdates) {
            frmMenu.show();
        }else if (ae.getSource() == cmdBack && current == frmOffences) {
            frmMenu.show();
        }
            

            }

    private String dbfunction(String createaccountURL) {

        String response = null;
        InputStream is = null;
        StringBuffer sb = null;
        HttpConnection http = null;
        String url;

        try {
            // encode the url
            url = encodeURL(createaccountURL);
            // establish a HTTP connection
            http = (HttpConnection) Connector.open(url);
            // set the request method to GET
            http.setRequestMethod(HttpConnection.GET);
            // get server response
            if (http.getResponseCode() == HttpConnection.HTTP_OK) {
                sb = new StringBuffer();
                int ch;
                is = http.openInputStream();

                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }
                response = sb.toString();
            } else {
                //  showDialog("Network Error", "Please try again");
                System.out.println("Network Error: Please try again");
            }
        } catch (Exception ex) {
            // showDialog("Network Error", ex.getMessage());
            System.out.println("Network Error" + ex.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (sb != null) {
                    response = sb.toString();
                } else {
                    // showDialog("Network Error", "Please try again");
                    System.out.println("Network Error: Please try again");
                }
                if (http != null) {
                    http.close();
                }
            } catch (IOException ex) {
            }

        }
        return response;
    }

    private String getResponse(HttpConnection http, InputStream iStrm) throws IOException {
        String response = "";
        if (http.getResponseCode() == HttpConnection.HTTP_OK) {
            int length = (int) http.getLength();
            String str = null;
            if (length != -1) {
                byte servletData[] = new byte[length];
                iStrm.read(servletData);
                str = new String(servletData);
            } else {
                ByteArrayOutputStream bStrm = new ByteArrayOutputStream();

                int ch;
                while ((ch = iStrm.read()) != -1) {
                    bStrm.write(ch);
                }

                str = new String(bStrm.toByteArray());
                bStrm.close();
            }
            response = str;
        } else {
            response = new String(http.getResponseMessage());
        }

        return response;
    }

    private String encodeURL(String url) {
        url = replace(url, 'à', "%E0");
        url = replace(url, 'è', "%E8");
        url = replace(url, 'é', "%E9");
        url = replace(url, 'ì', "%EC");
        url = replace(url, 'ò', "%F2");
        url = replace(url, 'ù', "%F9");
        url = replace(url, '$', "%24");
        url = replace(url, '#', "%23");
        url = replace(url, '£', "%A3");
        url = replace(url, '@', "%40");
        url = replace(url, '\'', "%27");
        url = replace(url, ' ', "%20");

        return url;
    }

//split
    public static String[] split(String s, char separator) {
        Vector v = new Vector();
        for (int ini = 0, end = 0; ini < s.length(); ini = end + 1) {
            end = s.indexOf(separator, ini);
            if (end == -1) {
                end = s.length();
            }

            String st = s.substring(ini, end).trim();
            if (st.length() > 0) {
                v.addElement(st);
            } else {
                v.addElement("null");
            }
        }

        String temp[] = new String[v.size()];
        v.copyInto(temp);
        return temp;
    }

    private String replace(String source, char oldChar, String dest) {
        String str = "";
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != oldChar) {
                str += source.charAt(i);
            } else {
                str += dest;
            }
        }
        return str;
    }

}
