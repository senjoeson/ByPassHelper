package com.senjoeson.bypasshelper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.senjoeson.bypasshelper.R;
import com.senjoeson.bypasshelper.model.SmsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     author : sunqiao
 *     e-mail : sunqiao@kayak.com.cn
 *     time   : 2019/02/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SmsModelAdapter extends RecyclerView.Adapter<SmsModelAdapter.ViewHolder> {


    private List<SmsModel> mSmsModels;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SmsModel smsModel = mSmsModels.get(position);
        holder.mNoticePerson.setText(smsModel.getNoticeName());
        holder.mNoticePhone.setText(smsModel.getNoticePhoneNumber());
        holder.mComment.setText(smsModel.getMsgContent());

    }


    @Override
    public int getItemCount() {
        return mSmsModels != null ? mSmsModels.size() : 0;
    }

    public void setDatas(List<SmsModel> smsModels) {
        this.mSmsModels = smsModels;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView mNoticePerson;
        TextView mNoticePhone;
        TextView mComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNoticePerson = itemView.findViewById(R.id.notice_person);
            mNoticePhone = itemView.findViewById(R.id.notice_phone);
            mComment = itemView.findViewById(R.id.comment);
        }
    }
}
