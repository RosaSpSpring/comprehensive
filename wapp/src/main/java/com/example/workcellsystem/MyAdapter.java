package com.example.workcellsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workcellsystem.data.CellItem;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<CellItem> mDataset;

    public MyAdapter() {
        super();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_detail_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.textView.setText(mDataset[position]);
        TextView cell_name = holder.view.findViewById(R.id.cell_name);
        cell_name.setText(mDataset.get(position).getCellName());

        TextView project_name = holder.view.findViewById(R.id.project_name);
        project_name.setText(mDataset.get(position).getProjectName());

        TextView number = holder.view.findViewById(R.id.number);
        number.setText(mDataset.get(position).getNumber());

        TextView start_time = holder.view.findViewById(R.id.start_time);
        start_time.setText(mDataset.get(position).getStartDate());

        TextView end_time = holder.view.findViewById(R.id.end_time);
        end_time.setText(mDataset.get(position).getEndDate());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public MyAdapter(List<CellItem> myDataset) {
        mDataset = myDataset;
    }

}
