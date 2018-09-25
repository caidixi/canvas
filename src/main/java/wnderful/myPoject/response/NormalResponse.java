package wnderful.myPoject.response;

public class NormalResponse {
    int state;
    String shape;

    public NormalResponse(int state, String shape) {
        this.state = state;
        this.shape = shape;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
