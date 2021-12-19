package command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.RootApplication;
import model.XmlProperty;
import views.RootMenuBar;

public class GeneratePropertyFileCommand extends Command {

	private static final Logger LOGGER = LogManager.getLogger(GeneratePropertyFileCommand.class);
	
	Properties prop=new Properties();
	
	@Override
	public void execute() {
		String path = "src/resources/generatedPropertyFileTest.properties";
		LOGGER.info(path);
		try {
				
		      File newPropertyFile = new File(path);
		      if (newPropertyFile.createNewFile()) {
		       LOGGER.info("File created: " + newPropertyFile.getName());
		      } else {
		        LOGGER.info("File already exists.");
		      }
		      try (OutputStream output = new FileOutputStream(newPropertyFile)) {

		            Properties prop = new Properties();

		            // set the properties values
		            for (XmlProperty xmlProperty : RootApplication.getInstance().getPropertiesXmlFileManager().getXmlProperties()) {
		            	prop.put(xmlProperty.getName(), xmlProperty.getStringValue());
		            }
		            

		            // save properties to project root folder
		            prop.store(output, null);

		            LOGGER.info("Propertiy file generated sucessfully");

		        }
		    } catch (IOException e) {
		      e.printStackTrace();
		    }

	}

	@Override
	public void undo() {

	}

}
