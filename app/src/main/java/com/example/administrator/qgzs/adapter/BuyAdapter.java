package com.example.administrator.qgzs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.qgzs.R;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.ui.BuyActivity;
import com.example.administrator.qgzs.ui.HomeActivity;
import com.example.administrator.qgzs.utils.BuyDatabaseHelper;
import com.example.administrator.qgzs.utils.DatabaseHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class BuyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Goods> list;

    public BuyAdapter(Context context, List<Goods> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder item= (ViewHolder) holder;
        item.id.setText(list.get(position).getGoodsID());
        item.price.setText(""+list.get(position).getPrice()+" 元");
        item.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyDatabaseHelper helper = new BuyDatabaseHelper(context);
                helper.deleteABuyGoodsBean(Integer.parseInt(list.get(position).getId()));
                ((BuyActivity)context).initData();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id)
        TextView id;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.delete)
        Button delete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
