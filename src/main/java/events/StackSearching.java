package events;

import com.google.api.services.customsearch.model.Result;
import info.uaic.GoogleTesting;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class StackSearching extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String messageSent = event.getMessage().getContentRaw();

        if (messageSent.startsWith("!pa stack")) {

            messageSent = messageSent.substring(10, messageSent.length());
            List<Result> results = new ArrayList<>();
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
                event.getChannel().sendMessage("Your top " + numberOfResults + " results are: ").queue();
                for (int i = 0; i < numberOfResults; i++) {
                    event.getChannel().sendMessage(results.get(i).getTitle() + " " + results.get(i).getLink()).queue();
                }
            } else event.getChannel().sendMessage("I found nothing to help you with.").queue();
        }
    }
}
