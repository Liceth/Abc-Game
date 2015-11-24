package licethovalles.administrador.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Information> data = Collections.emptyList();
    private RecyclerClickListner mRecyclerClickListner;
    private int value;

    public ViewAdapter(Context context,List<Information> data,int value){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        this.value=value;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information information = data.get(position);
        String imageName;
        if(value==1) {
            imageName = information.title;
        }
        else
        {   //information.title="Adivina";
            imageName = "";
        }

        holder.tv.setText(imageName);
        int resID = context.getResources().getIdentifier(information.title, "drawable", context.getPackageName());
        holder.ib.setImageResource(resID);

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setRecyclerClickListner(RecyclerClickListner recyclerClickListner){
        mRecyclerClickListner = recyclerClickListner;
    }




    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv;
        private ImageView ib;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv = (TextView) itemView.findViewById(R.id.listText);
            ib = (ImageView) itemView.findViewById(R.id.listImage);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerClickListner != null) {
                mRecyclerClickListner.itemClick(v, getPosition());
            }
        }
    }

    public interface RecyclerClickListner
    {
        public void itemClick(View view, int position);
    }
}
