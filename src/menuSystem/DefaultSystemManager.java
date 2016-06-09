package menuSystem;

import interfaces.SystemManager;
import utility.UserUtil;

import java.util.HashMap;

/**
 * Created by moth on 6/1/16.
 */
public class DefaultSystemManager implements SystemManager {
    public static final String[] MENU = {"Manage air travel",
            "Manage sea travel",
            "Print air travel system information",
            "Print sea travel system information",
            "Quit"};

    @Override
    public SystemManager runMenu(HashMap<String, SystemManager> systemMap) {
        System.out.println("Travel Management Simulator 2016");
        if (systemMap.get("Default") == null)
            systemMap.put("Default", this);
        int option;
        do {
            option = UserUtil.dynamicMenu(MENU);
            switch (option){
                case 1: if (systemMap.get("Air") == null)
                            systemMap.put("Air", new AirSystemManager());
                        return systemMap.get("Air");
                case 2: if (systemMap.get("Sea") == null)
                            systemMap.put("Sea", new SeaSystemManager());
                        return systemMap.get("Sea");
                case 3: return displaySystemDetails("Air", systemMap);
                case 4: return displaySystemDetails("Sea", systemMap);
                case 5: System.exit(0);
            }
        }while (option > 0);
        return null;
    }

    @Override
    public void displaySystemDetails() {

    }

    public SystemManager displaySystemDetails(String system, HashMap<String, SystemManager> systemMap){
        if (systemMap.get(system) == null){
            System.out.println("System not initialized yet");
        }else{
            systemMap.get(system).displaySystemDetails();
        }
        return this;
    }
}
