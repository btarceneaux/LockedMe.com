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
        
        int userOption = myUser.getUserInput('M');
        
        //File f = new File(".");
        File f = new File("./LockedMe_documents");
        
        while(userOption != 3)
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
                myUser.displayUserInteractionInformation();
                userOption = myUser.getUserInput('S');
            }
            else
            {
                System.out.println("Now exiting program.\n\n");
            }
        }
    }
}