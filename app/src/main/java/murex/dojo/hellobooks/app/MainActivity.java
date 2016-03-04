package murex.dojo.hellobooks.app;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements MyResultReceiver.Receiver {

   private RecyclerView mRecyclerView;
   private RecyclerView.Adapter mAdapter;
   private RecyclerView.LayoutManager mLayoutManager;
   private ArrayList<Book> booksList = new ArrayList<Book>();
   public MyResultReceiver mReceiver;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mReceiver = new MyResultReceiver(new Handler());
      mReceiver.setReceiver(this);

      Intent i = new Intent(this, TimerService.class);
      i.putExtra("receiverTag", mReceiver);
      startService(i);

      mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

      // use a linear layout manager
      mLayoutManager = new LinearLayoutManager(this);
      mRecyclerView.setLayoutManager(mLayoutManager);

      // Instantiate the RequestQueue.
      RequestQueue queue = Volley.newRequestQueue(this);

      booksList.add(new Book("Book1", "url1"));
      booksList.add(new Book("Book2", "url2"));

      mAdapter = new BooksAdapter(booksList, getApplicationContext(), mRecyclerView);
      mRecyclerView.setAdapter(mAdapter);

      //      String url = BOOKS_URL;
      //              // Request a string response from the provided URL.
      //              StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
      //                      new Response.Listener<String>() {
      //                          @Override
      //                          public void onResponse(String response) {
      //                              try {
      //                                  booksList = BooksJsonParser.parseJsonStringIntoBooksList(response);
      //                                  mAdapter = new BooksAdapter(booksList,getApplicationContext(),mRecyclerView);
      //                                  mRecyclerView.setAdapter(mAdapter);
      //
      //                              } catch (JSONException e) {
      //                                  e.printStackTrace();
      //                              }
      //                          }
      //                      }, new Response.ErrorListener() {
      //                  @Override
      //                  public void onErrorResponse(VolleyError error) {
      //                     Log.d("error",error.getMessage());
      //                  }
      //
      //              });
      //              // Add the request to the RequestQueue.
      //              queue.add(stringRequest);
   }


   @Override
   public void onReceiveResult(int resultCode, Bundle resultData) {
      final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
      bar.setProgress(Integer.valueOf(resultData.getString("ServiceTag")));
      Log.d("got", "received result from Service=" + resultData.getString("ServiceTag"));
   }
}