package com.example.tclapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tclapp.R;
import com.example.tclapp.adapters.ProductAdapter;
import com.example.tclapp.data.EndlessRecyclerViewScrollListener;
import com.example.tclapp.data.RetrofitApi;
import com.example.tclapp.model.Product;
import com.example.tclapp.model.ProductList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarTitle;
    ImageView toolbarIconBack;

    RecyclerView productsRecycler;
    ProductAdapter mAdapter;
    EditText searchEditText;
    TextView searchTextView;
    GridLayoutManager layoutManager;
    List<Product> productList;

    private int  current_page=1;
    private ProgressDialog mProgressDialog;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    RetrofitApi api = retrofit.create(RetrofitApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        toolbar=findViewById(R.id.custom_app_toolbar);
        toolbarTitle=findViewById(R.id.toolbaractivityname);
        productsRecycler=findViewById(R.id.productsrecyclerview);
        searchEditText=findViewById(R.id.searchEt);
        searchTextView=findViewById(R.id.searchClicl);
        toolbarIconBack=findViewById(R.id.backicon);

        productList=new ArrayList<>();
        toolbarTitle.setText("Products");
        toolbarIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        layoutManager=new GridLayoutManager(this,2);
        productsRecycler.setLayoutManager(layoutManager);
        mAdapter=new ProductAdapter(getApplicationContext(),productList);

        mProgressDialog = new ProgressDialog(ProductsActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

       // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        productsRecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                current_page ++;
                loadNextPage();

            }
        });



        searchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextData=searchEditText.getText().toString();
                Intent i=new Intent(ProductsActivity.this,SearchResult.class);
                i.putExtra("SearchText",editTextData);

                startActivity(i);
            }
        });



        Call<ProductList> call=api.getProducts(current_page);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

                mProgressDialog.dismiss();

                productList.addAll(response.body().getData().getData());
                productsRecycler.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProgressDialog.dismiss();

            }
        });

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void loadNextPage()
    {
        Call<ProductList> call=api.getProducts(current_page);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {

                productList.addAll(response.body().getData().getData());
                mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(),productList.size()-1);
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Log.e("TestProducts",t.getMessage());

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
