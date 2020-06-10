package Lock;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
public class User implements Serializable
{
    String userID;
    String password;
    //ArrayList<File> fileList = new ArrayList();
    static HashMap<String,User> userList;

    public User()
    {
        
        //System.out.println(this.fileList.get(0));
        userList = new HashMap();
        
        try
        {
            FileInputStream file = new FileInputStream("C:\\Users\\Public\\FolderLock\\User.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            Boolean eof = false;
            User user;
            while(!eof)
            {
                try
                {
                    user=(User)inputFile.readObject();
                    userList.put(user.userID,user);
                } 
                catch (EOFException e) { eof = true; } 
                catch (Exception e) {} 
                
            }
        } catch(FileNotFoundException fne){}
        catch (Exception e) {} 
    }
    
    public User(String userID, String password)
    {
        this.userID = userID;
        this.password = password;
    }
    public void addUser(String userID, String password)
    {
        if(userList.get(userID)==null)
        {
            userList.put(userID, new User(userID,password));
        }
        else
        {
            JOptionPane.showMessageDialog(null, "User already exists!");
        }
    }

    public static HashMap<String, User> getUserList()
    {
        return userList;
    }
    

    
    

    
}
