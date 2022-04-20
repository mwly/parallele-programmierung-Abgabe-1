import java.util.concurrent.TimeUnit;

/**
 * ZeitSimulator
 */
public class ZeitSimulator implements Runnable {
    
    
    public ZeitSimulator(){

    }

    @Override
    public void run() {

        nachmittags();
                
        ruschHour();
        
        abends();

    }

    private void nachmittags() {
        for ( int i = 0; i < 60 ; i++){
            System.out.println("Nachmittags Minute: " + (i+1));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ruschHour() {
        for ( int i = 0; i < 60 ; i++){
            System.out.println("RuschHour Minute: " + (i+1));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void abends() {
        for ( int i = 0; i < 60 ; i++){
            System.out.println("Abends Minute: " + (i+1));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

}
