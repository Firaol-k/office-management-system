package chatpackage;

import chat.MessageContent;
import chat.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.application.Platform;
import javax.swing.Timer;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import loginpackage.DialogFrame;
import remoteserver.RemoteServerInterface;

public class ChatRoom implements Serializable {

    private HBox mainbox;
    private VBox sidebox;
    private JFXListView<HBox> chatArea;
    private JFXListView<Profile> onlinelist;
    private JFXTextArea messageInput;
    private JFXButton sendbut, filebut, groupsend;
    private HBox bottom;
    private TextFlow textflow;
    private Text text;

    public static RemoteServerInterface server;
    private String ip = "";
    public static Timer timer, timer2;

    public void initComponents(JFXPanel jfxpanel) {
        mainbox = new HBox();
        mainbox.setFillHeight(true);
        mainbox.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("chat.jpg")), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        mainbox.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        sidebox = new VBox(10);
        sidebox.setPrefSize(700, 700);

        messageInput = new JFXTextArea();
        messageInput.setMaxSize(400, 70);
        messageInput.setPromptText("Write message...");

        sendbut = new JFXButton("Send");
        filebut = new JFXButton("File");
        groupsend = new JFXButton("Group Text");
        filebut.getStyleClass().add("filebut");
        sendbut.getStyleClass().add("sendbut");
        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PAPERCLIP);
        icon.setSize("16");
        filebut.setGraphic(icon);
        sendbut.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.SEND));
        groupsend.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.GROUP));
        groupsend.setGraphicTextGap(5);
        filebut.setGraphicTextGap(5);
        sendbut.setGraphicTextGap(5);

        bottom = new HBox(20);
        bottom.setPrefSize(615, 80);
        bottom.setAlignment(Pos.CENTER);
        VBox buttonholder = new VBox(6, filebut, sendbut, groupsend);
        buttonholder.setAlignment(Pos.BOTTOM_CENTER);
        bottom.getChildren().addAll(messageInput, buttonholder);

        onlinelist = new JFXListView<>();
        onlinelist.getStyleClass().add("userlist");
        onlinelist.setPrefSize(200, 700);
        onlinelist.setVerticalGap(new Double(20));
        onlinelist.setExpanded(true);
        chatArea = new JFXListView<>();
        chatArea.setVerticalGap(new Double(25));
        chatArea.setExpanded(true);
        chatArea.setPrefSize(700, 600);
        chatArea.setCellFactory((ListView<HBox> L) -> new UsersCell());
        onlinelist.setCellFactory((ListView<Profile> P) -> new OnlineCell());

        sidebox.getChildren().addAll(chatArea, bottom);
        mainbox.getChildren().addAll(onlinelist, sidebox);

        onlinelist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (onlinelist.getSelectionModel().getSelectedItem() != null) {
                    ip = onlinelist.getSelectionModel().getSelectedItem().getIp();
                }
            }

        });
        //single message send   
        sendbut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!"".equals(ip)) {
                    if (!messageInput.getText().isEmpty() && !messageInput.getText().trim().isEmpty()) {
                        String branch = DialogFrame.logindialog.getBranch();
                        String textcontent = messageInput.getText().trim();
                        String source = branch + "/->" + DialogFrame.logindialog.getUserName();
                        MessageContent message = new MessageContent(source, textcontent, branch);
                        try {
                            server.addMessage(ip, message);
                            prettyView(textcontent, source, "right", message.getTime() + " Sent");
                            messageInput.setText("");
                        } catch (RemoteException ex) {
                            System.out.println(ex.getMessage() + "\n");
                        }
                    }
                }
            }
        });
        //filesend button handler
        filebut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser filechose = new FileChooser();
                filechose.getExtensionFilters().addAll(new ExtensionFilter("All File", "*.*"));
                File file = filechose.showOpenDialog(new Stage());
                if (file != null && !file.isDirectory()) {
                    String branch = DialogFrame.logindialog.getBranch();
                    String source = branch + "/->" + DialogFrame.logindialog.getUserName();
                    MessageContent message = new MessageContent(source, branch, file.getName(), getByteArray(file));
                    try {
                        server.addMessage(ip, message);
                        prettyFileView("<<*File Sent click to see*>>", source, "right", message.getTime() + " Sent", file);
                        messageInput.setText("");
                    } catch (RemoteException ex) {
                        System.out.println(ex.getMessage() + "\n");
                    }

                }

            }
        });

        groupsend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!messageInput.getText().isEmpty() && !messageInput.getText().trim().isEmpty()) {
                    String branch = DialogFrame.logindialog.getBranch();
                    String textcontent = messageInput.getText().trim();
                    String source = "Group Text :" + branch + "/->" + DialogFrame.logindialog.getUserName();
                    MessageContent message = new MessageContent(source, textcontent, branch);
                    try {
                        for (Profile p : onlinelist.getItems()) {
                            server.addMessage(p.getIp(), message);
                        }
                        prettyView(textcontent, source, "right", message.getTime() + " Sent");
                        messageInput.setText("");
                    } catch (RemoteException ex) {
                        System.out.println(ex.getMessage() + "\n");
                    }
                }

            }
        });

        Scene scene = new Scene(mainbox, 900, 700);
        jfxpanel.setScene(scene);

    }

    public void startListening() {
        timer = new Timer(50, new Listener());
        timer2 = new Timer(50, new Listener2());
        timer2.start();
        timer.start();
    }

//pretty view
    public void prettyView(String message, String from, String orientation, String time) {
        HBox tmp = new HBox(5);
        FontMetrics metric = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.getDefault());
        float swidth = metric.computeStringWidth(message);
        textflow = new TextFlow();
        text = new Text();
        if (swidth > 400) {
            text.wrappingWidthProperty().set(400);
            textflow.prefWidthProperty().bind(text.wrappingWidthProperty());
        }
        text.setText(from + "\n" + message + "\n" + time);
        textflow.getChildren().add(text);

        if (orientation.equalsIgnoreCase("leftmessage")) {
            textflow.getStyleClass().add("leftmessage");
            tmp.setAlignment(Pos.CENTER_LEFT);
        } else {
            textflow.getStyleClass().add("rightmessage");
            tmp.setAlignment(Pos.CENTER_RIGHT);
        }

        tmp.getChildren().add(textflow);
        chatArea.getItems().add(tmp);
    }

//pretty view forfile   
    public void prettyFileView(String message, String from, String orientation, String time, File file) {
        HBox tmp = new HBox(5);
        FontMetrics metric = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.getDefault());
        float swidth = metric.computeStringWidth(message);
        textflow = new TextFlow();
        text = new Text();
        if (swidth > 400) {
            text.wrappingWidthProperty().set(400);
            textflow.prefWidthProperty().bind(text.wrappingWidthProperty());
        }
        text.setText(from + "\n" + message + "\n" + time);
        textflow.getChildren().add(text);
        textflow.getStyleClass().add("hoverable");
        textflow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException ex) {
                    System.out.println("Unable to open a file");
                }
            }

        });
        if (orientation.equalsIgnoreCase("leftmessage")) {
            textflow.getStyleClass().add("leftmessage");
            tmp.setAlignment(Pos.CENTER_LEFT);
        } else {
            textflow.getStyleClass().add("rightmessage");
            tmp.setAlignment(Pos.CENTER_RIGHT);
        }

        tmp.getChildren().add(textflow);
        chatArea.getItems().add(tmp);
    }

//updates when ever new user logged in
    public void updateOnlineList() {
        ArrayList<String> iplist = new ArrayList<>();
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {

        try {
            String removeadd = server.checkRemoved(InetAddress.getLocalHost().getHostAddress());
            if (!removeadd.equals("")) {
                for (Profile pro : onlinelist.getItems()) {
                    if (removeadd.equals(pro.getIp())) {
                        onlinelist.getItems().remove(pro);
                    }
                }
            }

            removeadd = server.checkNewUser(InetAddress.getLocalHost().getHostAddress());
            if (!removeadd.equals("")) {
                onlinelist.getItems().add(server.getAllUsers().get(removeadd));
            }
        } catch (UnknownHostException ex) {
            return;
        } catch (RemoteException ex) {
            return;
        }

//                }
//            });
    }

//Online users filler
    public void fillOnlineUsers() {
        try {
            onlinelist.getItems().removeAll(onlinelist.getItems());
            onlinelist.getItems().addAll(server.getAllUsers().values());
        } catch (RemoteException ex) {
            return;
        }
    }

//reading bytes from the file
    public byte[] getByteArray(File file) {
        byte[] bytedata = null;
        try {
            bytedata = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            return null;
        }

        return bytedata;
    }

//write bytes to a file
    public File writeBytes(String filename, byte[] bytedata) {
        File file = new File(filename);
        if (file.exists()) {
            file = new File(filename + "1");
        }
        try {
            file.createNewFile();
            Files.write(file.toPath(), bytedata, StandardOpenOption.WRITE);
        } catch (IOException ex) {
            return null;
        }
        return file;
    }

//customized list cell view for online list    
    private class OnlineCell extends ListCell<Profile> {

        @Override
        protected void updateItem(Profile item, boolean empty) {
            super.updateItem(item, empty);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getStyleClass().add("leftcell");
                    setGraphic(null);
                    setText(null);
                }
            });

            if (item != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setText(item.getName());
                    }
                });
            }
        }
    }

//customized list cell view for chat area
    private class UsersCell extends ListCell<HBox> {

        @Override
        protected void updateItem(HBox item, boolean empty) {
            super.updateItem(item, empty);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    getStyleClass().add("cell");
                    setGraphic(null);
                    setText(null);
                }
            });

            if (item != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        setGraphic(item);
                    }
                });
            }
        }
    }

//listener for incoming messages for each 50ms
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            MessageContent content = null;
            try {

                content = server.checkMessage(InetAddress.getLocalHost().getHostAddress());
                if (content != null) {
                    if (content.hasFile()) {
                        File file = writeBytes(content.getFileName(), content.getFile());
                        prettyFileView("<<*File received click to see*>>", content.getFrom(), "leftmessage", content.getTime() + " Received", file);
                    } else {
                        prettyView(content.getMessage(), content.getFrom(), "leftmessage", content.getTime() + " Recieved");
                    }
                }
            } catch (RemoteException | UnknownHostException ex) {
                return;
            }
//            updateOnlineList();
        }

    }

    private class Listener2 implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    updateOnlineList();
                }
            });

        }

    }

}
