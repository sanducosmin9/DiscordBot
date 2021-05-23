package music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class Play extends Command {

    public Play(){
        this.name = "play";
        this.help = "Plays the music of a youtube link yey";
    }

    @SuppressWarnings("ConstantConditions")
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

        String message = commandEvent.getMessage().getContentRaw();
        message = message.substring(6);

        if(!message.startsWith("https://www.youtube.com/watch")){
            textChannel.sendMessage("I cannot play that!");
            return;
        }

        PlayerManager.getInstance().loadAndPlay(textChannel, message);

    }
}
