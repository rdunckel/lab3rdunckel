package edu.calpoly.android.lab3.tests;

import java.util.ArrayList;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.calpoly.android.lab3.AdvancedJokeList;
import edu.calpoly.android.lab3.Joke;
import edu.calpoly.android.lab3.JokeListAdapter;
import edu.calpoly.android.lab3.JokeView;
import edu.calpoly.android.lab3.tests.FriendClass.FriendClassException;

public class AdvancedJokeListAcceptanceTest extends ActivityInstrumentationTestCase2<AdvancedJokeList> {

	private Instrumentation mInstrument = null;
	
	// AdvancedJokeList member variables
	private AdvancedJokeList mActivity = null;
	private Button m_vwJokeButton = null;
	private ViewGroup m_vwJokeLayout = null;
	private EditText m_vwJokeEditText = null;
	private ArrayList<Joke> m_arrJokeList = null;
	private JokeListAdapter m_jokeAdapter;
	
	public AdvancedJokeListAcceptanceTest() {
		super("edu.calpoly.android.lab3", AdvancedJokeList.class);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		mInstrument = getInstrumentation();
		
		// Setup Activity
//		setActivityInitialTouchMode(false);
		mActivity = getActivity();

		// Gather references to Activity member variables
		try {
			m_vwJokeButton = FriendClass.retrieveHiddenMember("addButton", m_vwJokeButton, mActivity);
			m_vwJokeLayout = FriendClass.retrieveHiddenMember("jokeLayout", m_vwJokeLayout, mActivity);
			m_vwJokeEditText = FriendClass.retrieveHiddenMember("textInput", m_vwJokeEditText, mActivity);
			m_arrJokeList = FriendClass.retrieveHiddenMember("jokes", m_arrJokeList, getActivity());
			m_jokeAdapter = FriendClass.retrieveHiddenMember("jokeAdapter", m_jokeAdapter, getActivity());
		} catch (FriendClassException exc) {fail(exc.getMessage());}
	}

	@SmallTest
	/**
	 * Testing Lab 3
	 * Preconditions to Section 1
	 */
	public void testSec1PreConditions() {
		assertNotNull("textInput should not be null", m_vwJokeEditText);
		assertNotNull("addButton should not be null", m_vwJokeButton);

		assertNotNull("jokes should not be null", m_arrJokeList);
		assertEquals("jokes should be pre-populated with 3 default jokes", 3, m_arrJokeList.size());
		
		assertNotNull("m_vwm_vwJokeLayout should not be null", m_vwJokeLayout);
		assertEquals("jokeLayout should be pre-populated with 3 default jokes", 3, m_vwJokeLayout.getChildCount());
		
		View view = m_vwJokeLayout.getChildAt(1);
		if (view instanceof TextView) {
			assertEquals("Checking text size",16.0,((TextView)view).getTextSize(),.001);
		}
		
		for (int ndx = 0; ndx < m_vwJokeLayout.getChildCount(); ndx++) {
			if (!(m_vwJokeLayout.getChildAt(ndx) instanceof TextView) &&
			    !(m_vwJokeLayout.getChildAt(ndx) instanceof JokeView)) {
				fail("jokeLayout should contain only TextView (if you haven't implemented the JokeView class) or JokeView objects and nothing else.");
			}
		}
		assertEquals("Check Button Text","Add Joke",m_vwJokeButton.getText());
	}
	
	@SmallTest
	/**
	 * Testing Lab 3
	 * Section 1.3 test addButton OnClickListenr
	 */
	public void testAddJokeViaButton() {
		final String jokeText = "Testing addJoke via Add Button...";

		// Add the joke
		mActivity.runOnUiThread(new Runnable() {
			public void run() {
				m_vwJokeEditText.setText(jokeText);
				m_vwJokeButton.performClick();
			}
		});
		mInstrument.waitForIdleSync();
		testForAddedJoke(jokeText);		
	}
	
	@SmallTest
	/**
	 * Testing Lab 3
	 * Section 1.3 test textInput OnKeyListener
	 */
	public void testAddJokeViaReturn() {
		final String jokeText = "Testing addJoke via return key...";

		// Add the joke
		mActivity.runOnUiThread(new Runnable() {
			public void run() {
				m_vwJokeEditText.setText(jokeText);
			}
		});
		mInstrument.waitForIdleSync();
		sendKeys(KeyEvent.KEYCODE_ENTER);
		mInstrument.waitForIdleSync();
		testForAddedJoke(jokeText);		
	}
	
	@SmallTest
	/**
	 * Testing Lab 3
	 * Section 1.3 test textInput OnKeyListener
	 */
	public void testAddJokeViaDPadTrackBall() {
		final String jokeText = "Testing addJoke via D-Pad Center & Track-Ball...";

		// Add the joke
		mActivity.runOnUiThread(new Runnable() {
			public void run() {
				m_vwJokeEditText.setText(jokeText);
			}
		});
		mInstrument.waitForIdleSync();
		sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		mInstrument.waitForIdleSync();
		testForAddedJoke(jokeText);		
	}
	
	private void testForAddedJoke(String jokeText) {
		assertEquals("jokes should have 4 jokes now", 4, m_arrJokeList.size());
		assertEquals("jokeLayout should have 4 jokes now", 4, m_vwJokeLayout.getChildCount());
		assertEquals("Checking index of joke, that it was added to the end of jokes", jokeText, m_arrJokeList.get(3).getText());
		
		// App under test could be using JokeViews or TextViews at this point
		View view = m_vwJokeLayout.getChildAt(3);
		if (view instanceof TextView) {
			TextView tv = (TextView)view;
			assertEquals("Checking index of joke, that it was added to the end of jokeLayout", jokeText, tv.getText().toString());
		}
		else if (view instanceof JokeView) {
			JokeView jokeView = (JokeView)view;
			Joke joke = null;
			try {
				joke = FriendClass.retrieveHiddenMember("m_joke", joke, jokeView);
			} catch (FriendClassException exc) {fail(exc.getMessage());}
			assertEquals("Checking index of joke, that it was added to the end of jokeLayout", jokeText, joke.getText());
		}
		else {
			fail("jokeLayout should contain only TextView (if you haven't implemented the JokeView class) or JokeView objects and nothing else.");
		}
	}
}
