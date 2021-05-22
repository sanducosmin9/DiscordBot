package info.uaic;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main{

    public static void main(String[] args) {
        try {
            JDA jda = JDABuilder.createDefault("ODQ1NTY3NDA0MDUwNDgxMTUy.YKi2Aw.8UxCNQcu7dzCSGdN9EoakZV08fg").build();
        } catch (LoginException e) {
            e.printStackTrace();
        }


    }
}
