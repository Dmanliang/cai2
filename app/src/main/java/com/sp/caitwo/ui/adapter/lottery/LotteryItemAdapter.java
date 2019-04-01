package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.xwjlibrary.widget.DoubleTextView;

import java.util.ArrayList;
import java.util.List;

import static com.sp.util.Util.getTwoDecimal;

/**
 * Created by Administrator on 2018/6/26.
 */

public class LotteryItemAdapter extends RecyclerView.Adapter {

    private List<BiliListInfo.ListBean> mList;
    private List<BiliListInfo.ListBean2> mList2;
    private Context context;
    private OnItemImageViewClickListener mOnItemImageViewClickListener;
    private int positionPare;
    public static final int CIRCLE_BALL = 1;
    public static final int RECTANGLE_BALL = 2;
    private int type = 1;
    private int game_type;
    private int mBaseWanfaId;

    public LotteryItemAdapter(List<BiliListInfo.ListBean> mList, Context context, int positionPare, int type, int game_type, int mBaseWanfaId) {
        if (mList != null) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList<>();
        }
        this.context = context;
        this.positionPare = positionPare;
        this.type = type;
        this.game_type = game_type;
        this.mBaseWanfaId = mBaseWanfaId;
    }

    public LotteryItemAdapter(List<BiliListInfo.ListBean2> mList2, Context context, int positionPare, int type, int game_type) {
        if (mList2 != null) {
            this.mList2 = mList2;
        } else {
            this.mList2 = new ArrayList<>();
        }
        this.context = context;
        this.positionPare = positionPare;
        this.type = type;
        this.game_type = game_type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (type) {
            case CIRCLE_BALL:
                view = layoutInflater.inflate(R.layout.item_lottery_ball_circle, parent, false);
                viewHolder = new LotteryCircleItemViewHolder(view);
                break;
            case RECTANGLE_BALL:
                view = layoutInflater.inflate(R.layout.item_lottery_ball_rect, parent, false);
                viewHolder = new LotteryRectItemViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BiliListInfo.ListBean ball = null;
        BiliListInfo.ListBean2 ball2 = null;
        if (mList != null) {
            ball = mList.get(position);
        } else {
            ball2 = mList2.get(positionPare);
        }
        switch (type) {
            case CIRCLE_BALL:
                if (holder instanceof LotteryCircleItemViewHolder) {
                    if (ball != null) {
                        if (ball.isFocus()) {
                            ((LotteryCircleItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_ball_red_red);
                            ((LotteryCircleItemViewHolder) holder).item_text.setTextColor(context.getResources().getColor(R.color.text_white));
                        } else {
                            ((LotteryCircleItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_ball_red);
                            ((LotteryCircleItemViewHolder) holder).item_text.setTextColor(context.getResources().getColor(R.color.text_red));
                        }
                        switch (game_type) {
                            case LotteryCons.CQSSC:
                            case LotteryCons.TJSSC:
                            case LotteryCons.BJPK10:
                            case LotteryCons.XYPK10:
                            case LotteryCons.JSK3:
                            case LotteryCons.BJK3:
                            case LotteryCons.AHK3:
                            case LotteryCons.XJP28:
                            case LotteryCons.JND_28:
                            case LotteryCons.BJ_28:
                                ((LotteryCircleItemViewHolder) holder).item_text.setText(ball.getBili_name());
                                break;
                            case LotteryCons.FENFENCAI:
                            case LotteryCons.YDWFC:
                            case LotteryCons.FUCAI3D:
                            case LotteryCons.PAILIE3:
                            case LotteryCons.PAILIE5:
                            case LotteryCons.ELEVENXFIVE:
                                ((LotteryCircleItemViewHolder) holder).item_text.setText(ball.getWanfa_name());
                                break;
                        }
                    } else {
                        if (ball2.getList().get(position).isFocus()) {
                            ((LotteryCircleItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_ball_red_red);
                            ((LotteryCircleItemViewHolder) holder).item_text.setTextColor(context.getResources().getColor(R.color.text_white));
                        } else {
                            ((LotteryCircleItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_ball_red);
                            ((LotteryCircleItemViewHolder) holder).item_text.setTextColor(context.getResources().getColor(R.color.text_red));
                        }
                        switch (game_type) {
                            case LotteryCons.CQSSC:
                            case LotteryCons.TJSSC:
                            case LotteryCons.BJPK10:
                            case LotteryCons.XYPK10:
                            case LotteryCons.JSK3:
                            case LotteryCons.BJK3:
                            case LotteryCons.AHK3:
                            case LotteryCons.XJP28:
                            case LotteryCons.JND_28:
                            case LotteryCons.BJ_28:
                                ((LotteryCircleItemViewHolder) holder).item_text.setText(ball2.getList().get(position).getBili_name());
                                break;
                            case LotteryCons.FENFENCAI:
                            case LotteryCons.YDWFC:
                            case LotteryCons.FUCAI3D:
                            case LotteryCons.PAILIE3:
                            case LotteryCons.PAILIE5:
                            case LotteryCons.ELEVENXFIVE:
                                ((LotteryCircleItemViewHolder) holder).item_text.setText(ball2.getList().get(position).getWanfa_name());
                                break;
                        }
                    }
                    ((LotteryCircleItemViewHolder) holder).item_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemImageViewClickListener != null) {
                                mOnItemImageViewClickListener.OnItemImageViewClick(v, positionPare, position);
                            }
                        }
                    });
                }
                break;
            case RECTANGLE_BALL:
                if (ball != null) {
                    if (ball.isFocus()) {
                        ((LotteryRectItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball_red);
                        ((LotteryRectItemViewHolder) holder).item_text.setOneColor(context.getResources().getColor(R.color.text_white));
                        ((LotteryRectItemViewHolder) holder).item_text.setTwoColor(context.getResources().getColor(R.color.text_white));
                    } else {
                        ((LotteryRectItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball);
                        ((LotteryRectItemViewHolder) holder).item_text.setOneColor(context.getResources().getColor(R.color.text_red));
                        ((LotteryRectItemViewHolder) holder).item_text.setTwoColor(context.getResources().getColor(R.color.gray2));
                    }
                    switch (game_type) {
                        case LotteryCons.CQSSC:
                        case LotteryCons.TJSSC:
                        case LotteryCons.BJPK10:
                        case LotteryCons.XYPK10:
                        case LotteryCons.JSK3:
                        case LotteryCons.BJK3:
                        case LotteryCons.AHK3:
                        case LotteryCons.XJP28:
                        case LotteryCons.JND_28:
                        case LotteryCons.BJ_28:
                            ((LotteryRectItemViewHolder) holder).item_text.setOneText(ball.getBili_name());
                            ((LotteryRectItemViewHolder) holder).item_text.setTwoText("赔" + ball.getBili());
                            break;
                        case LotteryCons.FENFENCAI:
                        case LotteryCons.YDWFC:
                        case LotteryCons.FUCAI3D:
                        case LotteryCons.PAILIE3:
                        case LotteryCons.PAILIE5:
                        case LotteryCons.ELEVENXFIVE:
                            ((LotteryRectItemViewHolder) holder).item_text.setOneText(ball.getWanfa_name());
                            ((LotteryRectItemViewHolder) holder).item_text.setTwoText("赔" + ball.getOdd());
                            break;
                    }
                } else {
                    if (ball2.getList().get(position).isFocus()) {
                        ((LotteryRectItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball_red);
                        ((LotteryRectItemViewHolder) holder).item_text.setOneColor(context.getResources().getColor(R.color.text_white));
                        ((LotteryRectItemViewHolder) holder).item_text.setTwoColor(context.getResources().getColor(R.color.text_white));
                    } else {
                        ((LotteryRectItemViewHolder) holder).item_text.setBackgroundResource(R.drawable.shape_lottery_rect_ball);
                        ((LotteryRectItemViewHolder) holder).item_text.setOneColor(context.getResources().getColor(R.color.text_red));
                        ((LotteryRectItemViewHolder) holder).item_text.setTwoColor(context.getResources().getColor(R.color.gray2));
                    }
                    switch (game_type) {
                        case LotteryCons.CQSSC:
                        case LotteryCons.TJSSC:
                        case LotteryCons.BJPK10:
                        case LotteryCons.XYPK10:
                        case LotteryCons.JSK3:
                        case LotteryCons.BJK3:
                        case LotteryCons.AHK3:
                        case LotteryCons.XJP28:
                        case LotteryCons.JND_28:
                        case LotteryCons.BJ_28:
                            ((LotteryRectItemViewHolder) holder).item_text.setOneText(ball2.getList().get(position).getBili_name());
                            ((LotteryRectItemViewHolder) holder).item_text.setTwoText("赔" + ball2.getList().get(position).getBili());
                            break;
                        case LotteryCons.FENFENCAI:
                        case LotteryCons.YDWFC:
                        case LotteryCons.FUCAI3D:
                        case LotteryCons.PAILIE3:
                        case LotteryCons.PAILIE5:
                        case LotteryCons.ELEVENXFIVE:
                            ((LotteryRectItemViewHolder) holder).item_text.setOneText(ball2.getList().get(position).getWanfa_name());
                            ((LotteryRectItemViewHolder) holder).item_text.setTwoText("赔" +ball2.getList().get(position).getOdd());
                            break;
                    }
                }
                ((LotteryRectItemViewHolder) holder).item_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemImageViewClickListener != null) {
                            mOnItemImageViewClickListener.OnItemImageViewClick(v, positionPare, position);
                        }
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        } else if (game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC || game_type == LotteryCons.ELEVENXFIVE) {
            return mList2.get(positionPare).getList().size();
        }else{
            return mList2.size();
        }
    }

    public class LotteryCircleItemViewHolder extends RecyclerView.ViewHolder {

        TextView item_text;

        public LotteryCircleItemViewHolder(View itemView) {
            super(itemView);
            item_text = itemView.findViewById(R.id.tv_num_circle);
        }
    }

    public class LotteryRectItemViewHolder extends RecyclerView.ViewHolder {
        DoubleTextView item_text;

        public LotteryRectItemViewHolder(View itemView) {
            super(itemView);
            item_text = itemView.findViewById(R.id.tv_num_rect);
        }
    }


    public void setOnItemImageViewClickListener(OnItemImageViewClickListener mOnItemImageViewClickListener) {
        this.mOnItemImageViewClickListener = mOnItemImageViewClickListener;
    }

    public interface OnItemImageViewClickListener {
        void OnItemImageViewClick(View view, int positionPare, int position);
    }
}
