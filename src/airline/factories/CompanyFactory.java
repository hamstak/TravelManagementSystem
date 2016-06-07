package airline.factories;

import java.util.ArrayList;

public interface CompanyFactory<T> {
    void createCompany(String name);
    ArrayList<T> getCompanies();
}
