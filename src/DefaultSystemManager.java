import utility.UserUtil;

import java.util.HashMap;

/**
 * Created by moth on 6/1/16.
 */
public class DefaultSystemManager implements SystemManager {
    public static final String[] MENU = {"1. Manage air travel",
            "2. Manage sea travel",
            "3. Print air travel system information",
            "4. Print sea travel system information"};

    @Override
    public SystemManager runMenu(HashMap<String, SystemManager> systemMap) {
        System.out.println("Travel Management Simulator 2016");
        int option;
        do {
            option = UserUtil.dynamicMenu(MENU);
            switch (option){
                case 1: if (systemMap.get("Air") == null)
                            systemMap.put("Air", new AirSystemManager());
                        return systemMap.get("Air");
                    break;
                case 2: if (systemMap.get("Sea") == null)
                            systemMap.put("Sea", new SeaSystemManager());
                        return systemMap.get("Sea");
                    break;
                case 3: return displaySystemDetails("Air", systemMap);
                    break;
                case 4: return displaySystemDetails("Sea", systemMap);
                    break;
                case 5: System.exit(0);
            }
        }while (option != 5);
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
