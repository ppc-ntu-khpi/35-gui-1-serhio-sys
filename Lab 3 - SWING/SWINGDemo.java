package com.mybank.gui;

import com.mybank.data.DataSource;
import com.mybank.domain.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alexander 'Taurus' Babich
 */
public class SWINGDemo {
    
    private final JEditorPane log;
    private final JButton show;
    private final JButton report;
    private final JComboBox clients;
    
    public SWINGDemo() {
        log = new JEditorPane("text/html", "");
        log.setPreferredSize(new Dimension(350, 150));
        show = new JButton("Show");
        report = new JButton("Report");
        clients = new JComboBox();
        for (int i=0; i<Bank.getNumberOfCustomers();i++)
        {
            clients.addItem(Bank.getCustomer(i).getLastName()+", "+Bank.getCustomer(i).getFirstName());
        }
        
    }
    
    private void launchFrame() {
        JFrame frame = new JFrame("MyBank clients");
        frame.setLayout(new BorderLayout());
        JPanel cpane = new JPanel();
        cpane.setLayout(new GridLayout(1, 3));
        
        cpane.add(clients);
        cpane.add(show);
        frame.add(cpane, BorderLayout.NORTH);
        frame.add(log, BorderLayout.CENTER);

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JEditorPane panel = new JEditorPane("text/html", "");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<center><h1>Customer Report</h1></center>");
                for (int i = 0;i<Bank.getNumberOfCustomers();i++){
                    Customer customer = Bank.getCustomer(i);
                    stringBuilder.append("<h2>"+customer.getFirstName()+" "+customer.getFirstName()+":</h2>");
                    for (int j = 0; j<customer.getNumberOfAccounts();j++){
                        Account account = customer.getAccount(j);
                        String type = account instanceof CheckingAccount?"Checking":"Savings";
                        stringBuilder.append("Account Type: "+type+"<br>Balance: "+account.getBalance()+"<br>");
                    }
                }
                panel.setText(stringBuilder.toString());
                panel.setPreferredSize(new Dimension(250,600));
                JFrame jFrame = new JFrame("Reports");
                jFrame.setLayout(new BorderLayout());
                jFrame.add(panel, BorderLayout.CENTER);
                jFrame.pack();
                jFrame.setLocationRelativeTo(log);
                panel.setAutoscrolls(true);
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFrame.setResizable(true);
                jFrame.setVisible(true);
            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer current = Bank.getCustomer(clients.getSelectedIndex());
                StringBuilder custInfo = new StringBuilder();
                custInfo.append("<br>&nbsp;<b><span style=\"font-size:2em;\">"+current.getLastName()+", "+
                        current.getFirstName()+"</span><br><hr>"+
                        "&nbsp;");
                //Adding to output all accounts
                for (int i = 0; i < current.getNumberOfAccounts(); i++)
                {
                    Account acc = current.getAccount(i);
                    String accType = current.getAccount(i)instanceof CheckingAccount?"Checking":"Savings";
                    custInfo.append("<b>Acc Type: </b>"+accType+
                            "<br>&nbsp;<b>Balance: <span style=\"color:red;\">$"+acc.getBalance()+"</span></b><br>");
                }

                //Adding to output all accounts
                log.setText(custInfo.toString());
            }
        });
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setResizable(false);
        frame.setVisible(true);
        cpane.add(report);
    }
    
    public static void main(String[] args) throws IOException {
        //File data loading
        File currentClass = new File(URLDecoder.decode(SWINGDemo.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath(), "UTF-8"));
        String classDirectory = currentClass.getParent();
        new DataSource(classDirectory+"\\35-gui-1-serhio-sys\\test.dat").loadData();
        //File data loading
        SWINGDemo demo = new SWINGDemo();        
        demo.launchFrame();
    }
    
}
