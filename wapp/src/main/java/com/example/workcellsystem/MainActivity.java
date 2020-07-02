package com.example.workcellsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.workcellsystem.data.CellItem;
import com.example.workcellsystem.histroyobservernote.HistoryObserverNote;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button3);
        EditText editText = findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("Name")){
                    start();
                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_LONG).show();
                }

//                MainActivity.this.finish();
            }
        });


//        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.grid_root);
//        for(int i=0; i<gridLayout.getChildCount(); i++){
//            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
//        }



//        gridLayout.removeAllViewsInLayout();


//
//        for (int i = 0; i < CHILD_VIEW_COUNT; i++) {
//            CardLayout childView = new CardLayout(getContext());
//            parent.addView(childView);
//            children.add(childView);
//        }
//        parent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Resources res = getResources();
//                int tileMargins = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, res.getDisplayMetrics()));
//                final int childWidth = parent.getWidth() / parent.getColumnCount() - (tileMargins * 2);
//                final int childHeight = parent.getHeight() / parent.getRowCount() - (tileMargins * 2);
//                for (int i = 0; i < parent.getChildCount(); i++) {
//                    View childView = parent.getChildAt(i);
//                    GridLayout.LayoutParams params = (GridLayout.LayoutParams) childView.getLayoutParams();
//                    params.width = childWidth;
//                    params.height = childHeight;
//                    params.setMargins(tileMargins, tileMargins, tileMargins, tileMargins);
//                    childView.setLayoutParams(params);
//                }
//                parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
//        listener.onParentAndChildrenPrepared(parent, children);
//        return rootView;



//        recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        String[] myDataset = new String[] {
//                "s","sdfs","sdfgghoojlj"
//        };
//        List<CellItem> list = new ArrayList<>();
//        list.add(new CellItem("e", "e","e", "e", "e"));
//        mAdapter = new MyAdapter(list);
//        recyclerView.setAdapter(mAdapter);
    }


    void start() {
//        startActivity(new Intent(this, HistoryObserverNote.class));
        startActivity(new Intent(this, LoginActivity.class));
    }
}
