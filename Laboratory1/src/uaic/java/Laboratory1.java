package uaic.java;

import java.util.Scanner;

public class Laboratory1 {
	public static void mandatory()
	{
		System.out.println("Hello World!");

		String languages[] = new String[] {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
		int n = (int) (Math.random() * 1_000_000);
		n = n * 3;
		n += Integer.parseInt("10101",2);
		n += Integer.parseInt("FF",16);
		n = n * 6;
		int result = 0;
		while(n > 0)
		{
			result += n%10;
			n /=10;
		}
		int finalResult = 0;
		while(result > 9)
		{
			while(result > 0)
			{
				finalResult += result%10;
				result /=10;
			}
			result = finalResult;
			finalResult = 0;
		}
		System.out.println("Willy-nilly, this semester I will learn " + languages[result]);

	}
	public static void homework(String[] args)
	{
		long time = System.currentTimeMillis();
		if(args.length  != 1)
		{
			System.out.println("You have to give only a  number as argument in the command line!");
			System.exit(-1);
		}
		int n = Integer.parseInt(args[0]);
		if( n <= 0 )
		{
			System.out.println("Introduceti un numar natural!");
			System.exit(-1);
		}
		int matrix[][] = new int[n][n];
		for(int i = 0; i < n; i++)
		{
			int number = 1;
			int j = i;
			while(j < n)
			{
				matrix[i][j] = number;
				number++;
				j++;
			}
			for(j = 0; j < i; j++)
			{
				matrix[i][j] = number;
				number++;
			}
		}
		if(n <= 30000)
		{
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			}
		}
		else
		{
			System.out.println(System.currentTimeMillis() - time);
		}
	}

	public static void bonus1()
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		int matrixA[][] = new int[n][n];
		for(int i = 0; i < n; i ++)
		{
			if(i == 0)
			{
				matrixA[0][1] = 1;
				matrixA[0][n-1] = 1;
			}
			else if(i == n-1)
			{
				matrixA[i][n-2] = 1;
				matrixA[i][0] = 1;
			}
			else
			{
				matrixA[i][i-1] = 1;
				matrixA[i][i+1] = 1;
			}
		}

		int matrixPower[][] = new int[n][n];

		for(int x = 1; x <= n; x++)
		{
			int aux[][] = new int[n][n];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
				{
					if(x == 1)
						matrixPower[i][j] = matrixA[i][j];
					else
					{

						aux[i][j] = 0;
						for(int k = 0; k < n; k++)
							aux[i][j] += matrixA[i][k] * matrixPower[k][j];
					}
				}
			if(x != 1)
				matrixPower = aux.clone();
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					System.out.print(matrixPower[i][j] + " ");
				}
				System.out.print('\n');
			}
			System.out.println("-------");
		}
	}

	public static void bonus2(String[] args)
	{
		if(args.length != 2)
		{
			System.out.println("Give the number of vertices and the degree of the graph");
			System.exit(-1);
		}

		int n = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);

		int matrix[][] = new int[n][n];

		for(int i = 0; i < n; i++ )
			for(int j = 0; j < n; j++)
				matrix[i][j] = 0;
		if(k%2 == 1 && n%2 == 0)
		{
			for(int i = 0; i < n/2; i++)
				matrix[i][n/2 + i] = matrix[n/2 + i][i] = 1;
			k--;
		}
		for(int i = 0; i < n; i++)
		{

			int ck = k;
			int neighbour = k/2;
			int left = i - neighbour;
			while(left < 0)
			{
				matrix[i][n + left] = 1;
				left++;
				ck--;

			}
			if(ck == k)
			{
				for(int x = i-neighbour; x < i; x++)
				{
					matrix[i][x] = 1;
					ck--;
				}
			}

			while(i + ck >= n)
			{
				matrix[i][i+ck - n] = 1;
				ck--;
			}

			while(ck > 0)
			{
				matrix[i][i + ck] = 1;
				ck--;
			}

		}

		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print('\n');
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//mandatory();
		//homework(args);
		bonus1();
	}
}
