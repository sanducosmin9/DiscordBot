package info.uaic;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.*;
import events.*;

import music.Join;
import music.Play;
import music.Skip;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.apache.commons.collections4.sequence.DeleteCommand;

import java.util.EnumSet;

public class Main {

    public static void main(String[] args) throws Exception {

        JDA jda = JDABuilder.createDefault("ODQ1NTY3NDA0MDUwNDgxMTUy.YKi2Aw.f1revvPLPHVE9tgg9voIs_QQEvg")
                .enableIntents(GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.GUILD_EMOJIS)
                .disableCache(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE
                ))
                .enableCache(CacheFlag.VOICE_STATE) // metoda de precautie.
                .build();

        jda.addEventListener(new Welcome());
        jda.addEventListener(new Invite());

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setOwnerId("845567404050481152");
        builder.setPrefix("!");
        builder.setHelpWord("helpme");
        builder.addCommand(new ServerInfo())
                .addCommand(new StackSearching())
                .addCommand(new WikiSearching())
                .addCommand(new Hello())
                .addCommand(new Purge())
                .addCommand(new Join())
                .addCommand(new Play())
                .addCommand(new Skip());



        CommandClient client = builder.build();

        jda.addEventListener(client);


    }
}
