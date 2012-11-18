package tk.wouterhabets.android.bcinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ActivityMain extends SherlockActivity {

	private int currentLevel;
	private final static String MENU_ABOUT = "BC info. Gemaakt door Wouter, Kevin, Rick, Justin en Tom.";
	private final static String PREFERENCES_NAME = "mSharedPreferences";

	private int troll;

	TextView tvTitle, tvSummary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// currentLevel uit de SharedPreferences halen
		SharedPreferences settings = getSharedPreferences(PREFERENCES_NAME, 0);
		setLevel(settings.getInt("currentLevel", 0));

		// actionbar met spinner instellen
		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.spinner1,
						android.R.layout.simple_spinner_dropdown_item);
		actionbar.setListNavigationCallbacks(mSpinnerAdapter,
				new ActionBar.OnNavigationListener() {

					@Override
					public boolean onNavigationItemSelected(int itemPosition,
							long itemId) {
						setLevel(itemPosition);
						refresh(itemPosition);

						return false;
					}
				});
		actionbar.setSelectedNavigationItem(getLevel());

		// 2 textviews van layout_main.xml inladen
		tvTitle = (TextView) findViewById(R.id.layout_main_level);
		tvSummary = (TextView) findViewById(R.id.layout_main_text);
	}

	@Override
	protected void onStop() {
		// currentLevel opslaan in SharedPreferences
		SharedPreferences settings = getSharedPreferences(PREFERENCES_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("currentLevel", getLevel());
		editor.commit();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// menu op actionbar/menutoets instellen
		MenuInflater mMenuInflater = getSupportMenuInflater();
		mMenuInflater.inflate(R.menu.activity_main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// controleert welk menu item is geselecteerd en voert code uit
		switch (item.getItemId()) {
		case R.id.menu_main_refresh_item:
			refresh(currentLevel);
			break;
		case R.id.menu_main_settings_item:
			// settings item
			break;
		case R.id.menu_main_about_item:
			Intent mIntent = new Intent(
					"tk.wouterhabets.android.bcinfo.ACTIVITYABOUT");
			startActivity(mIntent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setLevel(int level) {
		currentLevel = level;
	}

	public int getLevel() {
		return currentLevel;
	}

	private void refresh(int level) {
		// placeholder voor vernieuwen
		if (troll < 20) {
			troll++;
		} else {
			Toast.makeText(getApplicationContext(), "Je moeder",
					Toast.LENGTH_LONG).show();
			troll = 0;
		}
	}

}
