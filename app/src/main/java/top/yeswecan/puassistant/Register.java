package top.yeswecan.puassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    TextView textViewBack;
    EditText editTextID;
    EditText editTextPassword;
    EditText editTextConfirm;
    EditText editTextNick;
    Button buttonComplete;
    // 用户ID(学号)
    private String allID[]=new String[10];
    // 用户个数
    private int num;
    // 注册用户的 学号 密码 昵称
    private String newID, password, confirm, nickName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextID=findViewById(R.id.registerID);
        editTextPassword=findViewById(R.id.registerPassword);
        editTextConfirm=findViewById(R.id.confirmPassword);
        editTextNick=findViewById(R.id.userNickname);
        buttonComplete=findViewById(R.id.completeRegister);
        textViewBack=findViewById(R.id.backToMain);

        // 用户个数
        num=4;
        allID[0]="1401170211";
        allID[1]="1401170212";
        allID[2]="1401170213";
        allID[3]="1401170214";

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newID=editTextID.getText().toString();
                nickName=editTextNick.getText().toString();
                password=editTextPassword.getText().toString();
                confirm=editTextConfirm.getText().toString();
                check();
            }
        });

        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void check(){
        int flag = 0;
        // 判断信息是否完善
        if(newID==null || newID.equals("") || nickName==null || nickName.equals("") ||
                password==null || password.equals("") || confirm==null || confirm.equals("")){
            Toast.makeText(getApplicationContext(),"请完善输入信息",Toast.LENGTH_SHORT).show();
            flag = 1;
        }
        // 如果完善则执行
        if(flag == 0){
            for(int i = 0; i < num; ++i){
                // 判断学号是否被注册过
                if(newID.equals(allID[i])){
                    Toast.makeText(getApplicationContext(),"该学号已被注册过，请重新输入学号。",Toast.LENGTH_LONG).show();
                    editTextID.setText("");
                    flag = 1;
                    break;
                }
            }
            // 如果学号没被注册过且两次密码相同
            if(flag == 0 && password.equals(confirm)){
                Toast.makeText(getApplicationContext(),"恭喜你，注册成功。",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Register.this,Display.class);
                startActivity(intent);
            }else if(flag == 0){
                Toast.makeText(getApplicationContext(),"两次密码输入不相同，请重新输入密码。",Toast.LENGTH_LONG).show();
                editTextPassword.setText("");
                editTextConfirm.setText("");
            }
        }
    }
}
