import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



/**
 * Waschanlage
 */
public class Waschanlage {
   


    public Warteschlangen warteschlangen;
    private Waschstraße[] waschstraßen;
    private BoxInnenreinigung[] boxen;

    public static ExecutorService pool = Executors.newFixedThreadPool(5);

    public Waschanlage () {
        
        initVars(); 
        erbaueWaschanlage();        

        return;
    }    
    


    private void initVars()  {
        return;
    }   
    
    private void erbaueWaschanlage(){
        
        erbaueWarteschlangen();
        
        erbaueWaschstraßen();
        erbaueBoxen();

    }

    private void erbaueWarteschlangen() {
        warteschlangen = new Warteschlangen();
    }

    private void erbaueBoxen() {
        BoxInnenreinigung Box1 = new BoxInnenreinigung(1,warteschlangen);
        BoxInnenreinigung Box2 = new BoxInnenreinigung(2, warteschlangen);
        pool.execute(Box1);
        pool.execute(Box2);
        boxen = new BoxInnenreinigung[]{Box1,Box2};

    }

    private void erbaueWaschstraßen() {
        Waschstraße Straße1 = new Waschstraße(1,warteschlangen);
        Waschstraße Straße2 = new Waschstraße(2,warteschlangen);
        Waschstraße Straße3 = new Waschstraße(3,warteschlangen);
        
        pool.execute(Straße1);
        pool.execute(Straße2);
        pool.execute(Straße3);
        
        waschstraßen = new Waschstraße[]{Straße1, Straße2, Straße3};
    }

    public void Preise(){
        System.out.println("Tolle Tankstelle!\nPreise:\nDiesel 2 €\nBenzin 2€");
    }


    public void Schließen (){
        for (var elemStraße : waschstraßen) {
            elemStraße.offen = false;
        }
        for (var elemBox : boxen) {
            elemBox.offen = false ;
        }

        pool.shutdown();

        System.out.println("Schließe Tankstelle. Alle Autos die gerade noch geputzt werden, dürfen dies Beenden. Der Rest muss leider morgen wiederkommen.");
    }

    /**
     * Warteschlangen
     */
    public static class Warteschlangen {
    
        private int warteschlangeWaschen;
        private int warteschlangeSaugen;
        private int warteschlangeBeides;    
        
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        
        
        public Warteschlangen() {
            warteschlangeBeides = 0;
            warteschlangeSaugen = 0;
            warteschlangeWaschen = 0;
        }

        public void WarteAufAutos (){
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }

        public void SchickeAutosNachHause(){
            lock.lock();
            System.out.println("Autos die auf Wäsche und Saugen gewartet haben: " + warteschlangeBeides);
            System.out.println("Autos die auf Wäsche gewartet haben: " + warteschlangeWaschen);
            System.out.println("Autos die auf Saugen gewartet haben: " + warteschlangeSaugen);
            condition.signal();
            lock.unlock();
        }

        public void ReiheAutosInWarteschlangeWaschen (int Wert) {
            lock.lock();
            System.out.println(Wert + " neu in Warteschlange Waschen");
            warteschlangeWaschen += Wert;
            condition.signal();
            lock.unlock();
        }

        public void ReiheAutosInWarteschlangeBeides (int Wert) {
            lock.lock();
            System.out.println(Wert + " neu in Warteschlange Beides");
            warteschlangeBeides += Wert;
            condition.signal();
            lock.unlock();
        }

        public void ReiheAutosInWarteschlangeSaugen (int Wert) {
            lock.lock();
            System.out.println(Wert + " neu in Warteschlange Saugen");
            warteschlangeSaugen += Wert;
            condition.signal();
            lock.unlock();
        }

        public boolean AutosInWarteschlangeWaschen () {
            lock.lock();
            boolean r = true;
            if (warteschlangeWaschen == 0){
                r = false;
            }else{
                warteschlangeWaschen--;
            }
            lock.unlock();
            return r;
        }

        public boolean AutosInWarteschlangeBeides () {
            lock.lock();
            boolean r = true;
            if (warteschlangeBeides == 0){
                r = false;
            }else{
                warteschlangeBeides--;
            }
            lock.unlock();
            return r;
        }

        public boolean AutosInWarteschlangeSaugen () {
            lock.lock();
            var r = true;
            if (warteschlangeSaugen == 0){
                r = false;
            }else{
                warteschlangeSaugen--;
            }
            lock.unlock();
            return r;
        }

        
    }


    /**
     * Waschstraße
     */
    public static class Waschstraße implements Runnable{
        public int id,max,min;
        public boolean offen;
        private Warteschlangen warteschlangen;



        public Waschstraße(int initID, Warteschlangen ws){
            id = initID;
            min = 5;
            max = 12;
            offen = true;
            warteschlangen = ws;

        }
        public void Waschen(){
            System.out.println("Splish von Waschstraße " + this.id);
        }

        public void StopWaschen(int zeit){
            System.out.println("Nach " + zeit + " Minuten trocknet Waschstraße " + this.id);
        }


        @Override
        public void run() {
            while(offen){
                if (warteschlangen.AutosInWarteschlangeWaschen()){ 
                    Waschen();
                    int zeit = (int) Math.floor(Math.random()*(max-min+1)+min);
                    try {
                        TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*zeit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                    StopWaschen(zeit);
                }else{
                    if(warteschlangen.AutosInWarteschlangeBeides()){
                        Waschen();
                        int zeit = (int) Math.floor(Math.random()*(max-min+1)+min);
                        try {
                            TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*zeit);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        };
                        StopWaschen(zeit);
                        System.out.println("Jedoch möchte das Auto noch gesaugt werden.");
                        if(offen){
                        warteschlangen.ReiheAutosInWarteschlangeSaugen(1);
                        }else{
                            System.out.println("Aber, da die Tankstelle geschlossenhat fährt es traurig davon.");
                        } 

                    }else{
                        warteschlangen.WarteAufAutos();
                    }
                }


            }            
        }
    }

    /**
     * BoxInnenreinigung
     */
    public class BoxInnenreinigung implements Runnable{
        public int id,max,min;
        public boolean offen;
        private Warteschlangen warteschlangen;


        public BoxInnenreinigung(int initID, Warteschlangen ws) {
            id = initID;
            max = 3;
            min = 1;
            offen = true;
            warteschlangen = ws;

        }
        
        public void Saugen() {
            System.out.println("Sauggeräusche beginnen in Box " + this.id);

        }
        
        public void StopSaugen(int chipzahl) {
            System.out.println("Nach " + chipzahl*3 + " Minuten herrscht Stille in Box " + this.id);

        }

        @Override
        public void run() {
            while(offen) {
                if (warteschlangen.AutosInWarteschlangeBeides()){ 
                    Saugen();
                    int chipzahl = (int) Math.floor(Math.random()*(max-min+1)+min);
                    try {
                        TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*chipzahl*5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                    StopSaugen(chipzahl);
                    System.out.println("Jedoch möchte das Auto noch gewaschen werden.");
                    if(offen){
                        warteschlangen.ReiheAutosInWarteschlangeWaschen(1);
                    }else{
                        System.out.println("Aber, da die Tankstelle geschlossenhat fährt es traurig davon.");
                    } 
                }else{
                    if(warteschlangen.AutosInWarteschlangeSaugen()){
                        Saugen();
                        int chipzahl = (int) Math.floor(Math.random()*(max-min+1)+min);
                        try {
                            TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*chipzahl*5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        };
                        StopSaugen(chipzahl);

                    }else{
                        warteschlangen.WarteAufAutos();
                    }
                }
            }
        }
    }
}
