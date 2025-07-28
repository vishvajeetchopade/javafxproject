package com.core2web.view;
import java.util.List;
import com.core2web.controller.FormatController; 
import com.core2web.controller.NotesController; 
import com.core2web.model.Note; 
import javafx.animation.KeyFrame; 
import javafx.animation.KeyValue; 
import javafx.animation.Timeline; 
import javafx.application.Platform; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Parent; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.control.ScrollPane; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.layout.Background; 
import javafx.scene.layout.BackgroundFill; 
import javafx.scene.layout.CornerRadii; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.Pane; 
import javafx.scene.layout.Priority; 
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.util.Duration;
public class NotesPage {
private VBox c2w_ai_chatBox = new VBox(15);
ScrollPane c2w_ai_scrollPane = null;
Scene c2w_ai_notesScene;
String c2w_ai_userName;
FormatController c2w_ai_formatController = new FormatController();
NotesController c2w_ai_notesController = new NotesController();
public void setScene(Scene c2w_ai_notesScene) {
this.c2w_ai_notesScene = c2w_ai_notesScene;
}
public void setC2w_ai_userName(String c2w_ai_userName) {
this.c2w_ai_userName = c2w_ai_userName;
}
public Parent getView(Runnable c2w_ai_back) {
c2w_ai_scrollPane = new ScrollPane(c2w_ai_chatBox);
c2w_ai_scrollPane.setFitToWidth(true);
c2w_ai_scrollPane.setStyle("-fx-background: transparent;-fx-background-color: transparent;");
c2w_ai_scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
c2w_ai_scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
c2w_ai_scrollPane.setPannable(false);
c2w_ai_scrollPane.setOnScroll(event -> event.consume());
Platform.runLater(() -> c2w_ai_scrollPane.setVvalue(1.0));
c2w_ai_chatBox.setPadding(new Insets(10));
c2w_ai_chatBox.setBackground(
new Background(new BackgroundFill(Color.TRANSPARENT, 
CornerRadii.EMPTY, Insets.EMPTY)));
List<Note> c2w_ai_notes = 
c2w_ai_notesController.getAllNotesForUser(c2w_ai_userName);
System.out.println(c2w_ai_notes);
c2w_ai_notes.forEach(note ->{
// addUserBubble(note.getQuestion());
// addBotBubble(note.getAnswer());
c2w_ai_chatBox.getChildren().add(createCard(note.getQuestion(), 
note.getAnswer()));
});
Label c2w_ai_title = new Label("Notes");
c2w_ai_title.setStyle(" -fx-text-fill: white;");
Button c2w_ai_backButton = new Button("Back");
c2w_ai_backButton.setStyle(
"-fx-background-color:rgb(25, 73, 109); -fx-text-fill: white;-fx-background-radius: 20; -fx-font-size: 15px;");
c2w_ai_backButton.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent arg0) {
c2w_ai_back.run();
}
});
HBox c2w_ai_titleBar = new HBox(50, c2w_ai_backButton, c2w_ai_title);
c2w_ai_titleBar.setPadding(new Insets(10));
c2w_ai_titleBar.setAlignment(Pos.CENTER_LEFT);
c2w_ai_titleBar.setPrefHeight(50);
c2w_ai_titleBar.setStyle("-fx-background-color:rgb(38, 38, 41);-fx-text-fill: white;");
VBox c2w_ai_root = new VBox(10, c2w_ai_titleBar, c2w_ai_scrollPane);
c2w_ai_root.setStyle("-fx-background-color: black; -fx-font-size: 20px;-fx-font-family: 'Segoe UI';");
VBox.setVgrow(c2w_ai_scrollPane, Priority.ALWAYS);
Rectangle c2w_ai_clip = new Rectangle(300, 650);
c2w_ai_clip.setArcWidth(40);
c2w_ai_clip.setArcHeight(40);
c2w_ai_root.setClip(c2w_ai_clip);
return c2w_ai_root;
}
private Pane createCard(String c2w_ai_title, String c2w_ai_expandedContent) 
{
VBox c2w_ai_card = new VBox();
c2w_ai_card.setBackground(new Background(new 
BackgroundFill(Color.WHITE, new CornerRadii(8), Insets.EMPTY)));
c2w_ai_card.setPadding(new Insets(10));
c2w_ai_card.setMaxWidth(Double.MAX_VALUE);
c2w_ai_card.setStyle("-fx-background-color:rgba(34, 17, 129, 0.42);-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0.5, 0, 2);");
c2w_ai_card.setSpacing(10);
Label c2w_ai_titleLabel = new Label(c2w_ai_title);
c2w_ai_titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight:bold;");
// The expanded content area initially hidden
Label c2w_ai_contentLabel = new Label(c2w_ai_expandedContent);
c2w_ai_contentLabel.setWrapText(true);
c2w_ai_contentLabel.setPadding(new Insets(10, 0, 0, 0));
c2w_ai_contentLabel.setMaxWidth(380);
c2w_ai_contentLabel.setVisible(false);
c2w_ai_contentLabel.setManaged(false);
c2w_ai_contentLabel.setStyle("-fx-font-size: 14px;");
c2w_ai_card.getChildren().addAll(c2w_ai_titleLabel, 
c2w_ai_contentLabel);
// Toggle expand/collapse on click
c2w_ai_card.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
boolean isVisible = c2w_ai_contentLabel.isVisible();
if (isVisible) {
collapse(c2w_ai_contentLabel);
} else {
expand(c2w_ai_contentLabel);
}
});
return c2w_ai_card;
}
private void expand(Label c2w_ai_content) {
c2w_ai_content.setVisible(true);
c2w_ai_content.setManaged(true);
// Animate height grow
c2w_ai_content.setOpacity(0);
Timeline c2w_ai_fadeIn = new Timeline(
new KeyFrame(Duration.ZERO, new 
KeyValue(c2w_ai_content.opacityProperty(), 0)),
new KeyFrame(Duration.millis(250), new 
KeyValue(c2w_ai_content.opacityProperty(), 1))
);
c2w_ai_fadeIn.play();
}
private void collapse(Label c2w_ai_content) {
// Animate height shrink
Timeline c2w_ai_fadeOut = new Timeline(
new KeyFrame(Duration.ZERO, new 
KeyValue(c2w_ai_content.opacityProperty(), 1)),
new KeyFrame(Duration.millis(250), new 
KeyValue(c2w_ai_content.opacityProperty(), 0))
);
c2w_ai_fadeOut.setOnFinished(e -> {
c2w_ai_content.setVisible(false);
c2w_ai_content.setManaged(false);
});
c2w_ai_fadeOut.play();
} 
}


