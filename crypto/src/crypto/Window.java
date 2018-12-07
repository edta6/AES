package crypto;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Window extends Application {  
	
    private Stage primaryStage;
    private Scene scene;
    private GridPane grid;
    private Text title;
    private HBox hbTitle, hbUserTextField, hbPW, hbBtn, hbBtn1, hbBtn2;
    private Label userName, pw;
    private Button btn, btn1, btn2;
    private TextField userTextField, pwBox;
    File test;
    
    private void prepareScene(){
        
        grid = new GridPane();
        grid.setId("gridPane");
        
        title = new Text("Nr Indeksu");
        title.setId("title");
        
        hbTitle = new HBox();
        hbTitle.setId("hbTitle");
        hbTitle.getChildren().add(title);

        grid.add(hbTitle, 0, 0, 2, 1); 
        
        userName = new Label("File Path:");
        userName.setId("userName");
        grid.add(userName, 0, 1);
        
        userTextField = new TextField();
        userTextField.setId("userTextField");
       
        hbUserTextField = new HBox();
        hbUserTextField.setId("hbUserTextField");
        hbUserTextField.getChildren().add(userTextField);
        grid.add(hbUserTextField, 1, 1);
        
        pw = new Label("Key:");
        pw.setId("userName");
        grid.add(pw, 0, 2);
        
        pwBox = new TextField();
        pwBox.setId("userTextField");
        
        hbPW = new HBox();
        hbPW.setId("hbUserTextField");
        hbPW.getChildren().add(pwBox);
        grid.add(hbPW, 1, 2);
         
        btn = new Button("Code");
        btn.setId("btnSing");
        
        hbBtn = new HBox(10);
        hbBtn.setId("hbBtn");
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 0, 3, 2, 1);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start program = new Start(userTextField.getText(),pwBox.getText());
                String temp = new String(pwBox.getText());
                if(temp.length()==32 || temp.length()==48 || temp.length()==64 ) {
                try {
                	program.CalcNumRounds(pwBox.getText());
					program.readByte();
					program.fileCode();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }
                else {
                	Alert alert = new Alert(AlertType.ERROR);
                	alert.setTitle("Error Dialog");
                	alert.setHeaderText("Look, an Error Dialog");
                	alert.setContentText("Z³a d³ugoœæ klucza!");

                	alert.showAndWait();
                }
            }
        });
        
        //--------------------
        btn1 = new Button("Decode");
        btn1.setId("btnSing");
        
        hbBtn1 = new HBox(10);
        hbBtn1.setId("hbBtn");
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 0, 4, 2, 1);
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start program1 = new Start(userTextField.getText(),pwBox.getText());
                String temp = new String(pwBox.getText());
                if(temp.length()==32 || temp.length()==48 || temp.length()==64 ) {
                try {
                	program1.CalcNumRounds(pwBox.getText());
					program1.readByte();
					program1.fileDecode();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }
                
                else {
                	Alert alert = new Alert(AlertType.ERROR);
                	alert.setTitle("Error Dialog");
                	alert.setHeaderText("Look, an Error Dialog");
                	alert.setContentText("Z³a d³ugoœæ klucza!");

                	alert.showAndWait();
                }
                
            
            }
        });
        //--------------------
        
        btn2 = new Button("Wybierz plik");
        btn2.setId("btnSing");
        
        hbBtn2 = new HBox(10);
        hbBtn2.setId("hbBtn");
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 5, 2, 1);
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("Open Resource File");
            	test = fileChooser.showOpenDialog(null);
            	userTextField.setText(test.getAbsolutePath());
            }
        });
        //--------------------
        
        
        
        
        scene = new Scene(grid);
        
        scene.getStylesheets().add(Window.class.getResource("Login.css").toExternalForm());
    }
    
    @Override
    public void start(Stage stage) {
        
        primaryStage = new Stage();
        
        prepareScene();
        
        primaryStage.setTitle("Cryptografia AES");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
	        
	   public static void main(String args[]){           
	      launch(args);      
	   } 
	} 
