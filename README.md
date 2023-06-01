[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=11264153)
# UI Lab 3

![](/images/1.png)
![](/images/2.png)

## Змінений код 

``` java
//Load Saved data
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


//Output all customer accounts
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

//Customers report handler
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
```