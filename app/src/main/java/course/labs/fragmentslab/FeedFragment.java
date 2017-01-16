package course.labs.fragmentslab;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedFragment extends Fragment {

	private static final String TAG = "Lab-Fragments";
	private static final String EXTRA_FEED_POSITION = "course.labs.fragmentslab.Feed_Position";

	private static final int FEED_UNSELECTED = R.raw.class.getDeclaredFields().length;
	private int mFeedPosition = FEED_UNSELECTED;;
	private TextView mTextView;
	private static FeedFragmentData feedFragmentData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.feed, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Restore saved fragment state
		if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_FEED_POSITION)) {

			mFeedPosition = savedInstanceState.getInt(EXTRA_FEED_POSITION);

		}

		// Read in all Twitter feeds 
		if (null == feedFragmentData) { 
			
			feedFragmentData = new FeedFragmentData(getActivity());

		}
	}

	@Override
	public void onResume() {
		super.onResume();

		if (mFeedPosition != FEED_UNSELECTED) {

			updateFeedDisplay(mFeedPosition);

		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (mFeedPosition != FEED_UNSELECTED) {

			outState.putInt(EXTRA_FEED_POSITION, mFeedPosition);

		}

	}


	// Display Twitter feed for selected feed

	void updateFeedDisplay(int position) {

		Log.i(TAG, "Entered updateFeedDisplay()");

		mTextView = (TextView) getView().findViewById(R.id.feed_view);
		mTextView.setText(feedFragmentData.getFeed(position));
		mFeedPosition = position;

	}

}
