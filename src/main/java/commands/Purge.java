package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;

import java.awt.*;
import java.util.List;

public class Purge extends Command {

    public Purge() {
        this.name = "purge";
        this.help = "This one purges(deletes) a given number of messages";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

        int numberOfMessages;
        try {
            numberOfMessages = Integer.parseInt(commandEvent.getMessage().getContentRaw().substring(7));
        } catch (Exception e) {
            numberOfMessages = 0;
        }

        if (numberOfMessages > 0 && numberOfMessages < 20) {
            if (commandEvent.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                List<Message> messages = commandEvent.getChannel().getHistory().retrievePast(numberOfMessages + 1).complete();
                commandEvent.getChannel().purgeMessages(messages);
            }
        } else if(numberOfMessages > 20){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.RED);
            eb.setTitle("Too many messages selected");
            eb.setDescription("You can delete at most 20 messages");
            commandEvent.reply(eb.build());
        }
        else {
            commandEvent.reply("In order to purge messages, type in: !purge [number of messages you want to purge]");
        }

    }
}
