/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jaxb_albaped;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jaxb.albaran.* ;


/**
 *
 * @author JJBH
 */
public class ModificaAlbaPed {
public static void main(String[] args) {
  try {

   // Crear una instancia de la clase JAXBContext para
   // poder manipular las clases generadas en el paquete jaxb.albaran
   // La clase JAXBContext proporciona al cliente un punto de entrada a la API JAXB
   // Facilita una abstracci�n para manejar la informaci�n generada para
   // implementar las operaciones del JAXB binding framework como unmarshal y marshal
   // unmarshal: consiste en convertir datos XML en un �rbol de objetos Java
   // marshal: consiste en convertir un �rbol de objetos Java a datos XML
   JAXBContext jaxbContext = JAXBContext.newInstance("jaxb.albaran");

   // Crear un objeto de tipo Unmarshaller para convertir datos XML en un �rbol de objetos Java
   Unmarshaller u = jaxbContext.createUnmarshaller();

   // La clase JAXBElement representa a un elemento de un documento XML
   // en este caso a un elemento del documento albaran.xml
   JAXBElement jaxbElement = (JAXBElement) u.unmarshal(
                               new FileInputStream("C:..\\JAXB_albaran\\xml-resources\\jaxb\\albaranBinding\\albaran.xml"));

   // El m�todo getValue() retorna el modelo de contenido (content model) y el valor de los atributos del elemento
   PedidoType pedidoType = (PedidoType) jaxbElement.getValue();

   // Obtenemos una instancia de tipo PedidoType para obtener un Objeto de tipo Direccion
   Direccion direccion = pedidoType.getFacturarA();

   // Establecemos los datos
   direccion.setNombre("Paula");
   direccion.setCalle("Zafiro 3");
   direccion.setCiudad("Molina");
   direccion.setProvincia("Murcia");
   direccion.setCodigoPostal(new BigDecimal("30500"));

   // Crear un objeto de tipo Marshaller para posteriormente convertir un
   // el �rbol de objetos Java a datos XML
   Marshaller m = jaxbContext.createMarshaller();

   // El m�todo setProperty(String nombrePropiedad, Object value) recibe en este
   // caso la propiedad "jaxb.formatted.output". Esta propiedad controla si al
   // realizar un marshal, debe formatear el resultado XML con saltos de linea
   // e indentaciones para que las personas podamos leerlo c�modamente. Por defecto
   // su valor es falso es decir el XML creado no est� formateado
   // El argumento value en este caso tiene que ser concretamente de tipo Boolean
   // para indicar si queremos que el resultado XML est� formateado o no
   m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

   // El m�todo marshall(Object elementoJAXB, OutputStream os) recibe un objeto
   // de tipo JAXBElement para que su contenido lo muestre en la salida est�ndar
   // debido a que este m�todo est� sobrecargo, si miramos la documentaci�n de
   //la API podemos ver como podemos mostrar o escribir el resultado XML de
   //diferentes maneras
   m.marshal(jaxbElement, System.out);
  
  } catch (JAXBException je) {
    System.out.println(je.getCause()) ;
  } catch (IOException ioe) {
   System.out.println(ioe.getMessage());
  }
 }
}
