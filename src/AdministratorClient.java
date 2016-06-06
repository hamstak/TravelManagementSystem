import utility.UserUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by moth on 6/1/16.
 */
public class AdministratorClient {

    public static void main(String[] args) {
        int option;
        HashMap<String, SystemManager> systemMap = new HashMap<>();
        SystemManager system = new DefaultSystemManager();
        while (true)
            system = system.runMenu(systemMap);

    }
}
