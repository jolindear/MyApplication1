package forever.hepan.myapplication.view.lyd.com.sectionrecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import forever.hepan.myapplication.R;
import forever.hepan.myapplication.adapter.HotelEntityAdapter;
import forever.hepan.myapplication.adapter.SectionedSpanSizeLookup;
import forever.hepan.myapplication.callback.CallBack;
import forever.hepan.myapplication.entity.HotelEntity;
import forever.hepan.myapplication.utils.JsonUtils;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HotelEntityAdapter mAdapter;
    private HotelEntity entity;
    private ArrayList<HotelEntity.TagsEntity> allTagsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new HotelEntityAdapter(this);

        mAdapter.setOnClickListener(new CallBack() {
            @Override
            public void shortClick(int section,int position) {
                Toast.makeText(MainActivity.this,allTagsList.get(section).tagsName+"---"+allTagsList.get(section).tagInfoList.get(position).tagName,Toast.LENGTH_SHORT).show();
            }
        });

        // 设置 fridLayout  布局           4行
        GridLayoutManager manager = new GridLayoutManager(this,4);
        //设置header
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,manager));

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        entity = JsonUtils.analysisJsonFile(this,"json");
        mAdapter.setData(entity.allTagsList);

        allTagsList = entity.allTagsList;

    }
}
