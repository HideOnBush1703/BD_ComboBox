
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Pais {
    
    private int idPais;
    private String nombrePais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
    public String toString(){
        return this.nombrePais;
    }
    
    public Vector<Pais> mostrarPaises(){
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        Vector<Pais> vectorPaises = new Vector<Pais>(); //creando un vector de paises
        Pais pais = null; //creando un objeto de pais
        
        try{
            
            Conexion con=new Conexion();
            Connection conexion = con.getConnection();
            
            ps=conexion.prepareStatement("select * from paises");
            rs=ps.executeQuery();
            
            pais=new Pais();//instanciando el objeto
            pais.setIdPais(0); //id del pais es 0
            pais.setNombrePais("Seleccione país"); //nombre del pais es seleccione pais y como tiene id 0 no afecta
            vectorPaises.add(pais);
            
            while(rs.next()){
                //agregando cada pais que encontremos en nuestra base de datos está siendo agregado al vector
                pais=new Pais();
                pais.setIdPais(rs.getInt("idpais")); 
                pais.setNombrePais(rs.getString("nombrePais")); 
                vectorPaises.add(pais);
                
            }
            
            rs.close();
            
        }catch(Exception ex){
            System.err.println("Error, "+ex);
        }
        
        return vectorPaises;
    }
    
}
