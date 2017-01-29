package es.alruiz.networkinfoserver.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.alruiz.networkinfoserver.R;
import es.alruiz.networkinfoserver.network.Server;
import es.alruiz.networkinfoserver.utils.ip.IPUtils;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int PERMISSIONS_REQUEST_COARSE_LOCATION = 1;

    @BindView(R.id.btn_main_start)
    Button btnStart;
    @BindView(R.id.tv_main_log)
    TextView tvLog;
    @BindView(R.id.sv_main_log)
    ScrollView svLog;

    private MainPresenter presenter;
    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        server.stopServer();
    }

    @OnClick(R.id.btn_main_start)
    public void onClickStart() {
        checkPermissions();
    }

    @Override
    public void showMessage(String message) {
        appendMessageLog(message);
    }

    private void appendMessageLog(String message) {
        tvLog.append("\n" + message);
        svLog.fullScroll(View.FOCUS_DOWN);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_COARSE_LOCATION);
        } else {
            startServer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_COARSE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    appendMessageLog(getResources().getString(R.string.permissions_granted));
                    startServer();
                } else {
                    // permission denied
                    appendMessageLog(getResources().getString(R.string.permissions_not_granted));
                }
            }
        }
    }

    private void startServer() {
        server = new Server(this);
        showMessage("Internal IP: " + IPUtils.getInternalIp());
        new IpPublicTask().execute();
    }

    private class IpPublicTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                return IPUtils.getPublicIp();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                appendMessageLog("Public IP: " + result);
            }
        }
    }

}
