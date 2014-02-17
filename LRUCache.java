import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LRUCache{
	private String    key;
    private String    value;
    private long      lruNum;
    
    //Counts first no of heap size entries
    public static int setCount  = 0;
    
    public LRUCache(String key,String value,long lruNum){
         this.key    = key;
         this.value  = value;
         this.lruNum = lruNum;
             
    }
    public String getKey() {
		return key;
	}
    public void setKey(String key) {
		this.key = key;
	}
    public String getValue() {
		return value;
	}
    public void setValue(String value) {
		this.value = value;
	}
    public long getLruNum() {
		return lruNum;
	}
    public void setLruNum(long lruNum) {
		this.lruNum = lruNum;
	}




    private static void set(String string, String parseString,
		HashMap<String, LRUCache> entries, long count, ArrayList<LRUCache> heap, int heapSize) {
	
	//Insert in the HashTable directly for entries less than heap size
    	if(heap.size() < heapSize)
    	{
    		//Cheak if key is not in the table already
    		if(! entries.containsKey(string))
				{
			 
    			LRUCache toInsert = new LRUCache(string ,parseString,count);
    			entries.put(string, toInsert);
    			heap.add(toInsert);
			 
				}	
    		else
		    	{
    				LRUCache toInsert = entries.get(string);
    				toInsert.setValue(parseString);
    				toInsert.setLruNum(count);
    				int index = heap.indexOf(toInsert);
    				minHeapify(index,heap.size(),heap);
		    	}
    	}
    	//HashMap size in equal to the heap size so we will need to remove stuff
		else
		{
			
			//If enntry is already in the table just update it
			if(entries.containsKey(string)) 
			{
				LRUCache toInsert = entries.get(string);
				toInsert.setValue(parseString);
				toInsert.setLruNum(count);
				int index = heap.indexOf(toInsert);
				minHeapify(index,heap.size(),heap);
			}else {
				 LRUCache toInsert = new LRUCache(string ,parseString,count);
				 entries.remove(heap.get(0).getKey());
				 entries.put(string, toInsert);
				 heap.set(0, toInsert);
				 minHeapify(0,heap.size(),heap);
				 }
				
	
        }
	}

    private static void get(String string, HashMap<String, LRUCache> entries,ArrayList<LRUCache>heap,
		long count, int heapSize) {
	    
    	 if(entries.containsKey(string))
    			 {
    		 		LRUCache dummy = entries.get(string);
    		 		dummy.setLruNum(count);
    		 		System.out.println(dummy.getValue());
    		 		int index = heap.indexOf(dummy);
    		 		minHeapify(index,heap.size(),heap);
    			  }else System.out.println("NULL");
    }

    public static void minHeapify(int i,int heapsize,ArrayList<LRUCache>Q)
    {
    	int l;
    	int r;
    	int smallest;

    	l=2*i+1;
    	r=2*i+2;
    
    	//comparing parent with left child
    	if ((l<heapsize) && (Q.get(l).getLruNum()< Q.get(i).getLruNum()))		smallest=l;
    	else smallest=i;

    	int t=smallest;
	
    	//comparing smallest with right child
    	if ((r<heapsize) && (Q.get(r).getLruNum() < Q.get(smallest).getLruNum())) smallest=r;
	

    	if (smallest!=i)							
    	{
    		//Swap
    		LRUCache temp = Q.get(smallest);
    		Q.set(smallest, Q.get(i));
    		Q.set(i, temp);
		
    		minHeapify(smallest,heapsize,Q);
    	}
    }
    private static void dump(HashMap<String,LRUCache> entries) {
	
    	Iterator itr = entries.keySet().iterator();
    	while(itr.hasNext())
    	{
    		LRUCache toDump = entries.get(itr.next());
    		System.out.println(toDump.getKey() + " " + toDump.getValue() );
    	}
    }

    private static void peek(String peekKey,HashMap<String,LRUCache> entries) {
	
	if(entries.containsKey(peekKey))
			 {
				LRUCache toPeek = entries.get(peekKey);
				System.out.println(toPeek.getValue());
			 }else System.out.println("NULL");
	
    }


    
    public static void main(String[] args)
                        {
                            //COunts LRU number
                         long count    = 0;
                            //Sets HeapSize
                         int heapSize  = 0;
                            //Heap array
                         ArrayList<LRUCache> heap = new ArrayList<LRUCache>();
                            //Hashtbale to get and remove entries
                         HashMap<String,LRUCache> entries= new HashMap<String,LRUCache>();
                           
                         Scanner in    = new Scanner(System.in);
                         int  inputNum = in.nextInt();
                         
                         //Array of Commands
                         String[] commandArray = new String[inputNum];
                         String dbx =in.nextLine();
                            
                         for(int i=0;i<inputNum;i++)
                           {
                               commandArray[i]   = in.nextLine();
                           }
                         
                         for(int i=0;i<inputNum;i++)
                         {
                               String[] commandParse = new String[3];
                               StringTokenizer tok   = new StringTokenizer(commandArray[i]);
                               int   j               = 0;
                               while(tok.hasMoreTokens())
                               {
                                   commandParse[j++]=tok.nextToken();
                               }
                               
                               if(commandParse[0].equals("BOUND"))    {
                                   heapSize = Integer.parseInt(commandParse[1]);
                                   if(heapSize < heap.size() && heap.size() != 0) 
                                   {
                                	   int toPop = heap.size()- heapSize;
                                	   for(int k=0;k<toPop;k++)
                                	   {
                                		  entries.remove(heap.get(0).getKey());
                                		  heap.set(0, heap.get(heap.size()-1));
                                		  heap.remove(heap.size()-1);
                                		  minHeapify(0,heap.size() ,heap);
                                		  
                                		}
                                	  
                                   }
                                   else if(heapSize == 0)
                                   {
                                	   heap.clear();
                                	   entries.clear();
                                       setCount=0;
                                   }
                               }else if(commandParse[0].equals("SET")){
                            	   count++;
                            	   set(commandParse[1],commandParse[2],entries,count,heap,heapSize);
                               }else if(commandParse[0].equals("GET")) {
                            	   count++;
                                   get(commandParse[1],entries,heap,count,heapSize);
                               }else if(commandParse[0].equals("DUMP")){
                                   dump(entries);
                               }else if(commandParse[0].equals("PEEK")){
                            	   peek(commandParse[1],entries);
                               }else System.out.println("Input File Contains Invalid Commands");
                               
                         }
                        }


	}
