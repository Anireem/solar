package states;

public class StateA implements State {
    @Override
    public void methodA(Document document) {
        throw new RuntimeException();
    }

    @Override
    public void methodB(Document document) {
        document.goToState(new StateB());
    }

    @Override
    public void methodC(Document document) {
        document.goToState(new StateC());
    }
}
