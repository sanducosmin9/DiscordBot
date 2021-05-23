package events;

import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.handle.GuildEmojisUpdateHandler;
import org.jetbrains.annotations.NotNull;

public class Hello extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();
        String nickname = event.getMember().getUser().getName();

        if(messageSent.equals("!pa test"))
            event.getChannel().sendMessage("test").queue();
    }

}
