package AbstractFactory;
public class StandUpFactory implements AbstractFactoryTicket {
    @Override
    public Platea createPlatea() {
        System.out.println("Creando Platea de StandUp");
        return new Platea();
    }

    @Override
    public Balcon createBalcon() {
        System.out.println("Creando Balcón de StandUp");
        return new Balcon();
    }

    @Override
    public Vip createVip() {
        System.out.println("Creando Vip de StandUp");
        return new Vip();
    }
}
