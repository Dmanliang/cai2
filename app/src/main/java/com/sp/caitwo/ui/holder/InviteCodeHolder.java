package com.sp.caitwo.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sp.caitwo.R;
import com.sp.xwjlibrary.widget.IconView;

public class InviteCodeHolder extends RecyclerView.ViewHolder {

    public final TextView tvInviteCode;
    public final TextView tvGenerateTime;
    public final TextView tvRegisterNum;
    public final IconView tvDelete;
    public final TextView tvLookReturnPoint;
    public final TextView tvCopyInviteCode;
    public final TextView tvCopyRegisterLink;

    public InviteCodeHolder(@NonNull View itemView) {
        super(itemView);
        tvInviteCode = itemView.findViewById(R.id.tv_invite_code);
        tvGenerateTime = itemView.findViewById(R.id.tv_generate_time);
        tvRegisterNum = itemView.findViewById(R.id.tv_register_num);
        tvDelete = itemView.findViewById(R.id.tv_delete);
        tvLookReturnPoint = itemView.findViewById(R.id.tv_look_return_point);
        tvCopyInviteCode = itemView.findViewById(R.id.tv_copy_invite_code);
        tvCopyRegisterLink = itemView.findViewById(R.id.tv_copy_register_link);
    }
}
