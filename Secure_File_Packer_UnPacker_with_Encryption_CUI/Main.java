package Secure_File_Packer_UnPacker_with_Encryption_CUI;

import java.util.Scanner;

///////////////////////////////////////////////////////////////////////////////////////////////////
// Class Name  : Main
// Description : Entry point of the Marvellous Packer-Unpacker application. Displays the menu,
//               accepts user input, and invokes packing or unpacking operations.
// Author      : Sakshi Ashok Adale
// Date        : 01/07/2026
///////////////////////////////////////////////////////////////////////////////////////////////////
 
public class Main
{
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Function Name : main
    // Description   : Starts the application, displays the menu, accepts user choices,
    //                 and performs packing or unpacking operations.
    // Parameters    : String args[] - Command-line arguments.
    // Return        : void
    // Author        : Sakshi Ashok Adale
    // Date          : 01/07/2026
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String args[])
    {
        Scanner sobj = new Scanner(System.in);
        int choice = 0;
        
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------- Packer Unpacker -------------------------------------");
        System.out.println("--------------------------With Custom Key-Based ASCII Encryption ---------------------------");
        System.out.println("--------------------------------------------------------------------------------------------");
        
        do
        {
            System.out.println("\nPlease select your choice:");
            System.out.println("1. Pack Files (with encryption)");
            System.out.println("2. Unpack Files (with decryption) - Manual key");
            System.out.println("3. Unpack Files (with decryption) - Auto load from key file");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sobj.nextInt();
            
            switch(choice)
            {
                case 1:
                    System.out.print("\nEnter the name of packed file: ");
                    String packFileName = sobj.next();
                    
                    System.out.print("Enter the directory name to pack: ");
                    String dirName = sobj.next();
                    
                    System.out.print("Enter your encryption key: ");
                    String packKey = sobj.next();
                    
                    Packer packer = new Packer(packFileName, dirName, packKey);
                    packer.PackingActivity();
                    break;
                    
                case 2:
                    System.out.print("\nEnter the name of packed file to unpack: ");
                    String unpackFileName = sobj.next();
                    
                    System.out.print("Enter your decryption key: ");
                    String unpackKey = sobj.next();
                    
                    Unpacker unpacker = new Unpacker(unpackFileName, unpackKey);
                    unpacker.UnpackingActivity();
                    break;
                    
                case 3:
                    System.out.print("\nEnter the name of packed file to unpack: ");
                    String unpackFileName2 = sobj.next();
                    
                    Unpacker unpacker2 = new Unpacker(unpackFileName2);
                    unpacker2.UnpackingActivity();
                    break;
                    
                case 4:
                    System.out.println("\nThank you for using Packer Unpacker!");
                    System.out.println("Goodbye!");
                    break;
                    
                default:
                    System.out.println("\nInvalid choice! Please select 1, 2, 3, or 4.");
                    break;
            }
        } while(choice != 4);
        
        sobj.close();
    }
}
