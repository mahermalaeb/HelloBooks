package murex.dojo.hellobooks.providers;

import static murex.dojo.hellobooks.app.MainActivity.PROXY_ENABLED;

import android.content.Context;

public abstract class BooksProvider {
   protected final Context context;

   public static BooksProvider booksProvider(Context context) {
      return PROXY_ENABLED ? new ProxyEnabledProvider(context) : new ProxyDisabledProvider(context);
   }

   public BooksProvider(Context context) {
      this.context = context;
   }

   public abstract void fetchBooks();
}
