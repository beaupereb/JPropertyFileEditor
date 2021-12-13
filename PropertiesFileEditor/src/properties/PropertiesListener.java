package properties;

import java.util.EventListener;
import java.util.Properties;

public interface PropertiesListener extends EventListener{
	
	public void onPropertiesChange(Properties properties);

}
