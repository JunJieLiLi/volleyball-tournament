import java.io.*;

public class Player implements Serializable{			/* class to create a player object with the players details */

	String name, gender;								/* player name and gender */
	int age;											/* player age */
	float height, weight;								/* player height and weight */

	public Player(String name, String gender, int age, float height, float weight){		/* constructor for player object with all variables */
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public Player(String name){							/* constructor for player with only name given */
		this.name = name;
	}

	public String getName(){							/* get player name */
		return name;
	}

	public int getAge(){								/* get player age */
		return age;
	}

	public float getHeight(){							/* get player height */
		return height;
	}

	public float getWeight(){							/* get player weight */
		return weight;
	}

	public String getGender(){							/* get player gender */
		return gender;		
	}

	public void setName(String name){					/* set player name */
		this.name = name;
	}

	public void setAge(int age){						/* set player age */
		this.age = age;
	}

	public void setHeight(float height){				/* set player height */
		this.height = height;
	}

	public void setWeight(float weight){				/* set player weight */
		this.weight = weight;
	}

	public void setGender(String gender){				/* set player gender */
		this.gender = gender;
	}
}