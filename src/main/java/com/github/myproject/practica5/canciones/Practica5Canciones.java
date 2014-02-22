public class Practica5Canciones {
    
    public static void main(String[] args) {
        MediatecaCds mediateca = new MediatecaCds();
        mediateca.construirCds();
        System.out.println(mediateca);
        mediateca.ficheroCd();
        mediateca.mejoresCd();
    }
}
