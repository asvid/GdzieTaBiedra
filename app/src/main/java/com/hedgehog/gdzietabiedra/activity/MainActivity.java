package com.hedgehog.gdzietabiedra.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hedgehog.gdzietabiedra.R;
import com.hedgehog.gdzietabiedra.pojo.Shops.Shop;
import com.hedgehog.gdzietabiedra.pojo.Shops.ShopList;
import com.hedgehog.gdzietabiedra.utils.Biedra;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView hello = (TextView) findViewById(R.id.hello);
        realm = Realm.getInstance(this);
        Biedra.shopApi.getShops(52.4235699, 16.9166683, new Callback<ShopList>() {
            @Override
            public void success(ShopList shopList, Response response) {
                Log.d("sklepy", shopList.getShops().toString());

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(shopList.getShops());
                realm.commitTransaction();
                RealmQuery<Shop> query = realm.where(Shop.class);

                query.equalTo("id", "88");

                RealmResults<Shop> result1 = query.findAll();
                hello.setText(result1.toString());
                Log.d("sklep ", result1.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
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
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
