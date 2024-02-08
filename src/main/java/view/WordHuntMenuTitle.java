import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;


public class WordHuntMenuTitle extends Pane {
    private Text title; 

    public WordHuntMenuTitle(String titleName) { 
        String spread = ""; 
        for (char c: titleName.toCharArray()) { 
            spread += c + " "; 

        }

        title = new Text(spread); 
        title.setFont(Font.loadFont(this.getClass().getResource("/fonts/LilitaOne-Regular.ttf").toExternalForm(), 60));
        title.setFill(Color.GOLD); 
        title.setEffect(new DropShadow(30, Color.BLACK)); 
    
        getChildren().addAll(title);
    }

    public double getTitleWidth() { return title.getLayoutBounds().getWidth(); }
    
    
    public double getTitleHeight() { return title.getLayoutBounds().getHeight(); }

   

    
}
