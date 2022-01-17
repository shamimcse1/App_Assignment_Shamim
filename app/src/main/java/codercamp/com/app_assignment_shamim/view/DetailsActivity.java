package codercamp.com.app_assignment_shamim.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import codercamp.com.app_assignment_shamim.R;
import codercamp.com.app_assignment_shamim.model.Model;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imageView, itemAdd, itemRemove;
    private MaterialButton orderNow;
    private TextView title, price, description, quantity;
    private int totalQuantity = 1;
    private int TotalPrice = 0;
    private FirebaseFirestore database;
    private ProgressDialog progressDialog;
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);

        database = FirebaseFirestore.getInstance();

        title = findViewById(R.id.Title);
        description = findViewById(R.id.Description);
        price = findViewById(R.id.Price);
        imageView = findViewById(R.id.foodImage);
        quantity = findViewById(R.id.ItemQuantity);

        itemAdd = findViewById(R.id.ItemAdd);
        itemRemove = findViewById(R.id.ItemRemove);

        orderNow = findViewById(R.id.addToCartBtn);

        model = (Model) getIntent().getSerializableExtra("itemList");

        title.setText(model.getTitle());
        description.setText(model.getDescription());
        price.setText(String.valueOf(model.getPrice() + " Tk"));
        Picasso.get().load(model.getImageUrl()).placeholder(R.drawable.food).into(imageView);

        TotalPrice = model.getPrice() * totalQuantity;

        itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    TotalPrice = model.getPrice() * totalQuantity;
                }

            }
        });
        itemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    TotalPrice = model.getPrice() * totalQuantity;
                }
            }
        });

        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDate, currentTime;

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -1);

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                currentDate = dateFormat.format(calendar.getTime());

                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                currentTime = timeFormat.format(calendar.getTime());


                final HashMap<String, Object> map = new HashMap<>();

                map.put("Title", model.getTitle());
                map.put("Price", model.getPrice());
                map.put("TotalQuantity", quantity.getText().toString());
                map.put("TotalPrice", TotalPrice);
                map.put("CurrentDateTime", currentTime +" "+currentDate);


                DocumentReference key = database.collection("Order").document();
                String UniqueID = key.getId();
//.document(UniqueID).collection("Order")
                database.collection("MyOrder")
                        .add(map)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(DetailsActivity.this, "Order Successfully Submitted", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(DetailsActivity.this, "Failed to Submit", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });


    }
}