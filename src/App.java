
/**
 * App
 */
public class App {
    public static void main(String[] args) throws Exception {
        // Initialisiere Waschanlagen Objekt 
        Waschanlage ourWaschanlage = new Waschanlage();
        ourWaschanlage.Preise();
        
        ZeitSimulator ourZeitsimulator = new ZeitSimulator(1000 ,ourWaschanlage);

        Thread tZeitSimulator = new Thread(ourZeitsimulator);

        tZeitSimulator.start();
         

    }
}
