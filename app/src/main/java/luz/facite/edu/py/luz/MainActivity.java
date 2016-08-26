package luz.facite.edu.py.luz;

import android.hardware.Camera;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import java.security.Policy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Torch torch;
    private PowerManager.WakeLock wakeLock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        torch = new Torch();

        torch.on();
        setContentView(R.layout.activity_main);
        ToggleButton button = (ToggleButton) findViewById(R.id.button_on_off);
        button.setOnClickListener(this);


        PowerManager powerManager =
                (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,"Linterna");
        wakeLock.setReferenceCounted(false);
        if (!wakeLock.isHeld()) {
            wakeLock.acquire();
        }

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        // Apagar el flash
        torch.release();
        wakeLock.release();
    }

    @Override
    public void onClick(View viw) {
        if (torch.isOn()){
            torch.off();
        }else{
            torch.on();
        }
    }
}
