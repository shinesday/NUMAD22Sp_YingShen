package edu.neu.madcourse.numad22sp_yingshen;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {

    public TextView websiteName;
    public TextView websiteAddress;

    public RviewHolder(View itemView, RviewAdapter.LinkClickListener linkClickListener) {
        super(itemView);

        websiteName = itemView.findViewById(R.id.website_name);
        websiteAddress = itemView.findViewById(R.id.website_address);

        itemView.setOnClickListener(v -> {
            int position = getLayoutPosition();
            if (position != RecyclerView.NO_POSITION) {
                linkClickListener.onLinkClick(position);
            }
        });

    }
}