package proyectomongosh;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.List;
import java.util.Arrays;
public class Main {

    //Obtener la informacion de Mongosh
    public static MongoCollection<Document> getCollection(String collectionName) {
        MongoClient client = MongoClients.create("mongodb://localhost:27017"); // Realizar la conexion
        MongoDatabase database = client.getDatabase("Escolar");  //Realizar la conexion a Escolar
        return database.getCollection(collectionName);
    }

   
    public static Document findPerson(String nameOrId) {
        MongoCollection<Document> collection = getCollection("profesores"); 

        Document person = null;

        person = collection.find(new Document("Nombre.pila", nameOrId)).first();
        

        if (person == null) {
            try {
                int id = Integer.parseInt(nameOrId); 
                person = collection.find(new Document("_id", id)).first();  
            } catch (NumberFormatException e) {
                System.out.println("El ID ingresado no es válido.");
            }
        }
        return person;
    }

    public static void main(String[] args) {
   
        String input = "1";  
        Document person = findPerson(input);
        
        if (person != null) {
            System.out.println("Persona encontrada: " + person.toJson());
        } else {
            System.out.println("Persona no encontrada.");
        }
    }
    
 public static void darDeAltaProfesor(int id, String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, 
                                     String sexo, int edad, double sueldo, String gradoAcademico, 
                                     String[] materias, String[] pasatiempos, String telCasa, String telCelular) {
    // Crear el documento que se insertará en la base de datos
    Document profesor = new Document();
    profesor.append("_id", id) // Asignar el ID ingresado en el formulario
            .append("Nombre", new Document("pila", nombre)
                            .append("AP", apellidoPaterno)
                            .append("AM", apellidoMaterno))
            .append("RFC", rfc)
            .append("Sexo", sexo)
            .append("Edad", edad)
            .append("Sueldo", sueldo)
            .append("GradoAcademico", gradoAcademico)
            .append("Materias", Arrays.asList(materias))
            .append("Telefonos", new Document("casa", telCasa)
                                 .append("celular", telCelular))
            .append("Pasatiempos", Arrays.asList(pasatiempos));

    // Insertar el documento en MongoDB
    MongoCollection<Document> collection = getCollection("profesores");
    collection.insertOne(profesor);

    System.out.println("Profesor dado de alta correctamente.");
    
}

    static void darDeAltaProfesor(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, String sexo, int edad, double sueldo, String gradoAcademico, String[] materias, String[] pasatiempos, String telCasa, String telCelular) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
