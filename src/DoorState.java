public abstract class DoorState
{
  public abstract void click(Door door);

  public String status(){
    return getClass().getSimpleName();
  }
}
