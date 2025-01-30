package AbstractFactory;
public class TeatroFactory extends AbstractEventFactory {

    @Override
    protected String getEventName() {
        return "Teatro";
    }

}
