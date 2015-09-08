import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {

	int[][] numbers;
	int[][] tmpNumbers;
	int[] tmpArray;
	int[][] randNumbers;
	int n, numOfHorSigns, numOfVerSigns, numOfSigns;
	boolean [][][] horSigns;
	boolean [][][] verSigns;
	ArrayList<Integer> tmpList;

	public Game(int size) {
		numbers = new int[size][size];
		tmpList = new ArrayList<Integer>();
		this.n = size;
		fillArray();
		createSigns();
	}

	private void createSigns() {
		Random rn = new Random();
		numOfSigns = rn.nextInt(4)+n-2;
		numOfHorSigns = rn.nextInt(numOfSigns);
		numOfVerSigns = numOfSigns - numOfHorSigns;
		horSigns = new boolean[n][n][n];
		verSigns = new boolean[n][n][n];
		for(int t=0;t<numOfHorSigns;t++){
			int tmp1 = rn.nextInt(n-1);
			int tmp2 = rn.nextInt(n-1);
			if(horSigns[tmp1][tmp2][tmp2+1]==true){
				t--;
				continue;
			}
			horSigns[tmp1][tmp2][tmp2+1]=true;
		}
		for(int t=0;t<numOfVerSigns;t++){
			int tmp1 = rn.nextInt(n-1);
			int tmp2 = rn.nextInt(n-1);
			if(verSigns[tmp1][tmp2][tmp2+1]==true){
				t--;
				continue;
			}
			verSigns[tmp1][tmp2][tmp2+1]=true;
		}
		
	}
	public int[][] getRandomNumbers(){
		boolean [][] already = new boolean[n][n];
		Random rn = new Random();
		randNumbers = new int[n][n];
		//p broj brojeva koji ce se pojaviti u matrici, izmedju 4 i 8
		int p = rn.nextInt(5)+n-2;
		while(p-- >0){
			int tmp1=rn.nextInt(n);
			int tmp2=rn.nextInt(n);
			if(already[tmp1][tmp2]==true){
				p++;
				continue;
			}
			randNumbers[tmp1][tmp2]=numbers[tmp1][tmp2];
			already[tmp1][tmp2]=true;
		}
		return randNumbers;
	}
	public boolean[][][] getHorSigns(){
		return horSigns;
	}
	public boolean[][][] getVerSigns(){
		return verSigns;
	}
	private void fillArray() {
		tmpNumbers = new int[n][n];
		for(int i=0;i<n;i++){
			int start = i;
			for(int j=0;j<n;j++){
				tmpNumbers[i][j]=(j+start)%n+1;
			}
			tmpList.add(i);
		}
		Collections.shuffle(tmpList);
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				numbers[i][j]=tmpNumbers[tmpList.get(i)][j];
			}
		}
		//prvo istampaj pa kreiraj transpozu :)
		//uzmi sad transpozu pa
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				tmpNumbers[i][j]=numbers[j][i];
			}
		}
		Collections.shuffle(tmpList);
		//sad mijesaj opet redove
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				numbers[i][j]=tmpNumbers[tmpList.get(i)][j];
			}
		}
		
	}

	public int[][] getFilledArray() {
		return numbers;
	}
}
