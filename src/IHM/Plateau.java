package IHM;

public class Plateau {

	public int [][] plateau;
	int nbCase;

	public Plateau(int N) {
		plateau = new int[N][N];
		for(int i = 0; i < nbCase; i++)
		{
			for(int j = 0; j < nbCase; j++)
			{
				plateau[i][j] = 0;
			}
		}
		//billes joueur 1
		plateau[0][2] = 1;
		plateau[0][3] = 1;
		plateau[1][3] = 1;
		plateau[1][4] = 1;
		plateau[2][4] = 1;
		
		//billes joueur 2
		plateau[2][0] = 2;
		plateau[3][0] = 2;
		plateau[3][1] = 2;
		plateau[4][1] = 2;
		plateau[4][2] = 2;
	}
	
	public void reinit(){
		for(int i = 0; i < nbCase; i++)
		{
			for(int j = 0; j < nbCase; j++)
			{
				plateau[i][j] = 0;
			}
		}
		//billes joueur 1
		plateau[0][2] = 1;
		plateau[0][3] = 1;
		plateau[1][3] = 1;
		plateau[1][4] = 1;
		plateau[2][4] = 1;
		
		//billes joueur 2
		plateau[2][0] = 2;
		plateau[3][0] = 2;
		plateau[3][1] = 2;
		plateau[4][1] = 2;
		plateau[4][2] = 2;
	}

}
