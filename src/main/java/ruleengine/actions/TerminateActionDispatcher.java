package ruleengine.actions;

public class TerminateActionDispatcher implements ActionDispatcher{
    @Override
    public void fire() {
        System.out.println("Dispatching TERMINATE action...");
    }
}
