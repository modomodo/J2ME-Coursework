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
public class adminpolice extends MIDlet implements ActionListener {

    Form current, frmLogin, frmMenu, frmRegister, frmUpdatesR, frmDl, frmInsurance, frmOffences, 
            frmSummary, frmUpdates, frmAddOffences;
    TextField txtRegNo, txtRegNoR, txtFirstNameR, txtLastNameR, txtIDNoR;
    TextArea txtaUpdateR, txtaDl, txtaInsurance, txtaOffences, txtaSummary, txtaUpdates,
            txtaAddOffences;
    Button btnLogin, btnRegister, btnSummary, btnDL, btnInsuaranceDetails, btnOffences,
            btnUpdates, btnRegisterR, btnUpdatesR, btnSubmit, btnOffencesR, btnAdd;
    Command cmdExit, cmdBack;
    String updates;

    public void startApp() {
        Display.init(this);
        try {
            //access the theme created in the resource editor
            Resources res = Resources.open("/adminpolice.res");
            UIManager.getInstance().setThemeProps(res.getTheme("adminpolice"));
        } catch (IOException ex) {
            System.out.println("Unable to get theme" + ex.getMessage());
        }

        frmLogin = new Form("Login");
        cmdExit = new Command("Exit", 0);
        frmLogin.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        txtRegNo = new TextField("", 20);

        btnLogin = new Button("Login");
        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowMenu();

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
                ShowUpdatesR();

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

    public void ShowMenu() {
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
//                Label title = new Label(detailNews.getTitle()); 
//                form2.setTitleComponent(title);
//                String Description = detailNews.getDescription();
//                System.out.println("Description" + Description);//Here i am able to get different Description values Related to myList Screen but in text area it is displaying First one always
//                big = new TextArea();
//
//                big.setEditable(false);
//                big.setText(Description);
//
//                form2.addComponent(pubDate);
//                form2.addComponent(big);
//                form2.show();
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

    private void ShowRegister() {
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

    private void ShowUpdatesR() {
        frmUpdatesR = new Form("Register Updates");
        txtaUpdateR = new TextArea(10, 20, TextArea.ANY);
        txtaUpdateR.setEditable(true);
        btnSubmit = new Button("Update");
        btnSubmit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                updates = txtaUpdateR.getText().trim();
                //check if password match
               
                    //send to database
                    String updateURL = "http://localhost/project/register2.php?email_address="+updates;
                         //call the dbfunction method
                    String updateResponse = dbfunction(updateURL);
                    int resp = Integer.parseInt(updateResponse);
                    if (resp == 1) {
                        //send the user to login form
//                        txtaValidate.setText("you've registered successfully");
//                      validate.setTimeout(1000);
//                      validate.show(70,70,10,10,true);
                        frmLogin.show();
                    } else {
                        //alert "registration failed"  
                        //System.out.println("Failed");

                    }

               
            }
        });
        frmUpdatesR.addComponent(txtaUpdateR);
        frmUpdatesR.addComponent(btnSubmit);
        cmdBack = new Command("Back", 0);
        frmUpdatesR.addCommand(cmdBack);
        frmUpdatesR.addCommandListener(this);
        frmUpdatesR.show();
        
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
        String drivingLicenseURL = "http://localhost/mbank/services.php";
        String ServicesResponse = dbfunction(drivingLicenseURL);
        String[] ServicesArray = split(ServicesResponse, '#');
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
        btnAdd = new Button("Add");
        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                ShowAddOffences();
            }
        });
        frmOffences.addComponent(txtaOffences);
        frmOffences.addComponent(btnAdd);
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
    
     private void ShowAddOffences() {
        frmAddOffences = new Form("Add Offences");
        txtaAddOffences = new TextArea(10, 20, TextArea.ANY);
        txtaAddOffences.setEditable(true);
        btnOffencesR = new Button("Submit");
        btnOffencesR.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
              ShowOffences();
            }
        });
        
        frmAddOffences.addComponent(txtaAddOffences);
        frmAddOffences.addComponent(btnOffencesR);
        cmdBack = new Command("Back");
        frmAddOffences.addCommand(cmdBack);
        frmAddOffences.addCommandListener(this);
        frmAddOffences.show();
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
        } else if (ae.getSource() == btnLogin && current == frmLogin) {
            frmLogin.show();
        } else if (ae.getSource() == btnRegister && current == frmLogin) {
            frmRegister.show();
        
        } else if (ae.getSource() == cmdBack && current == frmMenu) {
            frmLogin.show();
        } else if (ae.getSource() == cmdBack && current == frmRegister) {
            frmLogin.show();
        }else if (ae.getSource() == cmdBack && current == frmUpdatesR) {
            frmLogin.show();
        }else if (ae.getSource() == cmdBack && current == frmDl) {
            ShowMenu();
        } else if (ae.getSource() == cmdBack && current == frmSummary) {
            ShowMenu();
        } else if (ae.getSource() == cmdBack && current == frmInsurance) {
            ShowMenu();
        }else if (ae.getSource() == cmdBack && current == frmUpdates) {
            ShowMenu();
        }else if (ae.getSource() == cmdBack && current == frmOffences) {
            ShowMenu();
        }else if (ae.getSource() == cmdBack && current == frmAddOffences) {
            ShowOffences();
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
