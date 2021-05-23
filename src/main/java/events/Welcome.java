package events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Welcome extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        // iau numele user-ului
        String user = event.getMember().getAsMention();

        // iau jda-ul pentru a lua text channel-urile
        JDA client = event.getJDA();
        List<TextChannel> channels = client.getTextChannelsByName("welcome", true);

        // iau primul canal cu numele welcome.
        channels.get(0).sendMessage("Lizardino crocodino, " + user + " has joined the server").queue();
    }

}
