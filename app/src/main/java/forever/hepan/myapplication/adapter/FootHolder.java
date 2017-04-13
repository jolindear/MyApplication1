package forever.hepan.myapplication.adapter;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import forever.hepan.myapplication.R;

/**
 * Created by Think on 2017/4/11.
 */

public class FootHolder extends RecyclerView.ViewHolder
{
    public   TextView textView;

    public FootHolder(View itemView)
    {
        super(itemView);
        textView=(TextView) itemView.findViewById(R.id.tv_foot);
    }
}
