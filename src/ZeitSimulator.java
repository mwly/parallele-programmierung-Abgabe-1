import java.util.concurrent.TimeUnit;

/**
 * ZeitSimulator
 */
public class ZeitSimulator implements Runnable {
   
    
    private Waschanlage waschanlage;    

    public static int zeitfaktor = 50;

    private int percentageCounter;
    public ZeitSimulator(int zf,Waschanlage wa){
        // Um eine Annäherung an eine "Live" Simulation zu bekommen muss der zeitfaktor zf = 1000 sein
        zeitfaktor = zf;
        waschanlage = wa;
        percentageCounter = 0;
    }

    @Override
    public void run() {

        nachmittags();
                
        ruschHour();
        
        abends();

        feierabend();

    }

    private void nachmittags() {
        for ( int i = 0; i < 60 ; i++){
            System.out.println("Nachmittags-Min: " + (i+1));
            if (i%5 == 0){
                generiereNachmittagsKunden();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(zeitfaktor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ruschHour() {
        for ( int i = 0; i < 60 ; i++){
            System.out.println("RuschHour-Min: " + (i+1));
            if (i%5 == 0){
                generiereRushHourKunden();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(zeitfaktor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void abends() {
        for ( int i = 0; i < 60 ; i++){
            System.out.println("Abends-Min: " + (i+1));
            if (i%5 == 0){
                generiereAbendsKunden();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(zeitfaktor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void feierabend() {
        waschanlage.Schließen();
        waschanlage.warteschlangen.SchickeAutosNachHause();

    }

    private void generiereNachmittagsKunden(){
        int kunden = (int) Math.floor(Math.random()*(2)+1);
        switch (kunden) {
            case 1:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1);
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1);
                    percentageCounter--;
                }
                
                break;
            case 2:
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1);
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1);
                break;
            case 3:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2);
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1);
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1);
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2);
                    percentageCounter--;
                }
                break;
            default:
                break;
        }
    }
 
    private void generiereRushHourKunden() {
        int kunden = (int) Math.floor(Math.random()*(3)+3);
        switch (kunden) {
            case 6:
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(4);
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2);
                break;
            case 5:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(4);
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1);
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(3);
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2); 
                    if (percentageCounter == 1){
                        percentageCounter++;
                    }else{
                        percentageCounter = 0 ;
                    }
                
                }
                
                break;
            case 4:
                if(percentageCounter == 1){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2);
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2); 
                    percentageCounter--;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(3);
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1);
                    if (percentageCounter == 2){
                        percentageCounter--;
                    }else {
                        percentageCounter = 2;
                    }
                }
                
                break;
            case 3: 
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2);
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1);
            break;
            default:
                break;
        }


    }
    
    
    private void generiereAbendsKunden() {
        int kunden = (int) Math.floor(Math.random()*(1)+1);
        waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(kunden);
    }


}
