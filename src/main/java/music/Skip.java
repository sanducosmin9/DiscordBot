package music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class Skip extends Command {

    public Skip() {
        this.name = "skip";
        this.help = "Skips the current track";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        TextChannel textChannel = commandEvent.getTextChannel();
        Member selfMember = commandEvent.getSelfMember();
        GuildVoiceState botVoiceState = selfMember.getVoiceState();

        if(!botVoiceState.inVoiceChannel()){
            textChannel.sendMessage("I'm not in a voice channel!").queue();
            return;
        }

        Member user = commandEvent.getMember();
        GuildVoiceState userVoiceState = user.getVoiceState();

        if(!userVoiceState.inVoiceChannel()){
            textChannel.sendMessage("You're not in a voice channel!").queue();
            return;
        }

        if(!userVoiceState.getChannel().equals(botVoiceState.getChannel())){
            textChannel.sendMessage("We are not in the same voice channel!").queue();
            return;
        }


        TrackScheduler scheduler = PlayerManager.getInstance().getMusicManager(textChannel.getGuild()).scheduler;

        PlayerManager.getInstance().loadAndPlay(textChannel, scheduler.nextTrack());


    }
}
