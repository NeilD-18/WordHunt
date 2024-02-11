import javafx.scene.control.Button; 
import javafx.scene.layout.StackPane; 

public class Tile extends StackPane{
    
    private Button tile; 

    public Tile(String letter) {
        tile = new Button(letter); 
        getChildren().add(tile); 
        setYellowState(); 

    } 

    public void setNeutralState() { 
        tile.getStyleClass().clear(); 
        tile.getStyleClass().add("button-neutral-state");
    }

    public void setGreenState() { 
        tile.getStyleClass().clear(); 
        tile.getStyleClass().add("button-green-state");
    }

    public void setYellowState() { 
        tile.getStyleClass().clear(); 
        tile.getStyleClass().add("button-yellow-state");
    }
}
