
/**
 * App
 */
public class App {
    public static void main(String[] args) throws Exception {
        // Initialisiere Waschanlagen Objekt 
        Waschanlage ourWaschanlage = new Waschanlage();
        ourWaschanlage.Preise();
        
        ZeitSimulator outZeitsimulator = new ZeitSimulator();

        Thread tZeitSimulator = new Thread(outZeitsimulator);

        tZeitSimulator.start();
         

    }
}
