package javafxapplication2;


public class Questions{

//inizializzo domande LEZIONE 1

      static final String[] questions = {
            "Quali sono le fasi in cui si divide la tecnica del Divide et Impera?",
            "Quali sono le applicazioni più note di questa tecnica?",
            "In cosa consiste la tecnica del Divide et Impera?",
            "In che modo vengono risolti i problemi nella fase impera?",
            "Che dimensione hanno i sottoproblemi P1, P2, ..., Ph?"
        };
      // public static void main(String[] args) throws Exception { launch(args); }
       int questionIndex = 0;
       static final String[] answers ={
           "Divide, impera",
           "Divide, impera, combina",
           "Divide, impera, unisci", 
           "Divide, combina",
           "Bubblesort, Quicksort",
           "Mergesort, Heapsort",
           "Mergesort, Quicksort",
           "Bubblesort, Heapsort",
           "Prova a fare qualcosa, se non funziona disfala e riprova con qualcos'altro",
           "Ordina gli oggetti in base ad un criterio di appetibilità",
           "La soluzione viene costruita (bottom-up) a partire da un\n" +
           "insieme di sotto-problemi potenzialmente ripetuti",
           "Partiziona il problema in sottoproblemi più piccoli",
           "Ricorsivamente",
           "Non vengono risolti",
           "Iterativamente",
           "Tramite altri algoritmi",
           "n+1, n+2, ..., 2n+n",
           "n1, n2^2, ..., nn^n",
           "2, 4, ..., 2n",
           "n1, n2, ..., nh"
           
       };
       
       //inizializzo domande LEZIONE2
       static final String[] questions1 ={
            "Per quanto il procedimento viene iterato?",
            "Cosa fa la procedura Merge()?",
            "Che tipo di processo viene usato con MergeSort?",
            "Quanti indici usa MergeSort() per scandire?",
            "Intuitivamente che complessità T(n) ha MergeSort?"
       };
       int questionIndex1 = 0;
       static final String[] answers1={
            "Iterato finchè una delle due metà è esaurita.",
            "Iterato finchè i=mezzo.",
            "Itera all'infinito.", 
            "Nessuna delle precedenti.",
            "Divide le due metà",
            "Legge le due metà",
            "Fonde le due metà",
            "Nulla",
            "Non utilizza procedure.", 
            "Utilizza Merge() e si avvale di un vettore di appoggio.",
            "Utilizza Merge() ma non si avvale di vettori di appoggio.",
            "Nessuna delle precedenti. ",
            "1",
            "2",
            "3",
            "nessuno",
            "O(n)",
            "O(nlogn)",
            "O(n^2)",
            "O(logn)"
       };
       
};  
    