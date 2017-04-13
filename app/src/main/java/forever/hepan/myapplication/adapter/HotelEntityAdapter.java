package forever.hepan.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import forever.hepan.myapplication.R;
import forever.hepan.myapplication.callback.CallBack;
import forever.hepan.myapplication.entity.HotelEntity;
import forever.hepan.myapplication.utils.HotelUtils;


/**
 * Created by lyd10892 on 2016/8/23.
 */

public class HotelEntityAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {

    //  接口回调用
    private CallBack callBack= null;
    public  void setOnClickListener(CallBack callBack)
    {
        this.callBack=callBack;
    }
    public ArrayList<HotelEntity.TagsEntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;
    private SparseBooleanArray mBooleanMap;


    public HotelEntityAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(ArrayList<HotelEntity.TagsEntity> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

                //获得节点的 个数   就是 大的标题二级目录
    @Override
    protected int getSectionCount() {
        return HotelUtils.isEmpty(allTagList) ? 0 : allTagList.size();
    }
/**
 *
 *
 *
 *           @time   20170411
 *
 *           @auther  lizhe
 *
 * */
    //      设置每个内容显示的  个数 返回了一个count
    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).tagInfoList.size();
        if (count >= 8 && !mBooleanMap.get(section)) {
            count = 8;
        }

        return HotelUtils.isEmpty(allTagList.get(section).tagInfoList) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return true;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.hotel_title_item, parent, false));
    }

    //创建一个底部的视图
    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType)
    {
        return new FootHolder(mInflater.inflate(R.layout.hotel_foot_item, parent, false));
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new DescHolder(mInflater.inflate(R.layout.hotel_desc_item, parent, false));
    }

		// 绑定头部的  视图

    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section)
    {

        holder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                String text = isOpen ? "展开" : "关闭";
                mBooleanMap.put(section, !isOpen);
                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });

        holder.titleView.setText(allTagList.get(section).tagsName);
        holder.openView.setText(mBooleanMap.get(section) ? "关闭" : "展开");

    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder,final int section)
    {
        final FootHolder holder1 = (FootHolder) holder;
        holder1.textView.setText("底部是"+(section+1));
        holder1.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"底部是"+(section+1),Toast.LENGTH_SHORT).show();
            }
        });
    }
		// 绑定每个条目的item   视图
    @Override
    protected void onBindItemViewHolder(DescHolder holder,final int section, final int position) {
        holder.descView.setText(allTagList.get(section).tagInfoList.get(position).tagName);
        holder.descView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null)
                    callBack.shortClick(section,position);
            }
        });

    }
}
