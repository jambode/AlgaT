package javafxapplication2;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import javafx.application.*;



public class MergeSort
{
    public int[] helper;
    public StackPane[] helperNodes;
    public Graphics graphics;


    // PLAY
    public void mergeSortPlay(int[] array, SequentialTransition sq, ArrayList<StackPane> list)
    {
        //se l'array è di dimensione minore di 2, considero l'array già ordinato
        if(array.length < 2)
            return;

        int step = 1; //dimensione degli array dimezzati, aumenta ad ogni iterazione
        
        int startL, startR;  //indici degli array di dx e sx
       
        while(step < array.length)
        {
            
            startL = 0; //a sx si inizia sempre a 0
            startR = step; //a dx inizia da step
           
            while(startR + step < array.length)
            {
                //l'array di dx finisce sempre in (startR + step)
                mergeSortArraysAlreadySortedPlay(array, list, startL, (startL + step), startR, (startR + step), sq);
                graphics.consoleWriteOutput("[startLeft="+startL+"]-[stopLeft="+(startL+step)+"]-[startRight="
                        +startR+"]-[stopRight="+(startR + step)+"]");
                //il prossimo array sx inizia dove finisce quello di dx
                startL = startR + step;
                //quello di dx inizia alla fine del sx corrente
                startR = startL + step;
            }
            graphics.consoleWriteOutput("- - - with step = " + step + " - - -" );

            //se non contiene multipli di step finisce su array.lenght
            if(startR < array.length)
            {
                mergeSortArraysAlreadySortedPlay(array, list, startL, (startL + step), startR, (array.length), sq);
                graphics.consoleWriteOutput("[startLeft="+startL+"]-[stopLeft="+(startL+step)+"]-[startRight="
                        +startR+"]-[stopRight="+(startR + step)+"]");
            }
            step *= 2;
        }
        graphics.consoleWriteOutput("ARRAY ORDINATO CORRETTAMENTE");
        new Thread(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    graphics.buttonStepByStep.setDisable(true);
                    graphics.buttonPlay.setDisable(true);
                });
            }
        }.start();
    }

    
    public void mergeSortArraysAlreadySortedPlay(int[] array, ArrayList<StackPane> list, int startL, int stopL, int startR,
                                                   int stopR, SequentialTransition transition)
    {
        //nuovo array per fare merge
        int[] right = new int[stopR - startR + 1];
        int[] left = new int[stopL - startL + 1];
        //nuovo array per la transizione
        StackPane[] rightPane = new StackPane[stopR - startR + 1];
        StackPane[] leftPane = new StackPane[stopL - startL + 1];

        for (int i = 0, k = startR; i < (right.length - 1); ++i, ++k) {
            right[i] = array[k];
            rightPane[i]=list.get(k);
        }
        for (int i = 0, k = startL; i < (left.length - 1); ++i, ++k) {
            left[i] = array[k];
            leftPane[i]=list.get(k);
        }

        right[right.length - 1] = Main.ARRAY_MAX_VALUE;
        left[left.length - 1] = Main.ARRAY_MAX_VALUE;

        //unione dei due array ordinati in quello inziale
        graphics.consoleWriteOutput("Merge two sorted arrays into the initial one");
        for (int k = startL, m = 0, n = 0; k < stopR; ++k)
        {
            if (left[m] <= right[n])
            {
                
                array[k] = left[m];
                list.set(k, leftPane[m]);
                graphics.consoleWriteOutput("array["+k+"] = left["+m+"] = "+left[m]);
                transition.getChildren().add(MergeSort.move(leftPane[m], k * Main.SPACING));
                m++;
            }
            else
            {
                array[k] = right[n];
                list.set(k, rightPane[n]);
                graphics.consoleWriteOutput("array["+k+"] = right["+m+"] = "+left[n]);
                transition.getChildren().add(MergeSort.move(rightPane[n], k * Main.SPACING));
                n++;
            }
        }

       //transizione finale
        ParallelTransition moveUp = new ParallelTransition();
        for (int k = startL, m = 0, n = 0; k < stopR; ++k)
        {
            if (left[m] <= right[n]) {
                TranslateTransition moveNodeUp = new TranslateTransition();
                moveNodeUp.setNode(leftPane[m]);
                moveNodeUp.setDuration(Main.SPEED);
                moveNodeUp.setByY((-Main.SORT_GROUP_MOVE_DELTA) + (Main.SORT_GROUP_MARGIN_DELTA));
                moveUp.getChildren().add(moveNodeUp);
                m++;
            } else {
                TranslateTransition moveNodeUp = new TranslateTransition();
                moveNodeUp.setNode(rightPane[n]);
                moveNodeUp.setDuration(Main.SPEED);
                moveNodeUp.setByY((-Main.SORT_GROUP_MOVE_DELTA) + (Main.SORT_GROUP_MARGIN_DELTA));
                moveUp.getChildren().add(moveNodeUp);
                n++;
            }
        }
        transition.getChildren().add(moveUp);
    }


    public void mergeSortStep(int[] array, SequentialTransition sq, ArrayList<StackPane> list, int step)
    {
        graphics.buttonPlay.setDisable(true);
        if(array.length < 2)
            return;
        int startL, startR;
        //step è la dimensione degli array dimezzati
        if(step < array.length)
        {
            startL = 0;
            startR = step;
            while(startR + step < array.length)
            {
                mergeSortArraysAlreadySortedStep(array, list, startL, (startL + step), startR, (startR + step), sq);
                graphics.consoleWriteOutput("[startLeft="+startL+"]-[stopLeft="+(startL+step)+"]-[startRight="
                        +startR+"]-[stopRight="+(startR + step)+"]");
                startL = startR + step;
                startR = startL + step;
            }
            graphics.consoleWriteOutput("- - - with step = " + step + " - - - ");
            if(startR < array.length)
            {
                mergeSortArraysAlreadySortedStep(array, list, startL, (startL + step), startR, (array.length), sq);
                graphics.consoleWriteOutput("[startLeft="+startL+"]-[stopLeft="+(startL+step)+"]-[startRight="
                        +startR+"]-[stopRight="+(startR + step)+"]");
            }
        }
        else
        {
            graphics.consoleWriteOutput("ARRAY ORDINATO CORRETTAMENTE");
            new Thread(){
                @Override
                public void run(){
                    Platform.runLater(() -> {
                        graphics.buttonStepByStep.setDisable(true);
                    });
                }
            }.start();
        }
    }

    // STEP-BY-STEP
    public void mergeSortArraysAlreadySortedStep(int[] array, ArrayList<StackPane> list, int startL, int stopL, int startR,
                                                 int stopR, SequentialTransition transition)
    {
        int[] right = new int[stopR - startR + 1];
        int[] left = new int[stopL - startL + 1];
        StackPane[] rightPane = new StackPane[stopR - startR + 1];
        StackPane[] leftPane = new StackPane[stopL - startL + 1];

        for (int i = 0, k = startR; i < (right.length - 1); ++i, ++k) {
            right[i] = array[k];
            rightPane[i]=list.get(k);
        }
    
        for (int i = 0, k = startL; i < (left.length - 1); ++i, ++k) {
            left[i] = array[k];
            leftPane[i]=list.get(k);
        }
        right[right.length - 1] = Main.ARRAY_MAX_VALUE;
        left[left.length - 1] = Main.ARRAY_MAX_VALUE;

        graphics.consoleWriteOutput("Merge two sorted arrays into the initial one");
        for (int k = startL, m = 0, n = 0; k < stopR; ++k) {
            if (left[m] <= right[n]) {
                array[k] = left[m];
                list.set(k, leftPane[m]);
                graphics.consoleWriteOutput("array["+k+"] = left["+m+"] = "+left[m]);
                transition.getChildren().add(MergeSort.move(leftPane[m], k * Main.SPACING));
                m++;
            } else {
                array[k] = right[n];
                list.set(k, rightPane[n]);
                graphics.consoleWriteOutput("array["+k+"] = right["+m+"] = "+left[n]);
                transition.getChildren().add(MergeSort.move(rightPane[n], k * Main.SPACING));
                n++;
            }
        }

        ParallelTransition moveUp = new ParallelTransition();
        for (int k = startL, m = 0, n = 0; k < stopR; ++k)
        {
            if (left[m] <= right[n])
            {
                TranslateTransition moveNodeUp = new TranslateTransition();
                moveNodeUp.setNode(leftPane[m]);
                moveNodeUp.setDuration(Main.SPEED);
                // set final position of sorted elements
                moveNodeUp.setByY((-Main.SORT_GROUP_MOVE_DELTA) + (Main.SORT_GROUP_MARGIN_DELTA));
                moveUp.getChildren().add(moveNodeUp);
                m++;
            }
            else
            {
                TranslateTransition moveNodeUp = new TranslateTransition();
                moveNodeUp.setNode(rightPane[n]);
                moveNodeUp.setDuration(Main.SPEED);
                moveNodeUp.setByY((-Main.SORT_GROUP_MOVE_DELTA) + (Main.SORT_GROUP_MARGIN_DELTA));
                moveUp.getChildren().add(moveNodeUp);
                n++;
            }
        }
        transition.getChildren().add(moveUp);
    }

    public static TranslateTransition move(StackPane sp, int X) {
        TranslateTransition t = new TranslateTransition();
        t.setNode(sp);
        t.setDuration(Main.SPEED);
        t.setToX(X);
        t.setToY(Main.SORT_GROUP_MOVE_DELTA);
        return t;
    }

    MergeSort(Graphics instanceOfGraphics)
    {
        helper = new int[Main.ARRAY_LENGHT];
        helperNodes = new StackPane[Main.ARRAY_LENGHT];
        graphics = new Graphics();
        graphics=instanceOfGraphics;
    }

}
