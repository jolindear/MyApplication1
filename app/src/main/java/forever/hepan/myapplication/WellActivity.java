package forever.hepan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import forever.hepan.myapplication.view.lyd.com.sectionrecyclerviewdemo.MainActivity;

public class WellActivity extends AppCompatActivity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well);
        initView();
    }

    private void initView()
    {
        listView = (ListView) findViewById(R.id.listView);

        ArrayList al = new ArrayList();
        al.add("酒店界面");



        listView.setAdapter(new ArrayAdapter<String>(WellActivity.this,android.R.layout.simple_list_item_1,al));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:enterActivity( MainActivity.class); break;



                }
            }
        });
    }

    private  void enterActivity(Class clazz)
    {
        Intent intent = new Intent(WellActivity.this,clazz);
        startActivity(intent);
    }
}
