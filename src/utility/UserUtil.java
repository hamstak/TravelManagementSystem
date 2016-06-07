package utility;
/* Tim Tanasse
 * User input utility
 */
import java.util.Scanner;
public class UserUtil{
	
	public static int getUserPositiveInt(String use){	//psuedo try catch for getting a positive integer from the user
		int temp = 0;
		System.out.print("Input an integer for " + use + " -> ");
		while (temp <= 0){
			temp = getUserInt(use);
			if (temp > 0){
				return temp;
			}
			System.out.println("This integer is not positive!");
		}
		return temp;
	}
	public static int getUserInt(String use){	//try catch for getting an integer from the user
		Scanner kb = new Scanner(System.in);
		int temp = 0;
		while (true){
			try{
				temp = kb.nextInt();
				System.out.println();
				break;
			}
			catch (Exception e){
				System.out.println("Input an integer for " + use + "!\nEnter an integer -> ");
				kb.nextLine();
				temp = 0;
			}
		}
		kb.nextLine();
		return temp;
	}
	public static String getUserString(String use){
		  Scanner kb = new Scanner(System.in);
		  String temp;
		  while(true){
			  try{
				  temp =  kb.nextLine();
				  System.out.println();
				  break;
			  }catch(Exception e){
				  System.out.println("Input a string for " + use + ". \nEnter the string -> ");
				  temp = null;
			  }
		  }
		  return temp;
	  }

	  public static int dynamicMenu(String[] options){
		int choice = 0;
		while ( choice <= 0 || choice > options.length){
			for (int i = 0; i < options.length; i++){
				System.out.println("\t\t"+ (i+1)+". " + options[i]);
			}
            System.out.print("Choice -> ");
			choice = getUserInt("");
			if ( choice <= 0 || choice > options.length){
				System.out.println("Invalid choice");
			}
		}
		return choice;
	}
}

