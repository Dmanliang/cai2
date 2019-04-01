package com.sp.caitwo.ui.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sp.caitwo.R;
import com.sp.caitwo.asynctask.InterfaceTask;
import com.sp.caitwo.bean.MessageBean;
import com.sp.caitwo.ui.activity.sysm.WebLoadActivity;
import com.sp.caitwo.ui.fragment.message.MessageFrag;
import com.sp.caitwo.ui.fragment.message.NoticeFrag;
import com.sp.caitwo.ui.holder.MessageHolder;
import com.sp.util.DateUtil;
import com.sp.xwjlibrary.adapter.RVBaseAdap;
import com.sp.xwjlibrary.util.BaseUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageAdap extends RVBaseAdap<MessageHolder> {

    public final static int TYPE_MSG = 0X01;
    public final static int TYPE_NOTICE = 0X02;
    private FragmentActivity activity;
    private int type;
    private ArrayList<MessageBean.DataBean.ListBean> dataList;

    public MessageAdap(FragmentActivity activity, int type) {
        this.activity = activity;
        this.type = type;
    }

    @Override
    public MessageHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new MessageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false));
    }

    @Override
    public int getCount() {
        return BaseUtil.isEmpty(dataList) ? 0 : dataList.size();
    }

    @Override
    public void onBindHolder(MessageHolder holder, int position) {
        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvTime.setText(DateUtil.getTime(dataList.get(position).getCreate_time()));
        switch(type){
            case TYPE_MSG:
                if (dataList.get(position).getStatus() == 1)
                    holder.ivRedPoint.setVisibility(View.VISIBLE);
                else
                    holder.ivRedPoint.setVisibility(View.GONE);
                break;
            case TYPE_NOTICE:
                holder.ivRedPoint.setVisibility(View.GONE);
                break;
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();

                //标记为已读
                InterfaceTask.getInstance().setMsgRead(dataList.get(pos).getId());

                Intent intent = new Intent(activity, WebLoadActivity.class);
                intent.putExtra(WebLoadActivity.URL,dataList.get(pos).getContent());
                intent.putExtra(WebLoadActivity.OTHER_INFO,dataList.get(pos).getTitle() + "," + dataList.get(pos).getCreate_time() + "," + dataList.get(pos).getId());
                switch(type){
                    case TYPE_MSG:
                        intent.putExtra(WebLoadActivity.TITLE,"消息详情");
                        break;
                    case TYPE_NOTICE:
                        intent.putExtra(WebLoadActivity.TITLE,"公告详情");
                        break;
                }
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public void onPageLoad() {
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (!fragment.isHidden() && fragment instanceof MessageFrag){
                ((MessageFrag) fragment).pageLoad();
            }
            if (!fragment.isHidden() && fragment instanceof NoticeFrag){
                ((NoticeFrag) fragment).pageLoad();
            }
        }
    }

    public void notifyDatasChang(ArrayList<MessageBean.DataBean.ListBean> dataList) {
        switch(type){
            case TYPE_MSG:
                this.dataList = dataList;
                break;
            case TYPE_NOTICE:
                this.dataList = dataList;
                for (MessageBean.DataBean.ListBean listBean : dataList) {
                    if (listBean.getNotice_type() != 1){
                        this.dataList.remove(listBean);
                    }
                }
                break;
        }
        super.notifyDatasChang();
    }

    @Override
    public void onPullDownToRefresh() {
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof MessageFrag){
                ((MessageFrag) fragment).setPage(1);
                ((MessageFrag) fragment).getData();
            }
            if (fragment instanceof NoticeFrag){
                ((NoticeFrag) fragment).setPage(1);
                ((NoticeFrag) fragment).getData();
            }
        }
    }
}
