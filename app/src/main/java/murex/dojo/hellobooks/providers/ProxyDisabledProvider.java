package murex.dojo.hellobooks.providers;

import static murex.dojo.hellobooks.app.Constants.BOOKS_FETCHED_RESULT_CODE;

import java.util.ArrayList;
import java.util.List;

import murex.dojo.hellobooks.app.Book;
import murex.dojo.hellobooks.app.MyResultReceiver;

import android.content.Context;

public class ProxyDisabledProvider extends BooksProvider {

   public ProxyDisabledProvider(Context context) {
      super(context);
   }

   @Override
   public void fetchBooks() {
      List<Book> books = new ArrayList<Book>();
      books.add(new Book("Book1", "url1"));
      books.add(new Book("Book2", "url2"));

      ((MyResultReceiver.Receiver) context).onReceiveResult(BOOKS_FETCHED_RESULT_CODE, books);
   }

}
