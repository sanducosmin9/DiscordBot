package commands;

import com.google.api.services.customsearch.model.Result;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import info.uaic.GoogleTesting;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WikiSearching extends Command {

    public WikiSearching() {
        this.name = "wiki";
        this.help = "Searches in wikipedia for facts about your search query";
    }

//    @Override
//    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
//
//        String messageSent = event.getMessage().getContentRaw();
//
//        if(messageSent.startsWith("!pa wiki")) {
//
//            messageSent = messageSent.substring(8, messageSent.length());
//            List<Result> results = new ArrayList<>();
//            try {
//                results = GoogleTesting.searchOnWikipedia(messageSent);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            int numberOfResults = results.size();
//            if(numberOfResults > 3)
//                numberOfResults = 3;
//
//            if(numberOfResults != 0) {
//                event.getChannel().sendMessage("Your top 3 results are: ").queue();
//                for (int i = 0; i < 3; i++) {
//                    event.getChannel().sendMessage(results.get(i).getTitle() + " " + results.get(i).getLink()).queue();
//                }
//            } else event.getChannel().sendMessage("I found nothing to help you with").queue();
//        }
//        else if(messageSent.contains("wiki") && !event.getMember().getUser().isBot()){
//            event.getChannel().sendMessage("In order to use the overused wikipedia, you have to type in: !pa wiki [your search query]").queue();
//        }
//    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String messageSent = commandEvent.getMessage().getContentRaw();

        messageSent = messageSent.substring(5, messageSent.length());
        if (messageSent.length() == 0) {
            commandEvent.getChannel().sendMessage("I found nothing to help you with").queue();
            return;
        }
        List<Result> results = new ArrayList<>();
        try {
            results = GoogleTesting.searchOnWikipedia(messageSent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int numberOfResults = results.size();
        if (numberOfResults > 3)
            numberOfResults = 3;

        if (numberOfResults != 0) {
            commandEvent.getChannel().sendMessage("Your top 3 results are: ").queue();
            for (int i = 0; i < 3; i++) {
                commandEvent.getChannel().sendMessage(results.get(i).getTitle() + "\n" + results.get(i).getLink()).queue();
            }
        } else commandEvent.getChannel().sendMessage("I found nothing to help you with").queue();
    }
}
