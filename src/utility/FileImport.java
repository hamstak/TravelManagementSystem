package utility;

import airline.Flight;
import airline.factories.AirlineFactory;
import airline.factories.AirportFactory;
import airline.factories.FlightFactory;
import airline.seats.SeatClass;
import airline.seats.Section;
import airline.seats.SectionLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class FileImport {
    private File getFile(){
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
                            trips.get(i).add(s.trim());

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

    public ArrayList<airline.Flight> buildFlights (ArrayList<ArrayList<String>> flights){
        FlightFactory factory = new FlightFactory();
        for(ArrayList<String> flight : flights){
            String airline = flight.get(0);
            String flightID = flight.get(1);
            String year = flight.get(2);
            String month = flight.get(3);
            String day = flight.get(4);
            String hour = flight.get(5);
            String minute = flight.get(6);
            String orig = flight.get(7);
            String dest = flight.get(8);
            String sc1 = flight.get(9);
            String sc1price = flight.get(10);
            String config1 = flight.get(11);
            String rows1 = flight.get(12);
            String sc2 = flight.get(13);
            String sc2price = flight.get(14);
            String config2 = flight.get(15);
            String rows2 = flight.get(16);

            factory.createTrip(airline, orig, dest, Integer.parseInt(year),
                    Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour),
                    Integer.parseInt(minute), flightID);

            Flight addSections = factory.findFlight(flightID);
            SeatClass[] classes = new SeatClass[] {SeatClass.valueOf(sc1)};
            SectionLayout[] layouts = new SectionLayout[] {SectionLayout.valueOf(config1)};
            int [] rows = new int[] {Integer.parseInt(rows1)};
            double[] prices = new double[] {Double.parseDouble(sc1price)};
            addSections.addSection(new Section(classes, layouts, rows, prices));

            classes = new SeatClass[] {SeatClass.valueOf(sc2)};
            layouts = new SectionLayout[] {SectionLayout.valueOf(config2)};
            rows = new int[] {Integer.parseInt(rows2)};
            prices = new double[] {Double.parseDouble(sc2price)};
            addSections.addSection(new Section(classes, layouts, rows, prices));
        }

        return factory.getTrips();

    }

}
