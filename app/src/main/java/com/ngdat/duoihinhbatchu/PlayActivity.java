package com.ngdat.duoihinhbatchu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private int heart;
    private int point;
    private Button btntiep;
    private TextView txtHeart;
    private TextView txtPoint;
    private ImageView imgPicture;
    private LinearLayout lnAnwser1, lnAnwser2, lnCh1, lnCh2;
    private List<Question> listQuestions;
    private Random random;
    private int i = 0;
    private int pst = 0;
    private String dapan;
    private List<IDButton> listChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initComponents();
        makeQuestion();
    }

    private void makeQuestion() {
        Question qs = listQuestions.get(i);
        dapan = qs.getContext();
        LayoutInflater inflater = LayoutInflater.from(this);
        if (dapan.length() > 8) {
            for (int i = 0; i < 8; i++) {
                Button view = (Button) inflater.inflate(R.layout.item_btn_anwser, lnAnwser1, false);
                view.setId(16 + i);
                lnAnwser1.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((Button) view).getText() != "") {
                            ((Button) view).setText("");
                            for (int i = 0; i < listChar.size(); i++) {
                                if (listChar.get(i).getIdAnswer() == view.getId()) {
                                    ((Button) findViewById(listChar.get(i).getIdPick())).setVisibility(View.VISIBLE);
                                    listChar.remove(i);
                                    break;
                                }
                            }
                            pst--;
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_anwser);
                            }
                        }
                    }
                });
            }
            for (int i = 8; i < dapan.length(); i++) {
                Button view = (Button) inflater.inflate(R.layout.item_btn_anwser, lnAnwser2, false);
                view.setId(16 + i);
                lnAnwser2.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((Button) view).getText() != "") {
                            ((Button) view).setText("");
                            for (int i = 0; i < listChar.size(); i++) {
                                if (listChar.get(i).getIdAnswer() == view.getId()) {
                                    ((Button) findViewById(listChar.get(i).getIdPick())).setVisibility(View.VISIBLE);
                                    listChar.remove(i);
                                    break;
                                }
                            }
                            pst--;
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_anwser);
                            }
                        }
                    }
                });
            }
        } else {
            for (int i = 0; i < dapan.length(); i++) {
                Button view = (Button) inflater.inflate(R.layout.item_btn_anwser, lnAnwser1, false);
                view.setId(16 + i);
                lnAnwser1.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((Button) view).getText() != "") {
                            ((Button) view).setText("");
                            for (int i = 0; i < listChar.size(); i++) {
                                if (listChar.get(i).getIdAnswer() == view.getId()) {
                                    ((Button) findViewById(listChar.get(i).getIdPick())).setVisibility(View.VISIBLE);
                                    listChar.remove(i);
                                    break;
                                }
                            }
                            pst--;
                            for (int i = 16; i < dapan.length() + 16; i++) {
                                ((Button) findViewById(i)).setBackgroundResource(R.drawable.ic_anwser);
                            }
                        }
                    }
                });
            }
        }
        imgPicture.setImageResource(qs.getId());

        String[] kt = {"a", "b", "c", "d", "e", "g", "h", "i", "k", "l", "m", "n", "o", "u", "q", "p", "r", "s", "t", "y", "v", "x"};
        List<String> tl = new ArrayList<>();

        for (int i = 0; i < dapan.length(); i++) {
            tl.add(dapan.charAt(i) + "");
        }

        for (int i = 0; i < 16 - dapan.length(); i++) {
            tl.add(kt[random.nextInt(kt.length)]);
        }
        Collections.shuffle(tl);

        for (int i = 0; i < 8; i++) {
            Button view = (Button) inflater.inflate(R.layout.item_btn, lnCh1, false);
            view.setId(i);
            view.setText(tl.get(i));
            lnCh1.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pst < dapan.length()) {
                        Button btn = (Button) view;
                        addChar(btn.getId(), btn.getText().toString());
                        btn.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
        for (int i = 8; i < 16; i++) {
            Button view = (Button) inflater.inflate(R.layout.item_btn, lnCh2, false);
            view.setId(i);
            view.setText(tl.get(i));
            lnCh2.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pst < dapan.length()) {
                        Button btn = (Button) view;
                        addChar(btn.getId(), btn.getText().toString());
                        btn.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    private void initComponents() {
        listChar = new ArrayList<>();
        heart = 5;
        point = 0;
        random = new Random();

        btntiep = (Button) findViewById(R.id.btn_tiep);
        btntiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newQuestion();
            }
        });
        imgPicture = (ImageView) findViewById(R.id.img_picture);
        lnAnwser1 = (LinearLayout) findViewById(R.id.anwser1);
        lnAnwser2 = (LinearLayout) findViewById(R.id.anwser2);
        lnCh1 = (LinearLayout) findViewById(R.id.ln_3);
        lnCh2 = (LinearLayout) findViewById(R.id.ln_4);
        txtHeart = (TextView) findViewById(R.id.txt_heart);
        txtPoint = (TextView) findViewById(R.id.txt_point);

        txtHeart.setText(String.valueOf(heart));
        txtPoint.setText(String.valueOf(point));

        listQuestions = new ArrayList<>();
        listQuestions.add(new Question(R.drawable.aomua, "aomua"));
        listQuestions.add(new Question(R.drawable.baocao, "baocao"));
        listQuestions.add(new Question(R.drawable.canthiep, "canthiep"));
        listQuestions.add(new Question(R.drawable.cattuong, "cattuong"));
        listQuestions.add(new Question(R.drawable.chieutre, "chieutre"));
        listQuestions.add(new Question(R.drawable.danong, "danong"));
        listQuestions.add(new Question(R.drawable.danhlua, "danhlua"));
        listQuestions.add(new Question(R.drawable.giandiep, "giandiep"));
        listQuestions.add(new Question(R.drawable.giangmai, "giangmai"));
        listQuestions.add(new Question(R.drawable.kiemchuyen, "kiemchuyen"));
        listQuestions.add(new Question(R.drawable.nambancau, "nambancau"));
        listQuestions.add(new Question(R.drawable.masat, "masat"));
        listQuestions.add(new Question(R.drawable.lancan, "lancan"));
        listQuestions.add(new Question(R.drawable.quyhang, "quyhang"));
        listQuestions.add(new Question(R.drawable.xedapdien, "xedapdien"));
        listQuestions.add(new Question(R.drawable.xakep, "xakep"));
        listQuestions.add(new Question(R.drawable.xaphong, "xaphong"));
        listQuestions.add(new Question(R.drawable.vuonbachthu, "vuonbachthu"));
        listQuestions.add(new Question(R.drawable.vuaphaluoi, "vuaphaluoi"));
        listQuestions.add(new Question(R.drawable.tranhthu, "tranhthu"));
        listQuestions.add(new Question(R.drawable.totien, "totien"));
        listQuestions.add(new Question(R.drawable.tichphan, "tichphan"));
        listQuestions.add(new Question(R.drawable.thattinh, "thattinh"));
        listQuestions.add(new Question(R.drawable.thothe, "thothe"));
        Collections.shuffle(listQuestions);

    }

    private void newQuestion() {
        if (i < listQuestions.size() - 1) {
            listChar.clear();
            btntiep.setVisibility(View.INVISIBLE);
            pst = 0;
            i++;
            lnAnwser1.removeAllViews();
            lnAnwser2.removeAllViews();
            lnCh1.removeAllViews();
            lnCh2.removeAllViews();
            makeQuestion();
        } else {
            Toast.makeText(this, "Bạn đã trả lời hết câu hỏi !", Toast.LENGTH_LONG).show();
        }

    }

    public void addChar(int id, String s) {
        for (int i = 16; i < dapan.length() + 16; i++) {
            if (((Button) findViewById(i)).getText() == "") {
                ((Button) findViewById(i)).setText(s);
                listChar.add(new IDButton(id, i));

                pst++;
                if (pst == dapan.length()) {
                    if (checkAnswer()) {
                        Toast.makeText(PlayActivity.this, "Thiên tài !", Toast.LENGTH_SHORT).show();
                        point += 100;
                        txtPoint.setText(String.valueOf(point));
                        for (int j = 16; j < dapan.length() + 16; j++) {
                            ((Button) findViewById(j)).setBackgroundResource(R.drawable.ic_tile_true);
                            ((Button) findViewById(j)).setClickable(false);
                        }
                        btntiep.setVisibility(View.VISIBLE);
                    } else {
                        heart--;
                        txtHeart.setText(String.valueOf(heart));
                        if (heart <= 0) {
                            Toast.makeText(PlayActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                        Toast.makeText(PlayActivity.this, "Đáp án sai !", Toast.LENGTH_SHORT).show();

                        for (int k = 16; k < dapan.length() + 16; k++) {
                            ((Button) findViewById(k)).setBackgroundResource(R.drawable.ic_tile_false);
                        }
                    }
                }
                return;
            }
        }

    }

    public boolean checkAnswer() {
        StringBuilder da = new StringBuilder();
        for (int i = 16; i < dapan.length() + 16; i++) {
            da.append(((Button) findViewById(i)).getText());
        }
        return da.toString().equals(dapan);
    }
}
