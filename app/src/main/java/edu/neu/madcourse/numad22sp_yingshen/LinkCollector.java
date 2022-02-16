package edu.neu.madcourse.numad22sp_yingshen;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity implements InputDialog.InputDialogListener {

    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ItemCard> itemList = new ArrayList<>();
    FloatingActionButton addButton;
    TextView websiteName;
    TextView websiteAddress;

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        addButton = findViewById(R.id.add_button);
        websiteName = findViewById(R.id.website_name);
        websiteAddress = findViewById(R.id.website_address);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkCollector.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void  openDialog() {
        InputDialog newDialog = new InputDialog();
        newDialog.show(getSupportFragmentManager(), "input dialog");

    }

    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);

                rviewAdapter.notifyItemChanged(position);
            }

            @Override
            public void onCheckBoxClick(int position) {
                //attributions bond to the item has been changed
                itemList.get(position).onCheckBoxClick(position);

                rviewAdapter.notifyItemChanged(position);
            }
        };
        rviewAdapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);


    }

    @Override
    public void addItem(int position, String name, String address) {
        itemList.add(position, new ItemCard(name, address, false));
        Toast.makeText(LinkCollector.this, "Added a link", Toast.LENGTH_SHORT).show();

        rviewAdapter.notifyItemInserted(position);
    }

    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getWebsiteName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "2", itemList.get(i).getWebsiteAddress());
            // put isChecked information into instance
            outState.putBoolean(KEY_OF_INSTANCE + i + "3", itemList.get(i).getStatus());
        }
        super.onSaveInstanceState(outState);

    }

    @Override
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(recyclerView, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

}

