package loginpackage;

import com.client.FrameUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import remoteserver.RemoteServerInterface;


public class DialogFrame extends javax.swing.JDialog {
   
    private RemoteServerInterface server;
    private String Ip=""; 
    private String protocol="";
    private ArrayList<Map<String, Object>> map;
    private FrameUI mainframe;
    public static DialogFrame logindialog;
    
    public DialogFrame(java.awt.Frame parent,boolean modal) {
        initComponents();
        disableInputs(true);
        mainframe = new FrameUI();
        setIconImage(new ImageIcon(getClass().getResource("/icons/Key2.png")).getImage());
    }

    
         
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        server_IP = new javax.swing.JTextField();
        d_branch_Id = new javax.swing.JPasswordField();
        d_cancelbut = new javax.swing.JButton();
        d_okbut = new javax.swing.JButton();
        d_holder_pass = new javax.swing.JPasswordField();
        d_branch_name = new javax.swing.JComboBox<>();
        ipinfo = new javax.swing.JLabel();
        instead_search = new javax.swing.JTextField();
        label_d = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        d_pcholder = new javax.swing.JTextField();
        reginfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImages(null);
        setResizable(false);

        dialogpanel.setBackground(new java.awt.Color(5, 16, 72));
        dialogpanel.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("User Login");
        dialogpanel.add(jLabel2);
        jLabel2.setBounds(100, 10, 370, 50);

        jSeparator1.setForeground(new java.awt.Color(204, 255, 255));
        dialogpanel.add(jSeparator1);
        jSeparator1.setBounds(10, 70, 550, 10);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Branch ID :");
        dialogpanel.add(jLabel3);
        jLabel3.setBounds(40, 300, 140, 50);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Branch :");
        dialogpanel.add(jLabel4);
        jLabel4.setBounds(70, 200, 110, 50);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Server IP :");
        dialogpanel.add(jLabel5);
        jLabel5.setBounds(40, 130, 140, 50);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Computer holder :");
        dialogpanel.add(jLabel6);
        jLabel6.setBounds(30, 380, 150, 50);

        server_IP.setBackground(new java.awt.Color(0,0,0,100));
        server_IP.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        server_IP.setForeground(new java.awt.Color(255, 255, 255));
        server_IP.setBorder(null);
        server_IP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                server_IPActionPerformed(evt);
            }
        });
        dialogpanel.add(server_IP);
        server_IP.setBounds(230, 130, 320, 50);

        d_branch_Id.setBackground(new java.awt.Color(0,0,0,100));
        d_branch_Id.setFont(new java.awt.Font("Goudy Stout", 1, 24)); // NOI18N
        d_branch_Id.setForeground(new java.awt.Color(255, 255, 255));
        d_branch_Id.setBorder(null);
        d_branch_Id.setEchoChar('.');
        dialogpanel.add(d_branch_Id);
        d_branch_Id.setBounds(230, 300, 320, 50);

        d_cancelbut.setBackground(new java.awt.Color(153, 153, 153));
        d_cancelbut.setForeground(new java.awt.Color(255, 255, 255));
        d_cancelbut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clear.png"))); // NOI18N
        d_cancelbut.setToolTipText("");
        d_cancelbut.setBorder(null);
        d_cancelbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_cancelbutActionPerformed(evt);
            }
        });
        dialogpanel.add(d_cancelbut);
        d_cancelbut.setBounds(410, 570, 70, 40);

        d_okbut.setBackground(new java.awt.Color(102, 102, 102));
        d_okbut.setForeground(new java.awt.Color(255, 255, 255));
        d_okbut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/approve.png"))); // NOI18N
        d_okbut.setBorder(null);
        d_okbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d_okbutActionPerformed(evt);
            }
        });
        dialogpanel.add(d_okbut);
        d_okbut.setBounds(490, 570, 70, 40);

        d_holder_pass.setBackground(new java.awt.Color(0,0,0,100));
        d_holder_pass.setFont(new java.awt.Font("Goudy Stout", 1, 24)); // NOI18N
        d_holder_pass.setForeground(new java.awt.Color(255, 255, 255));
        d_holder_pass.setBorder(null);
        d_holder_pass.setEchoChar('.');
        dialogpanel.add(d_holder_pass);
        d_holder_pass.setBounds(230, 460, 320, 50);

        d_branch_name.setBackground(new java.awt.Color(204, 204, 204));
        d_branch_name.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        d_branch_name.setForeground(new java.awt.Color(204, 204, 255));
        dialogpanel.add(d_branch_name);
        d_branch_name.setBounds(230, 200, 320, 50);

        ipinfo.setForeground(new java.awt.Color(255, 255, 255));
        ipinfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Info_1.png"))); // NOI18N
        ipinfo.setText("Give the IP of your server to run your program");
        dialogpanel.add(ipinfo);
        ipinfo.setBounds(220, 90, 320, 30);

        instead_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        instead_search.setToolTipText("search instead");
        instead_search.setName(""); // NOI18N
        instead_search.setOpaque(false);
        instead_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instead_searchActionPerformed(evt);
            }
        });
        dialogpanel.add(instead_search);
        instead_search.setBounds(350, 250, 200, 30);

        label_d.setForeground(new java.awt.Color(255, 255, 255));
        label_d.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label_d.setText("Search instead >>");
        dialogpanel.add(label_d);
        label_d.setBounds(220, 250, 120, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Holder password :");
        dialogpanel.add(jLabel8);
        jLabel8.setBounds(40, 460, 140, 50);

        d_pcholder.setBackground(new java.awt.Color(0,0,0,100));
        d_pcholder.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        d_pcholder.setForeground(new java.awt.Color(255, 255, 255));
        d_pcholder.setBorder(null);
        dialogpanel.add(d_pcholder);
        d_pcholder.setBounds(230, 380, 320, 50);

        reginfo.setForeground(new java.awt.Color(255, 255, 255));
        reginfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Info_1.png"))); // NOI18N
        reginfo.setText("If you are a new Holder you will be registered automatically!!");
        dialogpanel.add(reginfo);
        reginfo.setBounds(10, 570, 390, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(dialogpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(dialogpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    /***Getters for branchName,UserName,ServerIP**/
    public String getBranch(){
      return d_branch_name.getSelectedItem().toString();
    }
    public String getUserName(){
      return d_pcholder.getText();
    }
    public String getServerIp(){
      return server_IP.getText();
    }  
 
   
    /** Sets counted Data for Sale,Purchase and Employee **/
    public void setCounts(){
        try {
            mainframe.total_sales.setText("Total Sales :" + (server.getCounted("sales", getBranch())+1 ));
            mainframe.total_purchase.setText("Total Purchase :" +(server.getCounted("buy", getBranch())+1 ));
            mainframe.total_employee.setText("Total Employees :"+ (server.getCounted("employee", getBranch())+1 ));
        } catch (RemoteException ex) {
            mainframe.total_sales.setText("Total Sales : Unknown");
            mainframe.total_purchase.setText("Total Purchase : Unknown");
            mainframe.total_employee.setText("Total Employees : Unknown");
        } catch (SQLException ex) {
            
        }
    }
    
    /** Input verifier for data **/
    public boolean verifyInput() throws RemoteException,SQLException{
      String branch = d_branch_name.getSelectedItem().toString();
      String id = d_branch_Id.getText();
      String holder = d_pcholder.getText();
      String password = d_holder_pass.getText();
      String query1 = "SELECT Name FROM branch WHERE ID='"+ id + "' AND Name='"+ branch+"'";
      String query2 = "SELECT * FROM holder WHERE Password='"+ password +"'";  
      map = server.selectFromDb(query2);
      
      if(id.isEmpty() || holder.isEmpty() || password.isEmpty()){
          JOptionPane.showMessageDialog(null,"You must fill all fields","Empty Input",JOptionPane.WARNING_MESSAGE);
          return false;
      }
      else if(id.trim().isEmpty() || holder.trim().isEmpty() || password.trim().isEmpty()){
          JOptionPane.showMessageDialog(null,"You must fill all fields","Empty Input",JOptionPane.WARNING_MESSAGE);
          return false;
      }
      else if(password.length() < 6){
          JOptionPane.showMessageDialog(null,"Password must be atleast 6 symbols","Unaccepted password", JOptionPane.INFORMATION_MESSAGE);
          return false;
      }
      else if(server.selectFromDb(query1).isEmpty()){
          JOptionPane.showMessageDialog(null,"Incorrect Branch-Id","ID Incorrect",JOptionPane.WARNING_MESSAGE);
          return false;
      }
      else if(!map.isEmpty() && map.get(0).get("Name").toString().equalsIgnoreCase(holder) && map.get(0).get("BranchID").toString().equals(id)){
         return true;
      }
      else if(map.isEmpty()){
             int choice = JOptionPane.showConfirmDialog(null,"You are a new user!\ndo you want to Register?","New User",JOptionPane.YES_NO_OPTION);
              if(choice == JOptionPane.YES_OPTION){
                 query1 = "INSERT INTO holder values('"+holder.trim()+"','"+password.trim()+"','"+id.trim()+"')";
                 server.insertToDb(query1);
                    JOptionPane.showMessageDialog(null,"Registeration Success!!","Sign-up" ,JOptionPane.PLAIN_MESSAGE);
                 return true;      
              }else{
                 return false;
              }     
              
      }
      else{
        return false;
      }
       
    }
    
    /** Lookup the Remote Object **/ 
    public void setupServer() throws RemoteException, NotBoundException{
        Ip = server_IP.getText().trim();
        protocol = "rmi://"+ Ip + "/ServerRoom";
        Registry reg = LocateRegistry.getRegistry(Ip, 8077);
        server = (RemoteServerInterface)reg.lookup(protocol);
    
    }
   
    /** fetch branches from DB and add on the combo-box **/
    public void setupBranch() throws RemoteException, SQLException{
       String query = "SELECT Name From branch";
       map = server.selectFromDb(query);
       if(!map.isEmpty()){
         for(int i=0 ; i<map.size(); i++){
           d_branch_name.addItem(map.get(i).get("Name").toString());
         }
       }  
    }
    
    /** input field disabler **/
    private void disableInputs(boolean bool){
        d_branch_name.setEnabled(!bool);
        instead_search.setEnabled(!bool);
        d_branch_Id.setEnabled(!bool);
        d_pcholder.setEnabled(!bool);
        d_holder_pass.setEnabled(!bool);
        d_okbut.setEnabled(!bool);
        reginfo.setVisible(!bool);
        if(!bool){
          ipinfo.setVisible(bool);
        }
    }
    
    /** Server-IP action handler **/
    private void server_IPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_server_IPActionPerformed
        try {
            setupServer();
            FrameUI.setServer(server);
            setupBranch();
            disableInputs(false);
            server_IP.setEnabled(false);

        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Remote Exception",JOptionPane.ERROR_MESSAGE);
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Server Error",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"SQL Error",JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_server_IPActionPerformed

    /** Cancel button handler(clear input fields) **/
    private void d_cancelbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_cancelbutActionPerformed
        d_branch_Id.setText("");
        d_pcholder.setText("");
        d_holder_pass.setText("");
    }//GEN-LAST:event_d_cancelbutActionPerformed

    /** OK button handler **/
    private void d_okbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d_okbutActionPerformed
        try {
            if(verifyInput()){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainframe = new FrameUI();
                        mainframe.setVisible(true);
                        logindialog.setVisible(false);
//                        mainframe.setProfile(getUserName(), getBranch());
                        mainframe.branch_home.setText("Branch Name : "+ getBranch());
                        mainframe.Id_home.setText("Branch Id : "+ d_branch_Id.getText());
                        setCounts();
                    }
                });
            }else{
               JOptionPane.showMessageDialog(this,"Login Fail\n1->try another password","Access Denied",JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Cookie Error",JOptionPane.WARNING_MESSAGE);  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Remote SQL Error",JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_d_okbutActionPerformed
    
    /** search branch input action handler **/
    private void instead_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instead_searchActionPerformed
        d_branch_name.setSelectedItem(instead_search.getText().trim().toLowerCase());
    }//GEN-LAST:event_instead_searchActionPerformed
    
    
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    logindialog = new DialogFrame(new JFrame(),true);
                    logindialog.addWindowListener(new WindowAdapter() {
                    @Override
                     public void windowClosing(WindowEvent e){
                        System.exit(0);
                     }
                  });
                 logindialog.setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField d_branch_Id;
    private javax.swing.JComboBox<String> d_branch_name;
    private javax.swing.JButton d_cancelbut;
    private javax.swing.JPasswordField d_holder_pass;
    private javax.swing.JButton d_okbut;
    private javax.swing.JTextField d_pcholder;
    private javax.swing.JPanel dialogpanel;
    private javax.swing.JTextField instead_search;
    private javax.swing.JLabel ipinfo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label_d;
    private javax.swing.JLabel reginfo;
    private javax.swing.JTextField server_IP;
    // End of variables declaration//GEN-END:variables
}
