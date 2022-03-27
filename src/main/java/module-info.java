module JPropertyFileEditor.main {

    requires javafx.graphics;
    requires javafx.controls;
    requires org.apache.logging.log4j;
    requires java.xml;
    requires java.desktop;

    requires org.apache.commons.configuration2;
    requires jevolvedproperties.main;

    exports controller;

}