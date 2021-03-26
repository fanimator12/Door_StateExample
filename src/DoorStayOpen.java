public class DoorStayOpen extends DoorState
{
  @Override public void click(Door door){
    door.setState(new DoorOpening(door));
  }
}
