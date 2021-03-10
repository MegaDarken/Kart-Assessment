import java.lang.*;
import java.util.concurrent.*;

public class ThreadHandling
{

   private final static int THREAD_COUNT = 3;

   public static void OpenThreads()
   {
      //Executor
      ExecutorService threadExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
      
      //Runnable worldThread = new 
      //Runnable graphicsThread = new 
      //Runnable connectionThread = new 
      
      //threadExecutor.execute(worldThread);
      //threadExecutor.execute(graphicsThread);
      //threadExecutor.execute(connectionThread);

      threadExecutor.shutdown();
   }



}