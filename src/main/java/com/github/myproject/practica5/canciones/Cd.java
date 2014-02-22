import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;

public class Cd{
    
    private String autor;
    private String titulo;
    private String identificador;
    private ArrayList <Cancion> listaCanciones;
    
    public Cd (String titulo, String autor, String identificador){
        
        this.titulo = titulo;
        this.autor = autor;
        this.identificador = identificador;
        listaCanciones = new ArrayList <Cancion> ();
    }
    public Cd (String titulo, String autor, String identificador, ArrayList <Cancion> listaCanciones){
        this(titulo, autor, identificador);
        if (!(listaCanciones.isEmpty())){
            this.listaCanciones = listaCanciones;
        } else {
           this.listaCanciones = new ArrayList <Cancion> (); 
        }
    }

    public String getAutor() {
        return autor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public ArrayList<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }
    
    public boolean pertenece (Cancion c){
        boolean encontrado = false; 
        Iterator i = listaCanciones.iterator ();
        while (i.hasNext() && !(encontrado)){
            Cancion cancionActual = (Cancion) i.next();
            if (cancionActual.tituloIgual(c.getTitulo())){
                encontrado = true;
            }
        }
        return encontrado;
    }
    
    public boolean añadirCancion (Cancion c){
        if (!(this.pertenece(c))){
            listaCanciones.add(c);
        }
        return this.pertenece(c);
    }
    
    public void añadirLista (ArrayList <Cancion> miLista){
        Iterator i = miLista.iterator ();
        while (i.hasNext()){ 
            Cancion cancionActual = (Cancion) i.next();
            this.añadirCancion(cancionActual);
        }
    }
    
    public boolean borrar (Cancion c){
        Iterator i = listaCanciones.iterator ();
        boolean encontrado = false;
        while (i.hasNext() && !(encontrado)){ 
            Cancion cancionActual = (Cancion) i.next();
            if (cancionActual.tituloIgual(c.getTitulo())){
                encontrado = true;
                listaCanciones.remove(cancionActual);
            }
        }
        return encontrado;
    }
    
    public Cancion borrarConTitulo (String titulo){
        Iterator i = listaCanciones.iterator ();
        boolean encontrado = false;
        Cancion cancionActual = new Cancion ("",0,"");
        while (i.hasNext() && !(encontrado)){ 
            cancionActual = (Cancion) i.next();
            if (cancionActual.tituloIgual(titulo)){
                encontrado = true;
                listaCanciones.remove(cancionActual);
            }
        }
        return cancionActual;
    }
    
    public ArrayList listaCalificacion (double nota){
        Iterator i = listaCanciones.iterator ();
        ArrayList <Cancion> salida = new ArrayList <Cancion> ();
        while (i.hasNext()){ 
            Cancion cancionActual = (Cancion) i.next();
            if (cancionActual.getNota() > nota){
                salida.add(cancionActual);
            }
        }
        return salida;
    }
    
    public ArrayList CalificacionIgOSup (double nota){
        ArrayList <Cancion> salida = new <Cancion> ArrayList();;
        for (Cancion aux: listaCanciones){ 
            if (aux.getNota() >= nota){
                salida.add(aux);
            }
        }
        if (salida.isEmpty()){
            salida = null;
        }
        return salida;
    }
    
    public double notaMedia (){
        double media = 0;
        if (listaCanciones != null){
            Iterator i = listaCanciones.iterator ();
            while (i.hasNext()){
                Cancion actual = (Cancion) (i.next());
                media = media + actual.getNota();
            }
        }
        return  (media / listaCanciones.size());
    }
    
    public String listaIgOSupString (double nota){
        StringBuilder canciones = new StringBuilder();
        Iterator i = this.CalificacionIgOSup(nota).iterator ();
        while (i.hasNext()){
            Cancion cancionActual = (Cancion) i.next();
            canciones.append(cancionActual.toString());
        }
        return canciones.toString() ;
    }
    
    public String toString (){
        StringBuilder hitCd = new StringBuilder();
        hitCd.append("CD: \n");
        hitCd.append("Titulo: ");
        hitCd.append(titulo);
        hitCd.append(" Autor: ");
        hitCd.append(autor);
        hitCd.append(" Identificador: ");
        hitCd.append(identificador);
        hitCd.append("\n");
        hitCd.append("Canciones: \n");
        Iterator i = listaCanciones.iterator ();
        while (i.hasNext()){
            Cancion cancionActual = (Cancion) i.next();
            hitCd.append(cancionActual.toString());
        }
        return hitCd.toString();
    }
    public boolean tituloIgual (String titulo){
        return this.titulo.equals(titulo);
    }
}