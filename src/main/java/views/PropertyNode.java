package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class PropertyNode extends GridPane{
	
	public PropertyNode (String key, String value) {
		 this.setPadding(new Insets(20));
		 this.setHgap(25);
		 this.setVgap(15);
		 
		 this.add(new Label(key), 0, 0, 1, 1);
		 this.add(new Label(value), 1, 0, 1, 1);
	}

}
