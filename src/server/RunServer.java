package server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.settings.SettingsManager;

public class RunServer {

    public static void main(String[] args) throws Exception {
        File f = new File(
                System.getProperty("user.home") + System.getProperty("file.separator") + ".NoPHPSettings.xml");
        if (f.isFile()) {
            try {
                // Start Threads for changing HTMLs according to dynamic data
                System.out.println("Sever running...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No settings were detected.");
            System.out.println("The server will use the settings in this directory: " + System.getProperty("user.home")
                    + System.getProperty("file.separator") + ".NoPHPSettings.xml");
            SettingsManager settings = new SettingsManager();
            List<String> fields = new ArrayList<>();
            fields.add("JDBC_DRIVER");
            fields.add("DB_URL");
            fields.add("DB_USER");
            fields.add("DB_PASSWORD");
            List<String> data = new ArrayList<>();
            data.add(
                    "Any Java MySQL Database Driver (e.g. already packaged library org.mariadb.jdbc.Driver) may need to be added");
            data.add("jdbc:mysql://IP-Address or DNS of your Database:Port/NoPHP");
            data.add("Database username with sufficient rights to create and edit tables");
            data.add("Password for the database user");
            settings.writeXML(fields, data);
            System.out.println("New settings saved.");
        }
    }

}
