package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        mTextViewResult = findViewById(R.id.text_view_result);

        mQueue = Volley.newRequestQueue(this);




        String url = "http://ec2-65-1-47-142.ap-south-1.compute.amazonaws.com:8080/api/15/items/all";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String firstName = employee.getString("name");
                                int price = employee.getInt("price");
                                String description = employee.getString("description");
                                mTextViewResult.append(firstName + "\n " + String.valueOf(price) + "\n " + description + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                final ProgressDialog mDialog = new ProgressDialog(LogActivity.this);
                mDialog.setMessage("Error in API.....");
                mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mDialog.setIndeterminate(true);
                mDialog.setCancelable(true);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.show();
            }
        });
        mQueue.add(request);
    }
}


//package com.example.myapp;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.TextView;
//
//        import com.android.volley.Request;
//        import com.android.volley.RequestQueue;
//        import com.android.volley.Response;
//        import com.android.volley.VolleyError;
//        import com.android.volley.toolbox.JsonArrayRequest;
//        import com.android.volley.toolbox.JsonObjectRequest;
//        import com.android.volley.toolbox.Volley;
//
//        import org.json.JSONArray;
//        import org.json.JSONException;
//        import org.json.JSONObject;
//
//public class LogActivity extends AppCompatActivity {
//
//    TextView results;
//    String JsonURL = "http://ec2-65-1-47-142.ap-south-1.compute.amazonaws.com:8080/api/15/items/all";
//    String data = "";
//    RequestQueue requestQueue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_log);
//
//        requestQueue = Volley.newRequestQueue(this);
//        results = (TextView) findViewById(R.id.jsonData);
//
//        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,
//
//                new Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            JSONObject colorObj = response.getJSONObject(0);
//                            JSONArray colorArry = colorObj.getJSONArray("items");
//                            for (int i = 0; i < colorArry.length(); i++) {
//
//                                JSONObject jsonObject = colorArry.getJSONObject(i);
//                                String name = jsonObject.getString("name");
//                                String description = jsonObject.getString("description");
//
//                                data += "Color Number: " + (i + 1) + "\n" + "name: " + name + "\n" +
//                                        "description : " + description + "\n\n";
//                            }
//                            results.setText(data);
//                        }
//
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Volley", "Error");
//                    }
//                }
//        );
//        requestQueue.add(arrayreq);
//    }
//}