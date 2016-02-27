package murex.dojo.hellobooks.app;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private ArrayList<Book> booksList;
    private Context context;

    //The Class that will handle the itemView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //List all the views of the item view
        public TextView titleTextView;
        public ImageView thumbnailImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            //Cast each item to its type after finding it by ID
            titleTextView = (TextView) itemView.findViewById(R.id.book_title_text_view);
            thumbnailImageView = (ImageView) itemView.findViewById(R.id.book_thumbnail_image_view);
        }

    }


    //Takes the data we want to display in the main activity
    public BooksAdapter(ArrayList<Book> myBooksList,Context context) {
        booksList = myBooksList;
        this.context = context;
    }

    // Inflate the item view
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
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
}
