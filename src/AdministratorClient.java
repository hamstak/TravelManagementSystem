import menuSystem.DefaultSystemManager;
import menuSystem.SystemManager;

import java.util.HashMap;

/**
 * Created by moth on 6/1/16.
 */
public class AdministratorClient {

    public static void main(String[] args) {
        int option;
        HashMap<String, SystemManager> systemMap = new HashMap<>();
        SystemManager system = new DefaultSystemManager();
        try{
            while (true)
                system = system.runMenu(systemMap);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
