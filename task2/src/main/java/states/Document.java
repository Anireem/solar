package states;

public class Document {
    State state;

    public Document(State state) {
        this.state = state;
    }

    public void methodA() {
        state.methodA(this);
    }

    public void methodB() {
        state.methodB(this);
    }

    public void methodC() {
        state.methodC(this);
    }

    public void goToState(State state) {
        this.state = state;
    }
}
