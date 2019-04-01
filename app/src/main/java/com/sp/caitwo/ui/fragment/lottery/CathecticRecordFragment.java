package com.sp.caitwo.ui.fragment.lottery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.CathecticRecordInfo;
import com.sp.caitwo.ui.activity.UserInfo.UserBetRecordActivity;
import com.sp.caitwo.ui.activity.lottery.BetBillDetailActivity;
import com.sp.caitwo.ui.adapter.lottery.CathecticRecordApater;
import com.sp.caitwo.ui.view.refresh.PullLoadMoreRecyclerView;
import com.sp.xwjlibrary.util.TextViewUtil;
import com.sp.xwjlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

import static com.sp.util.DateUtil.getCurrDate;


/**
 * 最近投注
 */

public class CathecticRecordFragment extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener{

    private PullLoadMoreRecyclerView cathecticrecord;
    private CathecticRecordApater cathecticRecordApater;
    private List<CathecticRecordInfo.DataBean> list = new ArrayList<>();
    private View inflate;
    private int game_type;
    private TextView cathecticrecordMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(inflate == null){
            inflate = inflater.inflate(R.layout.fragment_cathecticrecord, null, false);
            init();
        }
        return inflate;
    }

    protected void init() {
        cathecticrecord = inflate.findViewById(R.id.cathecticrecord);
        cathecticrecordMessage = inflate.findViewById(R.id.cathecticrecord_message);
        cathecticrecord.setLinearLayout();
        cathecticrecord.setOnPullLoadMoreListener(this);
        cathecticrecord.setRefreshing(true);
        cathecticRecordApater = new CathecticRecordApater(CathecticRecordFragment.this.getContext(),list);
        cathecticrecord.setAdapter(cathecticRecordApater);
        cathecticrecord.setColorSchemeResources(R.color.light_red);
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(20,20,20,20);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        String content = "仅显示最近20条普通投注,查看更多";
        TextViewUtil.setHyperlink(textView, content, new ClickableSpan() {
            @Override
            public void onClick( View widget) {
                startActivity(new Intent(getActivity(), UserBetRecordActivity.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(getContext(),R.color.blue));
                ds.setUnderlineText(false);
            }
        },content.indexOf("查看更多"),content.length());
        cathecticRecordApater.addFooterView(textView);

        cathecticRecordApater.notifyDataSetChanged();
        cathecticRecordApater.setClickListener(new CathecticRecordApater.CheckOnClickListener() {
            @Override
            public void setCheckItemClick(RecyclerView.ViewHolder viewholder, int position) {
                Intent intent = new Intent(getContext(), BetBillDetailActivity.class);
                intent.putExtra("detail_list",list.get(position));
                intent.putExtra("game_type",list.get(position).getGame_type());
                intent.putExtra("orderNum",list.get(position).getOrder_no());
                startActivityForResult(intent,0);
            }
        });
        if(list.size() != 0){
            cathecticrecordMessage.setVisibility(View.GONE);
        }else{
            cathecticrecordMessage.setVisibility(View.VISIBLE);
        }
    }

    public void getBetRecordList(int game_type){
        String startTime = getCurrDate();
        String endTime = getCurrDate();
        InterfaceTask.getInstance().getBetRecordList(0,0, 1, 20, startTime, endTime, new InterfaceTask.OnInterfaceTask() {
            @Override
            public void onError() {
                super.onError();
                list.clear();
                cathecticrecordMessage.setVisibility(View.VISIBLE);
                cathecticRecordApater.notifyDataSetChanged();
                cathecticrecord.setPullLoadMoreCompleted();
            }

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                CathecticRecordInfo cathecticRecordInfo = (CathecticRecordInfo)o;
                list.clear();
                if (cathecticRecordInfo.getData().size() == 0) {
                    cathecticrecordMessage.setVisibility(View.VISIBLE);
                    cathecticrecord.setPullLoadMoreCompleted();
                    cathecticRecordApater.notifyDataSetChanged();
                    return;
                }
                cathecticrecordMessage.setVisibility(View.GONE);
                cathecticrecord.setPullLoadMoreCompleted();
                list.addAll(cathecticRecordInfo.getData());
                cathecticRecordApater.notifyDataSetChanged();
            }
        });
    }

    public void getGame_type(int game_type){
        this.game_type = game_type;
        getBetRecordList(game_type);
    }

    @Override
    public void onRefresh() {
        getBetRecordList(game_type);
    }

    @Override
    public void onLoadMore() {
        onXLoadMore();
    }

    public void onXLoadMore() {
        cathecticrecord.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(CathecticRecordFragment.this.getContext(), "没有更多数据!");
                if (cathecticrecord != null)
                    cathecticrecord.setPullLoadMoreCompleted();
            }
        }, 500);
    }
}
