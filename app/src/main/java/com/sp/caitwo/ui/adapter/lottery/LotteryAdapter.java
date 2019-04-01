package com.sp.caitwo.ui.adapter.lottery;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.base.LotteryCons;
import com.sp.caitwo.info.BiliListInfo;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.HashMap;
import java.util.List;

import static com.sp.caitwo.ui.adapter.lottery.LotteryItemAdapter.CIRCLE_BALL;
import static com.sp.caitwo.ui.adapter.lottery.LotteryItemAdapter.RECTANGLE_BALL;
import static com.sp.util.Util.getBitToString;
import static com.sp.util.Util.getBitToString2;

/**
 * Created by Administrator on 2018/11/7.
 */

public class LotteryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BiliListInfo.ListBeans> list;
    private LotteryItemAdapter lotteryItemAdapter;
    private int game_type;
    private int mBaseWanfaId;
    private int titleindex, tabindex;
    private OnItemClickListener mOnItemClickListener;
    private int type = 1;
    private OnItemTypeClickListener onItemTypeClickListener;

    public LotteryAdapter(Context context, List<BiliListInfo.ListBeans> list, int titleindex, int tabindex, int game_type) {
        this.context = context;
        this.list = list;
        this.titleindex = titleindex;
        this.tabindex = tabindex;
        this.game_type = game_type;
    }

    public LotteryAdapter(Context context, List<BiliListInfo.ListBeans> list, int titleindex, int tabindex, int game_type, int mBaseWanfaId) {
        this.context = context;
        this.list = list;
        this.titleindex = titleindex;
        this.tabindex = tabindex;
        this.game_type = game_type;
        this.mBaseWanfaId = mBaseWanfaId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lottery, parent, false);
        return new LotteryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (game_type == LotteryCons.CQSSC || game_type == LotteryCons.TJSSC){
            if (titleindex == 0 || titleindex == 1) {
                int len = list.size();
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
                ((LotteryViewHolder) holder).lottery_bit.setText(getBitToString(Math.abs(len - position - 1)));
            } else {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.GONE);
            }
            if (titleindex == 0 || titleindex == 2 || titleindex == 3) {
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, RECTANGLE_BALL, game_type, mBaseWanfaId);
                ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.GONE);
            } else {
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
                ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.VISIBLE);
            }
        } else if (game_type == LotteryCons.BJPK10 || game_type == LotteryCons.XYPK10) {
            if (titleindex == 0 || titleindex == 1 || titleindex == 2) {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
                ((LotteryViewHolder) holder).lottery_bit.setText(getBitToString2(position));
            } else {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.GONE);
            }
            if (titleindex == 0 || titleindex == 2 || titleindex == 3) {
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, RECTANGLE_BALL, game_type, mBaseWanfaId);
                ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.GONE);
            } else {
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
                ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.VISIBLE);
            }
        } else if (game_type == LotteryCons.FUCAI3D || game_type == LotteryCons.PAILIE3) {
            ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.VISIBLE);
            if (titleindex == 0 || titleindex == 3) {
                int len = list.size();
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
                ((LotteryViewHolder) holder).lottery_bit.setText(getBitToString(Math.abs(len - position - 1)));
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
            } else {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.GONE);
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 8, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
            }
        } else if (game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC) {
            ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
            ((LotteryViewHolder) holder).lottery_bit.setText(list.get(tabindex).getLists().get(position).getName());
            if (titleindex == 0 || titleindex == 5 || titleindex == 6 || titleindex == 1 && ( tabindex == 1 || tabindex == 3)) {
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(tabindex).getLists(), context, position, RECTANGLE_BALL, game_type);
                ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.GONE);
            } else {
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
                lotteryItemAdapter = new LotteryItemAdapter(list.get(tabindex).getLists(), context, position, CIRCLE_BALL, game_type);
                ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.VISIBLE);
            }
        } else if (game_type == LotteryCons.SIXHECAI) {
            ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.GONE);
            ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
            lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, RECTANGLE_BALL, game_type, mBaseWanfaId);
        } else if (game_type == LotteryCons.PAILIE5) {
            int len = list.size();
            ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.VISIBLE);
            ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
            ((LotteryViewHolder) holder).lottery_bit.setText(getBitToString(Math.abs(len - position - 1)));
            ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
            lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
        } else if (game_type == LotteryCons.ELEVENXFIVE) {
            ((LotteryViewHolder) holder).rgSpeedChoice.setVisibility(View.VISIBLE);
            if (titleindex == 1 || (titleindex == 2 && tabindex == 0) || (titleindex == 3 && tabindex == 0)) {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
                ((LotteryViewHolder) holder).lottery_bit.setText(list.get(tabindex).getLists().get(position).getName());
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
            } else if (titleindex == 5) {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.VISIBLE);
                ((LotteryViewHolder) holder).lottery_bit.setText(list.get(position).getName());
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 6, GridLayoutManager.VERTICAL, false));
            } else {
                ((LotteryViewHolder) holder).lottery_bit.setVisibility(View.GONE);
                ((LotteryViewHolder) holder).lottery_ball_list.setLayoutManager(new GridLayoutManager(context, 8, GridLayoutManager.VERTICAL, false));
            }
            if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
                lotteryItemAdapter = new LotteryItemAdapter(list.get(tabindex).getLists().get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
            } else {
                lotteryItemAdapter = new LotteryItemAdapter(list.get(position).getList(), context, position, CIRCLE_BALL, game_type, mBaseWanfaId);
            }
        }
        ((LotteryViewHolder) holder).lottery_ball_list.setAdapter(lotteryItemAdapter);
        lotteryItemAdapter.setOnItemImageViewClickListener(new LotteryItemAdapter.OnItemImageViewClickListener() {
            @Override
            public void OnItemImageViewClick(View view, int positionPare, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemImageViewClick(view, positionPare, position);
                }
            }
        });
        lotteryItemAdapter.notifyDataSetChanged();
        final RadioGroup rgSpeedChoice = ((LotteryViewHolder) holder).rgSpeedChoice;
        if (!BaseUtil.isEmpty(holder) && !BaseUtil.isEmpty(rgSpeedChoice)) {
            rgSpeedChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    for (int i = 0; i < group.getChildCount(); i++) {
                        View childAt = group.getChildAt(i);
                        if (checkedId == childAt.getId()) {
                            String menuType = (String) childAt.getTag();
                            onItemTypeClickListener.OnItemTypeClick(childAt, menuType, position);
                            if (BaseUtil.equals(menuType, "clear")) {
                                rgSpeedChoice.clearCheck();
                            }
                        }
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (game_type == LotteryCons.FENFENCAI || game_type == LotteryCons.YDWFC) {
            return list.get(tabindex).getLists() != null ? list.get(tabindex).getLists().size() : 0;
        } else if (game_type == LotteryCons.ELEVENXFIVE) {
            if (titleindex == 1 || titleindex == 2 || titleindex == 3) {
                return list.get(tabindex).getLists().size();
            } else {
                return list.size();
            }
        } else {
            return list.size();
        }
    }

    public List<BiliListInfo.ListBeans> getData() {
        return list;
    }


    public class LotteryViewHolder extends RecyclerView.ViewHolder {

        TextView lottery_bit;
        RecyclerView lottery_ball_list;
        private final RadioGroup rgSpeedChoice;

        public LotteryViewHolder(View itemView) {
            super(itemView);
            lottery_bit = itemView.findViewById(R.id.lottery_bit);
            lottery_ball_list = itemView.findViewById(R.id.lottery_ball_list);
            rgSpeedChoice = itemView.findViewById(R.id.rg_speed_choice);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemTypeClickListener(OnItemTypeClickListener onItemTypeClickListener) {
        this.onItemTypeClickListener = onItemTypeClickListener;
    }

    public interface OnItemClickListener {
        void OnItemImageViewClick(View view, int positionPare, int position);
    }

    public interface OnItemTypeClickListener {
        void OnItemTypeClick(View view, String key, int position);
    }

    public int getGameType() {
        return game_type;
    }

    public void setGameType(int game_type) {
        this.game_type = game_type;
    }

    public int getTitleindex() {
        return titleindex;
    }

    public void setTitleindex(int titleindex) {
        this.titleindex = titleindex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getmBaseWanfaId() {
        return mBaseWanfaId;
    }

    public void setmBaseWanfaId(int mBaseWanfaId) {
        this.mBaseWanfaId = mBaseWanfaId;
    }
}
