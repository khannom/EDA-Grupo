import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    // Bubble Sort
    void bubble_sort(int arr[], int n)
    {
        int i, j;
        int temp;
        for (i = 0; i < n-1; i++)
            for (j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
    // Counting Sort
    void count_sort(int array[],int n){
        int[] count = new int[1000];
        for (int i = 0; i < n; i++)
            count[array[i]]++;

        for (int i = 0; i < 999 ; i++)
            count[i + 1] = count[i] + count[i + 1];

        for (int i = 999; i > 0; i--)
            count[i] = count[i-1];
        count[0] = 0;

        int[] newA = new int[n];
        
        for (int i = 0; i < n; i++)
        {
            newA[count[array[i]]] = array[i];
            count[array[i]]++;
        }
        
        for (int i = 0; i < n; ++i)
            array[i] = newA[i];
    }
    // Heap Sort
    void swap(int arr[], int a, int b){
        int tmp = arr[a];
        arr[a]=arr[b];
        arr[b]=tmp;
    }

    void heapify(int arr[], int n, int i){
        int largo=i;
        int l=2*i+1;
        int r=2*i+2;
        if(l<n && arr[l]>arr[largo]){
            largo=l;
        }
        if(r<n && arr[r]>arr[largo]){
            largo=r;
        }
        if(largo != i){
            swap(arr,i,largo);
            heapify(arr,n,largo);
        }
    }
    public void heapsort(int arr[],int n){
        for(int i=n/2-1;i>=0;i--){
            heapify(arr,n,i);
        }
        for(int i=n-1;i>0;i--){
            swap(arr,0,i);
            heapify(arr,i,0);
        }
    }
    // Insertion Sort
    void insertionsort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    // Merge Sort
    void merge(int arr[], int l, int m, int r)
    {

        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;


        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    void mergesort(int arr[], int l, int r)
    {
        if (l < r) {

            int m = (l + r) / 2;

            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
    // Quick Sort
    static int partition (int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);

        int temp;

        for (int j = low; j <= high - 1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return (i + 1);
    }
    public void quick_sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            quick_sort(arr, low, pi - 1);
            quick_sort(arr, pi + 1, high);
        }
    }
    // Selection Sort
    void select_sort(int array[]){
        int primer;
        int temp;
        for(int i = 0; i < array.length-1; i++)
        {
            primer = i;
            for (int j = i+1; j < array.length; j++)
            {
                if(array[primer] > array[j])
                    primer = j;
            }
            temp = array[primer];
            array[primer] = array[i];
            array[i] = temp;
        }
    }
    // Funcion de utilidad para imprimir
    void print(int array[])
    {
        for (int i=0; i<array.length; ++i)
            System.out.print(array[i]+" ");
        System.out.println();
    }
    void generar(int array[]){
        for(int i=0;i<array.length;i++){
            array[i] = (int) (Math.random()*200000)+1;
        }
    }

    public static void main(String[] args) {
        //Colocar ruta del archivo txt
        /* File f = new File("/home/diego/Documents/VI Semestre UNSA/EDA/practica/prac1_sorts/arrays.txt"); */
        File f = new File("/home/diego/Documents/VI Semestre UNSA/EDA/practica/prac1_sorts/arrays2.txt");
        Main sort = new Main();
        try  (Scanner entrada = new Scanner(f)) {
            int pruebas = entrada.nextInt();
            for(int i=0;i<pruebas;i++){
                int tamano = entrada.nextInt();
                System.out.print(tamano + " ");
                double result = 0;
                for(int z=0;z<5;z++){
                    int[] array = new int[tamano];
                    for(int j=0;j<tamano;j++){
                        array[j] = entrada.nextInt();
                    }
                    long startTime = System.nanoTime();
                    sort.quick_sort(array,0,tamano-1);
                    /* sort.select_sort(array); */
                    /* sort.bubble_sort(array,tamano); */
                    sort.mergesort(array,0,tamano-1);
                    /* sort.heapsort(array,tamano); */
                    /* sort.insertionsort(array); */
                    /* sort.count_sort(array, tamano); */
                    long endTime = System.nanoTime();
                    double tiempo = (endTime-startTime)/1e6;
                    result += tiempo;
                }
                result /= 5;
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
