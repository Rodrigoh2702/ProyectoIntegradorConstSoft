public class NullRoom extends Room{

    public NullRoom() {
        super(null);
        this.northExit = null;
        this.southExit = null;
        this.eastExit = null;
        this.westExit = null;
    }

    public boolean isNull() {
        return true;
    }

}