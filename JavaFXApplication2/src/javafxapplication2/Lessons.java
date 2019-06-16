package javafxapplication2;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Orientation;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Lessons
{
    public  BorderPane  container;
    public  Scene       dialogScene;
    public  VBox        mainContainer;
    public  VBox        inputContainer;
    public  VBox        optionsContainer;
    public  Stage       myStage;
    public  Stage       myPrimaryStage;
    public  Stage       QStage;
    public  TextField   inputArraySize;
    public  TextField   inputMethod;
    public  Label       tutorial;
    public  Button      start;

    //******************************************************************************************************************
    int size, method; String file;
    //******************************************************************************************************************

    public void setContainer()
    {
        // set at center the HBox which contains the two main window (input, tutorial)
        container = new BorderPane();
        container.setCenter(mainContainer);
    }

    public void setStage()
    {
        // set the stage
        myStage.setTitle("MaTho - A Merge Sort Tutorial");
        myStage.setResizable(false);
        myStage.setX(Main.windowX);
        myStage.setY(Main.windowY);
        myStage.setWidth(Main.windowWidth);
        myStage.setHeight(Main.windowHeight);
        dialogScene = new Scene(container, 300, 200);
        myStage.setScene(dialogScene);
        myStage.show();
    }
       int questionIndex=0;
       int questionIndex1=0;
       private RadioButton [] radio= new RadioButton[4];
       int counter = 0; //counter per i punti
       int correct[] = {0,0,0,0,0}; //per controllare se la domanda è corretta o meno
       int correct1[]= {0,0,0,0,0};
       Alert a = new Alert(AlertType.NONE);    
    public void setOptionsContainer()
    {
        // Container parte tutorial
        optionsContainer = new VBox();
        //optionsContainer.setMaxHeight(Main.windowHeight/2);
        optionsContainer.setPrefHeight(Main.windowHeight/2);
        optionsContainer.setPrefWidth((Main.windowWidth/2)-15);
        optionsContainer.setStyle("-fx-background-color:rgb(43, 43, 43);"); //Colore container tutorial
        optionsContainer.setAlignment(Pos.CENTER);
        optionsContainer.setSpacing(10);
        
        Label title = new Label("Lezione 1");
        Label content1 = new Label ("La tecnica più importante e più usata per progettare algoritmi efficienti \n"+
                                    "consiste nel partizionare il problema in sottoproblemi più piccoli \n"+
                                    "dello stesso tipo, risolverli, e successivamente ricombinare le soluzioni \n"+
                                    "parziali per ottenere la soluzione del problema originale."
                                    + "Questo tipo di approccio è detto tipicamente top down.");
        Label content2 = new Label("\tDivide-et-impera (P, n)\n" +
                                        "\tif n ≤ k then \n"+
                                               "\t\t“risolvi direttamente”\n" +
                                        "\telse “dividi P in sottoproblemi P1, P2,…,Ph\n" +
                                                "\t\tdi dimensione n1, n2,…, nh risp.”\n" +
                                                "\t\tfor i ← 1 to h do\n" +
                                                "\t\tDivide-et-impera (Pi, ni)\n" +
                                                "\t\t“combina i risultati di P1,…, Ph\n" +
                                                "\t\tper ottenere quello di P”");
        title.setTextFill(Color.WHITE);
        Font fontTitle = Font.font("Monospace", FontWeight.BOLD, 25);  //Dimensione del titolo Lezione 1
        title.setFont(fontTitle);
        
        Button butt0 = new Button("Next step");
        
        content2.setTextFill (Color.WHITE); //colore testo argomento Lezione 1
        content1.setTextFill (Color.WHITE); //colore testo argomento Lezione 1
        Font font = Font.font("Monospace", FontWeight.BOLD, 12);
        content2.setFont(font);
        
        
        butt0.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle (ActionEvent e){
                newStep();
            }
        });
        
        
        
       
        optionsContainer.getChildren().addAll(title, content1, content2, butt0);
    }

    
    public void newStep(){
        optionsContainer.getChildren().clear();
        Button butt1 = new Button("Inizia Test");
        Label content5  = new Label("Con questo metodo abbiamo 3 diverse fasi dell'algoritmo:\n"
                 +"-Divide: in questa parte si procede alla suddivisione dei problemi in problemi di dimensione minore;\n" +
                  "-Impera: nella seconda parte i problemi vengono risolti in modo ricorsivo. Quando i sottoproblemi arrivano \n"
                + "\tad avere una dimensione sufficientemente piccola, essi vengono risolti direttamente tramite il caso base;\n" +
                  "-Combina: l'ultima fase del paradigma prevede di ricombinare l'output ottenuto dalle precedenti chiamate \n"
                + "\tricorsive al fine di ottenere il risultato finale.");
        content5.setTextFill(Color.WHITE);
        Font font = Font.font("Monospace", FontWeight.BOLD, 12);
        content5.setFont(font);
        
        butt1.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent showq){
                            showQuestion();
            }
       
        });
        optionsContainer.getChildren().addAll(content5, butt1);
        
    } 
    
    
    //funzione che permette di aprire le domande della lezione
    public void showQuestion(){                   
        Stage QStage = new Stage();
        VBox Qbox = new VBox();
        Qbox.setAlignment(Pos.TOP_LEFT);  //posizione contenuto domande bisognerebbe controllare un pò il padding (troppo attaccato)
        Button next = new Button("Prossima Domanda");   //prossima domanda
        Button check = new Button("Verifica Risposta");   //controlla la correttezza delle domande
        Button lesson = new Button("Lezione 2");
        Label count = new Label();
        
        
        final Text question = new Text(Questions.questions[questionIndex]);
        question.setFill(Color.RED); 
        question.setTextAlignment(TextAlignment.LEFT); //allineamento testo parte test
        
        if(questionIndex==0){
            for(int i=0; i<4; i++){
            radio[i]=new RadioButton(Questions.answers[i]);
            
            }
        }
       
        
        next.setOnAction(new EventHandler<ActionEvent>(){             //cambia domanda
            @Override public void handle(ActionEvent actionEvent) {
            if((correct[0] == 1 && questionIndex==0) || (correct[1]==1 && questionIndex==1) || (correct[2]==1 && questionIndex==2 || correct[3]==1 && questionIndex==3)){
            questionIndex++;
            question.setText(Questions.questions[questionIndex]);
            }
            if(questionIndex==1){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers[i+4]);
                    
                }
            }
            if(questionIndex == 2){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers[i+8]);
                }
            }
            if(questionIndex == 3){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers[i+12]);
                }
            }
            if(questionIndex == 4){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers[i+16]);
                }
                Qbox.getChildren().addAll(lesson);
            }
        }
            
    });
        
        //SELEZIONA UN SOLO BOTTONE ALLA VOLTA.
        ToggleGroup question1= new ToggleGroup();
        radio[0].setToggleGroup(question1);
        radio[1].setToggleGroup(question1);
        radio[2].setToggleGroup(question1);
        radio[3].setToggleGroup(question1);
        
        
        check.setOnAction(new EventHandler<ActionEvent>(){
            
           @Override public void handle(ActionEvent checking){
               a.setAlertType(AlertType.INFORMATION);     //ti dice che la risposta è esatta
               if(radio[1].isSelected() && questionIndex==0){
                    a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                    a.show();
                    correct[0] = 1;
                    //counter++;
                    //count.setText("Score:"+counter);
               }
               else if(radio[2].isSelected() && questionIndex==1){
                    a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                    a.show();
                    correct[1] = 1;
                    //counter++;
                    //count.setText("Score:"+counter);
               }
               else if(radio[3].isSelected() && questionIndex==2){
                   a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                   a.show();
                   correct[2] = 1;
                   //counter++;
                   //count.setText("Score:"+counter);
               }
               else if(radio[0].isSelected() && questionIndex==3){
                   a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                   a.show();
                   correct[3] = 1;
               }
               else if(radio[3].isSelected() && questionIndex==4){
                   a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                   a.show();
                   correct[4] = 1;
               }
               else {
                   a.setContentText("Risposta sbagliata, riprova.");
                   a.show();
               }
               
           } 
        });
        
        
        lesson.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent newless){
                if(radio[3].isSelected() && questionIndex==4){
                    QStage.close();
                    optionsContainer.getChildren().clear();
                    lesson2();
                    
                }
                else {
                    a.setAlertType(AlertType.INFORMATION);     //ti dice che la risposta è esatta
                    a.setContentText("Prima di continuare dovresti superare il test.");
                    a.show();
                }
                
                
               
        }
    });
        
       // count.setText("Score:" + counter);
        
        
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.getChildren().addAll(check, next);
        Qbox.getChildren().addAll(question, radio[0], radio[1], radio[2], radio[3], tileButtons, count);
        Scene scene = new Scene(Qbox, 400, 250);  //grande 400x250 della finestra test
        QStage.setScene(scene);
        QStage.show();
    }
    
    
    public void lesson2(){
        Label title2 = new Label("Lezione 2");
        Button butt2 = new Button("Next Step");
        title2.setTextFill(Color.WHITE);
        Font fontTitle2 = Font.font("Monospace", FontWeight.BOLD, 25);
        title2.setFont(fontTitle2);
        
        Label content3 = new Label("Un noto algoritmo che usa questa tecnica è il MergeSort. \n"+
                                    "Divide il vettore (di dimensione n) in due sequenze di n/2 elementi \n"+
                                    "ordina ciascuna sequenza e poi 'fonde' le due metà ordinate in \n"+
                                    "un'unica sequenza ordinata.");
        Label content4 = new Label("\tMergesort(A, primo, ultimo)\n"
                                 + "\t\tint mezzo\n"
                                 + "\t\tif primo<ultimo then\n"
                                 + "\t\t\tmezzo=(primo+ultimo)div2\n"
                                 +"\t\t\tMergesort(A, primo, mezzo)\n"
                                 +"\t\t\tMergesort(A, mezzo+1, ultimo)\n"
                                 +"\t\t\tMerge(A, primo, ultimo, mezzo)\n"
              );
        Font font = Font.font("Monospace", FontWeight.BOLD, 12);
        content4.setFont(font);
        content3.setTextFill(Color.WHITE);
        content4.setTextFill(Color.WHITE);
        
        butt2.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                nextStep1();
            }
    });
        
        optionsContainer.getChildren().addAll(title2, content3, content4, butt2);
    }
    
    public void nextStep1(){
        optionsContainer.getChildren().clear();
       optionsContainer.getChildren().clear();
        Button butt1 = new Button("Inizia Test");
        Label content5  = new Label("Modalità funzionamento di MergeSort():\n"
                 +"Per fondere le due metà ordinate la MergeSort() chiama la procedura Merge() che si avvale di un vettore di appoggio B.\n" +
                  " La Merge() inoltre usa gli indici i,j,k\n" + "per scandire -> A[primo...mezzo], A[mezzo + 1...ultimo] e B[primo...ultimo]. "
                + "Ad ogni passo, sono confrontati gli elementi A[i] e A[j], il minore dei due copiato in \nB[k] e sono incrementati k e l'indice dell'elemento minore.\n" +
                  "Procedimento iterato finchè una delle due metà A[primo...mezzo], A[mezzo+1...ultimo] è esaurita.\n"
                + "Se i > mezzo gli eventuali elementi non scanditi A[j...ultimo] della seconda metà si trovano già nelle posizioni che\n competono loro nell'ordinamento finale.\n" +
                
                 "Se invece i <= mezzo, gli elementi non scanditi A[i...mezzo] della metà vengono subito spostati nelle ultime posizioni A[k...ultimo] che competono loro nell'ordinamento finale.\n" + 
                "Infine B[primo...k-1] è ricopiata in A[primo...k-1], ottenendo così A[primo...ultimo] ordinato.");
        content5.setTextFill(Color.WHITE);
        Font font = Font.font("Monospace", FontWeight.BOLD, 12);
        content5.setFont(font);
        
        butt1.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent showq){
                            showQuestion1();
            }
       
        });
        optionsContainer.getChildren().addAll(content5, butt1);
        
    } 
    
     public void showQuestion1(){                   
        Stage QStage = new Stage();
        VBox Qbox = new VBox();
        Qbox.setAlignment(Pos.TOP_LEFT);  //posizione contenuto domande bisognerebbe controllare un pò il padding (troppo attaccato)
        Button next = new Button("Prossima Domanda");   //prossima domanda
        Button check = new Button("Verifica Risposta");   //controlla la correttezza delle domande
        
        
        
        final Text question = new Text(Questions.questions1[questionIndex1]);
        question.setFill(Color.RED); 
        question.setTextAlignment(TextAlignment.LEFT); //allineamento testo parte test
        
        if(questionIndex1==0){
            for(int i=0; i<4; i++){
            radio[i]=new RadioButton(Questions.answers1[i]);
            
            }
        }
       
        
 
        
        next.setOnAction(new EventHandler<ActionEvent>(){             //cambia domanda
            @Override public void handle(ActionEvent actionEvent) {
            if((correct1[0] == 1 && questionIndex1==0) || (correct1[1]==1 && questionIndex1==1) || (correct1[2]==1 && questionIndex1==2) || (correct1[3]==1 && questionIndex1==3)){
            questionIndex1++;
            question.setText(Questions.questions1[questionIndex1]);
            }
            
            if(questionIndex1==1){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers1[i+4]);
                    
                }
            }
            if(questionIndex1 == 2){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers1[i+8]);
                }
            }
            if(questionIndex1 == 3){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers1[i+12]);
                }
            }
            if(questionIndex1 == 4){
                for(int i=0; i<4; i++){
                    radio[i].setText(Questions.answers1[i+16]);
                }
            }
        }
            
    });
        
        //SELEZIONA UN SOLO BOTTONE ALLA VOLTA.
        ToggleGroup question1= new ToggleGroup();
        radio[0].setToggleGroup(question1);
        radio[1].setToggleGroup(question1);
        radio[2].setToggleGroup(question1);
        radio[3].setToggleGroup(question1);
        
        
        check.setOnAction(new EventHandler<ActionEvent>(){
            
           @Override public void handle(ActionEvent checking){
               a.setAlertType(AlertType.INFORMATION);     //ti dice che la risposta è esatta
               if(radio[0].isSelected() && questionIndex1==0){
                    a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                    a.show();
                    correct1[0] = 1;
                    //counter++;
                    //count.setText("Score:"+counter);
               }
               else if(radio[2].isSelected() && questionIndex1==1){
                    a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                    a.show();
                    correct1[1] = 1;
                    //counter++;
                    //count.setText("Score:"+counter);
               }
               else if(radio[1].isSelected() && questionIndex1==2){
                   a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                   a.show();
                   correct1[2] = 1;
                   //counter++;
                   //count.setText("Score:"+counter);
               }
               else if(radio[2].isSelected() && questionIndex1 == 3){
                   a.setContentText("Risposta Corretta! Chiudi il box e passa alla prossima domanda.");
                   a.show();
                   correct1[3] = 1;                   
               }
               else if(radio[1].isSelected() && questionIndex1 == 4){
                   a.setContentText("Risposta Corretta! Hai passato il test!.");
                   a.show();
                   correct1[4] = 1;
                   QStage.close();
               }
               else {
                   a.setContentText("Risposta sbagliata, riprova.");
                   a.show();
               }
               
           } 
        });
        
        TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
        tileButtons.setPadding(new Insets(20, 10, 20, 0));
        
        tileButtons.setHgap(10.0);
        tileButtons.setVgap(8.0);
        tileButtons.getChildren().addAll(check, next);
        Qbox.getChildren().addAll(question, radio[0], radio[1], radio[2], radio[3], tileButtons);
        Scene scene = new Scene(Qbox, 400, 250);  //grande 400x250 della finestra test
        QStage.setScene(scene);
        QStage.show();
    }
   
    
    
    public void setOptionsContainerTutorial()
    {
        // Info for generate a correct input txt files
        tutorial = new Label();
        tutorial.setTextFill(Color.AZURE);
        tutorial.setAlignment(Pos.CENTER);
        // retrieve the text of "tutorial"
       
        
        tutorial.setTextAlignment(TextAlignment.CENTER);
        tutorial.setMinWidth((Main.windowWidth/2)-15);
        tutorial.setWrapText(true);
        optionsContainer.getChildren().add(tutorial);
    }


    public void setInputContainer()
    {
        // container for input options
        inputContainer = new VBox();
        inputContainer.setPrefHeight((Main.windowHeight/2)-30);
        inputContainer.setPrefWidth((Main.windowWidth/2)-15);
        inputContainer.setStyle("-fx-background-color:rgb(43, 43, 43);");  //colore container programma
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setSpacing(30);
    }

    public void setInputContainerDetail()
    {
        // container --> size of array
        VBox container1 = new VBox();
        container1.setAlignment(Pos.CENTER);
        container1.setPrefWidth((Main.windowWidth/2)-15);
        container1.setSpacing(25);
        Label inputTitle = new Label("Scegli la dimensione dell'array (valori generati casualmente).");
        Label inputSubTitle = new Label("Attenzione: non inserire un array troppo grande, altrimenti non visualizzabile." +
                                             " Dimensioni raccomandate inferiori a: " + Integer.toString(Main.ARRAY_LENGHT));
        Font fontTitle2 = Font.font("Monospace", FontWeight.BOLD, 25);  //Dimensione del titolo Scegli dimensione array
        inputTitle.setFont(fontTitle2);
        inputTitle.setTextFill(Color.AZURE);
        inputSubTitle.setTextFill(Color.AZURE);
        inputTitle.setAlignment(Pos.CENTER);
        // input for retrieve array's size
        inputArraySize = new TextField ();
        inputArraySize.setText("");  //testo interno box scelta grandezza array
        inputArraySize.setMaxWidth(150);
        container1.getChildren().addAll(inputTitle, inputSubTitle, inputArraySize);

        
        VBox container3 = new VBox();
        container3.setAlignment(Pos.CENTER);
        container3.setPrefWidth((Main.windowWidth/2)-15);
        container3.setSpacing(15);
        Label buttonInstruction = new Label("Clicca Start per avviare il programma");
        buttonInstruction.setTextFill(Color.AZURE);
        buttonInstruction.setAlignment(Pos.CENTER);
        // next for launch program
        Button start = new Button();
        start.setText(" Start ");
        start.setMinWidth(50);
       //start.setAlignment(Pos.CENTER);
        size =Main.ARRAY_LENGHT;   method = 1;
        start.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
                start.setDisable(true);
                try
                {
                    // get array's size in input
                    size = Integer.parseInt(inputArraySize.getText());
                  
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("Eccezione generata nel recuperare lunghezza dell'array, opzione di input e nome del file --> " + ex.getMessage());
                }
                // check if input are correct
                if((size>0)&&(size<=Main.ARRAY_LENGHT))
                {
                    // set file name
                    //Main.fileName=file;
                    // set array's size
                    Main.ARRAY_LENGHT=size;
                    // set array max value
                    Main.ARRAY_MAX_VALUE = size * 5;
                    // input option = 2 --> textfile
                    // input option is 1 or 2
                    
                    
                        Main.sceltaInputOptions(method, null);
                        myStage.close();
                        myPrimaryStage.show();
                        return;
                    
                }
                else
                {
                    // wrong input options or size
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Attenzione");
                    alert.setHeaderText("Input non validi");
                    alert.setContentText("Leggere l'istruzione riportata sopra il box.\n"+ "Inserire quindi un input di massimo "+
                            Integer.toString(Main.ARRAY_LENGHT)  +
                                         " e riprovare");
                    alert.showAndWait();
                    // allow user to change input options and re-click on start next
                    start.setDisable(false);
                    return;
                }
            }
        });

    
        // set the input container
        inputContainer.getChildren().addAll(container1, start);
    }

    public void createGraphics()
    {
        setOptionsContainer();
        setOptionsContainerTutorial();
        setInputContainer();
        setInputContainerDetail();
        // prevent NullPointerException
        if(inputContainer!=null && optionsContainer!=null)
        {
            mainContainer = new VBox();
            mainContainer.setPadding(new Insets(15, 15, 15, 15));
            mainContainer.setSpacing(15);
            mainContainer.setStyle("-fx-background-color: rgb(86, 168, 255);"); //colore divisore delle due finestra dx e sx
            mainContainer.getChildren().addAll(optionsContainer, inputContainer);
        }
        setContainer();
        setStage();
    }

    
    
    Lessons(Stage primary, Stage dialog)
    {
        myPrimaryStage = primary;
        myStage = dialog;
        myStage.initOwner(primary);
        createGraphics();
    }

    
}

