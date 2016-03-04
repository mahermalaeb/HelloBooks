package murex.dojo.hellobooks.app;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class TimerService extends IntentService {


   public TimerService() {
      super("TimerService");
   }

   @Override
   protected void onHandleIntent(Intent intent) {

      final ResultReceiver rec = intent.getParcelableExtra("receiverTag");

      Thread timer = new Thread() {
         int i;

         @Override
         public void run() {
            while (true) {
               Bundle b = new Bundle();
               b.putString("ServiceTag", String.valueOf(i++));
               rec.send(0, b);
               try {
                  sleep(1000);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      };
      timer.start();


   }

}