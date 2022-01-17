package codercamp.com.app_assignment_shamim.viewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import codercamp.com.app_assignment_shamim.R;
import codercamp.com.app_assignment_shamim.model.Model;
import codercamp.com.app_assignment_shamim.view.DetailsActivity;


public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.MyViewHolder> {
    private List<Model> modelList;
    private Context context;

    public FoodItemAdapter() {
    }

    public FoodItemAdapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model = modelList.get(position);

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.price.setText(String.valueOf(model.getPrice() + " Tk"));
        Picasso.get().load(model.getImageUrl()).placeholder(R.drawable.food).into(holder.imageUrl);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("itemList",model);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, price, description;
        private ImageView imageUrl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.Title);
            price = itemView.findViewById(R.id.Price);
            description = itemView.findViewById(R.id.Description);
            imageUrl = itemView.findViewById(R.id.foodImage);
        }
    }
}
