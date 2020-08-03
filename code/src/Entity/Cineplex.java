package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cineplex class contains information such as cinemas, cineplex name.
 * 
 * @see MovieGoerMgr
 * @see StaffMgr
 * @author SS3 Group 8
 *
 */
public class Cineplex implements Serializable{
	/**
	 * Cineplex Name
	 */
	private String cineplexName;
	/**
	 * Cineplex Code
	 */
	private String CineplexCode;
	/**
	 * ArrayList of Cinemas
	 */
	private ArrayList<Cinema> cinemas;
	
	/**
	 * Constructor for Cineplex Object
	 * @param cineplexName Cineplex Name
	 * @param cinemasList ArrayList of cinemas
	 * @param CineplexCode Cineplex Code
	 * @param numOfCinema Number of Cinemas
	 */
	public Cineplex(String cineplexName,ArrayList<Cinema> cinemasList ,String CineplexCode, int numOfCinema) {
		this.cineplexName = cineplexName;
		cinemas = new ArrayList<Cinema>();
		this.CineplexCode = CineplexCode;
		for (int i=0;i<numOfCinema;i++){
			cinemas.add(cinemasList.get(i));
		}
	}

	/**
	 * Gets Cineplex name
	 * @return cineplex name
	 */
	public String getCineplexName()
	{
		return cineplexName;
	}
	
	/**
	 * Gets Cineplex code
	 * @return cineplex code
	 */
	public String getCineplexCode()
	{
		return CineplexCode;
	}
	
	/**
	 * Gets ArrayList of cinemas
	 * @return ArrayList of cinemas
	 */
	public ArrayList<Cinema> getCinema() 
	{
		return cinemas;
	}
	
	/**
	 * Sets cineplex code
	 * @param code New cineplex code
	 */
	public void setCineplexCode(String code)
	{
		CineplexCode = code;
	}
	
	/**
	 * Sets cineplex name
	 * @param name New cineplex name
	 */
	public void setCineplexName(String name) 
	{
		cineplexName = name;
	}
}