package menuSystem;

import java.util.HashMap;

/**
 * Created by moth on 6/1/16.
 */
public interface SystemManager {
    SystemManager runMenu(HashMap<String, SystemManager> systemMap);

    void displaySystemDetails();
}
