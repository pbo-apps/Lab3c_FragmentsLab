package course.labs.fragmentslab;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements
		FriendsFragment.SelectionListener {

	private static final String TAG = "Lab-Fragments";
	private static final String FRIENDS_FRAG = "course.labs.fragmentslab.Friends_Fragment";

	private FriendsFragment mFriendsFragment;
	private FeedFragment mFeedFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		if (savedInstanceState != null) {

			if (savedInstanceState.containsKey(FRIENDS_FRAG)) {
				mFriendsFragment = (FriendsFragment) getFragmentManager().getFragment(savedInstanceState, FRIENDS_FRAG);
			}

		}

		// If the layout is single-pane, create the FriendsFragment 
		// and add it to the Activity

		if (!isInTwoPaneMode()) {

			if (mFriendsFragment == null) {
				mFriendsFragment = new FriendsFragment();

				//TODO 1 - add the FriendsFragment to the fragment_container
				getFragmentManager()
						.beginTransaction()
						.add(R.id.fragment_container, mFriendsFragment)
						.commit();

			}

		} else if (mFeedFragment == null) {

			// Otherwise, save a reference to the FeedFragment for later use
			mFeedFragment = (FeedFragment) getFragmentManager()
					.findFragmentById(R.id.feed_frag);

		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		FragmentManager fm = getFragmentManager();

		if (mFriendsFragment != null) {
			fm.putFragment(outState, FRIENDS_FRAG, mFriendsFragment);
		}

	}

	// If there is no fragment_container ID, then the application is in
	// two-pane mode

	private boolean isInTwoPaneMode() {

		return findViewById(R.id.fragment_container) == null;
	
	}

	// Display selected Twitter feed

	public void onItemSelected(int position) {

		Log.i(TAG, "Entered onItemSelected(" + position + ")");

		// If there is no FeedFragment instance, then create one

		if (mFeedFragment == null)
			mFeedFragment = new FeedFragment();

		// If in single-pane mode, replace single visible Fragment

		if (!isInTwoPaneMode()) {

			//TODO 2 - replace the fragment_container with the FeedFragment
			getFragmentManager()
					.beginTransaction()
					.replace(R.id.fragment_container, mFeedFragment)
					.addToBackStack(mFeedFragment.toString())
					.commit();

			// execute transaction now
			getFragmentManager().executePendingTransactions();

		}

		// Update Twitter feed display on FriendFragment
		mFeedFragment.updateFeedDisplay(position);

	}

}
