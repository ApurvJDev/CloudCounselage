package com.example.cloudcounselageconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class msgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<msgModel> msgModelArrayList;
    int ITEM_SEND = 1;
    int ITEM_RECEIVE = 2;


    public msgAdapter(Context context, ArrayList<msgModel> msgModelArrayList) {
        this.context = context;
        this.msgModelArrayList = msgModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent ,false);
            return new senderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.receiver_layout, parent, false);
            return new receiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        msgModel msgModel = msgModelArrayList.get(position);
        if(holder.getClass() == senderViewHolder.class) {
            senderViewHolder viewHolder = (senderViewHolder) holder;
            viewHolder.txtSender.setText(msgModel.getMessage());
        }
        else if(holder.getClass() == receiverViewHolder.class){
            receiverViewHolder receiverViewHolder = (receiverViewHolder) holder;
            receiverViewHolder.txtReceiver.setText(msgModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return msgModelArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        msgModel msgModel = msgModelArrayList.get(position);
        if(msgModel.isSender()) {
            return ITEM_SEND;
        }
        else {
            return ITEM_RECEIVE;
        }
    }

    static class senderViewHolder extends RecyclerView.ViewHolder {
        TextView txtSender;

        public senderViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSender = itemView.findViewById(R.id.txtSender);
            txtSender.setTextIsSelectable(true);
        }
    }

    static class receiverViewHolder extends RecyclerView.ViewHolder {

        TextView txtReceiver;

        public receiverViewHolder(@NonNull View itemView) {
            super(itemView);

            txtReceiver = itemView.findViewById(R.id.txtReceiver);
            txtReceiver.setTextIsSelectable(true);
        }
    }
}
