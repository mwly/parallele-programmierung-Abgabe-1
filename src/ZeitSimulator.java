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

        System.out.println("Starte Zeitsimulator");
        System.out.println("Autos, die Nachmittags hinzugefügt werden, bekommen eine ID zwischen 000 und 100 zugeordnet");
        System.out.println("Autos, die während der RuschHour hinzugefügt werden, bekommen eine ID zwischen 100 und 200 zugeordnet");
        System.out.println("Autos, die Abends hinzugefügt werden, bekommen eine ID zwischen 200 und 300 zugeordnet");
        System.out.println("Autos, die eine Premiummitgliedschaft abgeschlossen haben, führen eine 1, Autos ohne Mitgliedschaft eine 2.");
       
        nachmittags();
                
        ruschHour();
        
        abends();

        feierabend();

    }

    private void nachmittags() {
        int x = 2000;
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
        int x = 2100;
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
        int x = 2200;
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
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1000 : 0)) });
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"Auto#" + ((no+1)  - (Math.random()<0.3 ? 1000 : 0))});
                    percentageCounter--;
                }
                
                break;
            case 2:
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1,new String[] {"Auto#" + ((no+1)  - (Math.random()<0.3 ? 1000 : 0))});
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"Auto#" + ((no+2)  - (Math.random()<0.3 ? 1000 : 0))});
                break;
            case 3:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1000 : 0)), "Auto#"+ ((no+2) - (Math.random()<0.3 ? 1000 : 0)) });
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"Auto#" + ((no+3) - (Math.random()<0.3 ? 1000 : 0)) });
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(1,new String[] {"Auto#" + ((no+1)  - (Math.random()<0.3 ? 1000 : 0))});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"Auto#" + ((no+2) - (Math.random()<0.3 ? 1000 : 0)),"Auto#" + ((no+3)  - (Math.random()<0.3 ? 1000 : 0))});
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
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(4,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+2) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+3) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+4)  - (Math.random()<0.3 ? 1100 : 0))});
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"Auto#" + ((no+5) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+6) - (Math.random()<0.3 ? 1100 : 0))});
                break;
            case 5:
                if(percentageCounter == 0){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(4,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+2) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+3) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+4) - (Math.random()<0.3 ? 1100 : 0)) });
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0))});
                    percentageCounter++;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(3,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+2) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+3) - (Math.random()<0.3 ? 1100 : 0))});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"Auto#" + ((no+4) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+5) - (Math.random()<0.3 ? 1100 : 0))}); 
                    if (percentageCounter == 1){
                        percentageCounter++;
                    }else{
                        percentageCounter = 0 ;
                    }
                
                }
                
                break;
            case 4:
                if(percentageCounter == 1){
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+2) - (Math.random()<0.3 ? 1100 : 0))});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(2,new String[] {"Auto#" + ((no+3) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+4) - (Math.random()<0.3 ? 1100 : 0))}); 
                    percentageCounter--;
                }else{
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(3,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+2) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+3) - (Math.random()<0.3 ? 1100 : 0))});
                    waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"Auto#" + ((no+4) - (Math.random()<0.3 ? 1100 : 0))});
                    if (percentageCounter == 2){
                        percentageCounter--;
                    }else {
                        percentageCounter = 2;
                    }
                }
                
                break;
            case 3: 
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeWaschen(2,new String[] {"Auto#" + ((no+1) - (Math.random()<0.3 ? 1100 : 0)),"Auto#" + ((no+2) - (Math.random()<0.3 ? 1100 : 0))});
                waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(1,new String[] {"Auto#" + ((no+3) - (Math.random()<0.3 ? 1100 : 0))});
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
            tmpListe.add("Auto#"+((no+i+1) - (Math.random()<0.3 ? 1200 : 0)));
        }
        String[] NamenListe = new String[tmpListe.size()];
        tmpListe.toArray(NamenListe);
        waschanlage.warteschlangen.ReiheAutosInWarteschlangeBeides(kunden,NamenListe);
        return kunden;
    }


}
