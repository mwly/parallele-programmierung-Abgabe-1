import java.util.ArrayList;
import java.util.List;
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
        int x = 0;
        for ( int i = 0; i < 60 ; i++){
            System.out.println("Nachmittags-Min: " + (i+1));
            if (i%5 == 0){
                x += generiereNachmittagsKunden(x);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(zeitfaktor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ruschHour() {
        int x = 0;
        for ( int i = 0; i < 60 ; i++){
            System.out.println("RuschHour-Min: " + (i+1));
            if (i%5 == 0){
                x += generiereRushHourKunden(x);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(zeitfaktor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void abends() {
        int x = 0;
        for ( int i = 0; i < 60 ; i++){
            System.out.println("Abends-Min: " + (i+1));
            if (i%5 == 0){
                x += generiereAbendsKunden(x);
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

    private int generiereNachmittagsKunden(int no){
        int kunden = (int) Math.floor(Math.random()*(2)+1);
        switch (kunden) {
            case 1:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1,new String[] {"NachmittagAuto#" + (no+1) });
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"NachmittagAuto#" + (no+1) });
                    percentageCounter--;
                }
                
                break;
            case 2:
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1,new String[] {"NachmittagAuto#" + (no+1) });
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"NachmittagAuto#" + (no+2) });
                break;
            case 3:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2,new String[] {"NachmittagAuto#" + (no+1), "NachmittagAuto#"+ (no+2) });
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"NachmittagAuto#" + (no+3) });
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1,new String[] {"NachmittagAuto#" + (no+1) });
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"NachmittagAuto#" + (no+2),"NachmittagAuto#" + (no+3) });
                    percentageCounter--;
                }
                break;
            default:
                break;
        }
        return kunden;
    }
 
    private int generiereRushHourKunden(int no) {
        int kunden = (int) Math.floor(Math.random()*(3)+3);
        switch (kunden) {
            case 6:
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(4,new String[] {"RushHourAuto#" + (no+1),"RushHourAuto#" + (no+2),"RushHourAuto#" + (no+3),"Auto#" + (no+4) });
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"RushHourAuto#" + (no+5),"RushHourAuto#" + (no+6)});
                break;
            case 5:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(4,new String[] {"RushHourAuto#" + (no+1),"RushHourAuto#" + (no+2),"RushHourAuto#" + (no+3),"RushHourAuto#" + (no+4) });
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"RushHourAuto#" + (no+1)});
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(3,new String[] {"RushHourAuto#" + (no+1),"RushHourAuto#" + (no+2),"RushHourAuto#" + (no+3)});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"RushHourAuto#" + (no+4),"RushHourAuto#" + (no+5)}); 
                    if (percentageCounter == 1){
                        percentageCounter++;
                    }else{
                        percentageCounter = 0 ;
                    }
                
                }
                
                break;
            case 4:
                if(percentageCounter == 1){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2,new String[] {"RushHourAuto#" + (no+1),"RushHourAuto#" + (no+2)});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"RushHourAuto#" + (no+3),"RushHourAuto#" + (no+4)}); 
                    percentageCounter--;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(3,new String[] {"RushHourAuto#" + (no+1),"RushHourAuto#" + (no+2),"RushHourAuto#" + (no+3)});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"RushHourAuto#" + (no+4)});
                    if (percentageCounter == 2){
                        percentageCounter--;
                    }else {
                        percentageCounter = 2;
                    }
                }
                
                break;
            case 3: 
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2,new String[] {"RushHourAuto#" + (no+1),"RushHourAuto#" + (no+2)});
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"RushHourAuto#" + (no+3)});
            break;
            default:
                break;
        }
        return kunden;

    }
    
    
    private int generiereAbendsKunden(int no) {
        int kunden = (int) Math.floor(Math.random()*(1)+1);
        List<String> tmpListe = new ArrayList<String>();
        for(int i = 0;i<kunden;i++){
            tmpListe.add("AbendsAuto#"+(no+i+1));
        }
        String[] NamenListe = new String[tmpListe.size()];
        tmpListe.toArray(NamenListe);
        waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(kunden,NamenListe);
        return kunden;
    }


}
