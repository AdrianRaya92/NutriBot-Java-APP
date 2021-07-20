//@author Adrian Raya Hernandez 

//CLASE ALIMENTOS
public class Alimentos {
    
    //VARIABLES
    public String nombre;               //NOMBRE DEL ALIMENTO
    public String calorias;             //CALORIAS QUE TIENE UN ALIMENTO
    public String horario_comida;       //HORARIO DE LA COMIDA QUE PUEDE SER DESAYUNO, MEDIA MAÑANA, COMIDA, MERIENDA Y CENA
    
    //VARIABLE PARA GENERAR LISTA SEGÚN LAS CALORÍAS QUE INTRODUZCAMOS
    public String nuevasCalorias="0";
    
    //VARIABLES DE CALORIAS PERMITIDAS SEGUN EL HORARIO DE LA COMIDA
    public int calorias_desayuno=0;
    public int calorias_media_manana=0;
    public int calorias_comida=0;
    public int calorias_merienda=0;
    public int calorias_cena=0;
    
    //GETTER Y SETTER DEL ALIMENTO
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCalorias() {
        return calorias;
    }
    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }    
    public String getHorario() {
        return horario_comida;
    }
    public void setHorario(String horario_comida) {
        this.horario_comida = horario_comida;
    }  
    
    

    //GETTER Y SETTER DE LAS CALORÍAS QUE INTRODUCIMOS PARA GENERAR UNA LISTA DE ALIMENTOS
    public String getNuevasCalorias() {
        return nuevasCalorias;
    }
    
    public void setNuevasCalorias(String nuevasCalorias) {
        this.nuevasCalorias = nuevasCalorias;
    }

    /*METODOS DE LAS CALORIAS QUE SE DEBEN TENER EN CADA HORARIO DE COMIDA SEGUN
    LAS CALORIAS ESCRITAS POR EL CLIENTE*/
    
    //EN EL DESAYUNO EL 15% DE LAS CALORIAS DE UN DIA
    public int calDesayuno(){
        try {
        calorias_desayuno = (Integer.parseInt(nuevasCalorias)*15)/100;
        } catch ( NumberFormatException ex ) {
        }
    return calorias_desayuno;
    }
    //EN LA MEDIA MAÑANA EL 10% DE LAS CALORIAS DE UN DIA
    public int calMediaManana(){
        try {
        calorias_media_manana = (Integer.parseInt(nuevasCalorias)*10)/100;
        } catch ( NumberFormatException ex ) {
        }
    return calorias_media_manana;
    }
    //EN LA COMIDA EL 40% DE LAS CALORIAS DE UN DIA
    public int calComida(){
        try {
        calorias_comida = (Integer.parseInt(nuevasCalorias)*40)/100;
        } catch ( NumberFormatException ex ) {
        }
    return calorias_comida;
    }
    //EN LA MERIENDA EL 10% DE LAS CALORIAS DE UN DIA
    public int calMerienda(){
        try {
        calorias_merienda = (Integer.parseInt(nuevasCalorias)*10)/100;
        } catch ( NumberFormatException ex ) {
        }
    return calorias_merienda;
    }
    //EN LA CENA EL 25% DE LAS CALORIAS DE UN DIA
    public int calCena(){
        try {
        calorias_cena = (Integer.parseInt(nuevasCalorias)*25)/100;
        } catch ( NumberFormatException ex ) {
        }
    return calorias_cena;
    }
 
}
