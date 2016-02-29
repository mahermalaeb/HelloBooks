package murex.dojo.hellobooks.app;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Book> booksList = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        String url = "https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyBN8xJKNTqENR17M7uyAgBocqYHXY1eYi8";
        //        // Request a string response from the provided URL.
        //        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        //                new Response.Listener<String>() {
        //                    @Override
        //                    public void onResponse(String response) {
        //                        try {
        //                            booksList = BooksJsonParser.parseJsonStringIntoBooksList(response);
        //                            mAdapter = new BooksAdapter(booksList,getApplicationContext());
        //                            mRecyclerView.setAdapter(mAdapter);
        //
        //                        } catch (JSONException e) {
        //                            e.printStackTrace();
        //                        }
        //                    }
        //                }, new Response.ErrorListener() {
        //            @Override
        //            public void onErrorResponse(VolleyError error) {
        //
        //            }
        //
        //        });
        //        // Add the request to the RequestQueue.
        //        queue.add(stringRequest);
    }

}