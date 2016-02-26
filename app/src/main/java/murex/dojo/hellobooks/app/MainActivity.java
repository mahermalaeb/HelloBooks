package murex.dojo.hellobooks.app;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      final TextView mTextView = (TextView) findViewById(R.id.message);

      // Instantiate the RequestQueue.
      RequestQueue queue = Volley.newRequestQueue(this);
      String url ="https://www.googleapis.com/books/v1/volumes?q=java&key=AIzaSyBN8xJKNTqENR17M7uyAgBocqYHXY1eYi8";

      // Request a string response from the provided URL.
      StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
              // Display the first 500 characters of the response string.
              mTextView.setText("Response is: "+ response.substring(0,500));
           }
        }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
            mTextView.setText(error.toString());
         }
      });
      // Add the request to the RequestQueue.
      queue.add(stringRequest);
   }
}
