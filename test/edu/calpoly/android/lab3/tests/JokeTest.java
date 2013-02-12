package edu.calpoly.android.lab3.tests;

import junit.framework.TestCase;
import android.test.suitebuilder.annotation.SmallTest;
import edu.calpoly.android.lab3.Joke;

public class JokeTest extends TestCase {

	@SmallTest
	/**
	 * Test Default Constructor
	 */
	public void testJoke() {
		Joke joke = new Joke();
		assertTrue("text should be initialized to \"\".", joke.getText()
				.equals(""));
		assertTrue("author should be initialized to \"\".", joke.getAuthor()
				.equals(""));
		assertEquals("rating should be initialized to Joke.UNRATED.",
				Joke.UNRATED, joke.getRating());
	}

	@SmallTest
	/**
	 * Test Parameterized Constructor: Joke(String strJoke, String strAuthor)
	 */
	public void testJokeStringString() {
		String text = "testJoke";
		String author = "testAuthor";
		Joke joke = new Joke(text, author);
		assertEquals("text should be initialized to \"testJoke\".", text,
				joke.getText());
		assertEquals("author should be initialized to \"testAuthor\".", author,
				joke.getAuthor());
		assertEquals("rating should be initialized to Joke.UNRATED.",
				Joke.UNRATED, joke.getRating());
	}

	@SmallTest
	/**
	 * Test Parameterized Constructor: Joke(String strJoke, String strAuthor, int nRating)
	 */
	public void testJokeStringStringInt() {
		String text = "testJoke";
		String author = "testAuthor";
		Joke joke = new Joke(text, author, Joke.DISLIKE);
		assertEquals("text should be initialized to \"testJoke\".", text,
				joke.getText());
		assertEquals("author should be initialized to \"testAuthor\".", author,
				joke.getAuthor());
		assertEquals("rating should be initialized to Joke.DISLIKE.",
				Joke.DISLIKE, joke.getRating());
	}

	@SmallTest
	/**
	 * Test Mutator Method
	 */
	public void testSetJoke() {
		String text = "testJoke";
		Joke joke = new Joke();
		joke.setText(text);
		assertEquals("text should be set to \"testJoke\".", text,
				joke.getText());
	}

	@SmallTest
	/**
	 * Test Mutator Method
	 */
	public void testSetAuthor() {
		Joke joke = new Joke();
		String author = "testAuthor";
		joke.setAuthor(author);
		assertEquals("author should be set to \"testAuthor\".", author,
				joke.getAuthor());
	}

	@SmallTest
	/**
	 * Test Mutator Method
	 */
	public void testSetRating() {
		Joke joke = new Joke();
		joke.setRating(Joke.LIKE);
		assertEquals("rating should be set to Joke.LIKE.", Joke.LIKE,
				joke.getRating());
	}

	@SmallTest
	public void testEquals() {
		String text = "testJoke";
		String textEQ = "testJoke";
		String author = "testAuthor";
		String authorEQ = "testAuthor";

		Joke joke = new Joke(text, author);
		Joke jokeEQ = new Joke(text, author);
		Joke jokeEQ2 = new Joke(textEQ, authorEQ);
		Joke jokeNEQ = new Joke("different", author);
		Joke jokeNEQ2 = new Joke(text, "different");
		Joke jokeNEQ3 = new Joke("different", "different");

		assertFalse(
				"equals(Object obj) should return false. Testing against null",
				joke.equals(null));
		assertFalse(
				"equals(Object obj) should return false. Not comparing two obj is not an instance of Joke",
				joke.equals(text));
		assertFalse(
				"equals(Object obj) should return false. The two jokes have different joke values",
				joke.equals(jokeNEQ));
		assertFalse(
				"equals(Object obj) should return false. The two jokes have different joke values",
				joke.equals(jokeNEQ2));
		assertFalse(
				"equals(Object obj) should return false. The two jokes have different joke values",
				joke.equals(jokeNEQ3));
		assertTrue(
				"equals(Object obj) should return true. The testing against itself",
				joke.equals(joke));
		assertTrue(
				"equals(Object obj) should return true. The testing against different Joke containing a reference to the same String object",
				joke.equals(jokeEQ));
		assertTrue(
				"equals(Object obj) should return true. The testing against different Joke containing different String with same text",
				joke.equals(jokeEQ2));
	}

	@SmallTest
	public void testToString() {
		String text = "testJoke";
		Joke joke = new Joke(text, "author");
		assertEquals("toString() should return \"testJoke\".", text,
				joke.toString());
	}

}
