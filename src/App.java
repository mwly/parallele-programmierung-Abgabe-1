
/**
 * App
 */
public class App {
    public static void main(String[] args) throws Exception {
        // Initialisiere Waschanlagen Objekt 
        Waschanlage ourWaschanlage = new Waschanlage();
        ourWaschanlage.Preise();
        
        ZeitSimulator ourZeitsimulator = new ZeitSimulator( 50 ,ourWaschanlage);

        Thread tZeitSimulator = new Thread(ourZeitsimulator);

        tZeitSimulator.start();
         

    }
}
