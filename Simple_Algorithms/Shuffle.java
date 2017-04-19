
/**
 * (Shuffle Array )
 * @author UnknownSoldier
 *
 */
public class Shuffle
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		    
	    
	    int[] ara = new int[5]; 
	    ara[0] = 1;
	    ara[1] = 3;
	    ara[2] = 5;
	    ara[3] = 6;
	    ara[4] = 8;
	    
	    
	    printArray(ara);
	    shuffleArray(ara);
	    printArray(ara);
	    
	    
	}
	
	
	public static void shuffleArray(int[] arr)
	{
		int index = 0;
		int temp=0;
		
		for (int i=0; i<arr.length-1;i++)
		{  
			index = (int)(Math.random()*arr.length);
			temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;
		} // for
	}
	
	static void printArray(int[] arr)
	{
		for (int i=0;i<arr.length-1;i++)
		{
			System.out.print(arr[i]+", ");
		}
		System.out.print(arr[arr.length-1]);
		System.out.println();
			
	}
	
	

}
