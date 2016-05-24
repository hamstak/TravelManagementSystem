package interfaces;

import java.util.ArrayList;

/**
 * Created by moth on 5/22/16.
 */
public interface CompanyFactory<T> {
    void createCompany(String name);
    ArrayList<T> getCompanies();
}
