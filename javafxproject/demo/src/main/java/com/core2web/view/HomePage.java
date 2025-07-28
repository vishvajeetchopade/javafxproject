package com.core2web.view;
import javafx.event.ActionEvent; 
import javafx.event.Event; 
import javafx.event.EventHandler; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Parent; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.StackPane; 
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 
import javafx.scene.shape.Rectangle; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.stage.Stage;
public class HomePage {
Stage c2w_ai_primaryStage;
String c2w_ai_userName;
Scene c2w_ai_homePageScene, c2w_ai_searchPageScene, c2w_ai_notesPageScene;
public void setScene(Scene c2w_ai_homePageScene) {
this.c2w_ai_homePageScene = c2w_ai_homePageScene;
}
public void setStage(Stage c2w_ai_stage) {
this.c2w_ai_primaryStage = c2w_ai_stage;
}
public void setUserName(String c2w_ai_userName) {
this.c2w_ai_userName = c2w_ai_userName;
}
public Parent getView(Runnable c2w_ai_logout) {
Circle c2w_ai_search_circle = new Circle();
c2w_ai_search_circle.setRadius(70);
c2w_ai_search_circle.setFill(Color.WHITE);
Circle c2w_ai_notes_circle = new Circle();
c2w_ai_notes_circle.setRadius(70);
c2w_ai_notes_circle.setFill(Color.WHITE);
Text c2w_ai_label_search = new Text("Search");
c2w_ai_label_search.setFill(Color.BLACK);
c2w_ai_label_search.setFont(Font.font(20));
Text c2w_ai_label_note = new Text("Notes");
c2w_ai_label_note.setFill(Color.BLACK);
c2w_ai_label_note.setFont(Font.font(20));
StackPane c2w_ai_stack_search_circle = new 
StackPane(c2w_ai_search_circle, c2w_ai_label_search);
c2w_ai_stack_search_circle.setOnMouseClicked(new EventHandler<Event>() 
{
@Override
public void handle(Event arg0) {
openSearchPage();
System.out.println("called");
}
});
StackPane c2w_ai_stack_notes_circle = new 
StackPane(c2w_ai_notes_circle, c2w_ai_label_note);
c2w_ai_stack_notes_circle.setOnMouseClicked(new EventHandler<Event>() {
@Override
public void handle(Event arg0) {
openNotesPage();
System.out.println("called");
}
});
VBox c2w_ai_vb = new VBox(60, c2w_ai_stack_search_circle, 
c2w_ai_stack_notes_circle);
c2w_ai_vb.setStyle(" -fx-alignment : center;");
Label c2w_ai_title = new Label("Chat App");
c2w_ai_title.setStyle(" -fx-text-fill: white;");
Button c2w_ai_backButton = new Button("Back");
c2w_ai_backButton.setStyle(
"-fx-background-color:rgb(25, 73, 109); -fx-text-fill: white;-fx-background-radius: 20; -fx-font-size: 15px;");
c2w_ai_backButton.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent arg0) {
c2w_ai_logout.run();
}
});
HBox c2w_ai_titleBar = new HBox(50, c2w_ai_backButton, c2w_ai_title);
c2w_ai_titleBar.setPadding(new Insets(10));
c2w_ai_titleBar.setAlignment(Pos.CENTER_LEFT);
c2w_ai_titleBar.setPrefHeight(50);
c2w_ai_titleBar.setStyle("-fx-background-color:rgb(38, 38, 41);-fx-text-fill: white;");
VBox c2w_ai_root = new VBox(120, c2w_ai_titleBar, c2w_ai_vb);
c2w_ai_root.setStyle(
"-fx-background-color: black; -fx-alignment : top center;-fx-pref-width : 500; -fx-pref-height : 650; -fx-font-size: 20px;-fx-font-family: 'Segoe UI';");
// Scene sc = new Scene(root, 500, 650);
Rectangle c2w_ai_clip = new Rectangle(300, 650);
c2w_ai_clip.setArcWidth(40);
c2w_ai_clip.setArcHeight(40);
c2w_ai_root.setClip(c2w_ai_clip);
return c2w_ai_root;
}
public void openSearchPage() {
SearchPage c2w_ai_searchPage = new SearchPage();
c2w_ai_searchPage.setC2w_ai_userName(c2w_ai_userName);
c2w_ai_searchPage.setC2w_ai_stage(c2w_ai_primaryStage);
c2w_ai_searchPageScene = new 
Scene(c2w_ai_searchPage.getView(this::backToHomePage), 300, 650);
c2w_ai_searchPageScene.setFill(Color.TRANSPARENT);
c2w_ai_searchPage.setScene(c2w_ai_searchPageScene);
c2w_ai_primaryStage.setScene(c2w_ai_searchPageScene);
}
public void openNotesPage() {
NotesPage c2w_ai_notesPage = new NotesPage();
c2w_ai_notesPage.setC2w_ai_userName(c2w_ai_userName);
c2w_ai_notesPageScene = new 
Scene(c2w_ai_notesPage.getView(this::backToHomePage), 300, 650);
c2w_ai_notesPageScene.setFill(Color.TRANSPARENT);
c2w_ai_notesPage.setScene(c2w_ai_notesPageScene);
c2w_ai_primaryStage.setScene(c2w_ai_notesPageScene);
}
public void backToHomePage() {
c2w_ai_primaryStage.setScene(c2w_ai_homePageScene);}}



