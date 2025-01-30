package AbstractFactory;
public class MicroTeatroFactory implements AbstractFactoryTicket {
    @Override
    public Platea createPlatea() {
        System.out.println("Creando Platea de MicroTeatro");
        return new Platea();
    }

    @Override
    public Balcon createBalcon() {
        System.out.println("Creando Balcón de MicroTeatro");
        return new Balcon();
    }

    @Override
    public Vip createVip() {
        System.out.println("Creando Vip de MicroTeatro");
        return new Vip();
    }
}
