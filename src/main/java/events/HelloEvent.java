package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();
        String nickname = event.getMember().getUser().getName();

        if(messageSent.equals("!pa Hello"))
            event.getChannel().sendMessage("Hello, young " + nickname).queue();
    }

}
