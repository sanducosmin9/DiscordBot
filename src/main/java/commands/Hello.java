package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.handle.GuildEmojisUpdateHandler;
import org.jetbrains.annotations.NotNull;

public class Hello extends Command {

    public Hello(){
        this.name = "hello";
        this.help = "Mainly used for test purposes. returns a hello";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String nickname = commandEvent.getMember().getUser().getName();

        commandEvent.getChannel().sendMessage("Hello, " + nickname).queue();
    }


}
