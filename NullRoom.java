public class NullRoom extends Room{

    public NullRoom() {
        super(null);
        this.setExits(null, null, null, null);
    }

    public boolean isNull() {
        return true;
    }

}