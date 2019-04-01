package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.bean.CathecticInfo;
import com.sp.caitwo.ui.view.LongDragonCountDown;
import com.sp.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import static com.sp.caitwo.base.LotteryCons.iconRes;

/**
 * Created by Administrator on 2018/10/19.
 */

public class CathecticAdpater extends RecyclerView.Adapter {


    private long curtime;
    private Context context;
    private List<CathecticInfo.DataBeanX.DataBean> list = new ArrayList();
    private ButtonOnClickListener buttonOnClickListener;

    public CathecticAdpater(Context context, List<CathecticInfo.DataBeanX.DataBean> list) {
        this.context = context;
        if (list != null) {
            this.list = list;
        }
        curtime = System.currentTimeMillis();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cathectic, null, false);
        CathecticViewHolder viewHolder = new CathecticViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CathecticViewHolder) {
            ((CathecticViewHolder) holder).lottery_name.setText(list.get(position).getGame_name());
            ((CathecticViewHolder) holder).game_num.setText(list.get(position).getGame_num() + "期");
            ((CathecticViewHolder) holder).bit_number.setText(list.get(position).getPosiname());
            ((CathecticViewHolder) holder).bxds_text.setText(list.get(position).getType());
            ((CathecticViewHolder) holder).cathectic_times.setText(list.get(position).getTimes() + "期");
            if (list.get(position).getType().equals("大") || list.get(position).getType().equals("双")) {
                ((CathecticViewHolder) holder).bxds_text.setBackground(context.getResources().getDrawable(R.drawable.shape_rect_yellow));
            } else {
                ((CathecticViewHolder) holder).bxds_text.setBackground(context.getResources().getDrawable(R.drawable.shape_rect_blue));
            }
            ((CathecticViewHolder) holder).cathectic_ds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonOnClickListener.setButtonOnclick(holder, position, 0);
                }
            });
            ((CathecticViewHolder) holder).cathectic_dx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonOnClickListener.setButtonOnclick(holder, position, 1);
                }
            });
            if (list.get(position).getSeconds() >= 0) {
                ((CathecticViewHolder) holder).game_time.startCountDown(list.get(position).getSeconds(), 1000, 1, curtime);
                ((CathecticViewHolder) holder).cathectic_type1.setTextColor(context.getResources().getColor(R.color.red_gesture));
                ((CathecticViewHolder) holder).cathectic_type2.setTextColor(context.getResources().getColor(R.color.red_gesture));
                ((CathecticViewHolder) holder).cathectic_ds.setClickable(true);
                ((CathecticViewHolder) holder).cathectic_dx.setClickable(true);
            } else {
                ((CathecticViewHolder) holder).game_time.setText("开奖中");
                ((CathecticViewHolder) holder).cathectic_type1.setTextColor(context.getResources().getColor(R.color.chat_bottom_line));
                ((CathecticViewHolder) holder).cathectic_type2.setTextColor(context.getResources().getColor(R.color.chat_bottom_line));
                ((CathecticViewHolder) holder).game_time.startCountDown(list.get(position).getSeconds(), 1000, 3, curtime);
                ((CathecticViewHolder) holder).cathectic_ds.setClickable(false);
                ((CathecticViewHolder) holder).cathectic_dx.setClickable(false);
            }
            if (!list.get(position).isFocus1()) {
                ((CathecticViewHolder) holder).cathectic_ds.setBackground((context.getResources().getDrawable(R.drawable.shape_rect_gray_white)));
                ((CathecticViewHolder) holder).cathectic_type1.setTextColor(context.getResources().getColor(R.color.red_gesture));
                ((CathecticViewHolder) holder).bili1.setTextColor(context.getResources().getColor(R.color.gray2));
            } else {
                ((CathecticViewHolder) holder).cathectic_ds.setBackground(context.getResources().getDrawable(R.drawable.shape_rect_blue));
                ((CathecticViewHolder) holder).cathectic_type1.setTextColor(context.getResources().getColor(R.color.text_white));
                ((CathecticViewHolder) holder).bili1.setTextColor(context.getResources().getColor(R.color.text_white));
            }
            if (!list.get(position).isFocus2()) {
                ((CathecticViewHolder) holder).cathectic_dx.setBackground((context.getResources().getDrawable(R.drawable.shape_rect_gray_white)));
                ((CathecticViewHolder) holder).cathectic_type2.setTextColor(context.getResources().getColor(R.color.red_gesture));
                ((CathecticViewHolder) holder).bili2.setTextColor(context.getResources().getColor(R.color.gray2));
            } else {
                ((CathecticViewHolder) holder).cathectic_dx.setBackground((context.getResources().getDrawable(R.drawable.shape_rect_blue)));
                ((CathecticViewHolder) holder).cathectic_type2.setTextColor(context.getResources().getColor(R.color.text_white));
                ((CathecticViewHolder) holder).bili2.setTextColor(context.getResources().getColor(R.color.text_white));
            }
            ((CathecticViewHolder) holder).cathectic_type1.setText(list.get(position).getBili_list().get(0).getBili_name());
            ((CathecticViewHolder) holder).cathectic_type2.setText(list.get(position).getBili_list().get(1).getBili_name());
            ((CathecticViewHolder) holder).bili1.setText("赔率" + list.get(position).getBili_list().get(0).getBili());
            ((CathecticViewHolder) holder).bili2.setText("赔率" + list.get(position).getBili_list().get(1).getBili());
            LotteryCons.setImageIcn(((CathecticViewHolder) holder).lottery_photo, list.get(position).getGame_type());
            ((CathecticViewHolder) holder).rr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public void setOnClickListener(ButtonOnClickListener buttonOnClickListener) {
        this.buttonOnClickListener = buttonOnClickListener;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void setCurrTime() {
        curtime = System.currentTimeMillis();
    }

    public class CathecticViewHolder extends RecyclerView.ViewHolder {

        public ImageView lottery_photo;
        public TextView lottery_name, game_num, bit_number, bxds_text, cathectic_times, cathectic_type1, cathectic_type2, bili1, bili2;
        public LinearLayout cathectic_ds, cathectic_dx;
        public LongDragonCountDown game_time;
        public View rr;

        public CathecticViewHolder(View itemView) {
            super(itemView);
            rr = itemView.findViewById(R.id.rr);
            lottery_photo = itemView.findViewById(R.id.lottery_photo);
            lottery_name = itemView.findViewById(R.id.lottery_name);
            game_time = itemView.findViewById(R.id.game_time);
            game_num = itemView.findViewById(R.id.game_num);
            bit_number = itemView.findViewById(R.id.bit_number);
            bxds_text = itemView.findViewById(R.id.bxds_text);
            cathectic_times = itemView.findViewById(R.id.cathectic_times);
            cathectic_type1 = itemView.findViewById(R.id.cathectic_type1);
            cathectic_type2 = itemView.findViewById(R.id.cathectic_type2);
            bili1 = itemView.findViewById(R.id.bili1);
            bili2 = itemView.findViewById(R.id.bili2);
            cathectic_ds = itemView.findViewById(R.id.cathectic_ds);
            cathectic_dx = itemView.findViewById(R.id.cathectic_dx);
        }
    }

    public interface ButtonOnClickListener {
        void setButtonOnclick(RecyclerView.ViewHolder viewHolder, int position, int index);
    }

}
