package bot;

import io.github.alekseysotnikov.cmd.core.Cmd;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class YandexToken {
    public static String TOKEN;
    public static Date LAST_UPDATE_DATE;
    public void UpdateYandexToken() throws IOException, InterruptedException, TimeoutException {
        Date currentDate = new Date();
        if (TOKEN == null || LAST_UPDATE_DATE == null
                || Duration.ofMillis(Math.abs(currentDate.getTime() - LAST_UPDATE_DATE.getTime())).toHours() > 12){
            TOKEN = new Cmd()
                    .configuring(c -> c.readOutput(true))
                    .interpreter("yc")
                    .command("iam","create-token")
                    .execute()
                    .outputUTF8();
            LAST_UPDATE_DATE = currentDate;
            System.out.println(TOKEN);
            System.out.println(LAST_UPDATE_DATE);
        }
    }
    public String GetToken(){
        return TOKEN;
    }

}
