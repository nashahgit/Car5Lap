package com.awesomists.lap5mobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isInternetAvailable(this)) {
            InitializeWeb();
        }else {
            ShowNoInternet();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Lap5RentaCar");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I’m using Lap 5 Rent A Car. It’s smooth, easy, awesome! No charge to cancel! Try it! http://lap5rentacar.me ");
//                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://com.awesomists.lap5mobileapp/drawable/la5_app_final"));
//                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
//                        getResources().getResourcePackageName(R.drawable.lap_5_launcher) + '/'
//                        + getResources().getResourceTypeName(R.drawable.lap_5_launcher) + '/'
//                        + getResources().getResourceEntryName(R.drawable.lap_5_launcher)));
                startActivity(Intent.createChooser(shareIntent, "Send Message"));
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Close App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        //close();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

    public boolean isInternetAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected()
                && connectivityManager.getActiveNetworkInfo().isAvailable();
    }

    public void InitializeWeb() {
        webview = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://lap5rentacar.com/yazad/");
    }

    public void ShowNoInternet() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}
