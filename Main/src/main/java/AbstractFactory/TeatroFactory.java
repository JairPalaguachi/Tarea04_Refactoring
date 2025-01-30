package AbstractFactory;
public class TeatroFactory implements AbstractFactoryTicket {
    @Override
    public Platea createPlatea() {
        System.out.println("Creando Platea de Teatro");
        return new Platea();
    }

    @Override
    public Balcon createBalcon() {
        System.out.println("Creando Balcón de Teatro");
        return new Balcon();
    }

    @Override
    public Vip createVip() {
        System.out.println("Creando Vip de Teatro");
        return new Vip();
    }
}
