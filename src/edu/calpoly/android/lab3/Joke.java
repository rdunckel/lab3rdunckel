package edu.calpoly.android.lab3;

/**
 * 
 * This class encapsulates the data pertaining to a Joke.
 * 
 */

public class Joke {

	/** The three possible rating values for jokes **/
	public static final int UNRATED = 1;
	public static final int LIKE = 2;
	public static final int DISLIKE = 4;

	/** Contains the text of this joke **/
	private String text;

	/**
	 * Contains the rating of this joke, should only be one of the constant
	 * rating values declared above.
	 **/
	private int rating;

	/** Contains the name of the Author of this joke. **/
	private String author;

	/**
	 * Initializes with an empty joke and author string and the default rating
	 * of UNRATED.
	 */
	public Joke() {
		text = "";
		author = "";
		rating = UNRATED;
	}

	/**
	 * Initializes with the joke and author strings passed in and the default
	 * rating of UNRATED.
	 * 
	 * @param text
	 *            Joke String used to initialize the text of this joke.
	 * 
	 * @param author
	 *            The name of the Author of this Joke.
	 */
	public Joke(String text, String author) {
		this.text = text;
		this.author = author;
		rating = UNRATED;
	}

	/**
	 * Initializes with a joke and author string and rating value passed in.
	 * 
	 * @param text
	 *            Joke String used to initialize the text of this joke.
	 * 
	 * @param author
	 *            The name of the Author of this Joke.
	 * 
	 * @param rating
	 *            Rating value to initialize the rating of this joke.
	 */
	public Joke(String text, String author, int rating) {
		this.text = text;
		this.author = author;
		this.rating = rating;
	}

	/**
	 * Accessor for the text of this joke.
	 * 
	 * @return A String value containing the text of this joke.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Mutator that changes the text of this joke.
	 * 
	 * @param joke
	 *            The text of this joke.
	 */
	public void setText(String joke) {
		this.text = joke;
	}

	/**
	 * Accessor for the rating of this joke.
	 * 
	 * @return An integer value containing one of the possible rating constants.
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Mutator that changes the rating of this joke.
	 * 
	 * @param rating
	 *            One of the possible rating constants.
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Accessor for the Author of this joke.
	 * 
	 * @return A String containing the Authors name.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Mutator that changes the Author of this joke.
	 * 
	 * @param author
	 *            A String containing the Authors name.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Returns only the text of the joke. This method should mimic getJoke().
	 * 
	 * @return A string containing the text of the joke
	 */
	@Override
	public String toString() {
		return text;
	}

	/**
	 * An Object is equal to this Joke if all items below are true:
	 * 
	 * 1) The Object is a Joke.
	 * 
	 * 2) The Joke's text is the same as this Joke's text. Whereby text equality
	 * is defined by String.equals(...).
	 * 
	 * 3) the Author of the Joke is the same as this Joke's Author. Whereby
	 * equality is defined by String.equals(...).
	 * 
	 * The rating is ignored in the determination of equality.
	 * 
	 * @return True if the object passed in is a Joke with the same text and
	 *         Author as this one; False otherwise.
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Joke other = (Joke) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + rating;
		return result;
	}
}
