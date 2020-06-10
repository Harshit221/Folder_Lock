package Lock;
import java.io.PrintWriter;
public class Logic {
    String[] command ={"cmd"};
    Process p;
    PrintWriter stdin;
    public Logic(){}
    public void lock(String file)
    {
        try
        {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
	    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();	                
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
	    stdin.println("attrib +h +s " + file);
            stdin.println("cacls " + file +" /P %USERNAME%:N");
            stdin.println("y");					
	    stdin.close();
	    p.waitFor();
	} catch (Exception e) { e.printStackTrace(); }
    }
    public void unlock(String file)
    {
        try
        {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
	    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();	                
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
	    stdin.println("attrib +h +s" + file);
            stdin.println("cacls " + file +" /P %USERNAME%:F");
            stdin.println("y");					
	    stdin.close();
	    p.waitFor();
	} catch (Exception e) { e.printStackTrace(); }
        unHide(file);
    }
    public void unHide(String file)
    {
        try
        {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
	    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();	                
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
	    stdin.println("attrib -h -s " + file);				
	    stdin.close();
	    p.waitFor();
	} catch (Exception e) { e.printStackTrace(); }
    }
    public void customCommand(String file)
    {
        try
        {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
	    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();	                
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
	    stdin.println("mkdir " + file);				
	    stdin.close();
	    p.waitFor();
	} catch (Exception e) { e.printStackTrace(); }
    }
}
