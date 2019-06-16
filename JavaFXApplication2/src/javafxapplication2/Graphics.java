package javafxapplication2;

import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


import java.util.ArrayList;

public class Graphics
{
    
    public  Pane                     containerStackPane;
    public  Button                   buttonStepByStep;
    public  Button                   buttonPlay;
    public  Button                   buttonExit;
    public  BorderPane               container;
    public  ArrayList<StackPane>     arrayCells;
    public  VBox                     arrayContainer;
    public  HBox                     consoleContainer;
    public  VBox                     consoleApplicationBox;
    public  VBox                     consoleExceptionBox;
    public  HBox                     buttonContainer;
    public  Scene                    scene;
    public  Stage                    stage;
    public  MergeSort                mergeTemp;
    public  HBox                     durationContainer;
    public  TextField                durationTransition;

    public  Label                    consoleOutputLabel;
    public  Label                    consoleExceptionLabel;
    public ScrollPane                consoleApplicationScroll;
    public ScrollPane                consoleExceptionScroll;
    public Graphics                  graphics;

    public Label exception;
    
    
    public  static  StringBuilder consoleText;
    public void createConsoleOutput()
    {
        consoleText=new StringBuilder();
        consoleText.append("CONSOLE FOR OUTPUT & DEBUG");
        consoleText.append("\n"); consoleText.append("\n");
        consoleOutputLabel = new Label(" ");
        consoleOutputLabel.setTextFill(Color.DARKCYAN);
        consoleOutputLabel.setAlignment(Pos.TOP_LEFT);
        consoleOutputLabel.setText(consoleText.toString());
        consoleOutputLabel.setTextAlignment(TextAlignment.LEFT);
        consoleOutputLabel.setMinWidth((Main.windowWidth / 2) - 15);
        consoleOutputLabel.setWrapText(true);
    }

    public void consoleWriteOutput(String msg)
    {
        consoleText.append('\t' + ">> " + msg);
        consoleText.append("\n");
        consoleText.append("\n");
        consoleOutputLabel.setText(consoleText.toString());
        consoleOutputLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                consoleOutputLabel.setText(consoleText.toString());
               
            }
        });
    }

    public  static  StringBuilder exceptionText;


    int step=1; int time=0;
    
    public SequentialTransition mergeSortOneStep(int array[], ArrayList<StackPane> list, SequentialTransition seqTrans)
    {
        if(step==1)
            consoleWriteOutput("Modalità : STEP BY STEP");

        mergeTemp.mergeSortStep(array, seqTrans, list, step);
        step *= 2;

        return seqTrans;
    }

    
    public SequentialTransition mergeSortAllElements(int array[], ArrayList<StackPane> list, SequentialTransition seqTrans)
    {
        consoleWriteOutput("Modalità : PLAY");
        mergeTemp.mergeSortPlay(array, seqTrans, list);
        return seqTrans;
    }

    
    private StackPane createValueSingleNode(int position, int number)
    {
       //celle dell'array
        Rectangle   arraySingelCell = new Rectangle(Main.ARRAY_CELL_DIMENSION, Main.ARRAY_CELL_DIMENSION);
        arraySingelCell.setFill(Color.DEEPPINK);
        Text arrayCellValue = new Text(String.valueOf(number));
        arrayCellValue.setFill(Color.WHITE);
        StackPane   cell = new StackPane();
        cell.setPrefSize(arraySingelCell.getWidth(), arraySingelCell.getHeight());
        cell.setId(String.valueOf(number));
        cell.getChildren().addAll(arraySingelCell, arrayCellValue);
        cell.setAlignment(Pos.CENTER);
        cell.setTranslateX(Main.SPACING * position);
        return cell;
    }

    public void setContainerStackPane(int[] array)
    {
        //container dell'array generato
        containerStackPane = new Pane();
        arrayCells = new ArrayList<>();
        for (int i = 0; i < (array.length); i++)
        {
            //grafica per ogni cella dell'array
            StackPane stackPane = createValueSingleNode(i, array[i]);
            arrayCells.add(stackPane);
        }
        containerStackPane.getChildren().addAll(arrayCells);
    }

    public void setButtonStepByStep(int[] array)
    {
        // step-by-step button
        exception = new Label ("Tutto ok");
        buttonStepByStep = new Button("Step-by-Step");
        buttonStepByStep.setStyle("-fx-background-color: #00c9ff;");
        // handle <onCLick> event
        buttonStepByStep.setOnAction(event ->
        {
            
                //transizione per lo step
                SequentialTransition transition1 = new SequentialTransition();
                transition1 = mergeSortOneStep(array, arrayCells, transition1);
                buttonStepByStep.setDisable(true);
                transition1.play();
                transition1.setOnFinished(event1 -> buttonStepByStep.setDisable(false));
        
        });
    }

    public void setButtonPlay(int[] array)
    {
        //bottone per la transizione continua
        buttonPlay = new Button("Play");
        buttonPlay.setStyle("-fx-background-color: #00c9ff;");
        buttonPlay.setOnAction(event ->
        {
                // crea la transizione
                SequentialTransition transition2 = new SequentialTransition();
                //lancia la funzione che ordina l'array
                transition2 = mergeSortAllElements(array, arrayCells, transition2);
                transition2.play();
                transition2.setOnFinished(event1 -> buttonStepByStep.setDisable(false));
       
        });
    }

    public void setButtonExit()
    {
        buttonExit = new Button("Exit");
        buttonExit.setStyle("-fx-background-color: #00c9ff;");
        buttonExit.setOnAction(event -> {
            closeProgram();
        });
    }

    public void setContainers()
    {
        container = new BorderPane();
        container.setTop(arrayContainer);
        container.setCenter(consoleContainer);
        container.setBottom(buttonContainer);
    }

    public void setArrayContainer()
    {
        // top
        arrayContainer = new VBox();
        Label arrayLabel = new Label(" ARRAY GENERATO ");
        arrayLabel.setTextFill(Color.AZURE);
        arrayLabel.setPrefHeight(25);
        arrayContainer.setPadding(new Insets(15, 15, 15, 15));
        arrayContainer.setSpacing(10);
        arrayContainer.setPrefHeight((Main.SPACING * 7));
        arrayContainer.setStyle("-fx-background-color: rgb(43, 43, 43);");
        arrayContainer.getChildren().addAll(arrayLabel, containerStackPane);
    }

    public void setConsoleContainer()
    {
        setConsoleApplication();
        //setConsoleException();
        consoleContainer = new HBox();
        consoleContainer.setPadding(new Insets(15, 15, 15, 15));
        consoleContainer.setSpacing(15);
        consoleContainer.setStyle("-fx-background-color: rgb(60, 63, 65);");
        consoleContainer.getChildren().addAll(consoleApplicationScroll);
    }

    public void setConsoleApplication()
    {
        consoleApplicationBox = new VBox();
        consoleApplicationBox.setPrefWidth(Main.windowWidth);
        consoleApplicationBox.setMinHeight((Main.windowHeight-(arrayContainer.getHeight()+buttonContainer.getHeight()+250)));
        consoleApplicationBox.setAlignment(Pos.TOP_LEFT);
        consoleApplicationBox.getChildren().add(consoleOutputLabel);
        // scroll
        consoleApplicationScroll = new ScrollPane();
        consoleApplicationScroll.setContent(consoleApplicationBox);
        consoleApplicationScroll.setFitToWidth(true);
    }

    public void setButtonContainer()
    {
        // bottom
        buttonContainer = new HBox();
        buttonContainer.setPadding(new Insets(15, 15, 15, 15));
        buttonContainer.setSpacing(20);
        buttonContainer.setPrefHeight((50));
        buttonContainer.setStyle("-fx-background-color: rgb(43, 43, 43);");
        buttonContainer.getChildren().addAll(buttonPlay, buttonStepByStep, buttonExit);
    }

    public void setScene()
    {
        scene = new Scene(container, Main.windowWidth, Main.windowHeight);
    }

    public void setStage(Stage s)
    {
        stage = s;
        stage.setTitle("MaTho-A Merge Sort Tutorial");
        stage.setResizable(false);
        stage.setX(Main.windowX);
        stage.setY(Main.windowY);
        stage.setWidth(Main.windowWidth);
        //stage.setHeight(Main.windowHeight-42);
        stage.setHeight(Main.windowHeight);
        stage.setScene(scene);
        stage.show();
    }

    public void closeProgram()
    {
        this.stage.close();
    }

    public Stage createGraphics(int[] array, Stage s)
    {
        createConsoleOutput();
        mergeTemp=new MergeSort(this);
        setContainerStackPane(array);
        setButtonStepByStep(array);
        setButtonPlay(array);
        //setDurationTransition();
        setButtonExit();
        setArrayContainer();
        setButtonContainer();
        setConsoleContainer();
        setContainers();
        setScene();
        setStage(s);
        return stage;
    }

}
