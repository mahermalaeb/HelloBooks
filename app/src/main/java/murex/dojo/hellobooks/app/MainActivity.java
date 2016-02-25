package murex.dojo.hellobooks.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity {

   private RecyclerView mRecyclerView;
   private RecyclerView.Adapter mAdapter;
   private RecyclerView.LayoutManager mLayoutManager;
   private ArrayList<Book> booksList = new ArrayList<Book>();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      fillBooksListWithDummyData();

      mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

      // use a linear layout manager
      mLayoutManager = new LinearLayoutManager(this);
      mRecyclerView.setLayoutManager(mLayoutManager);

      // specify an adapter (see also next example)
      mAdapter = new BooksAdapter(booksList);
      mRecyclerView.setAdapter(mAdapter);

   }

   public void fillBooksListWithDummyData(){
      Book newBook;
      for(int i=0; i<100;i++){
         newBook = new Book ("book");
         booksList.add(newBook);
      }
   }
}
