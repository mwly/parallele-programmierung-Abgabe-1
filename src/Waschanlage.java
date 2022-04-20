/**
 * Waschanlage
 */
public class Waschanlage {
   


    public Warteschlangen warteschlangen;
    private Waschstraße[] waschstraßen;
    private BoxInnenreinigung[] boxen;


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
        BoxInnenreinigung Box1 = new BoxInnenreinigung(1);
        BoxInnenreinigung Box2 = new BoxInnenreinigung(2);

        boxen = new BoxInnenreinigung[]{Box1,Box2};
        for (var elemBox : boxen) {
            elemBox.Saugen();
        }
    }

    private void erbaueWaschstraßen() {
        Waschstraße Straße1 = new Waschstraße(1);
        Waschstraße Straße2 = new Waschstraße(2);
        Waschstraße Straße3 = new Waschstraße(3);
        
        waschstraßen = new Waschstraße[]{Straße1, Straße2, Straße3};
        for (var elemStraße : waschstraßen) {
            elemStraße.Waschen();
        }
    }

    public void Preise(){
        System.out.println("Tolle Tankstelle!\nPreise:\nDiesel 2 €\nBenzin 2€");
    }

    /**
     * Warteschlangen
     */
    public static class Warteschlangen {
    
        private int warteschlangeWaschen;
        private int warteschlangeSaugen;
        private int warteschlangeBeides;    
        
        public Warteschlangen() {
            warteschlangeBeides = 0;
            warteschlangeSaugen = 0;
            warteschlangeWaschen = 0;
        }
        public void AddiereAufWarteschlangeWaschen (int Wert) {
            warteschlangeWaschen += Wert;
        }

        public void AddiereAufWarteschlangeBeides (int Wert) {
            warteschlangeBeides += Wert;
        }

        public void AddiereAufWarteschlangeSaugen (int Wert) {
            warteschlangeSaugen += Wert;
        }

        public void DoneWarteschlangeWaschen () {
            warteschlangeWaschen--;
        }

        public void DoneWarteschlangeBeides () {
            warteschlangeBeides--;
        }

        public void DoneWarteschlangeSaugen () {
            warteschlangeSaugen--;
        }

        public boolean WartendeInWarteschlangeWaschen () {
            if (warteschlangeWaschen == 0){
                return false;
            }else{
                return true;
            }
        }

        public boolean WartendeInWarteschlangeBeides () {
            if (warteschlangeBeides == 0){
                return false;
            }else{
                return true;
            }
        }

        public boolean WartendeInWarteschlangeSaugen () {
            if (warteschlangeSaugen == 0){
                return false;
            }else{
                return true;
            }
        }

        
    }


    /**
     * Waschstraße
     */
    public static class Waschstraße implements Runnable{
        public int id;


        public Waschstraße(int initID){
            id = initID;
        }
        public void Waschen(){
            System.out.println("Splish von Waschstraße " + this.id);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            
        }
        
    }

    /**
     * BoxInnenreinigung
     */
    public class BoxInnenreinigung implements Runnable{
        public int id;

        public BoxInnenreinigung(int initID) {
           id = initID;
        
        }
        
        public void Saugen() {
            System.out.println("Sauggeräusche aus Box " + this.id);

        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            
        }
        
    }


}
