package lab4;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class ErrorHandler implements ValidationEventHandler {

  @Override
  public boolean handleEvent(ValidationEvent event) {
    boolean stop = false;
    switch (event.getSeverity()) {
      case ValidationEvent.WARNING:
        stop = true;
        break;
      case ValidationEvent.ERROR:
        stop = false;
        System.err.println(event.getMessage());
        System.err.println("line " + event.getLocator().getLineNumber());
        System.err.println("column " + event.getLocator().getColumnNumber());
        break;
      case ValidationEvent.FATAL_ERROR:
        stop = false;
        System.err.println(event.getMessage());
        System.err.println("line " + event.getLocator().getLineNumber());
        System.err.println("column " + event.getLocator().getColumnNumber());
        break;
      default:
        throw new RuntimeException("unknow validation error");
    }
    return stop;
  }

}
