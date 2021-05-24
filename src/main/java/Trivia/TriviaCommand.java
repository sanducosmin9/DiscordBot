package Trivia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TriviaCommand extends Command implements EventListener {

    private static final String EMOJI1 = "\uD83C\uDDE6";
    private static final String EMOJI2 = "\uD83C\uDDE7";
    private static final String EMOJI3 = "\uD83C\uDDE8";
    private static final String EMOJI4 = "\uD83C\uDDE9";
    EventWaiter waiter;

    public TriviaCommand(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "trivia";
        this.help = "Starts a trivia quiz.";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {

        // * fac rost de o intrebare
        Result result = new Result();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            result = objectMapper.readValue(new URL("https://opentdb.com/api.php?amount=1&type=multiple"), Result.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * adaug raspunsurile intr o lista pe care urmeaza sa o amestec
        List<String> answers = new ArrayList<>();

        answers.add(result.results.get(0).getCorrect_answer());
        for (int i = 0; i < 3; i++)
            answers.add(result.results.get(0).getIncorrect_answers().get(i));

        // * amestec lista
        Collections.shuffle(answers);

        final TextChannel channel = commandEvent.getTextChannel();

        EmbedBuilder eb = getEmbedBuilder(result, answers);

        Result finalResult = result;
        channel.sendMessage(eb.build())
                .queue((message) -> {
                            message.addReaction(EMOJI1).queue();
                            message.addReaction(EMOJI2).queue();
                            message.addReaction(EMOJI3).queue();
                            message.addReaction(EMOJI4).queue();

                            this.waiter.waitForEvent(
                                    GuildMessageReactionAddEvent.class,
                                    (e) -> e.getMessageIdLong() == message.getIdLong() && !e.getUser().isBot(),
                                    (e) -> {
                                        // * verificare raspuns cred
                                        String emoji = e.getReactionEmote().getName();
                                        if (emoji.equals("\uD83C\uDDE6")) // * a
                                            if (checkAnswer(finalResult.results.get(0).getCorrect_answer(), answers.get(0)))
                                                channel.sendMessage("Raspuns corect").queue();
                                            else
                                                channel.sendMessage("Raspuns gresit").queue();
                                        if (emoji.equals("\uD83C\uDDE7")) // * b
                                            if (checkAnswer(finalResult.results.get(0).getCorrect_answer(), answers.get(1)))
                                                channel.sendMessage("Raspuns corect").queue();
                                            else
                                                channel.sendMessage("Raspuns gresit").queue();
                                        if (emoji.equals("\uD83C\uDDE8")) // * c
                                            if (checkAnswer(finalResult.results.get(0).getCorrect_answer(), answers.get(2)))
                                                channel.sendMessage("Raspuns corect").queue();
                                            else
                                                channel.sendMessage("Raspuns gresit").queue();
                                        if (emoji.equals("\uD83C\uDDE9")) // * d
                                            if (checkAnswer(finalResult.results.get(0).getCorrect_answer(), answers.get(3)))
                                                channel.sendMessage("Raspuns corect").queue();
                                            else
                                                channel.sendMessage("Raspuns gresit").queue();
                                    },
                                    20L, TimeUnit.SECONDS,
                                    () -> {
                                        channel.sendMessage("De ce iti ia asa de mult sa gandesti LMAO").queue();
                                    }
                            );
                        }
                );
    }

    private Boolean checkAnswer(String correct, String answer)
    {
        return correct.equals(answer);
    }

    @NotNull
    private EmbedBuilder getEmbedBuilder(Result result, List<String> answers) {
        EmbedBuilder eb = new EmbedBuilder();

        if (result.results.get(0).getDifficulty().equals("easy"))
            eb.setColor(Color.GREEN);
        if (result.results.get(0).getDifficulty().equals("medium"))
            eb.setColor(Color.yellow);
        if (result.results.get(0).getDifficulty().equals("hard"))
            eb.setColor(Color.red);

        System.out.println(result.results.get(0).getCorrect_answer());

        eb.setTitle("Question");
        eb.setDescription(result.results.get(0).getQuestion());
        eb.addField("a.", answers.get(0), true);
        eb.addField("b.", answers.get(1), true);
        eb.addBlankField(false);
        eb.addField("c.", answers.get(2), true);
        eb.addField("d.", answers.get(3), true);

        eb.setFooter(result.results.get(0).getDifficulty());
        return eb;
    }
}
