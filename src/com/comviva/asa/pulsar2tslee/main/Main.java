package com.comviva.asa.pulsar2tslee.main;

import java.util.concurrent.atomic.AtomicBoolean;
import com.ats_connection.log.LogPrefix;
import com.ats_connection.log.Logger;
import com.comviva.asa.pulsar2tslee.Pulsar2TsleeService;

public class Main {

  private Logger              logger  = null;
  private Pulsar2TsleeService  service = null;
  private final AtomicBoolean running = new AtomicBoolean(true);
  
  public static void main( String[] args )
  {
    Main process = new Main();
    process.run();
  }
  
  public Main()
  {
    service = new Pulsar2TsleeService();
    logger = service.getLogger(Logger.SERVICE_LOG, new LogPrefix("Main"));
  }
  
  public void run()
  {
    logger.info("Process started.");
    
    try
    {
      service.start();
      
      Runtime.getRuntime().addShutdownHook(new Thread()
      {
        @Override
        public void run()
        {
          logger.info("Stopping Process");
          running.set(false);
          synchronized (running)
          {
            running.notify();
          }
        }
      });
      
      while (running.get())
      {
        synchronized (running)
        {
          if (running.get())
          {
            try
            {
              running.wait();
            }
            catch (final InterruptedException ex)
            {
            }
          }
        }
      }
      
      service.stopService();
      try
      {
        service.join();
      }
      catch (InterruptedException ex)
      {
        logger.error("Join interrupted in Process", ex);
      }
    }
    catch (final Exception ex)
    {
      logger.error("Unexpected exception", ex);
    }
    finally
    {
      logger.info("Process stopped.");
    }
  }
}
