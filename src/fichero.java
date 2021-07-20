
import java.util.*;
import java.io.*;

public class fichero {
    private String buscarPalabra; //Variable para el apartado de buscar un producto en el ArrayList
    //Nombre del fichero que bamos a crear y su ArrayList
    private static String fi = "alimentos.dat";
    private ArrayList <Alimentos> datos = new ArrayList<Alimentos>();
    
    //Constructor
    public fichero() throws IOException{

            DataInputStream ois=null;
            try {
                
                File ficherodatos =new File (fi);
                if (!ficherodatos.exists()){
                    ficherodatos.createNewFile();
                }
                
                ois=new DataInputStream(new FileInputStream(fi));
                
                Alimentos aux = new Alimentos();
                aux.setNombre(ois.readUTF());
                while (aux.getNombre()!=null){
                    aux.setCalorias (ois.readUTF());
                    aux.setHorario (ois.readUTF());
                    datos.add(aux);
                    aux=new Alimentos();
                    aux.setNombre(ois.readUTF());
                }
                ois.close();
                
            }
            catch (EOFException ex){
            
            }
    }
    
    //Método para guardar los datos de los archivos que hemos escrito
    private void guardar () throws IOException{
    
        DataOutputStream oos = null;
        try {
            File ficherosdatos = new File (fi);
            oos = new DataOutputStream (new FileOutputStream(fi));
           
            for (int i =0; i<datos.size(); i++){
                oos.writeUTF(datos.get (i).getNombre());
                oos.writeUTF(datos.get (i).getCalorias());
                oos.writeUTF(datos.get (i).getHorario());
            }
            oos.close ();
            
        }
        catch (Exception ex){
        ex.printStackTrace();
        
        }
        finally{
            oos.close();
        }
    
    }
    
    //Dar altas
    public void altas (String nombre, String calorias, String horario_comidas) throws IOException {
    
        Alimentos aux = new Alimentos();
        aux.setNombre(nombre);
        aux.setCalorias(calorias);
        aux.setHorario(horario_comidas);
        
        datos.add(aux);
        guardar();
    }
    
    //Mostrar listado
    public String listado(){
        String texto="";
        for(int i=0; i< datos.size();i++){
            texto= texto + "Alimento(100g): "+ 
            datos.get(i).getNombre() + " | Kcal: "+ datos.get(i).getCalorias()+ " | Horario adecuado: "+
            datos.get(i).getHorario() +"\n"; 
        }
    return "+++LISTA DE ALIMENTOS+++\n"+texto;
    
    }
    
    /*Método Buscar, que introducimos el nombre que queremos buscar y lo 
    comparamos con el que tenemos en el fichero. Si son iguales muestra los demás datos*/  
        
    public String getBuscarPalabra() {
        return buscarPalabra;
    }

    public void setBuscarPalabra(String buscarPalabra) {
        this.buscarPalabra = buscarPalabra;
    }
    
    public String buscar(){
        String texto="+++ ALIMENTO NO ENCONTRADO +++";
        for(int i=0; i< datos.size();i++){
            if(datos.get(i).getNombre().equals(buscarPalabra)){
                texto= "ALIMENTO ENCONTRADO ------> \n" + "Alimento(100g): "+ datos.get(i).getNombre() + " | Kcal: "+ 
                datos.get(i).getCalorias()+ " | Horario adecuado: "+ datos.get(i).getHorario() +"\n";        
            }
        
        }
    return texto;
    
    }
    
    //Modificar Calorías del alimento
    public int modificar (String nombre, String calorias, String horario_comidas) throws IOException {
        int modificado=-1;
        for (int i =0; i<datos.size(); i++){
                if (datos.get(i).getNombre().equals(nombre) && datos.get(i).getHorario().equals(horario_comidas))
                {
                    modificado=i;
                    break;
                }
            }
        if (modificado>=0){
            datos.get(modificado).setCalorias(calorias);
            guardar();
            return modificado;
        }
        else{
        return modificado;
        }
    
    }
    
    //Borrar
    public int borrar (String nombre) throws IOException {
        int borrado=-1;
        for (int i =0; i<datos.size(); i++){
                if (datos.get(i).getNombre().equals(nombre))
                {
                    borrado=i;
                    break;
                }
            }
        if (borrado>=0){
            datos.remove(borrado);
            guardar();
            return borrado;
        }
        else{
        return borrado;
        }
    } 
    
    
    
    public String planificador(String nuevasCalorias){
        int desayuno=0;
        int media_manana=0;
        int comida=0;
        int merienda=0;
        int cena=0;
        int valorArray=0;
        
        Alimentos a = new Alimentos();
        a.setNuevasCalorias(nuevasCalorias);
        
        String texto="+++ DESAYUNO +++ Total= "+a.calDesayuno()+" Kcal Máximas"+"\n\n";
        
        while(desayuno<a.calDesayuno()){
            
            valorArray = (int) Math.floor(Math.random()*datos.size());
            
            if(datos.get(valorArray).getHorario().equals("desayuno")){
                desayuno=desayuno+Integer.parseInt(datos.get(valorArray).getCalorias());
                if(desayuno<a.calDesayuno()){
                    texto= texto + "Alimento(100g): "+ datos.get(valorArray).getNombre() + " | Kcal: "+ 
                    datos.get(valorArray).getCalorias()+ " | Horario adecuado: "+ datos.get(valorArray).getHorario() +"\n";
                }
            }
            
        }
        
        /*Quitamos las calorias del alimento que ha sobrepasado las calorias totales del desayuno
        y creamos un for para encontrar un alimento que tenga las calorias necesarias para
        ajustarse a las totales del desayuno*/
        if(desayuno>a.calDesayuno()){
        desayuno=desayuno-Integer.parseInt(datos.get(valorArray).getCalorias());
        }
        
        for (int i =0; i<datos.size(); i++){
            if(a.calDesayuno()>desayuno+Integer.parseInt(datos.get(i).getCalorias()) && datos.get(i).getHorario().equals("desayuno")){
                desayuno=desayuno+Integer.parseInt(datos.get(i).getCalorias());
                texto= texto + "Alimento(100g): "+ datos.get(i).getNombre() + " | Kcal: "+ 
                datos.get(i).getCalorias()+ " | Horario adecuado: "+ datos.get(i).getHorario() +"\n";
            }
        }
        

        
                
        texto=texto + "\n+++ MEDIA MAÑANA +++ Total= "+a.calMediaManana()+" Kcal Máximas"+"\n\n";
        
        while(media_manana<a.calMediaManana()){
            
            valorArray = (int) Math.floor(Math.random()*datos.size());
            
            if(datos.get(valorArray).getHorario().equals("media mañana")){
            media_manana=media_manana+Integer.parseInt(datos.get(valorArray).getCalorias());
                if(media_manana<a.calMediaManana()){
                    texto= texto + "Alimento(100g): "+ datos.get(valorArray).getNombre() + " | Kcal: "+ 
                    datos.get(valorArray).getCalorias()+ " | Horario adecuado: "+ datos.get(valorArray).getHorario() +"\n";
                }
            }
        }
        
        /*Quitamos las calorias del alimento que ha sobrepasado las calorias totales de la media mañana
        y creamos un for para encontrar un alimento o varios que tenga las calorias necesarias para
        ajustarse a las totales de la media mañana*/
        if(media_manana>a.calMediaManana()){
        media_manana=media_manana-Integer.parseInt(datos.get(valorArray).getCalorias());
        }
        
        for (int i =0; i<datos.size(); i++){
            if(a.calMediaManana()>media_manana+Integer.parseInt(datos.get(i).getCalorias()) && datos.get(i).getHorario().equals("media mañana")){
                media_manana=media_manana+Integer.parseInt(datos.get(i).getCalorias());
                texto= texto + "Alimento(100g): "+ datos.get(i).getNombre() + " | Kcal: "+ 
                datos.get(i).getCalorias()+ " | Horario adecuado: "+ datos.get(i).getHorario() +"\n";
            }
        }
        
       texto=texto + "\n+++ COMIDA +++ Total= "+a.calComida()+" Kcal Máximas"+"\n\n";
        
        while(comida<a.calComida()){
            
            valorArray = (int) Math.floor(Math.random()*datos.size());
            
            if(datos.get(valorArray).getHorario().equals("comida")){
            comida=comida+Integer.parseInt(datos.get(valorArray).getCalorias());
                if(comida<a.calComida()){
                    texto= texto + "Alimento(100g): "+ datos.get(valorArray).getNombre() + " | Kcal: "+ 
                    datos.get(valorArray).getCalorias()+ " | Horario adecuado: "+ datos.get(valorArray).getHorario() +"\n";
                }
            }
        }
        
        /*Quitamos las calorias del alimento que ha sobrepasado las calorias totales de la comida
        y creamos un for para encontrar un alimento o varios que tenga las calorias necesarias para
        ajustarse a las totales de la comida*/
        if(comida>a.calComida()){
        comida=comida-Integer.parseInt(datos.get(valorArray).getCalorias());
        }
        
        for (int i =0; i<datos.size(); i++){
            if(a.calComida()>comida+Integer.parseInt(datos.get(i).getCalorias()) && datos.get(i).getHorario().equals("comida")){
                comida=comida+Integer.parseInt(datos.get(i).getCalorias());
                texto= texto + "Alimento(100g): "+ datos.get(i).getNombre() + " | Kcal: "+ 
                datos.get(i).getCalorias()+ " | Horario adecuado: "+ datos.get(i).getHorario() +"\n";
            }
        }
        
       texto=texto + "\n+++ MERIENDA +++ Total= "+a.calMerienda()+" Kcal Máximas"+"\n\n";
        
        while(merienda<a.calMerienda()){
            
            valorArray = (int) Math.floor(Math.random()*datos.size());
            
            if(datos.get(valorArray).getHorario().equals("merienda")){
            merienda=merienda+Integer.parseInt(datos.get(valorArray).getCalorias());
                if(merienda<a.calMerienda()){
                    texto= texto + "Alimento(100g): "+ datos.get(valorArray).getNombre() + " | Kcal: "+ 
                    datos.get(valorArray).getCalorias()+ " | Horario adecuado: "+ datos.get(valorArray).getHorario() +"\n";
                }
            }
        } 
        
        /*Quitamos las calorias del alimento que ha sobrepasado las calorias totales de la merienda
        y creamos un for para encontrar un alimento o varios que tenga las calorias necesarias para
        ajustarse a las totales de la merienda*/
        if(merienda>a.calMerienda()){
        merienda=merienda-Integer.parseInt(datos.get(valorArray).getCalorias());
        }
        
        for (int i =0; i<datos.size(); i++){
            if(a.calMerienda()>merienda+Integer.parseInt(datos.get(i).getCalorias()) && datos.get(i).getHorario().equals("merienda")){
                merienda=merienda+Integer.parseInt(datos.get(i).getCalorias());
                texto= texto + "Alimento(100g): "+ datos.get(i).getNombre() + " | Kcal: "+ 
                datos.get(i).getCalorias()+ " | Horario adecuado: "+ datos.get(i).getHorario() +"\n";
            }
        }
        
       texto=texto + "\n+++ CENA +++ Total= "+a.calCena()+" Kcal Máximas"+"\n\n";
        
        while(cena<a.calCena()){
            
            valorArray = (int) Math.floor(Math.random()*datos.size());
            
            if(datos.get(valorArray).getHorario().equals("cena")){
            cena=cena+Integer.parseInt(datos.get(valorArray).getCalorias());
                if(cena<a.calCena()){
                    texto= texto + "Alimento(100g): "+ datos.get(valorArray).getNombre() + " | Kcal: "+ 
                    datos.get(valorArray).getCalorias()+ " | Horario adecuado: "+ datos.get(valorArray).getHorario() +"\n";
                }
            }
        }
        
        /*Quitamos las calorias del alimento que ha sobrepasado las calorias totales de la cena
        y creamos un for para encontrar un alimento o varios que tenga las calorias necesarias para
        ajustarse a las totales de la cena*/
        if(cena>a.calCena()){
        cena=cena-Integer.parseInt(datos.get(valorArray).getCalorias());
        }
        
        for (int i =0; i<datos.size(); i++){
            if(a.calCena()>cena+Integer.parseInt(datos.get(i).getCalorias()) && datos.get(i).getHorario().equals("cena")){
                cena=cena+Integer.parseInt(datos.get(i).getCalorias());
                texto= texto + "Alimento(100g): "+ datos.get(i).getNombre() + " | Kcal: "+ 
                datos.get(i).getCalorias()+ " | Horario adecuado: "+ datos.get(i).getHorario() +"\n";
            }
        }
        
    return texto;
    }
    
    
}
