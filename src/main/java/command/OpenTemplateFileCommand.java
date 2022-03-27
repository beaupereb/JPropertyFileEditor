package command;

import controller.RootApplication;
import controller.TemplatePropertyFileManager;

public class OpenTemplateFileCommand extends Command{

    @Override
    public void execute() {
        RootApplication.getInstance().getTemplatePropertyFileManager().openTemplatePropertyFile();
    }

    @Override
    public void undo() {

    }
}
