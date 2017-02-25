package com.example.administrator.qgzs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.qgzs.R;
import com.example.administrator.qgzs.bean.BooleanBean;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.event.BingoEvent;
import com.example.administrator.qgzs.event.SetCheckedEvent;
import com.example.administrator.qgzs.ui.HomeActivity;
import com.example.administrator.qgzs.utils.DatabaseHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Goods> list;

    public HomeAdapter(Context context, List<Goods> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder item = (ViewHolder) holder;
        item.id.setText(list.get(position).getGoodsID());
        item.price.setText("" + list.get(position).getPrice() + " 元");
        if (list.get(position).getMiaosha()== 1)item.miaosha.setVisibility(View.VISIBLE);
        if (list.get(position).getDanjia()==1)item.danjia.setVisibility(View.VISIBLE);
        //设置是否选中
        item.isChecked.setChecked(list.get(position).getIsChecked()==1);
        if (list.get(position).getBingo()==1)item.bingoText.setVisibility(View.VISIBLE);
        item.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper helper = new DatabaseHelper(context);
                helper.deleteAGoodsBean(Integer.parseInt(list.get(position).getId()));
                ((HomeActivity) context).initData();
            }
        });
        item.isChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SetCheckedEvent(item.isChecked.isChecked()?1:0,position));
                if(item.isChecked.isChecked())item.bingoText.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.isChecked)
        CheckBox isChecked;
        @BindView(R.id.id)
        TextView id;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.delete)
        TextView delete;
        @BindView(R.id.miaosha)
        TextView miaosha;
        @BindView(R.id.danjia)
        TextView danjia;
        @BindView(R.id.bingo_text)
        TextView bingoText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
