import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.PriorityBlockingQueue;



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
    
        private PriorityBlockingQueue<String> warteschlangeWaschen;
        private PriorityBlockingQueue<String> warteschlangeSaugen;
        private PriorityBlockingQueue<String> warteschlangeBeides;    
       
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        
        
        public Warteschlangen() {
            warteschlangeBeides = new PriorityBlockingQueue<String>();
            warteschlangeSaugen = new PriorityBlockingQueue<String>();
            warteschlangeWaschen =new PriorityBlockingQueue<String>();
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
            System.out.println("Autos die auf Wäsche und Saugen gewartet haben: " + warteschlangeBeides.toString());
            System.out.println("Autos die auf Wäsche gewartet haben: " + warteschlangeWaschen.toString());
            System.out.println("Autos die auf Saugen gewartet haben: " + warteschlangeSaugen.toString());
            condition.signal();
            lock.unlock();
        }

        public void ReiheAutosInWarteschlangeWaschen (int Wert, String[] namenStrings) {
            lock.lock();
            for (String string : namenStrings) {
                System.out.println("Füge " + string + " in Warteschlange Waschen");
                warteschlangeWaschen.add(string);
            }
            condition.signal();
            lock.unlock();
        }

        public void ReiheAutosInWarteschlangeBeides (int Wert, String[] namenStrings) {
            lock.lock();
            for (String string : namenStrings) {
                System.out.println("Füge " + string + " in Warteschlange Beides");
                warteschlangeBeides.add(string);
            } 
            condition.signal();
            lock.unlock();
        }

        public void ReiheAutosInWarteschlangeSaugen (int Wert, String[] namenStrings) {
            lock.lock();
            for (String string : namenStrings) {
                System.out.println("Füge " + string + " in Warteschlange Saugen");
                warteschlangeSaugen.add(string);
            }
            condition.signal();
            lock.unlock();
        }

        public String AutosInWarteschlangeWaschen () {
            lock.lock();
            String r = warteschlangeWaschen.poll();
            lock.unlock();
            return r;
        }

        public String AutosInWarteschlangeBeides () {
            lock.lock();
            String r = warteschlangeBeides.poll();
            lock.unlock();
            return r;
        }

        public String AutosInWarteschlangeSaugen () {
            lock.lock();
            String r = warteschlangeSaugen.poll();
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
        public void Waschen(String Autoid){
            System.out.println("Splish! Wasche " + Autoid + " in Waschstraße " + this.id);
        }

        public void StopWaschen(int zeit){
            System.out.println("Nach " + zeit + " Minuten trocknet Waschstraße " + this.id);
        }


        @Override
        public void run() {
            while(offen){
                String Auto = warteschlangen.AutosInWarteschlangeWaschen();
                if (Auto != null){ 
                    Waschen(Auto);
                    int zeit = (int) Math.floor(Math.random()*(max-min+1)+min);
                    try {
                        TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*zeit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                    StopWaschen(zeit);
                }else{
                    Auto = warteschlangen.AutosInWarteschlangeBeides();
                    if(Auto != null){
                        Waschen(Auto);
                        int zeit = (int) Math.floor(Math.random()*(max-min+1)+min);
                        try {
                            TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*zeit);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        };
                        StopWaschen(zeit);
                        System.out.println("Jedoch möchte "+ Auto +"noch gesaugt werden.");
                        if(offen){
                        warteschlangen.ReiheAutosInWarteschlangeSaugen(1, new String[] {Auto});
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
        
        public void Saugen(String Autoid) {
            System.out.println("CHooow! Sauge " + Autoid + " in Box " + this.id);

        }
        
        public void StopSaugen(int chipzahl) {
            System.out.println("Nach " + chipzahl*5 + " Minuten herrscht Stille in Box " + this.id);

        }

        @Override
        public void run() {
            while(offen) {
                String Auto = warteschlangen.AutosInWarteschlangeBeides();
                if ( Auto != null){ 
                    Saugen(Auto);
                    int chipzahl = (int) Math.floor(Math.random()*(max-min+1)+min);
                    try {
                        TimeUnit.MILLISECONDS.sleep(ZeitSimulator.zeitfaktor*chipzahl*5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                    StopSaugen(chipzahl);
                    System.out.println("Jedoch möchte "+ Auto +"noch gewaschen werden.");
                    if(offen){
                        warteschlangen.ReiheAutosInWarteschlangeWaschen(1, new String[] {Auto});
                    }else{
                        System.out.println("Aber, da die Tankstelle geschlossenhat fährt es traurig davon.");
                    } 
                }else{
                    Auto = warteschlangen.AutosInWarteschlangeSaugen(); 
                    if( Auto != null){
                        Saugen(Auto);
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
