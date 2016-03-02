package murex.dojo.hellobooks.app;

import static murex.dojo.hellobooks.app.Constants.NAME;

import java.util.ArrayList;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> implements View.OnClickListener {

   private final ArrayList<Book> booksList;
   private final Context context;
   private final RecyclerView mRecyclerView;

   @Override
   public void onClick(View view) {
      int itemPosition = mRecyclerView.getChildPosition(view);
      String bookName = booksList.get(itemPosition).getTitle();
      final Intent detailsIntent = new Intent(context, BookDetails.class);
      detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      detailsIntent.putExtra(NAME, bookName);
      context.startActivity(detailsIntent);
   }

   public BooksAdapter(ArrayList<Book> myBooksList, Context context, RecyclerView mRecyclerView) {
      booksList = myBooksList;
      this.context = context;
      this.mRecyclerView = mRecyclerView;
   }


   // Inflate the item view
   @Override
   public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
      // create a new view
      final View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.books_list_item, parent, false);
      v.setOnClickListener(this);

      return new ViewHolder(v);
   }

   // Set the content of each item view from our data
   @Override
   public void onBindViewHolder(BooksAdapter.ViewHolder holder, int position) {
      // - get element from your dataset at this position
      // - replace the contents of the view with that element
      holder.titleTextView.setText(booksList.get(position).getTitle());
      Glide.with(context).load(booksList.get(position).getImageUrl()).into(holder.thumbnailImageView);
   }


   // Return the size of your dataset (invoked by the layout manager)
   @Override
   public int getItemCount() {
      return booksList.size();
   }

   //The Class that will handle the itemView
   public static class ViewHolder extends RecyclerView.ViewHolder {

      public TextView titleTextView;
      public ImageView thumbnailImageView;

      public ViewHolder(View itemView) {
         super(itemView);

         titleTextView = (TextView) itemView.findViewById(R.id.book_title_text_view);
         thumbnailImageView = (ImageView) itemView.findViewById(R.id.book_thumbnail_image_view);
      }
   }
}
