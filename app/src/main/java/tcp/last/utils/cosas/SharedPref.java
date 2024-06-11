package tcp.last.utils.cosas;

import android.content.SharedPreferences;


public class SharedPref {
    private void saveSession() {
        SharedPreferences.Editor prefs = null;  //this.activity.getSharedPreferences("PREFERENCES_FILE_KEY", Context.MODE_PRIVATE).edit();
        prefs.putString("email", null//TextInputEditText.getEmail().getText().toString()
        );
        prefs.apply();
    }
    private void clearSession () {
        SharedPreferences.Editor prefs = null; //this.activity.getSharedPreferences("PREFERENCES_FILE_KEY", Context.MODE_PRIVATE).edit();
        prefs.clear();
        prefs.apply();
    }
    private boolean checkSession () {
        SharedPreferences prefs = null; //this.activity.getSharedPreferences(this.activity.getString(R.string.prefs_files), Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        return email != null;
    }
}
