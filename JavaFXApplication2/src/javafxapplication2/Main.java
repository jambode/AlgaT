package javafxapplication2;


import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;


public class Main extends Application {

   
    public static void main(String[] args) {    launch(args);   }

    public  static int SPACING = 35;
    public  static int ARRAY_CELL_DIMENSION = 30;
    public  static  final int SORT_GROUP_MOVE_DELTA = 105;
    public  static  final int SORT_GROUP_MARGIN_DELTA = 35;
    public  static Duration SPEED = Duration.millis(200);
    public  static int ARRAY_LENGHT = 35;
    public  static int ARRAY_MAX_VALUE = 400;
    public  static int windowWidth = 0;
    public  static int windowHeight = 0;
    public  static int windowX = 0;
    public  static int windowY = 0;
    public  static int[] arrayInput = {};
    public  static boolean stopExecution = false;
    //public Input input;
    public Stage dialog;
    public Stage firstStage;
    public Graphics graphics;


    int scelta=0;
    public static void sceltaInputOptions(int scelta, int[] case3Array)
    {
        
        switch(scelta)
        {
            case 1:
                //crea un array
                stopExecution=false;
                Input.createInput(ARRAY_LENGHT, ARRAY_MAX_VALUE); //massimo input e valore
                break;

            case 3:
               //input da tastiera
                stopExecution=false;
                if(case3Array!=null)
                    Input.createInput(case3Array);
                else
                    sceltaInputOptions(1, null);

                break;

            default:
                stopExecution=true;
                break;
        }
    }

   
    //funzione principale
    public void launchProgram(Stage primaryStage)
    {
        
        primaryStage.setOnShown(new EventHandler<WindowEvent>() { public void handle(WindowEvent e) {
                return;
            }});
        
        if(!stopExecution) {
            if (arrayInput.length > 0) {
                try
                {
                    //crea la grafica e il primaryStage
                    graphics = new Graphics();
                    graphics.createGraphics(arrayInput, primaryStage);
                    //ordina l'array
                    MergeSort algorithm = new MergeSort(graphics);
                }
                catch (RuntimeException ex1)
                {
                    System.out.println("Eccezzione generata --> " + ex1.getLocalizedMessage());
                }
            }
        }
    }
  

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        
        stopExecution=false; //serve per fermare l'esecuzione
        firstStage=primaryStage;  //menu
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
       //dimensioni finestra
        windowWidth  = (int)bounds.getWidth();
        windowHeight = (int)bounds.getHeight();
        windowX      = (int)bounds.getMinX();
        windowY      = (int)bounds.getMinY();
        
        SequentialTransition transition = new SequentialTransition();
        //qui ci sono tutte le lezioni 
        dialog = new Stage();
        Lessons lez = new Lessons(primaryStage, dialog);

        
        primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e)
            {
                
                primaryStage.close();
                try
                {
                    launchProgram(primaryStage);
                }
                catch(NullPointerException ex)
                {
                    System.out.println("Eccezione generata nel lancio dell'interfaccia grafica dell'algortimo (puntatore non trovato) --> " + ex.getMessage());
                }

            }
        });
    }
    
}

   

