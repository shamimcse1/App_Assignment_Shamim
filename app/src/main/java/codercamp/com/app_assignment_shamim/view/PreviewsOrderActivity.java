package codercamp.com.app_assignment_shamim.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import codercamp.com.app_assignment_shamim.R;
import codercamp.com.app_assignment_shamim.Utils;
import codercamp.com.app_assignment_shamim.model.WaiterModel;
import codercamp.com.app_assignment_shamim.viewModel.WaiterAdapter;

public class PreviewsOrderActivity extends AppCompatActivity {
    private RecyclerView previewsRecyclerView;
    private List<WaiterModel> previewOrderList;
    private WaiterAdapter previewOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previews_order);

        previewsOrder();

    }

    @SuppressLint("NotifyDataSetChanged")
    private void previewsOrder() {
        previewsRecyclerView =findViewById(R.id.PreviewRecyclerview);
        previewsRecyclerView.setHasFixedSize(true);
        previewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        previewOrderList = Utils.readWaiterPref(this);
        if (previewOrderList == null){
            previewOrderList = new ArrayList<>();
        }

        Utils.createWaiterPref(this,previewOrderList);
        previewOrderAdapter = new WaiterAdapter(previewOrderList,this);
        previewsRecyclerView.setAdapter(previewOrderAdapter);
        previewOrderAdapter.notifyDataSetChanged();

    }
}