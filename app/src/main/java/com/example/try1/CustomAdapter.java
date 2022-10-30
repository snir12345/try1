package com.example.try1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList  Medication_id,Medication_Name, Medication_Type,Medication_Count;

    CustomAdapter(Context context, ArrayList Medication_id, ArrayList Medication_Name, ArrayList Medication_Type, ArrayList Medication_Count){
        this.context = context;
        this.Medication_id = Medication_id;
        this.Medication_Name = Medication_Name;
        this.Medication_Type = Medication_Type;
        this.Medication_Count = Medication_Count;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false );
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        holder.Medication_id_text.setText(String.valueOf(Medication_id.get(position)));
        holder.Medication_name_text.setText(String.valueOf(Medication_Name.get(position)));
        holder.Medication_type_text.setText(String.valueOf(Medication_Type.get(position)));
        holder.Medication_count_text.setText(String.valueOf(Medication_Count.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(Medication_id.get(position)));
                intent.putExtra("name",String.valueOf(Medication_Name.get(position)));
                intent.putExtra("type",String.valueOf(Medication_Type.get(position)));
                intent.putExtra("count",String.valueOf(Medication_Count.get(position)));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Medication_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Medication_id_text,Medication_name_text, Medication_type_text,Medication_count_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Medication_id_text = itemView.findViewById(R.id.Medication_id_text);
            Medication_name_text = itemView.findViewById(R.id.Medication_name_text);
            Medication_type_text = itemView.findViewById(R.id.Medication_type_text);
            Medication_count_text = itemView.findViewById(R.id.Medication_count_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
