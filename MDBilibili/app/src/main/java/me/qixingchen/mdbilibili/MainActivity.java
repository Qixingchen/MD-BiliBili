package me.qixingchen.mdbilibili;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import me.qixingchen.mdbilibili.app.App;


public class MainActivity extends ActionBarActivity {

	private EditText aidEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aidEditText = (EditText) findViewById(R.id.AidEditText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent mActivity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void PlayDemo(View view) {
		Intent playDemoIntent = new Intent(App.getApplication(), Player.class);
		String Aid = aidEditText.getText().toString();
		playDemoIntent.putExtra("AID", Aid);
		startActivity(playDemoIntent);
	}
}
