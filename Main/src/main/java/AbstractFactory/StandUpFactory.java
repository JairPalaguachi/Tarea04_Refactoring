package AbstractFactory;
public class StandUpFactory extends AbstractEventFactory {

    @Override
    protected String getEventName() {
        return "StandUp";
    }

}
