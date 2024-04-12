package states;

public class StateB implements State {
    @Override
    public void methodA(Document document) {
        document.goToState(new StateA());
    }

    @Override
    public void methodB(Document document) {
        throw new RuntimeException();
    }

    @Override
    public void methodC(Document document) {
        document.goToState(new StateC());
    }
}
