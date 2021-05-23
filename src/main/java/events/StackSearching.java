package events;

import com.google.api.services.customsearch.model.Result;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import info.uaic.GoogleTesting;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class StackSearching extends Command {

    public StackSearching(){
        this.name = "stack";
        this.help = "Searches in stack overflow for an answer to your question";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

        String messageSent = commandEvent.getMessage().getContentDisplay();
        messageSent = messageSent.substring(5, messageSent.length());

        List<Result> results = new ArrayList<>();

        if (messageSent.length() == 0) {
            commandEvent.getChannel().sendMessage("I found nothing to help you with").queue();
            return;
        }

        try {
            results = GoogleTesting.searchOnStackOverflow(messageSent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // I set the standard number of earch results to 3.
        int numberOfResults = results.size();
        if (numberOfResults > 3)
            numberOfResults = 3;

        if (numberOfResults != 0) {
            commandEvent.getChannel().sendMessage("Your top " + numberOfResults + " results are: ").queue();
            for (int i = 0; i < numberOfResults; i++) {
                commandEvent.getChannel().sendMessage(results.get(i).getTitle() + "\n" + results.get(i).getLink()).queue();
            }
        } else commandEvent.getChannel().sendMessage("I found nothing to help you with.").queue();

    }
}
