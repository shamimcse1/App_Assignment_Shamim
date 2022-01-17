package codercamp.com.app_assignment_shamim.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import codercamp.com.app_assignment_shamim.Utils;
import codercamp.com.app_assignment_shamim.model.WaiterModel;
import codercamp.com.app_assignment_shamim.viewModel.FoodItemAdapter;
import codercamp.com.app_assignment_shamim.R;
import codercamp.com.app_assignment_shamim.model.Model;
import codercamp.com.app_assignment_shamim.viewModel.WaiterAdapter;

public class MainActivity extends AppCompatActivity {
    private TextView userType, userType1;
    private LinearLayout userTypeLayout, waiterTypeLayout;
    private String value;
    private RecyclerView userTypeRecycler, waiterTypeRecycler;
    private List<Model> modelList;
    private FoodItemAdapter adapter;
    private List<WaiterModel> waiterModels;
    private WaiterAdapter waiterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userTypeLayout = findViewById(R.id.LinearUser);
        waiterTypeLayout = findViewById(R.id.LinearWaiter);

        userTypeRecycler = findViewById(R.id.RecyclerviewUser);
        waiterTypeRecycler = findViewById(R.id.RecyclerviewWaiter);
        //modelList.clear();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }

        if (value.equals("user")) {

            waiterTypeLayout.setVisibility(View.GONE);
            userData();

        } else if (value.equals("waiter")) {
            userTypeLayout.setVisibility(View.GONE);
            waiterTypeLayout.setVisibility(View.VISIBLE);
            waiterData();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void waiterData() {
        waiterTypeRecycler.setHasFixedSize(true);
        waiterTypeRecycler.setLayoutManager(new LinearLayoutManager(this));
        //waiterModels = Utils.readWaiterPref(this);
        if (waiterModels == null){
            waiterModels = new ArrayList<>();
        }
       // waiterModels.clear();
        Utils.createWaiterPref(this,waiterModels);
        waiterAdapter = new WaiterAdapter(waiterModels,this);
        waiterTypeRecycler.setAdapter(waiterAdapter);
        waiterAdapter.notifyDataSetChanged();

    }

    private void userData() {
        userTypeRecycler.setHasFixedSize(true);
        userTypeRecycler.setLayoutManager(new LinearLayoutManager(this));
        modelList = Utils.readList(this);
        if (modelList == null){
            modelList = new ArrayList<>();
        }
        modelList.clear();
        Utils.createPref(this,modelList);
        adapter = new FoodItemAdapter(modelList, this);
        userTypeRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}