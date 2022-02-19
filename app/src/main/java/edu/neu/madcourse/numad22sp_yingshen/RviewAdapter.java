package edu.neu.madcourse.numad22sp_yingshen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<ItemCard> itemList;
    private LinkClickListener linkClickListener;

    public RviewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    public void setOnLinkClickListener(LinkClickListener linkClickListener) {
        this.linkClickListener = linkClickListener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new RviewHolder(view, linkClickListener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        ItemCard currentItem = itemList.get(position);

        holder.websiteName.setText(currentItem.getWebsiteName());
        holder.websiteAddress.setText(currentItem.getWebsiteAddress());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface LinkClickListener {
        void onLinkClick(int position);
    }
}
