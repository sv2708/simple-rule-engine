package ruleengine.actions;

public class RunActionDispatcher implements ActionDispatcher{
    @Override
    public void fire() {
        System.out.println("Dispatching RUN action...");
    }
}
