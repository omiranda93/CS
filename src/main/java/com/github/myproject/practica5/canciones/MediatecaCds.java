import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class MediatecaCds {
    
    private ArrayList <Cd> listaCds;
    private String fentrada = "ficheros" + System.getProperty("file.separator") + "todo.txt";
    private String fcd = "ficheros" + System.getProperty("file.separator") + "cds.txt";
    private String fdata = "ficheros" + System.getProperty("file.separator") + "cds.dat";
    private String fmejoras = "ficheros" + System.getProperty("file.separator") + "mejorescanciones.txt";
    
    public MediatecaCds (){
        listaCds = new ArrayList <Cd> ();
    }

    public ArrayList<Cd> getListaCds() {
        return listaCds;
    }
    
    public void construirCds (){
        try {
            String linea;    
            BufferedReader flec;
            flec = new BufferedReader (new FileReader (fentrada));
            Cd prueba = new Cd("","",""); 
            Cancion cancionPrueba = new Cancion("",0,"");
            while(((linea = flec.readLine())!= null)){
                String [] palabras = linea.split("\t");
                prueba = new Cd (palabras[4], palabras[0], palabras[1]);
                cancionPrueba = new Cancion (palabras[2], Double.parseDouble(palabras[3]), palabras[5]);
                
                if (!(this.pertenece(prueba))){
                    this.listaCds.add(prueba);
                }
                this.añadirCancionCd(prueba, cancionPrueba);
                
            } 
            flec.close();
        }catch (IOException e){ 
          System.err.println(e);
        }
    }  
    
    public void añadirCancionCd (Cd c, Cancion nueva){
        boolean encontrado = false; 
        Iterator i = listaCds.iterator ();
        while (i.hasNext() && !(encontrado)){
            Cd cdActual = (Cd) i.next();
            if (cdActual.tituloIgual(c.getTitulo())){
                encontrado = true;
                cdActual.añadirCancion(nueva);
            }
        }
    }
    
    public boolean pertenece (Cd c){
        boolean encontrado = false; 
        Iterator i = listaCds.iterator ();
        while (i.hasNext() && !(encontrado)){
            Cd cdActual = (Cd) i.next();
            if (cdActual.tituloIgual(c.getTitulo())){
                encontrado = true;
            }
        }
        return encontrado;
    }
    
    public void ficheroCd (){
        try{
            PrintWriter escritor = new PrintWriter( new BufferedWriter( new FileWriter (fcd)));
            for ( Cd cdAct : listaCds){
                escritor.println(cdAct.getIdentificador() + " " + cdAct.getAutor() + " " + cdAct.getTitulo());
            }   
            escritor.close();
        }catch (Exception e){
            System.out.println ("Error al escribir");
        }
    }
    
    public String toString (){
        StringBuilder hitCd = new StringBuilder();
        for (Cd aux : listaCds){
            hitCd.append(aux.toString()+"\n"); 
        }
        return hitCd.toString();
    }
    
    public void mejoresCd (){
       try{
            PrintWriter escritor = new PrintWriter( new BufferedWriter( new FileWriter (fmejoras)));
            for ( Cd cdAct : listaCds){
                double nota = 0;
                for (Cancion cAct : cdAct.getListaCanciones()){
                    if (nota <= cAct.getNota()){
                        nota = cAct.getNota();
                    }
                }
                ArrayList <Cancion> mejoresCd = cdAct.CalificacionIgOSup(nota);
                escritor.println("CD:");
                escritor.println(cdAct.getIdentificador() + " " + cdAct.getAutor() + " " + cdAct.getTitulo());
                escritor.println("CANCIONES:");
                for (Cancion cAct : mejoresCd){
                    escritor.println(cAct.getTitulo() + " " + cdAct.getIdentificador() + " " + cdAct.getAutor());
                }
            }   
            escritor.close();
        }catch (IOException e){
            System.out.println ("Error al escribir");
        } 
    }
}

