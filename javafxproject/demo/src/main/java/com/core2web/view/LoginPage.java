package com.core2web.view;
import com.core2web.controller.UserController; 
import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.control.PasswordField; 
import javafx.scene.control.TextField; 
import javafx.scene.image.ImageView; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginPage extends Application {
    private Stage c2w_ai_primaryStage;
private Scene c2w_ai_loginScene, c2w_ai_homeScene;
private UserController c2w_ai_userController = new UserController();
public static String c2w_ai_key;
// Method to initialize the login page
public void getLoginPage(Stage c2w_ai_primaryStage) {
this.c2w_ai_primaryStage = c2w_ai_primaryStage;
initLoginScene();
}
// Method to initialize the login scene
private void initLoginScene() {
ImageView c2w_ai_logo = new ImageView("/asset/login_8043682.png");
c2w_ai_logo.setFitWidth(120);
c2w_ai_logo.setPreserveRatio(true);
Label c2w_ai_title = new Label("Login");
c2w_ai_title.setStyle(
"-fx-font-size:25 ;-fx-font-weight: bold; -fx-pref-width: 300;-fx-pref-height: 30; -fx-alignment : CENTER; -fx-text-fill:#FFFFFFF");
VBox c2w_ai_header = new VBox(20, c2w_ai_logo, c2w_ai_title);
c2w_ai_header.setAlignment(Pos.CENTER);
Label c2w_ai_userLabel = new Label("Username:");
TextField c2w_ai_userTextField = new TextField();
c2w_ai_userTextField.setPromptText("Enter Username");
c2w_ai_userTextField.setStyle("-fx-max-width: 270; -fx-min-height: 30;-fx-background-radius: 15;");
c2w_ai_userTextField.setFocusTraversable(false);
Label c2w_ai_passLabel = new Label("Password:");
PasswordField c2w_ai_passField = new PasswordField();
c2w_ai_passField.setFocusTraversable(false);
c2w_ai_passField.setPromptText("Enter Password");
c2w_ai_passField.setStyle("-fx-pref-width: 270; -fx-min-height:30;-fx-background-radius: 15;");
Label c2w_ai_output = new Label();
c2w_ai_output.setStyle("-fx-text-fill: white;");
Button c2w_ai_loginButton = new Button("Login");
c2w_ai_loginButton.setStyle(
"-fx-pref-width: 70;-fx-min-height: 30;-fx-background-radius:15; -fx-background-color : #2196F3; -fx-text-fill:#FFFFFFF");
Label c2w_ai_signupButton = new Label("Signup");
c2w_ai_signupButton.setStyle(" -fx-background-radius: 15; -fx-text-fill: white");
// Set action for the login button
c2w_ai_loginButton.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent c2w_ai_event) {
if (!c2w_ai_userTextField.getText().isEmpty() && 
!c2w_ai_passField.getText().isEmpty()) {
if 
(c2w_ai_userController.authenticateUser(c2w_ai_userTextField.getText(),
c2w_ai_passField.getText())) {
initUserScene(c2w_ai_userTextField.getText());
c2w_ai_primaryStage.setScene(c2w_ai_homeScene);
c2w_ai_userTextField.clear();
c2w_ai_passField.clear();
} else {
c2w_ai_output.setText("Invalid Username or Password");
}
} else {
c2w_ai_output.setText("Please Enter Username andPassword");
}
}
});
// Set action for the signup button
c2w_ai_signupButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
@Override
public void handle(MouseEvent c2w_ai_event) {
showSignupScene();
c2w_ai_userTextField.clear();
c2w_ai_passField.clear();
}});
// Style the labels
c2w_ai_userLabel.setStyle("-fx-text-fill: white;");
c2w_ai_passLabel.setStyle("-fx-text-fill: white;");
// Create VBox layouts for the fields and buttons
VBox c2w_ai_fieldBox1 = new VBox(10, c2w_ai_userLabel, 
c2w_ai_userTextField); // VBox for username
c2w_ai_fieldBox1.setMaxSize(300, 30);
VBox c2w_ai_fieldBox2 = new VBox(10, c2w_ai_passLabel, 
c2w_ai_passField); // VBox for password
c2w_ai_fieldBox2.setMaxSize(300, 30);
// Main VBox layout for the login page
VBox c2w_ai_loginBox = new VBox(20, c2w_ai_header, c2w_ai_fieldBox1, 
c2w_ai_fieldBox2, c2w_ai_loginButton,
c2w_ai_signupButton, c2w_ai_output);
c2w_ai_loginBox.setStyle(
"-fx-pref-height : 200 ; -fx-alignment : CENTER ; -fx-padding :30 ;-fx-background-color : rgba(0, 0, 0);");
c2w_ai_loginBox.setMaxSize(300, 200);
Rectangle clip = new Rectangle(300, 650);
clip.setArcWidth(40);
clip.setArcHeight(40);
c2w_ai_loginBox.setClip(clip);
c2w_ai_loginScene = new Scene(c2w_ai_loginBox, 300, 650);
c2w_ai_loginScene.setFill(Color.TRANSPARENT);
}
// Method to initialize the user scene
private void initUserScene(String userName) {
HomePage c2w_ai_homePage = new HomePage();
c2w_ai_homePage.setStage(c2w_ai_primaryStage);
c2w_ai_homePage.setUserName(userName);
c2w_ai_homeScene = new 
Scene(c2w_ai_homePage.getView(this::handleLogout), 300, 650);
c2w_ai_homeScene.setFill(Color.TRANSPARENT);
c2w_ai_homePage.setScene(c2w_ai_homeScene);
}
// Method to get the login scene
public Scene getLoginScene() {
    return c2w_ai_loginScene;
}
// Method to show the login scene
public void showLoginScene() {
c2w_ai_primaryStage.setScene(c2w_ai_loginScene);
c2w_ai_primaryStage.show();
}
// Method to show the signup scene
private void showSignupScene() {
SignupPage signupPage = new SignupPage();
Scene signupScene = new 
Scene(signupPage.createSignupScene(this::handleBack), 300, 650);
signupScene.setFill(Color.TRANSPARENT);
c2w_ai_primaryStage.setScene(signupScene);
c2w_ai_primaryStage.show();
}
// Method to handle logout action
private void handleLogout() {
c2w_ai_primaryStage.setScene(c2w_ai_loginScene);
}
// Method to handle back action from signup
private void handleBack() {
c2w_ai_loginScene.setFill(Color.TRANSPARENT);
c2w_ai_primaryStage.setScene(c2w_ai_loginScene);
}
@Override
public void start(Stage c2w_ai_primaryStage) throws Exception {
Class.forName("com.core2web.configuration.FirebaseInitialization");
getLoginPage(c2w_ai_primaryStage);
c2w_ai_primaryStage.setScene(getLoginScene());
c2w_ai_primaryStage.setTitle("ChatApp");
c2w_ai_primaryStage.initStyle(StageStyle.TRANSPARENT);
c2w_ai_primaryStage.show();
}}