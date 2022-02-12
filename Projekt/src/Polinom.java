
public class Polinom 
{
	float[] k;
	public Polinom(float [] k)
	{
		this.k = k;
	}
	
	public float vrednost(float x)
	{
		float rez = 0;
		for (int i = 0; i < k.length; i++)
			rez += k[i]*(Math.pow(x, i));
		return rez;
	}
	
}
