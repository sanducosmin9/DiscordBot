package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServerInfo extends Command {

    public ServerInfo(){
        this.name = "serverinfo";
        this.help = "Gives info about the server";
    }


    @Override
    protected void execute(CommandEvent commandEvent) {

        commandEvent.getGuild().loadMembers();

        List<String> members = new ArrayList<>();

        for(Member member : commandEvent.getGuild().getMembers()){
            members.add(member.getEffectiveName());
        }


        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.BLUE);
        eb.setAuthor(commandEvent.getGuild().getName());
        eb.addField("Owner: ", commandEvent.getGuild().retrieveOwner().complete().getEffectiveName(), true);
        eb.setDescription("Members: -- " + members);
        eb.setFooter("Member count: " + members.size());

        commandEvent.getChannel().sendMessage(eb.build()).queue();

    }
}
