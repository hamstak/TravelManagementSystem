package travelPackage.utility;

import travelPackage.airline.*;
import travelPackage.airline.seats.FlightSection;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class FileExport {
    public String convertSystem(ArrayList<Airport> ports, ArrayList<Airline> lines, ArrayList<Flight> flights){
        String result = "[";
        for(Airport port : ports){
            result += port.getName() + ", ";
        }
        result += "]{";
        for(Airline line : lines){
            for(Flight flight : flights){
                result += line.getName() + "[" + flight.getID() + "|" + flight.getDate().getYear() + ", " +
                        flight.getDate().getMonth() + ", " + flight.getDate().getDay() + ", " + flight.getDate().getHour() +
                        ", " + flight.getDate().getMinute() + "|" + flight.getOrigin() + "|" + flight.getDestination() +
                        "[";
                for(FlightSection fs : flight.getSections()){
                    result += fs.getType().encode() + ":" + (int)fs.getPrice() + ":" + fs.getSeatLayout().getID() + ":" + fs.getRows() + ",";
                }
                result += "]";
            }
            result += "],";
        }
        result += "}";
        return result;
    }

    private File getFile(){
        File fout;
        Scanner kb = new Scanner(System.in);
        System.out.println("Name the file you'd like to write to: ");
        String filename = kb.nextLine();
        fout = new File(filename);
        while(fout.exists()){
            System.out.println("File already exists! Enter 'Y' to overwrite or a filename not in use! ");
            filename = kb.nextLine();
            if(filename.equalsIgnoreCase("Y"))
                break;
            fout = new File(filename);
        }
        return fout;
    }

    public void writeFile(String system) {
        try {
            PrintWriter fout = new PrintWriter(getFile());
            fout.write(system);
            fout.flush();
        }
        catch(Exception e){
            System.out.println("Something went wrong! " + e.getClass() + " : " + e.getMessage());
        }
    }

}
