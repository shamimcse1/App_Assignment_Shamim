package codercamp.com.app_assignment_shamim;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import codercamp.com.app_assignment_shamim.model.Model;
import codercamp.com.app_assignment_shamim.model.WaiterModel;

public class Utils {
    private final static String KEY = "save_all";
    private final static String WAITER_KEY = "save_all_waiter_data";

    public static void createPref(Context context, List<Model> list) {

        Model model = new Model("Food 1", context.getString(R.string.description), "https://www.freeiconspng.com/uploads/greek-salad-png-21.png", 500);
        list.add(model);
        model = new Model("Food 2", context.getString(R.string.description), "https://1.bp.blogspot.com/-IpKcfGvEZlo/W_pgjJByPbI/AAAAAAAAAE0/J_OtA_0Lp5MUXT_ycKgiRKtd7tmjUJJSwCLcBGAs/s1600/FR_PORI_fr_hero_2047.png", 450);
        list.add(model);

        Gson gson = new Gson();
        String gsonString = gson.toJson(list);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, gsonString);
        editor.apply();

    }

    public static List<Model> readList(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(KEY, "");

        Gson jsonString = new Gson();
        Type type = new TypeToken<ArrayList<Model>>() {
        }.getType();

        List<Model> list = jsonString.fromJson(json, type);
        return list;
    }


    public static void createWaiterPref(Context context, List<WaiterModel> waiterModel) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        try {
            db.collection("MyOrder")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            //Dialog will show
                            //progressDialog.show();
                            if (task.isSuccessful() && task.getResult() != null) {
                                progressDialog.dismiss();

                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    //String documentID = document.getId();
                                    WaiterModel model = document.toObject(WaiterModel.class);
                                    waiterModel.add(model);
                                    Log.d("TAG", model.getTitle());

                                }

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Gson gson = new Gson();
        String gsonString = gson.toJson(waiterModel);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WAITER_KEY, gsonString);
        editor.apply();

    }

    public static List<WaiterModel> readWaiterPref(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(WAITER_KEY, "");

        Gson jsonString = new Gson();
        Type type = new TypeToken<ArrayList<Model>>() {
        }.getType();

        List<WaiterModel> list = jsonString.fromJson(json, type);
        return list;
    }


}
