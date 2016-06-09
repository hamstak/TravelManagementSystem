package utility;

import airline.*;

import java.io.File;
import java.util.*;

public class FileExport {
    private String convertSystem(ArrayList<Airport> ports, ArrayList<Airline> lines, ArrayList<Flight> flights){
        String result = "[";
        for(Airport port : ports){
            result += port.getName() + ", ";
        }
        result += "]{";
        for(Airline line : lines){
            for(Flight flight : flights){
           //     result += line.getName() + "[" + flight.getID() + "|" + flight.getYear() + ", " +
           //             flight.getMonth() + ", " + flight.getDay() + ", " + flight.getHour() + ", " + flight.getMinute() +
                //            "|" + flight.getOrigin() + "|" + flight.getDestination() + "["; //add sections, costs, etc. ]
            }
        }
        result += "]}";
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
            Scanner fout = new Scanner(getFile());
            //write out
        }
        catch(Exception e){
            System.out.println("Something went wrong! " + e.getClass() + " : " + e.getMessage());
        }
    }

}
