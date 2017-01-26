package es.alruiz.networkinfoserver.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.alruiz.networkinfoserver.R;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.btn_main_start)
    Button btnStart;
    @BindView(R.id.tv_main_log)
    TextView tvLog;
    @BindView(R.id.sv_main_log)
    ScrollView svLog;

    private MainPresenter presenter;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this, this);
    }

    @OnClick(R.id.btn_main_start)
    public void onClickStart() {
        presenter.getPhoneInfo();
    }

    @Override
    public void showMessage(String message) {
        appendMessageLog(message);
    }

    private void appendMessageLog(String message) {
        tvLog.append(message + "\n");
        svLog.fullScroll(View.FOCUS_DOWN);
    }
}
