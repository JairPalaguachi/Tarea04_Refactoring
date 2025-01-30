package AbstractFactory;
public class Map {
    private SelectFunctionsMaps funciones;
    
    public SelectFunctionsMaps selectFunction(){
        return this.funciones;
    }
    public void show_map(){
        System.out.println("Mostrando mapa....");
    }

    
}
