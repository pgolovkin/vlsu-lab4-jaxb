package lab4;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.io.File;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import lab4.children.Child;
import lab4.children.Children;

public class JAXBAnalyzer {

  public static void main(String[] args) {
    JAXBContext context;
    try {
      context = JAXBContext.newInstance("lab4.children");
      Unmarshaller unmarshaller = context.createUnmarshaller();
      unmarshaller.setEventHandler(new ErrorHandler());
      Children children = null;
      children = (Children) unmarshaller.unmarshal(JAXBAnalyzer.class.getClassLoader().getResourceAsStream("children.xml"));
      //выводим всё в html. Реализавция вывода в html находится в методе toString() классов - сущностей
      System.out.println(children);
      Child child = new Child();
      child.setBirthday(new XMLGregorianCalendarImpl(new GregorianCalendar(1993, 3, 4)));
      child.setFio("Иванов Иван Иванович");
      child.setTotalApples(new BigInteger("10"));
      child.setEatenApples(new BigInteger("3"));
      children.getChild().add(child);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setEventHandler(new ErrorHandler());
      //выводим получившееся дерево объектов в xml файл.
      marshaller.marshal(children, new File("result.xml"));
    } catch (MarshalException e) {
      System.exit(0);
    } catch (UnmarshalException e) {
      System.exit(0);
    } catch (JAXBException ex) {
      ex.printStackTrace();
    }
  }
}
