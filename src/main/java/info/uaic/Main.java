package info.uaic;

import events.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main{

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.createDefault("ODQ1NTY3NDA0MDUwNDgxMTUy.YKi2Aw.8UxCNQcu7dzCSGdN9EoakZV08fg").build();
        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new WelcomeEvent());


    }
}
