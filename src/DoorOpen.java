public class DoorOpen extends DoorState
{
  private Thread timer;
  private boolean completed;

  public DoorOpen(Door door){
    completed = false;
    timer = new Thread(() -> {
      try
      {
        Thread.sleep(5000);
        timeout(door);
      }
      catch (InterruptedException e)
      {
        System.out.println("Timer interrupted (open)");
      }
    });
    timer.start();
  }

  @Override public synchronized void click(Door door){
    if (!completed) {
      timer.interrupt();
      door.setState(new DoorStayOpen());
      completed = true;
    }
  }

  private synchronized void timeout(Door door){
    if (!completed){
      System.out.println("Timeout (open)");
      door.setState(new DoorClosing(door));
      completed = true;
    }
  }
}
