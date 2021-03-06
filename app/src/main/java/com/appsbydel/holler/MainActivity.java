package com.appsbydel.holler;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadDataTask task =  new DownloadDataTask();
        task.execute();
    }

    public void SetUpGridView(List<WootDetails> woots)
    {
        GridView gvWoots = (GridView)this.findViewById(R.id.gvWoots);
        gvWoots.setAdapter(new WootListAdapter(this, woots));
        gvWoots.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String wootDetails = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, wootDetails, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity (new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_refresh:
                DownloadDataTask task =  new DownloadDataTask();
                task.execute();
                return true;
            case R.id.action_contact:
                ContactDev();
                return true;
        }
        super.onOptionsItemSelected(item);
        return false;
    }

    private void ContactDev() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@appsbydel.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Holler for Android");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }


    //Downloads woot data from web service call
    public class DownloadDataTask extends AsyncTask<String, Void, String> {

        private String generateURL() {
            String builder = "";
            builder += "http://api.woot.com/2/events.json?";
            builder += GetSitesString();
            builder += "eventType=Daily&eventType=WootOff&key=";
            builder += getString(R.string.api_key);
            builder += "&select=Title,Site,Offers.Photos,Offers.Items.SalePrice,Offers.Url,Offers.WriteUp,Offers.Specs";
            return builder;
        }

        private String GetSitesString() {
            String builder = "";
            builder += "site=www.woot.com&";
            builder += "site=wine.woot.com&" ;
            builder += "site=shirt.woot.com&";
            builder += "site=sellout.woot.com&";
            builder += "site=kids.woot.com&";
            builder += "site=home.woot.com&";
         /*
        builder.append(this.settings.WootMainSetting ? "site=www.woot.com&" : "");
        builder.append(this.settings.WineWootSetting ? "site=wine.woot.com&" : "");
        builder.append(this.settings.ShirtWootSetting ? "site=shirt.woot.com&" : "");
        builder.append(this.settings.SelloutWootSetting ? "site=sellout.woot.com&" : "");
        builder.append(this.settings.KidsWootSetting ? "site=kids.woot.com&" : "");
        builder.append(this.settings.HomeWootSetting ? "site=home.woot.com&" : "");
        builder.append(this.settings.SportWootSetting ? "site=sport.woot.com&" : "");
        builder.append(this.settings.TechWootSetting ? "site=tech.woot.com&" : "");
        builder.append(this.settings.PopWootSetting ? "site=pop.woot.com&" : "");
        builder.append(this.settings.ToolsWootSetting ? "site=tools.woot.com&" : "");
        builder.append(this.settings.AccessoriesWootSetting ? "site=accessories.woot.com&" : "");
        builder.append(this.settings.ComputersWootSetting ? "site=computers.woot.com&" : "");
        */
            return builder;
        }

        @Override
        protected String doInBackground(String[] params) {
            final String serviceUrl = generateURL();
            HttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
            HttpGet request = new HttpGet(serviceUrl);
            HttpResponse response;
            InputStream inputStream;
            try {
                response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                inputStream = entity.getContent();
                BufferedReader reader;
                reader = new BufferedReader
                        (new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                return sb.toString();
            } catch (IOException e) {
                //Handle Exception
            }
            return null;
        }

        protected void onPostExecute(String result) {
            JSONHandler handler = new JSONHandler();
            SetUpGridView(handler.JsonToWoot(result));
        }
    }
}

