package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.XmlProperty;

/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public class PropertyNode extends GridPane{
	
	public PropertyNode (XmlProperty xmlProperty) {
		 this.setPadding(new Insets(20));
		 this.setHgap(25);
		 this.setVgap(15);
		 
		 this.add(new Label(xmlProperty.getName()), 0, 0, 1, 1);
		 this.add(new Label(xmlProperty.getStringValue()), 1, 0, 1, 1);
	}

}
