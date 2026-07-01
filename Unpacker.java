package Secure_File_Packer_UnPacker_with_Encryption_CUI;

import java.util.*;
import java.util.function.Function;
import java.io.*;

///////////////////////////////////////////////////////////////////////////////////////////////////
// Class Name  : UnpackerX
// Description : Extracts files from a packed file and restores their original
//               contents by decrypting them using the provided or stored key.
// Author      : Sakshi Ashok Adale
// Date        : 01/07/2026
///////////////////////////////////////////////////////////////////////////////////////////////////

public class Unpacker
{
    private String PackName;
    private String decryptionKey;
    
    public Unpacker(String A, String key)
    {
        this.PackName = A;
        this.decryptionKey = key;
    }
    
    public Unpacker(String A)
    {
        this.PackName = A;
        loadEncryptionKey();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////// 
    // Function Name : loadEncryptionKey
    // Description   : Loads the encrypted key from the key file, converts it back
    //                 to the original key, and stores it for decryption.
    // Parameters    : None
    // Return        : void
    // Author        : Sakshi Ashok Adale
    // Date          : 01/07/2026
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    private void loadEncryptionKey()
    {
        try
        {
            File keyFile = new File(PackName + ".key");
            if(!keyFile.exists())
            {
                System.out.println("Encryption key file not found: " + PackName + ".key");
                System.out.println("Please ensure the key file exists for decryption.");
                return;
            }
            
            Scanner scanner = new Scanner(keyFile);
            String encryptedKey = scanner.nextLine();
            scanner.close();
            
            // Convert the +3 ASCII shifted key back to original key
            String originalKey = "";
            for(int i = 0; i < encryptedKey.length(); i++)
            {
                char shiftedChar = encryptedKey.charAt(i);
                char originalChar = (char)(shiftedChar - 3);
                originalKey += originalChar;
            }
            
            this.decryptionKey = originalKey;
            System.out.println("Decryption key loaded successfully from key file...");
        }
        catch(Exception e)
        {
            System.out.println("Error loading encryption key: " + e.getMessage());
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Function Name : decryptData
    // Description   : Decrypts encrypted file data using the custom key-based
    //                 ASCII decryption algorithm.
    // Parameters    : byte[] encryptedData - Encrypted file data.
    // Return        : byte[] - Decrypted original data.
    // Author        : Sakshi Ashok Adale
    // Date          : 01/07/2026
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    private byte[] decryptData(byte[] encryptedData)
    {
        try
        {
            byte[] decryptedData = new byte[encryptedData.length];
            int keyIndex = 0;
            
            for(int i = 0; i < encryptedData.length; i++)
            {
                // Get the current character from the key (cycling through the key)
                char keyChar = decryptionKey.charAt(keyIndex % decryptionKey.length());
                
                // Apply decryption: encrypted byte - (key character - 3)
                int decryptedByte = (encryptedData[i] - (keyChar - 3) + 256) % 256;
                decryptedData[i] = (byte)decryptedByte;
                
                keyIndex++;
            }
            
            return decryptedData;
        }
        catch(Exception e)
        {
            System.out.println("Error decrypting data: " + e.getMessage());
            return encryptedData; // Return encrypted data if decryption fails
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Function Name : UnpackingActivity
    // Description   : Reads the packed file, extracts file headers,
    //                 decrypts file contents, recreates original files,
    //                 and displays the unpacking statistics.
    // Parameters    : None
    // Return        : void
    // Author        : Sakshi Ashok Adale
    // Date          : 01/07/2026
    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void UnpackingActivity()
    {
        try
        {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------- Packer Unpacker -------------------------------------");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------ Unpacking Activity ------------------------------------");
            System.out.println("--------------------------------------------------------------------------------------------");


            File fobjnew = null;
            String Header = null;
            int FileSize = 0, iRet = 0, iCountFile = 0;

            File fobj = new File(PackName);

            // If packed file is not present.
            if(!fobj.exists())
            {   
                System.out.println("Unable to access packed file...");
                return;
            }
            
            FileInputStream fiobj = new FileInputStream(fobj);

            System.out.println("Successfully accessed packed file...");

            // Buffer to read the Header
            byte HeaderBuffer[] = new byte[100];

            // Scan the packed file to extract the files from it
            while((iRet = fiobj.read(HeaderBuffer, 0 ,100)) != -1)
            {
                // Convert byte array to String
                Header = new String(HeaderBuffer);           
            
                Header = Header.trim();

                // Tokenize the header into 2 parts     File_name and File_size
                String Tokens[] = Header.split(" ");
                
                fobjnew = new File(Tokens[0]);             

                // Create new file to extract
                fobjnew.createNewFile();

                // convert string to integer
                FileSize = Integer.parseInt(Tokens[1]);

                // Create new buffer to store files data
                byte Buffer[] = new byte[FileSize];

                FileOutputStream foobj = new FileOutputStream(fobjnew);             

                // Read the encrypted data from packed file
                fiobj.read(Buffer,0,FileSize);                  

                // Decrypt the data before writing
                byte[] decryptedData = decryptData(Arrays.copyOf(Buffer, FileSize));
                
                // Write the decrypted data into extracted file
                foobj.write(decryptedData, 0, decryptedData.length);              

                System.out.println("File unpack with name : "+Tokens[0]+"having size :"+ FileSize);
                
                iCountFile++;
                
                foobj.close();
            } // End of while()
            
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("----------------------------------- Statistical Report -------------------------------------");
            System.out.println("--------------------------------------------------------------------------------------------");

            System.out.println("Total Number of Files unpacked :" + iCountFile);
            System.out.println("All files have been decrypted using custom key-based ASCII shifting");

            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("---------------------------- Thank You For Using Our Application ---------------------------");
            System.out.println("--------------------------------------------------------------------------------------------");

            fiobj.close();
        }    
        catch(Exception eobj)
        {}
    }
}
