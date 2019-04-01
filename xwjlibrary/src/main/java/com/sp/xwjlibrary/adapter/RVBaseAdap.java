package com.sp.xwjlibrary.adapter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vinson on 2017/9/7.
 */

public abstract class RVBaseAdap<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final int TYPE_HEADER = 0;
    protected final int TYPE_FOOTER = 1;
    protected final int TYPE_NORMAL = 2;
    private View mHeaderView;
    private View mFooterView;
    private SwipeRefreshLayout srly;

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        //第一个item应该加载Header
        if (position == 0){
            return TYPE_HEADER;
        }
        //最后一个,应该加载Footer
        if(position == getItemCount() - 1){
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    //添加HeaderView和FooterView
    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public void addFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return onCreateHolder(parent, viewType);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return onCreateHolder(parent, viewType);
        }
        VH vh = onCreateHolder(parent, viewType);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, (Integer) v.getTag());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            if(mHeaderView != null){
                position --;
            }
            vh.itemView.setTag(position);
            onBindHolder(vh,position);
        }
    }

    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return getCount();
        }else if (mHeaderView != null && mFooterView != null){
            return getCount() + 2;
        }else{
            return getCount() + 1;
        }
    }

    public void notifyDatasChang(){
        super.notifyDataSetChanged();
        if(srly != null && srly.isRefreshing()){
            srly.setRefreshing(false);
        }
    }

    /**
     * 注册分页加载
     * @param rv
     */
    public void registerPageLoad(RecyclerView rv){
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int dy;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int lastPosition = recyclerView.getAdapter().getItemCount() - 1;
                if(((srly != null && !srly.isRefreshing()) || dy > 0)
                        && lastPosition == lastVisibleItemPosition
                        && newState == RecyclerView.SCROLL_STATE_IDLE){
                    onPageLoad();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                this.dy = dy;
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void registerPullDownToRefresh(SwipeRefreshLayout srly){
        this.srly = srly;
        srly.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullDownToRefresh();
            }
        });
    }

    /**行布局监听*/
    public void onItemClick(View itemView, int position){}
    /** 必须调用registerPageLoad()进行注册*/
    public void onPageLoad(){}
    /** 必须调用registerPullDownToRefresh()进行注册*/
    public void onPullDownToRefresh(){}

    public abstract VH onCreateHolder(ViewGroup parent, int viewType);
    public abstract int getCount();
    public abstract void onBindHolder(VH vh, int position);

}
