package events;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Invite extends ListenerAdapter {


    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if(messageSent.startsWith("!invite")){
            int expireTime = 300;

            event.getChannel().sendMessage("Here you go " + event.getChannel().createInvite().setMaxAge(expireTime).complete().getUrl()).queue();
            event.getChannel().sendMessage("The invite expires in " + expireTime/60 + " minute(s)").queue();
        }
        else if(messageSent.contains("invite") && !event.getMember().getUser().isBot()){
            event.getChannel().sendMessage("In order to create an invite, you have to type in: !pa invite").queue();
        }
    }

}
