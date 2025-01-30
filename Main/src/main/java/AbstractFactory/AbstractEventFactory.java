package AbstractFactory;

public abstract class AbstractEventFactory implements AbstractFactoryTicket {
    
    @Override
    public Platea createPlatea() {
        System.out.println("Creando Platea para " + getEventName());
        return new Platea();
    }

    @Override
    public Balcon createBalcon() {
        System.out.println("Creando Balcón para " + getEventName());
        return new Balcon();
    }

    @Override
    public Vip createVip() {
        System.out.println("Creando VIP para " + getEventName());
        return new Vip();
    }

    // Método que deberá ser implementado por las subclases para personalizar el nombre del evento
    protected abstract String getEventName();

}
