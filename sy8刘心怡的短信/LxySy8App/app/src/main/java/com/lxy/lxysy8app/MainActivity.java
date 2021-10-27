package com.lxy.lxysy8app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private List<Msg> msgRanList = new ArrayList<>();

    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        initRandomMsg();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_re_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                    getMsg();
                }
                else{
                    Toast.makeText(MainActivity.this, "不能发送空消息", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initMsg(){
        Msg msg1 = new Msg("哎lxy，借我点钱呗？", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("借多少？", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("1000。", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
        Msg msg4 = new Msg("行。哎，要不要多借你 24，好凑个整？", Msg.TYPE_SEND);
        msgList.add(msg4);
        Msg msg5 = new Msg("也好。", Msg.TYPE_RECEIVED);
        msgList.add(msg5);
    }

    private void initRandomMsg(){
        Msg msg1 = new Msg("ojbk", Msg.TYPE_RECEIVED);
        msgRanList.add(msg1);
        Msg msg2 = new Msg("雀食", Msg.TYPE_RECEIVED);
        msgRanList.add(msg2);
        Msg msg3 = new Msg("笑死", Msg.TYPE_RECEIVED);
        msgRanList.add(msg3);
        Msg msg4 = new Msg("难搞哦", Msg.TYPE_RECEIVED);
        msgRanList.add(msg4);
        Msg msg5 = new Msg("好家伙", Msg.TYPE_RECEIVED);
        msgRanList.add(msg5);
        Msg msg6 = new Msg("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈", Msg.TYPE_RECEIVED);
        msgRanList.add(msg6);
        Msg msg7 = new Msg("？？？？？", Msg.TYPE_RECEIVED);
        msgRanList.add(msg7);
        Msg msg8 = new Msg("啊这。。。", Msg.TYPE_RECEIVED);
        msgRanList.add(msg8);
    }

    private void getMsg(){
        int r = (int)(Math.random()*8);
        msgList.add(msgRanList.get(r));
        adapter.notifyItemInserted(msgList.size()-1);
        msgRecyclerView.scrollToPosition(msgList.size()-1);
    }
}


















