package states;

public class StateC implements State {
    @Override
    public void methodA(Document document) {
        document.goToState(new StateA());
    }

    @Override
    public void methodB(Document document) {
        document.goToState(new StateB());
    }

    @Override
    public void methodC(Document document) {
        throw new RuntimeException();
    }
}
