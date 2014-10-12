/*
 * Author: Rahul Sarna, Computer Major at Stony Brook University
 *
 * Minimal Set Cover Challenge in My Algorithm Class
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author RahulSarna
 */
public class MinimalSetCover {
    
       
    boolean finished = false; //(* found all solutions yet? *)    
    boolean[] c = new boolean[100]; //(* candidates for next position *)                    
    static int ncandidates = 0; //(* next position candidate count *)
    static boolean flag = true;
    
    static LinkedList myList = new LinkedList();

    static LinkedList solutionSet = new LinkedList();
    
    static LinkedList preparingSolution = new LinkedList();
    
    static LinkedList minSet = new LinkedList();
    
    static int[] occurrences = new int[10];
    
    static int limit;
    static int totalSets;
    
    static boolean debug = true;
                 
    public void backtrack(int a[], int k, int n){
         
        int c[];
        int i;
                    
            if(is_a_solution(a,k,n)){
                process_solution(a,k);                
            }else{

                    c = construct_candidates(a,k,n);
                
                
                if(!(c[0]==0&&c[1]==0))
                for(i = 0; i < c.length; i++){
                    a[k] = c[i];
                    
                    int [] temp = Arrays.copyOf(a, n);
                    backtrack(a,k+1,n);
                    a = Arrays.copyOf(temp, n);
                    if(finished){
                        break;
                    }
                }              
            }
        }
    
     boolean is_a_solution(int a[], int k, int n){
         return (k==n);
    }
     
     
    int [] construct_candidates(int a[], int k, int n){
    
        int [] c1 = new int[2];

        int[] set = ((int[])(myList.get(k)));
               
        int counter = 0;
        
        for (int i=1; i<=k; i++){            
            if(a[i-1] == 1){                
                counter++;                
            }
     }            
        if(!solutionSet.isEmpty()&&counter>((LinkedList)(solutionSet.get(0))).size())
            return c1;
        
            if((set.length==0||(set.length==1 && occurrences[set[0]]>1)||this.pruningSetSize(a, k)||set[0]==0)){//&&(!(set.length==1 && occurrences[set[0]]==1))){
                for(int i = 0; i < c1.length; i++)
                    a[k] = c1[i];
                backtrack(a,k+1,n);

            }else{                
                c1[0] = 0;
                c1[1] = 1;
            }
        
            return c1;
    }
      
     public void process_solution(int a[], int k){
        int i; //(* counter *)
        
        if(debug){
        System.out.print("{");
        
            for(i=1;i<=k;i++){
                if(a[i-1] == 1)
                    System.out.print(" "+ i);
            }
            
            System.out.print(" }\n"); 
            
        }
        
        boolean[] complete = new boolean[limit+1];
                
            for (i=1; i<=k; i++){
                
                int[] set = null;
                
                if(a[i-1] == 1){
                    
                    set = ((int[])(myList.get(i-1)));                
                    
                    preparingSolution.add(i);

                    for(int j=0;j<set.length;j++){
                    
                        complete[set[j]] = true;                                                                                                                                   
                    }                           
            }
                                                 
            }
            
            for(int l=1;l<complete.length;l++){ 
                                
                if(complete[l]==false) {               
                    flag = false;                                         
                }
            }                                   
            
            if(flag){            
                
                if(minSet.isEmpty()){       
                    solutionSet.add(preparingSolution);   
                    minSet = preparingSolution;
                }
                else if(minSet.size()>preparingSolution.size()){
                    solutionSet.addFirst(preparingSolution);
                }
                                                
            }     
            else
                flag = true;

            preparingSolution = new LinkedList();
                               
            complete = new boolean[limit+1];
            
     }           
    

boolean pruningSetSize(int[] a, int k){    
        
    if(solutionSet.isEmpty())
        return false;
    
    int counter = 0;
    
    for (int i=1; i<=k; i++){            
            if(a[i-1] == 1){
                
                counter++;
                
            }
     }            
        if(counter>((LinkedList)(solutionSet.get(0))).size())
            return true;

                        
          int[] set = null;
                set = ((int[])(myList.get(k)));                                
                    if(set.length > ((LinkedList)(solutionSet.get(0))).size()){
                        return true;
                    }  
         return false;
}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String filename = null;
        File file = null;
 
        Scanner input = new Scanner(System.in); 
              
        String path = "s-X-12-6.txt";
//          String path = "s-k-30-55.txt";
        
        System.out.println("File: "+ path);
         
        try{
            input = new Scanner(new File(path));
//            input = new Scanner(new File("s-rg-8-10.txt"));            
//            input = new Scanner(new File("s-rg-118-30.txt"));
        }catch(FileNotFoundException e){
            System.exit(0);
        }

        limit = input.nextInt();
                
        totalSets = input.nextInt();
        
        StringBuilder sb = new StringBuilder();
        
        input.nextLine();
        
        while(input.hasNext()){
            
            String text = input.nextLine();

            String[] numbers = text.split(" ");
                        
            int[] intnumbers = new int[numbers.length];
            
            for(int j=0;j<numbers.length;j++){
                
                intnumbers[j] = 0;
                
                if(!text.equals("")){
                    intnumbers[j] = Integer.parseInt(numbers[j]);                    
                }
             }
                       
            myList.add(intnumbers);

            sb.append(text);
            sb.append(System.lineSeparator());
        }        
        
        System.out.println("Limit: "+limit+" Subsets: " + totalSets);
    
        occurrences = new int[limit+1];
        
        int i = 1;
        
        for (Object myList1 : myList) {
            int[] sample = (int[]) myList1;
            
            System.out.print(i++ +". ");
            for(int j=0;j<sample.length;j++){
                System.out.print(sample[j] + " ");                
                    occurrences[sample[j]]++;
            }
            System.out.println();
        }
        
        System.out.println();

        System.out.println();

        MinimalSetCover minimalCover = new MinimalSetCover();

        int[] a = new int[100];
        
         minimalCover.backtrack(a,0,totalSets);             
         
         System.out.println("Minimal Set Covered by Sets: " + solutionSet.get(0).toString()+ "\nMinimal Set Size: "+ ((LinkedList) solutionSet.get(0)).size());
         
         System.out.println("All Computed Solution: "+ solutionSet.toString()); 
    }      
}