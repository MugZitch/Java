import com.fazecast.jSerialComm.*;
import javax.smartcardio.Card;
import javax.sql.rowset.serial.SerialDatalink;
import java.util.Scanner;
import com.sun.corba.se.spi.activation.ServerOperations;

public class Serial {
    public static String data;

    public static void listenSerial() {
        /* Change "COM4" to your USB port connected to the Arduino
         * You can find the right port using the ArduinIDE
                */
            SerialPort comPort = SerialPort.getCommPort("COM3");

            //set the baud rate to 9600 (same as the Arduino)
            comPort.setBaudRate(9600);

            //open the port
            comPort.openPort();

            //create a listener and start listening
            comPort.addDataListener(new SerialPortDataListener() {
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                public void serialEvent(SerialPortEvent event)
                {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                        CardReader.setInput(null);
                        return; //wait until we receive data
                    }

                    byte[] newData = new byte[comPort.bytesAvailable()]; //receive incoming bytes
                    comPort.readBytes(newData, newData.length); //read incoming bytes
                    //convert bytes to string
                    String serialData = new String(newData);
                    serialData = serialData.replaceAll("\\s+",""); //Removes all whitespaces and non-visible characters
//                    if(serialData.length() == 8){//8 length of nuid
                    if(serialData.length() == 14){//14 length of IBAN
                        CardReader.setInput(serialData);
                    }else if(serialData.length() == 1){
                        Keypad.setInput(serialData);
                    }
                }
            });
        }
}

