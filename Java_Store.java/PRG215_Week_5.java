package week_5;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Caleb Spindler
 */
public class PRG215_Week_5 {
    public static void main(String[] args) {
        
        
        //Constant for the total number of items for sale
        final int TOTAL_ITEMS =6;
        
        //Create the items object arrays
        ItemsForSale[] items = new ItemsForSale[TOTAL_ITEMS];
        
        //Loop and instantiate each object - you must always ucreate the individual object
        for(int i = 0; i<TOTAL_ITEMS; i++)
        {
            items[i] = new ItemsForSale();
        }
        
        //Use the PopulateItem method from the ItemsForSale class to inser the properties of each item object for sale
        items[0].PopulateItem("Tennis Shoes", 45.89, true);
        items[1].PopulateItem("Shirts", 25.55, true);
        items[2].PopulateItem("Coats", 89.99, true);
        items[3].PopulateItem("Belts", 15, true);
        items[4].PopulateItem("Pants", 25.99, true);
        items[5].PopulateItem("Donation", 10, false);
        
        //Variable to for the financial calculations
        double totalAmount = 0.0;
        double totalTax = 0.0;
        double taxRate = 0.081;
        
        //Discount for large purchases
        final double DISCOUNT_RATE = 0.025; //2.5%
        final double AMOUNT_TO_QUALIFY_FOR_DISCOUNT = 100;
        double discountAmount = 0;
        
        //Display items for sale on the console
        System.out.println("The following clothing items are available for purchase:");
        for(int i = 0; i < items.length; i++)
        {
            //Display each item in the array
            System.out.println("    "+(i+1)+"."+items[i].itemName+"for $"+items[i].itemCost+" each");
        }
        System.out.println("");
        
        //Create a keyboard input object
        Scanner keyboard = new Scanner(System.in);
        
        //Create a new customer object
        Customer newCust = new Customer();
        
        //Display on the console - ask for the customer's first name
        System.out.println("Please enter your first name: ");
        
        //Get the customer firstt name from the keyboard and store it in the cnewCust object's firstName property
        newCust.firstName = keyboard.next();
        
        //Display on the console - ask for the customer's last name
        System.out.println("Please enter your last name: ");
        
        //Get the customer last name from the keyboard and store it in the newCust object's lastName property
        newCust.lastName = keyboard.next();
        
        System.out.println("");
        
        //Display the customer's full name (a method in the Customer class) and provide instructions
        System.out.println("Ok, "+newCust.FullName()+", please enter the prodcut ID (the number to the left of the item name) that you wish to purchase. Enter 0 when you are finished. ");
        
        //Loop until the user enters 0
        int itemID = 0; //Set the condition to 0
        int itemCounter = 1; //No longer the loop condition counter, now used for display
        do
        {
            
            //Prompt the user
            System.out.println("Please enter item ID number "+(itemCounter)+":");
            
            //Wrap the input in a TRY/CATCH block to capture any user data entry exceptions
            try
            {
                itemID = keyboard.nextInt();
                
                //Test if the user did not enter the exit condition
                if(itemID > 0)
                {
                    totalAmount = totalAmount + items[itemID - 1].itemCost;
                    
                    //Moved tax calculation to here since we might not charge tax on all items
                    if(items[itemID - 1].taxable == true)
                    {
                        totalTax = totalTax + (items[itemID - 1].itemCost * taxRate);
                    }
                    
                    //Increment the item display counter
                    itemCounter++;
                }
            }
            
            //User entered a number outside the size of the array
            catch(ArrayIndexOutOfBoundsException e1)
            {
                //Display the error message
                System.out.println("The item Id you entered is outside the range of possible items. This must be a number between 1 and "+ TOTAL_ITEMS+". Please re-enter your item ID. ");
                
                //Set this flas to remain in the loop
                itemID = -1;
            }
            
            //User didn't enter an integer
            catch (InputMismatchException e2)
            {
                //Display the error message
                System.out.println("The item ID you entered does not appear to be an integer number. The item ID must be a number between 1 and "+TOTAL_ITEMS+". Please re-enter your item ID. ");
                
                //This is required to remove additional non-integer items stored in the scanner input buffer
                keyboard.nextLine();
                
                //Set this as a flag to remain in the loop
                itemID = -1;
            }
        }while (itemID !=0); //Check if exit condition has been met
        
        //The loop is complete, calculate the discount and taxes and then display the results
        if(totalAmount >= AMOUNT_TO_QUALIFY_FOR_DISCOUNT)
        {
            discountAmount = totalAmount * DISCOUNT_RATE;
        }
        else
        {
            discountAmount = 0;
        }
        
        //Display the results
        System.out.println("");
        System.out.println("You selected "+itemCounter+" items to purchase.");
        System.out.println("Your sales total $"+totalAmount);
        System.out.println("Your discount amount is $"+discountAmount);
        System.out.println("Your sales tax is $"+totalTax);
        System.out.println("The total amount due is $"+(totalAmount - discountAmount + totalTax));
        System.out.println("");
    }
}
