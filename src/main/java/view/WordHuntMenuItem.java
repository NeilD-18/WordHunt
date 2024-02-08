import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;

public class WordHuntMenuItem extends Pane {
    private Text text; 
    private Effect shadow = new DropShadow(5, Color.BLACK); 
    private Effect blur = new BoxBlur(1,1,3); 

    public WordHuntMenuItem(String menuItemName) { 
        Polygon bg = new Polygon(
            0,0,
            210,0,
            230, 10, 
            210, 30,
            0, 30 
        ); 

        bg.setStroke(Color.color(1,1,1,0.75)); 
        bg.setEffect(new GaussianBlur()); 
        
        bg.fillProperty().bind(
            Bindings.when(pressedProperty())
                    .then(Color.color(0,0,0,.75)).otherwise(Color.color(0,0,0,.25))

        );

        text = new Text(menuItemName);
        text.setTranslateX(5); 
        text.setTranslateY(20);     
        text.setFont(Font.loadFont(this.getClass().getResource("/fonts/LilitaOne-Regular.ttf").toExternalForm(), 14));
        text.setFill(Color.GOLD); 

        text.effectProperty().bind(
            Bindings.when(hoverProperty())
                    .then(shadow).otherwise(blur)

        );

        getChildren().addAll(bg, text); 


    }
    public void setOnAction(Runnable action) { 
        setOnMouseClicked(e -> action.run()); 
    }

}
