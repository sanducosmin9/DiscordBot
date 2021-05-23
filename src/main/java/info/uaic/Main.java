package info.uaic;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.Hello;
import commands.ServerInfo;
import commands.StackSearching;
import commands.WikiSearching;
import events.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main {

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.createDefault("ODQ1NTY3NDA0MDUwNDgxMTUy.YKi2Aw.f1revvPLPHVE9tgg9voIs_QQEvg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_EMOJIS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build();

        jda.addEventListener(new Welcome());

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setOwnerId("845567404050481152");
        builder.setPrefix("!");
        builder.setHelpWord("helpme");
        builder.addCommand(new ServerInfo())
                .addCommand(new StackSearching())
                .addCommand(new WikiSearching()).
                addCommand(new Hello());

        CommandClient client = builder.build();

        jda.addEventListener(client);


    }
}
