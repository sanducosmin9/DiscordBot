package events;

import com.google.api.services.customsearch.model.Result;
import info.uaic.GoogleTesting;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WikiSearching extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        String messageSent = event.getMessage().getContentRaw();

        if(messageSent.startsWith("!pa wiki")) {

            messageSent = messageSent.substring(8, messageSent.length());
            List<Result> results = new ArrayList<>();
            try {
                results = GoogleTesting.searchOnWikipedia(messageSent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int numberOfResults = results.size();
            if(numberOfResults > 3)
                numberOfResults = 3;

            if(numberOfResults != 0) {
                event.getChannel().sendMessage("Your top 3 results are: ").queue();
                for (int i = 0; i < 3; i++) {
                    event.getChannel().sendMessage(results.get(i).getTitle() + " " + results.get(i).getLink()).queue();
                }
            } else event.getChannel().sendMessage("I found nothing to help you with").queue();
        }
    }

}
