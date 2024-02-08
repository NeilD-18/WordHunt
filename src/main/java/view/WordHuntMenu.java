import java.util.List;
import java.util.Arrays;
import javafx.util.Pair;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import java.io.IOException;
import java.io.InputStream;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths; 
import java.net.URL; 


public class WordHuntMenu extends Application {
    
    private static final int WIDTH = 1280; 
    private static final int HEIGHT = 720; 

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
        new Pair<String, Runnable>("New Game", () -> {}),
        new Pair<String, Runnable>("Load Game", () -> {}),
        new Pair<String, Runnable>("Create Game", () -> {}),
        new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    private Pane root = new Pane(); 
    private VBox menuBox = new VBox(-5); 
    private Line line;

    private Parent createContent(){ 
        addTitle();

        root.setId("pane"); 
        double lineX = WIDTH / 2 - 100; 
        double lineY = HEIGHT / 3 + 50; 

        addLine(lineX, lineY); 
        addMenu(lineX + 5, lineY + 5); 

        startAnimation(); 

        return root; 
    }


    private void addLine(double x, double y) { 
        line = new Line(x, y, x, y + 155); 
        line.setStrokeWidth(3);
        line.setStroke(Color.BLACK);
        line.setEffect(new DropShadow(5, Color.BLACK)); 
        line.setScaleY(0);
        root.getChildren().add(line); 

    }

    private void startAnimation() { 
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished( e -> { 
            
            for (int i = 0; i < menuBox.getChildren().size(); i++) { 
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n); 
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play(); 
            }


        }); 
        st.play(); 
    }

    private void addMenu(double x, double y) { 
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            WordHuntMenuItem item = new WordHuntMenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);
            
            Rectangle clip = new Rectangle(300, 30); 
            clip.translateXProperty().bind(item.translateXProperty().negate()); 

            item.setClip(clip); 

            menuBox.getChildren().addAll(item); 

        }); 
        root.getChildren().add(menuBox);
    }

    public void addTitle() { 
        WordHuntMenuTitle title = new WordHuntMenuTitle("Word Hunt"); 
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2); 
        title.setTranslateY(HEIGHT / 3); 
        root.getChildren().add(title); 
    }


    
    public static void main(String[] args) {
        launch(args);
    }

    @Override 
    public void start(Stage primaryStage) throws Exception { 
        
        Scene scene = new Scene(createContent());
        
        URL cssResource = getClass().getResource("css/styles.css");
        if (cssResource != null) {
            scene.getStylesheets().add(cssResource.toExternalForm());
        } else {
            //System.out.println("Error: CSS resource 'styles.css' not found!");
        }

        primaryStage.setTitle("WordHunt Menu");
        primaryStage.setScene(scene); 
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.show(); 
    }

}
