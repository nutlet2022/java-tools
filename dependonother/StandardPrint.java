package dependonother;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StandardPrint {
    private static final Logger LOGGER = LogManager.getLogger(StandardPrint.class);
    public static void main(String[] args) {
        LOGGER.warn("log4j2 print");
        System.out.println("standard print");
    }
 }