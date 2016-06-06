package utility;

import airline.factories.AirlineFactory;
import airline.factories.AirportFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class FileImport {
    public File getFile(){
        File fin;
        Scanner kb = new Scanner(System.in);
        System.out.println("Select a file to read from: ");
        String filename = kb.nextLine();
        fin = new File(filename);
        while(!fin.exists()){
            System.out.println("File does not exist! Please enter a valid file name: ");
            filename = kb.nextLine();
            fin = new File(filename);
        }
        return fin;
    }

    public String[] readFile(ArrayList<ArrayList<String>> trips){
        String line;
        String[] stops = new String[1];
        int open, close;
        File fin = getFile();

        try {
            Scanner buff = new Scanner(fin);
            line = buff.nextLine();

            close = line.indexOf(']');
            open = line.indexOf('{');

            stops = new String[line.substring(1, close).split(",").length];
            stops = line.substring(1, close).split(", ");

            for(int i = 0; close+3 < line.length(); i++){
                    trips.add(new ArrayList<String>());
                    if(i!=0 && line.charAt(open-3) != ']')
                        trips.get(i).add(trips.get(i-1).get(0));

                    close = line.indexOf(']', open);
                    if (open + 1 < line.length() && close < line.length() && open > 0 && close > 0) {

                        for (String s : line.substring(open + 1, close).split("[\\[|:,]"))
                            trips.get(i).add(s);

                        System.out.println("read in line with ID " + trips.get(i).get(0));
                        open = line.indexOf(',', close) + 1;
                    } else
                        break;
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong! " + e.getClass().getSimpleName() + " : " + e.getMessage());
        }

        return stops;
    }

    public ArrayList<airline.Airport> buildPorts(String[] ports){
        AirportFactory factory = new AirportFactory();
        for(String name : ports){
            factory.createPort(name);
        }
        return factory.getPorts();
    }

    public ArrayList<airline.Airline> buildLines(ArrayList<ArrayList<String>> lines){
        AirlineFactory factory = new AirlineFactory();
        for(ArrayList<String> line : lines){
            if(!factory.getCompanies().contains(new airline.Airline(line.get(0))))
                factory.createCompany(line.get(0));
        }
        return factory.getCompanies();
    }



}
