import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;

public class Cancion{
    private String titulo;
    private int duracion;
    private GregorianCalendar fecha = new GregorianCalendar();
    private double nota;
    private String identificador;
    
    private final double MINNOTA = 0.0;
    private final double MAXNOTA = 10.0;
    private final int MINDURACION = 0;
    private final int MAXDURACION = 600;
    
    public Cancion (String titulo, int duracion){
        this.titulo = titulo;
        this.duracion = ((duracion >= MINDURACION) && (duracion <= MAXDURACION)) ? duracion:MINDURACION;
    }
    
    public Cancion (String titulo, double nota){
        this.titulo = titulo;
        this.nota = ((nota >= MINNOTA)&&(nota <= MAXNOTA)) ? nota:MINNOTA;
        duracion = ((int) (Math.random()*600) + 1);
    }
    
    public Cancion (String titulo, double nota, String identificador){
       this.titulo = titulo;
       this.identificador = identificador;
       this.nota = ((nota >= MINNOTA)&&(nota <= MAXDURACION)) ? nota:MINNOTA;
    }
    
    public Cancion (Cancion cancion){
        this (cancion.titulo,cancion.nota,cancion.identificador);
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public double getNota() {
        return nota;
    }

    public String getIdentificador() {
        return identificador;
    }
    

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDuracion(int duracion) {
        this.duracion = ((duracion >= MINDURACION) && (duracion <= MAXDURACION)) ? duracion:MINDURACION;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public void setNota(double nota) {
        this.nota = ((nota >= MINNOTA)&&(nota <= MAXDURACION)) ? nota:MINNOTA;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
    public String toString (){
        StringBuilder cancionaca = new StringBuilder();
        cancionaca.append("Titulo:");
        cancionaca.append(titulo);
        cancionaca.append(" Duracion:");
        cancionaca.append(duracion);
        Date f = fecha.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String fs= df.format(f);
        cancionaca.append(" Fecha:");
        cancionaca.append(fs);
        cancionaca.append(" Nota:");
        cancionaca.append(nota);
        cancionaca.append("\n");
        return cancionaca.toString();
    }
    
    public boolean tituloIgual (String titulo){
        return this.titulo.equals(titulo);
    }
    
    public boolean notaSuperior (Cancion c){
        return (this.nota >= c.nota);
    }
    
    
}
