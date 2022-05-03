package com.LockedMe.client;

import com.LockedMe.domain.User;
import java.io.IOException;
import java.io.File;

public class LockedMe
{

    public static void main(String[] args) throws IOException
    {
        // Welcome screen and developer details
        System.out.println("*********************************************\n");
        System.out.println("Welcome to the LockedMe.com application.\n");
        System.out.println("Developer Name  : Burton Arceneaux\n");
        System.out.println("Developer Title : Java Full Stack Developer\n");
        System.out.println("*********************************************\n");
        
        // Create a new user object
        User myUser = new User();
        
        // Display user options
        myUser.displayUserOptions();
        char userInputOption = 'M';
        
        int userOption = myUser.getUserInput(userInputOption);
        
        String directoryLocation = "./LockedMe_documents";
        File f = new File("./LockedMe_documents");
        
        while((userOption != 3) && (userInputOption == 'M'))
        {
            //User choices
            if (userOption == 1)
            {
                myUser.displayFilesInDirectory(f);
                myUser.displayUserOptions();
                userOption = myUser.getUserInput('M');
            }
            else if (userOption == 2)
            {
                do
                {
                    userInputOption = 'S';
                    myUser.displayUserInteractionInformation();
                    userOption = myUser.getUserInput(userInputOption);
                    
                    if (userOption == 1)
                    {
                        System.out.print("Please put in the complete path for the file to copy : ");
                        String fullPathOfFile = myUser.getFullPathOfFile();
                        
                        myUser.addFileToExistingDirectory(fullPathOfFile, directoryLocation);
   
                    }
                    else if (userOption == 4)
                    {
                        userInputOption = 'M';
                        System.out.println("You entered 4");
                        myUser.displayUserOptions();
                        userOption = myUser.getUserInput('M');
                    }
                    
                }while(userInputOption == 'S');

                
            }
            else
            {
                System.out.println("Now exiting program.\n\n");
            }
        }
    }
}