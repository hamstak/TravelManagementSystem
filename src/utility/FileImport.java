package utility;

import airline.Flight;
import airline.factories.AirlineFactory;
import airline.factories.AirportFactory;
import airline.factories.FlightFactory;
import airline.SeatClass;
import airline.seats.SectionLayout;
import airline.seats.FlightSection;

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

            TravelSystemDate date = new TravelSystemDate(Integer.parseInt(year), Integer.parseInt(month),
                    Integer.parseInt(day), Integer.parseInt(hour),Integer.parseInt(minute));
            factory.createTrip(airline, orig, dest, date, flightID);

            for(int i = 9; i < flight.size(); i++){
                Flight f = factory.findFlight(flightID);
                if(flight.get(i).equals("E") || flight.get(i).equals("F") || flight.get(i).equals("B")){
                    String c = flight.get(i);
                    double price = Double.parseDouble(flight.get(i+1));
                    String layout = flight.get(i+2);
                    int rows = Integer.parseInt(flight.get(i+3));
                    f.addSection(new FlightSection(SeatClass.valueOf(c), SectionLayout.valueOf(layout), rows, price));
                }
            }
        }

        return factory.getTrips();

    }

}
