package com.example.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String KEY_COUNT = "count";
    private final String KEY_DATA1 = "data1";
    private final String KEY_DATA2 = "data2";
    private final String KEY_DATA3 = "data3";
    private final String KEY_STR = "str";

    private TextView tv;
    private ImageView iv;
    String s;
    private int crossWin = 0;
    private int circleWin = 0;
    private Button[] buttons;
    private int count = 1;
    private int[][] data = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.set);

        iv = findViewById(R.id.iv);

        tv = findViewById(R.id.tv);
        s = crossWin + " : " + circleWin;
        tv.setText(s);

        buttons = new Button[9];
        buttons[0] = findViewById(R.id.but_11);
        buttons[1] = findViewById(R.id.but_12);
        buttons[2] = findViewById(R.id.but_13);
        buttons[3] = findViewById(R.id.but_21);
        buttons[4] = findViewById(R.id.but_22);
        buttons[5] = findViewById(R.id.but_23);
        buttons[6] = findViewById(R.id.but_31);
        buttons[7] = findViewById(R.id.but_32);
        buttons[8] = findViewById(R.id.but_33);

        for (Button btn : buttons) {
            btn.setOnClickListener(this);
            btn.setAnimation(anim);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Заново");
        menu.add(0, 2, 0, "Выход");
        menu.add(0, 3, 4, "Обновить счет");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                clear();
                break;
            case 2:
                finish();
                break;
            case 3:
                resetCount();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(KEY_DATA1, data[0]);
        outState.putIntArray(KEY_DATA2, data[1]);
        outState.putIntArray(KEY_DATA3, data[2]);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_STR, s);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt(KEY_COUNT);
        tv.setText(s = savedInstanceState.getString(KEY_STR));
        data[0] = savedInstanceState.getIntArray(KEY_DATA1);
        data[1] = savedInstanceState.getIntArray(KEY_DATA2);
        data[2] = savedInstanceState.getIntArray(KEY_DATA3);
        buttons[0].setBackgroundResource(data[0][0] == 2 ? R.drawable.o : data[0][0] == 1 ? R.drawable.x : R.drawable.border);
        buttons[1].setBackgroundResource(data[0][1] == 2 ? R.drawable.o : data[0][1] == 1 ? R.drawable.x : R.drawable.border);
        buttons[2].setBackgroundResource(data[0][2] == 2 ? R.drawable.o : data[0][2] == 1 ? R.drawable.x : R.drawable.border);
        buttons[3].setBackgroundResource(data[1][0] == 2 ? R.drawable.o : data[1][0] == 1 ? R.drawable.x : R.drawable.border);
        buttons[4].setBackgroundResource(data[1][1] == 2 ? R.drawable.o : data[1][1] == 1 ? R.drawable.x : R.drawable.border);
        buttons[5].setBackgroundResource(data[1][2] == 2 ? R.drawable.o : data[1][2] == 1 ? R.drawable.x : R.drawable.border);
        buttons[6].setBackgroundResource(data[2][0] == 2 ? R.drawable.o : data[2][0] == 1 ? R.drawable.x : R.drawable.border);
        buttons[7].setBackgroundResource(data[2][1] == 2 ? R.drawable.o : data[2][1] == 1 ? R.drawable.x : R.drawable.border);
        buttons[8].setBackgroundResource(data[2][2] == 2 ? R.drawable.o : data[2][2] == 1 ? R.drawable.x : R.drawable.border);

    }

    void dialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Важное сообщение!")
                .setMessage(s)
                .setCancelable(false)
                .setNegativeButton("Заново", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        clear();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    Boolean check(int num) {
        String x = "WIN CROSS";
        String o = "WIN CIRCLE";

        if (data[0][0] == num && data[0][1] == num && data[0][2] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[1][0] == num && data[1][1] == num && data[1][2] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[2][0] == num && data[2][1] == num && data[2][2] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[0][0] == num && data[1][0] == num && data[2][0] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[0][1] == num && data[1][1] == num && data[2][1] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[0][2] == num && data[1][2] == num && data[2][2] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[0][0] == num && data[1][1] == num && data[2][2] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else if (data[0][2] == num && data[1][1] == num && data[2][0] == num) {
            dialog(num == 1 ? x : o);
            return true;
        } else
            return false;
    }

    Boolean checkWinner() {
        if (check(1)) {
            crossWin++;
            s = crossWin + " : " + circleWin;
            tv.setText(s);
            return true;
        }
        if (check(2)) {
            circleWin++;
            s = crossWin + " : " + circleWin;
            tv.setText(s);
            return true;
        }
        return false;
    }

    void click(View v, int row, int col) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        if (count % 2 == 0) {
            v.setAnimation(anim);
            v.setBackgroundResource(R.drawable.o);
            iv.setImageResource(R.drawable.x);
            data[row][col] = 2;
            v.setEnabled(false);
            if (checkWinner())
                return;
            if (isFull())
                return;
        } else {
            v.setAnimation(anim);
            v.setBackgroundResource(R.drawable.x);
            iv.setImageResource(R.drawable.o);
            data[row][col] = 1;
            v.setEnabled(false);
            if (checkWinner())
                return;
            if (isFull())
                return;
        }
        count++;
    }

    Boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (data[i][j] == 0)
                    return false;
            }
        }
        dialog(getString(R.string.draw));
        return true;
    }

    void clear() {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.border);
            button.setEnabled(true);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                data[i][j] = 0;
            }
        }
        count = 1;
    }

    void resetCount() {
        crossWin = 0;
        circleWin = 0;
        s = crossWin + " : " + circleWin;
        tv.setText(s);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.but_11:
                click(v.findViewById(R.id.but_11), 0, 0);
                break;
            case R.id.but_12:
                click(v.findViewById(R.id.but_12), 0, 1);
                break;
            case R.id.but_13:
                click(v.findViewById(R.id.but_13), 0, 2);
                break;
            case R.id.but_21:
                click(v.findViewById(R.id.but_21), 1, 0);
                break;
            case R.id.but_22:
                click(v.findViewById(R.id.but_22), 1, 1);
                break;
            case R.id.but_23:
                click(v.findViewById(R.id.but_23), 1, 2);
                break;
            case R.id.but_31:
                click(v.findViewById(R.id.but_31), 2, 0);
                break;
            case R.id.but_32:
                click(v.findViewById(R.id.but_32), 2, 1);
                break;
            case R.id.but_33:
                click(v.findViewById(R.id.but_33), 2, 2);
                break;
        }
    }
}
