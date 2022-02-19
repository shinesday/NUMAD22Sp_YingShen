package edu.neu.madcourse.numad22sp_yingshen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.app.AlertDialog;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class LinkCollector extends AppCompatActivity {

    private ArrayList<ItemCard> itemList;

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private FloatingActionButton addButton;
    private AlertDialog inputAlertDialog;

    private EditText websiteName;
    private EditText websiteAddress;


    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        init(savedInstanceState);

        itemList = new ArrayList<>();
        addButton = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.recycler_view);

        addButton.setOnClickListener(v -> addInfo());

        createInputAlertDialog();
        createRecyclerView();

        rviewAdapter.setOnLinkClickListener(position -> itemList.get(position).onLinkClicked(this));

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(recyclerView, "Deleted a link", Snackbar.LENGTH_LONG).show();
                int position = viewHolder.getLayoutPosition();
                itemList.remove(position);

                rviewAdapter.notifyItemRemoved(position);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void createInputAlertDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.dialog_layout, null);

        websiteName = view.findViewById(R.id.edit_website_name);
        websiteAddress = view.findViewById(R.id.edit_website_address);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);

        alertDialogBuilder
                .setTitle("Add a link")
                .setCancelable(false)
                .setPositiveButton("Add",
                        (dialog, id) -> {
                            ItemCard link = new ItemCard(websiteName.getText().toString(), websiteAddress.getText().toString());
                            if (link.isValid()) {
                                itemList.add(0, link);
                                rviewAdapter.notifyDataSetChanged();
                                Snackbar.make(recyclerView, "Link was added", Snackbar.LENGTH_LONG).show();
                            } else {
                                Snackbar.make(recyclerView, "Invalid link", Snackbar.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        (dialog, id) -> dialog.cancel());

        inputAlertDialog = alertDialogBuilder.create();
    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState){

        if(savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)){
            if(itemList == null || itemList.size() == 0){

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                for(int i=0; i<size; i++){
                    String name = savedInstanceState.getString(KEY_OF_INSTANCE+i+"0");
                    String address = savedInstanceState.getString(KEY_OF_INSTANCE+i+"1");

                    ItemCard link = new ItemCard(name, address);

                    itemList.add(link);

                }
            }
        }
    }

    public void createRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        rviewAdapter = new RviewAdapter(itemList);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void addInfo() {
        websiteName.getText().clear();
        websiteAddress.setText("http://");
        websiteName.requestFocus();
        inputAlertDialog.show();
    }

    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getWebsiteName());
            outState.putString(KEY_OF_INSTANCE + i + "2", itemList.get(i).getWebsiteAddress());
        }
        super.onSaveInstanceState(outState);

    }

}

