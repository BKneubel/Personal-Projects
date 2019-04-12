package tests;

public class Prime implements Runnable{
	private int max;
	private int value;
	private boolean calculated;

	public Prime(int max_){
		max = max_;
		calculated = false;
	}

	@Override
	public void run(){
		calculate();
	}

	private void calculate(){
		int totalPrimes = 0;
		boolean[] primes = computePrimes(new boolean[max + 1]);
		for(int i = 0; i < primes.length; i++)//find total number of primes
			if(primes[i] == true)
				totalPrimes++;
		int bigPrime = 0;
		for(int i = primes.length - 1; i > 0; i--)//finds the largest prime number in the set by going through the boolean[] in reverse
			if(primes[i] == true){
				bigPrime = i;
				break;
			}
		value = bigPrime;
		calculated = true;
	}

	public int getValue(){
		return value;
	}

	public boolean wasCalculated(){
		return calculated;
	}

	private boolean[] computePrimes(boolean[] primes){//compute primes method
		for(int i = 0; i < primes.length; i++)//initialize primes to true, with 0 and 1 automatically being false
			primes[i] = true;
		primes[0] = false;
		primes[1] = false;
		for(int i = 2; i < primes.length; i++)//loops through all of the numbers in primes
			for(int j = 2 * i; j < primes.length; j += i)//marks all of the multiples of the number starting at 2 * the number as false
				primes[j] = false; //Doing so prevents counting the number as a multiple of itself
		return primes;
	}

}
