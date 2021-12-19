package properties;

import java.util.EventListener;
import java.util.Properties;


/**
 * //TODO Write javadoc
 *
 * @author  BBE
 * @version 1.0
 * @since   2021
 */
public interface PropertiesListener extends EventListener{
	
	public void onPropertiesChange(Properties properties);

}
