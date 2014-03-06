import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N;
	private Item[] q; 
	
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue()
	{
		this.q = (Item[]) new Object[1];
		this.N = 0;
	}
	
	
	@SuppressWarnings("unchecked")
	private void resize(int max)
	{
		Item[] temp = (Item[]) new Object[max];
		
		for (int i = 0; i < N; i++)
		{
			temp[i] = q[i];
		}
		
		q = temp;
	}
	
	
//	private void shuffle()
//	{
//		for (int i = 0; i < N; i++)
//		{
//			int r = i + StdRandom.uniform(N - i);
//			
//			Item temp = q[i];
//			q[i] = q[r];
//			q[r] = temp;
//		}
//	}
	
	
	public boolean isEmpty()
	{
		return N == 0;
	}
	
	
	public int size()
	{
		return N;
	}
	
	
//	public void enqueue(Item item)
//	{
//		if (item == null)
//		{
//			throw new NullPointerException();
//		}
//		if (N == q.length)
//		{
//			resize(2 * q.length);
//		}
//		q[N++] = item;
//		this.shuffle();
//	}
	
	public void enqueue(Item item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		if (N == q.length)
		{
			resize(2 * q.length);
		}

		int rIndex = StdRandom.uniform(N + 1);
		if (q[rIndex] == null)
		{
			q[rIndex] = item;
		}
		else
		{
			q[N] = q[rIndex];
			q[rIndex] = item;
		}
		N++;
	}
	
	
	public Item dequeue()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		//Item removed = null;
		int rIndex = StdRandom.uniform(N);
		Item removed = q[rIndex];
		q[rIndex] = q[--N];
		q[N] = null;
		
		if (N > 0 && N == q.length / 4)
		{
			resize(q.length / 2);
		}
		return removed;
	}
	
	
	public Item sample()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		Item sample = null;
		int rSample = StdRandom.uniform(N);
		sample = q[rSample];
	    return sample;
	}
	
	
	@Override
	public Iterator<Item> iterator()
	{
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	
	private class RandomizedQueueIterator implements Iterator<Item>
	{
		private int i = N;
		private int[] indexShuffler = new int[N];
		
		public RandomizedQueueIterator() {
            for (int j = 0; j < i; j++)
            {
                indexShuffler[j] = j;
            }
            StdRandom.shuffle(indexShuffler);
		}

		@Override
		public boolean hasNext()
		{
			// TODO Auto-generated method stub
			return i > 0;
		}

		@Override
		public Item next()
		{
			// TODO Auto-generated method stub
            if (!hasNext())
            {
            	throw new NoSuchElementException();
            }
            return q[indexShuffler[--i]];
		}

		@Override
		public void remove()
		{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		StdOut.println("Current size of collection " + q.size());
		String[] input = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
		for (String s : input)
		{
		    q.enqueue(s);	
		}
		StdOut.println("Current size of collection " + q.size());
//		for(int i = 0; i<q.size(); i++)
//		{
//			System.out.println(q.sample());
//		}
		Iterator<String> i = q.iterator();
		Iterator<String> j = q.iterator();
		while (i.hasNext())
		{
			String s = i.next();
			StdOut.println(s);
		}
		StdOut.println();
		while (j.hasNext())
		{
			String s = j.next();
			StdOut.println(s);
		}
		StdOut.println();
		while (!q.isEmpty())
		{
				StdOut.println(q.dequeue());
				StdOut.println("Current size of collection " + q.size());
		}
	}

}
