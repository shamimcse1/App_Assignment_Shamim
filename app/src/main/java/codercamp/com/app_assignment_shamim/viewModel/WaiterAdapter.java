package codercamp.com.app_assignment_shamim.viewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import codercamp.com.app_assignment_shamim.R;
import codercamp.com.app_assignment_shamim.model.WaiterModel;

public class WaiterAdapter extends RecyclerView.Adapter<WaiterAdapter.MyHolder> {
    private List<WaiterModel> modelList;
    private Context context;

    public WaiterAdapter() {
    }

    public WaiterAdapter(List<WaiterModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.waiter_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        WaiterModel model = modelList.get(position);

        holder.title.setText(model.getTitle());
        holder.price.setText(String.valueOf(model.getPrice()));
        holder.quantity.setText(model.getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(model.getTotalPrice()));
        holder.dateTime.setText(model.getCurrentDateTime());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView title, price, quantity, totalPrice, dateTime;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            dateTime = itemView.findViewById(R.id.dateTime);
        }
    }
}
