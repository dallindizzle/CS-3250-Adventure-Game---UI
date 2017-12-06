import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.File;


public class UI extends Application {
    private Map m = new Map();
    private GameChar c = new GameChar(m);
    private Adventure a = new Adventure(c);

    private TextArea messages = new TextArea();
    TextField input;

    Pane pane;

    private Parent createContent() {

        pane = new Pane();
        pane.setPrefSize(300, 300);

        int x = -2;
        int y;
        for (int i = 0; i < 5; i++) {
            y = -2;
            for (int j = 0; j < 5; j++) {
                String file = "";

                if (x == 0 && y == 0) {
                    file = m.getPersonImgFile();

                    Tile tile = new Tile(file);
                    tile.setTranslateX(j * m.getTileSize()[0]);
                    tile.setTranslateY(i * m.getTileSize()[1]);

                    pane.getChildren().add(tile);
                    y++;
                    continue;
                }

                char type = m.getCell(c.getLocX() + x, c.getLocY() + y);
                switch (type) {
                    case '.':
                        file = m.getPlainsImgFile();
                        break;
                    case 'M':
                        file = m.getMountainImgFile();
                        break;
                    case 'f':
                        file = m.getForestImgFile();
                        break;
                    case '~':
                        file = m.getWaterImgFile();
                        break;
                    case '-':
                        file = m.getOutImgFile();
                        break;
                    case '*':
                        file = m.getGoalImgFile();
                        break;
                }

                Tile tile = new Tile(file);
                tile.setTranslateX(j * 60);
                tile.setTranslateY(i * 60);

                pane.getChildren().add(tile);
                y++;
            }
            x++;
        }
        input = new TextField();

        input.setOnAction(e -> {
            String message = input.getText();
            if (message.equals("")) {
                return;
            }
            input.clear();
            messages.appendText(message + "\n");
            messages.appendText(a.action(message) + "\n" + "\n");
            refresh();
        });


        messages.setEditable(false);
        messages.setPrefHeight(300);

        VBox vbox = new VBox(10, messages, input);
        vbox.setPrefSize(300,300);

        VBox root = new VBox(20, pane, vbox);

        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                String message = "go north";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
            else if (event.getCode() == KeyCode.DOWN) {
                String message = "go south";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
            else if (event.getCode() == KeyCode.RIGHT) {
                String message = "go east";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
            else if (event.getCode() == KeyCode.LEFT) {
                String message = "go west";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
        });

        input.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                String message = "go north";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
            else if (e.getCode() == KeyCode.DOWN) {
                String message = "go south";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
            else if (e.getCode() == KeyCode.RIGHT) {
                String message = "go east";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }
            else if (e.getCode() == KeyCode.LEFT) {
                String message = "go west";
                input.clear();
                messages.appendText(message + "\n");
                messages.appendText(a.action(message) + "\n" + "\n");
                refresh();
            }

        });

        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.getRoot().requestFocus();

        stage.setScene(scene);
        stage.show();
    }

    private class Tile extends StackPane {
        public Tile(String img) {
            Rectangle border = new Rectangle(60,60);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            setAlignment(Pos.CENTER);
            getChildren().add(border);

            ImageView imageView = new ImageView();
            Image image1 = new Image(new File(img).toURI().toString());
            imageView.setImage(image1);

            getChildren().addAll(imageView);
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void refresh() {
        pane.getChildren().clear();

        int x = -2;
        int y;
        for (int i = 0; i < 5; i++) {
            y = -2;
            for (int j = 0; j < 5; j++) {
                String file = "";

                if (x == 0 && y == 0) {
                    file = m.getPersonImgFile();

                    Tile tile = new Tile(file);
                    tile.setTranslateX(j * 60);
                    tile.setTranslateY(i * 60);

                    pane.getChildren().add(tile);
                    y++;
                    continue;
                }

                char type = m.getCell(c.getLocX() + x, c.getLocY() + y);
                switch (type) {
                    case '.':
                        file = m.getPlainsImgFile();
                        break;
                    case 'M':
                        file = m.getMountainImgFile();
                        break;
                    case 'f':
                        file = m.getForestImgFile();
                        break;
                    case '~':
                        file = m.getWaterImgFile();
                        break;
                    case '-':
                        file = m.getOutImgFile();
                        break;
                    case '*':
                        file = m.getGoalImgFile();
                        break;
                }

                Tile tile = new Tile(file);
                tile.setTranslateX(j * 60);
                tile.setTranslateY(i * 60);

                pane.getChildren().add(tile);
                y++;
            }
            x++;
        }
    }
}