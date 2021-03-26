public class DoorOpening extends DoorState
{
  private Thread motor;
  private boolean completed;

  public DoorOpening(Door door){
    completed = false;
    motor = new Thread(() -> {
      try
      {
        Thread.sleep(5000);
        complete(door);
      }
      catch (InterruptedException e)
      {
        System.out.println("Motor interrupted (opening)");
      }
    });
    motor.start();
  }

  @Override public synchronized void click(Door door){
    if (!completed) {
    motor.interrupt();
    door.setState(new DoorOpening(door));
    completed = true;
    }
  }

  private synchronized void complete(Door door){
    if (!completed){
      System.out.println("Motor ended (opening)");
      door.setState(new DoorOpen(door));
      completed = true;
    }
  }
}
