/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bikerace.pkg2;

/**
 *
 * @author Salma Jiffry
 */
import java.util.*;
class Bike{
    int id;
    String brand, sponsor, driver;
    int roundPosition;
    
    public Bike(int id, String brand, String sponsor, String driver){
        this.id=id;
        this.brand=brand;
        this.sponsor=sponsor;
        this.driver=driver;
        this.roundPosition = 0;
    }
}
public class Bikerace2{
    public static int search(Stack<Bike> bikeStack, int x) {
        Stack<Bike> tempStack = new Stack<>();
        int bikeIndex = -1;

        while (!bikeStack.isEmpty()) {
            Bike bike = bikeStack.pop();
            tempStack.push(bike);

            if (bike.id == x) {
                bikeIndex = tempStack.size() - 1; 
                break;
            }
        }

        while (!tempStack.isEmpty()) {
            bikeStack.push(tempStack.pop());
        }

        return bikeIndex;
    }
    public static int linearSearch(Stack<Bike> bikeStack, int x){
        Stack<Bike> tempStack = new Stack<>();
        int bikeIndex = -1;

        while (!bikeStack.isEmpty()) {
            Bike bike = bikeStack.pop();
            tempStack.push(bike);

            if (bike.id == x) {
                bikeIndex = tempStack.size() - 1; 
                break;
            }
        }

        while (!tempStack.isEmpty()) {
            bikeStack.push(tempStack.pop());
        }

        return bikeIndex;        
    }
    public static void main(String[] args) {
        Scanner objbike = new Scanner(System.in);
        
        LinkedList<Bike> bikeList = new LinkedList<Bike>();
        Stack<Bike> bikeStack = new Stack<>();
        
        while (true){
            System.out.println("**** CYCLYING FESTIVAL ****");
        System.out.println("**** BIKE RACING EVENT ****");
        System.out.println();
        System.out.println("Please select an option...");
        System.out.println("Option 1: Register Bike Details");
        System.out.println("Option 2: Display Bike details");
        System.out.println("Option 3: Delete a Bike");
        System.out.println("Option 4: Insert round results");
        System.out.println("Option 5: Find out the winners");
        System.out.println("Option 6: Search a particular Bike");
        System.out.println("Option 7: Exit");
        System.out.println("Enter your option here: ");
        
        int option = objbike.nextInt();
        
        switch (option){
            
            case 1:
                for(int x=0; x<4; x++){
                    System.out.println("Register Bike Details");
                    System.out.print("Enter Car ID: ");
                    int id = objbike.nextInt();
                    System.out.print("Enter Car Brand: ");
                    String brand = objbike.next();
                    System.out.print("Enter Car Sponsor: ");
                    String sponsor = objbike.next();
                    System.out.print("Enter Car Driver: ");
                    String driver = objbike.next();
                    
                    Bike b1 = new Bike(id, brand, sponsor, driver);
                    bikeList.add(b1);
                    bikeStack.add(b1);
                    System.out.println("Car added successfully!");
                }
                
                break;
            case 2:
                System.out.println("Display Bike details");
                
                for(Bike bike : bikeStack){
                    System.out.println(bike.id + " " + bike.brand + " " + bike.sponsor + " " + bike.driver);
                }
                break;
            case 3:
                System.out.println("Delete a Bike");
                System.out.print("Enter Bike ID to delete: ");
                int deleteId = objbike.nextInt();
                
                boolean bikeFound = false;

                Stack<Bike> tempStack = new Stack<>();

                while (!bikeStack.isEmpty()) {
                    Bike bike = bikeStack.pop();
                    if (bike.id == deleteId) {
                        bikeFound = true;
                        System.out.println("Bike with ID " + deleteId + " found:");
                        System.out.println("Bike ID: " + bike.id);
                        System.out.println("Bike Brand: " + bike.brand);
                        System.out.println("Bike Sponsor: " + bike.sponsor);
                        System.out.println("Bike Driver: " + bike.driver);
            
                        System.out.print("Do you want to delete this bike? (yes/no): ");
                        String confirmation = objbike.next();
                        if (confirmation.equalsIgnoreCase("yes")) {
                           System.out.println("Bike with ID " + deleteId + " has been deleted.");
                        } else {
                
                           tempStack.push(bike);
                           System.out.println("Bike with ID " + deleteId + " was not deleted.");
                               }
                    } else {
                        tempStack.push(bike);
                      }
                }
    
    
                while (!tempStack.isEmpty()) {
                    bikeStack.push(tempStack.pop());
                }

                
                
                break;
            case 4:
                System.out.println("Insert Round Results");

                    for (int round = 1; round <= 3; round++) {
                       System.out.println("Enter Round " + round + " Results (Bike ID and Position, separated by a space):");

                        for (Bike bike : bikeStack) {
                            System.out.print("Bike ID " + bike.id + ": ");
                            int position = objbike.nextInt();
                            objbike.nextLine();
                            bike.roundPosition = position;
                        }

                        bikeStack.sort(Comparator.comparingInt(c -> c.roundPosition));
                        Bike eliminatedBike = bikeStack.pop();
                        System.out.println("Bike " + eliminatedBike.id + " has been eliminated in round " + round);
                    }
                break;
            case 5:
                System.out.println("Find out the winners");

                List<Bike> sortedBikes = new ArrayList<>(bikeStack);
                sortedBikes.sort(Comparator.comparingInt(c -> c.roundPosition));

                if (sortedBikes.size() >= 3) {
                    Bike firstPlace = sortedBikes.get(0);
                    Bike secondPlace = sortedBikes.get(1);
                    Bike thirdPlace = sortedBikes.get(2);

                    System.out.println("1st Place: Bike ID " + firstPlace.id + " (" + firstPlace.driver + ")");
                    System.out.println("2nd Place: Bike ID " + secondPlace.id + " (" + secondPlace.driver + ")");
                    System.out.println("3rd Place: Bike ID " + thirdPlace.id + " (" + thirdPlace.driver + ")");
                } else {
                    System.out.println("No winners yet. Please complete the rounds.");
                  }
                break;
            case 6:
                System.out.println("Search a Bike");
                System.out.print("Enter Bike ID to search: ");
                int searchId = objbike.nextInt();
                
                int searchResult = -1;
                
                for (int i = 0; i < bikeStack.size(); i++) {
                    Bike bike = bikeStack.get(i);
                    if (bike.id == searchId) {
                    searchResult = i;
                    break;
                    }           
                 }
                if (searchResult != -1) {
                 Bike foundBike = bikeStack.get(searchResult); 
                 System.out.println("Bike with ID " + searchId + " found:");
                 System.out.println("Bike ID: " + foundBike.id);
                 System.out.println("Bike Brand: " + foundBike.brand);
                 System.out.println("Bike Sponsor: " + foundBike.sponsor);
                 System.out.println("Bike Driver: " + foundBike.driver);
                 } 
                 else {
                    System.out.println("Bike with ID " + searchId + " not found.");
                 }
                break;
            case 7:
                System.out.println("Exit");
                objbike.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
            break;
        }
    
        }
        
    
    }
    
}