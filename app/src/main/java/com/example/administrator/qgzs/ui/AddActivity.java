package com.example.administrator.qgzs.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.qgzs.R;
import com.example.administrator.qgzs.bean.Goods;
import com.example.administrator.qgzs.utils.DatabaseHelper;
import com.example.administrator.qgzs.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class AddActivity extends Activity {

    @BindView(R.id.goodsID)
    EditText goodsID;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.miaosha)
    CheckBox miaosha;
    @BindView(R.id.danjia)
    CheckBox danjia;
    @BindView(R.id.goodsName)
    EditText goodsName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.buyButton, R.id.enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buyButton:
                Finish();
                break;
            case R.id.enter:
                save();
                break;
        }
    }

    private void Finish() {
        finish();
        overridePendingTransition(R.animator.activity_home_to_buy_in, R.animator.activity_bottom_to_top_out);
    }

    private void save() {
        Goods bean = new Goods();
        bean.setName(goodsName.getText().toString());
        bean.setGoodsID(goodsID.getText().toString());
        bean.setPrice(price.getText().toString());
        bean.setMiaosha(miaosha.isChecked()?1:0);
        bean.setDanjia(danjia.isChecked()?1:0);
        if (bean.getName().equals("")) {
            Toast.makeText(this, "请填写商品名称！"+bean.getName(), Toast.LENGTH_SHORT).show();
            return;
        }
        if (bean.getGoodsID().equals("")) {
            Toast.makeText(this, "请填写商品编码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bean.getPrice().equals("")) {
            Toast.makeText(this, "请填写期望价格！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!miaosha.isChecked()&!danjia.isChecked()){
            Toast.makeText(this, "请选择检测模式！", Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.insert(bean);
        ToastUtil.showToast("添加完成");
        Finish();


    }
}
