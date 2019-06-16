package javafxapplication2;

import javafx.scene.control.Alert;


public class ArrayGen
{

    //funzione che stampa l'array
    public static void printArray(int[] array)
    {
        System.out.println(" ");
        System.out.println(" *********************** STAMPA ARRAY ***********************");
        for (int i = 0; i < (array.length); i++)
            System.out.println(" posizione = " + i  + " --> " + "[" + array[i] + "] ");
        System.out.println(" ************************************************************");
        System.out.println(" ");
    }

    //controlla se un elemento è gia contenuto nell'array
    static public boolean isContained(int[] a, int n)
    {
        boolean response = false;
        for (int i = 0; i < (a.length); i++)
        {
            if(a[i]==n)
            {
                return true;
            }
        }
        return response;
    }

    //controlla se ci sono errori nella generazione dell'array
    static public boolean checkErrorWhileGeneratingArray(int[] a)
    {
        boolean response = false;
        for (int i = 0; i < (a.length-1); i++)
        {
            for (int j= 1; j < (a.length); j++)
            {
                if(i!=j)
                    if (a[i]==a[j])
                        return true;
            }
        }
        return response;
    }


    //genera l'array senza ripetizioni
    static public int[] createRandomArray(int numberOfElements, int maxNumberReached)
    {
        int n=0;
        int[] duplicate = new int[numberOfElements];
        int[] array = new int[numberOfElements];
        duplicate[0]=0;
        if(numberOfElements>0)
        {
            for (int i = 0; i < (numberOfElements); i++)
            {
                n = (int) (Math.random() * (maxNumberReached) + 1);
                if(!(isContained(duplicate, n)))
                {
                    array[i] = n;
                    duplicate[i] = n;
                }
                else
                    i--;
            }
            System.out.println(" ");
            if(checkErrorWhileGeneratingArray(array))
            {
                //se ci sono duplicati genera un nuovo array
                System.out.print("ERRORE NELLA GENERAZIONE DI UN ARRAY DI NUMERI CASUALI");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attenzione");
                alert.setContentText("ERRORE NELLA GENERAZIONE DI UN ARRAY DI NUMERI CASUALI");
                alert.showAndWait();
                
                createRandomArray(numberOfElements, maxNumberReached);
            }
        }
        return array;
    }



}
