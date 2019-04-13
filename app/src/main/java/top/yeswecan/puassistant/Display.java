package top.yeswecan.puassistant;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Display extends AppCompatActivity {

    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private int[] imageIds = new int[] { R.mipmap.img1, R.mipmap.img2, R.mipmap.img3 };
    // 正在寻找的活动的个数
    private int findingNum=2;
    // 最多查找5个
    private final int maxfindingNum=5;
    // 每个查找文本框的ID
    private int[] categoryID=new int[]{R.id.category1, R.id.category2, R.id.category3, R.id.category4,R.id.category5};
    private int[] groupID=new int[]{R.id.group1, R.id.group2, R.id.group3, R.id.group4, R.id.group5};
    // 下拉框当前的文本
    String itemCategory, itemGroup;
    // 第几张图片
    private int currentImageId=0;
    private ImageView imageView;
    private String eligibleActivity[]=new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        imageView=findViewById(R.id.image);
        TextView textViewBack=findViewById(R.id.backToMain);

        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Display.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final Handler myHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 0x9999) {
                    imageView.setImageResource(imageIds[currentImageId++]);
                    if (currentImageId == 3) {
                        currentImageId = 0;
                    }
                }
                return false;
            }
        });
        // 定时更换图片
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0x9999;
                myHandler.sendMessage(msg);
            }
        }, 0, 1200);

        eligibleActivity[0]="第三届“次元口袋”跳蚤市场（参与人员）";
        eligibleActivity[1]="“寄梦于徽，计情于心”院徽设计征集大赛";
        // 符合条件的活动的个数 eligibleActivityNum
        int eligibleActivityNum=2;
        TableLayout tableLayout=findViewById(R.id.eligibleActivity);
        tableLayout.removeAllViews();
        tableLayout.setStretchAllColumns(true);
        for(int i=0;i<eligibleActivityNum;i++)
        {
            TableRow tableRow=new TableRow(Display.this);
            TextView textView=new TextView(Display.this);
            textView.setText(eligibleActivity[i]);
            textView.setTextSize(20);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Display.this,Details.class);
                    startActivity(intent);
                }
            });
            tableRow.addView(textView);
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(MP, WC,1));
        }

        // 下拉框设置监听
        final Spinner spinnerCategory=findViewById(R.id.activityCategories);
        final Spinner spinnerGroup=findViewById(R.id.ownershipGroup);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 获取下拉框当前的文本
                itemCategory = (String) spinnerCategory.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 获取下拉框当前的文本
                itemGroup = (String) spinnerGroup.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 添加查找活动
        TextView textViewAdd=findViewById(R.id.addActivity);
        textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findingNum==maxfindingNum){
                    Toast.makeText(getApplicationContext(),"最多只能查找5个。",Toast.LENGTH_SHORT).show();
                }else{
                    // 获取空闲文本框的ID
                    TextView textView1=findViewById(categoryID[findingNum]);
                    TextView textView2=findViewById(groupID[findingNum]);
                    // 设置文本框的内容
                    textView1.setText(itemCategory);
                    textView2.setText(itemGroup);
                    findingNum++;
                }
            }
        });
        // 删除查找活动
        final TextView textViewGroup1=findViewById(R.id.group1);
        final TextView textViewGroup2=findViewById(R.id.group2);
        final TextView textViewGroup3=findViewById(R.id.group3);
        final TextView textViewGroup4=findViewById(R.id.group4);
        final TextView textViewGroup5=findViewById(R.id.group5);
        textViewGroup1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for(int i = 0; i < findingNum; ++i){
                    updateFindingText(i);
                }
                findingNum--;
                return false;
            }
        });
        textViewGroup2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for(int i = 1; i < findingNum; ++i){
                    updateFindingText(i);
                }
                findingNum--;
                return false;
            }
        });
        textViewGroup3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for(int i = 2; i < findingNum; ++i){
                    updateFindingText(i);
                }
                findingNum--;
                return false;
            }
        });
        textViewGroup4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for(int i = 3; i < findingNum; ++i){
                    updateFindingText(i);
                }
                findingNum--;
                return false;
            }
        });
        textViewGroup5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                for(int i = 4; i < findingNum; ++i){
                    updateFindingText(i);
                }
                findingNum--;
                return false;
            }
        });
    }
    private void updateFindingText(int i){
        TextView textViewCur, textViewPre;
        if(i==4){
            textViewCur=findViewById(groupID[4]);
            textViewCur.setText("");
            textViewCur=findViewById(categoryID[4]);
            textViewCur.setText("");
            return;
        }
        textViewCur=findViewById(groupID[i]);
        textViewPre=findViewById(groupID[i+1]);
        textViewCur.setText(textViewPre.getText());
        textViewCur=findViewById(categoryID[i]);
        textViewPre=findViewById(categoryID[i+1]);
        textViewCur.setText(textViewPre.getText());
    }
}
