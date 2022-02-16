package edu.neu.madcourse.numad22sp_yingshen;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {

    public TextView itemName;
    public TextView itemDesc;
    public CheckBox checkBox;

    public RviewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        itemName = itemView.findViewById(R.id.website_name);
        itemDesc = itemView.findViewById(R.id.website_address);
        checkBox = itemView.findViewById(R.id.checkbox);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onCheckBoxClick(position);
                    }
                }
            }
        });
    }
}
