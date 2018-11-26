package crypto;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Window extends Application {  
	
    private Stage primaryStage;
    private Scene scene;
    private GridPane grid;
    private Text title;
    private HBox hbTitle, hbUserTextField, hbPW, hbBtn, hbBtn1;
    private Label userName, pw;
    private Button btn, btn1;
    private TextField userTextField, pwBox;

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
                try {
					program.readByte();
					program.fileCode();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
                try {
					program1.readByte();
					program1.fileDecode();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
