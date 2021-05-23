package music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class Join extends Command {

    public Join(){
        this.name = "join";
        this.help = "This makes the bot join a voice channel";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        // iau text channel
        TextChannel textChannel = commandEvent.getTextChannel();

        // iau bot-ul
        Member bot = commandEvent.getSelfMember();

        // iau voice state-ul bot-ului
        GuildVoiceState botVoiceState = bot.getVoiceState();

        //verific daca e intr-un voice channel
        if(botVoiceState.inVoiceChannel()) {
            textChannel.sendMessage("I'm busy. Leave me alone!").queue();
            return;
        }

        // iau voice state-ul user-ului
        Member user = commandEvent.getMember();
        GuildVoiceState userVoiceState = user.getVoiceState();

        // verific daca e intr-un voice channel
        if(!userVoiceState.inVoiceChannel()){
            textChannel.sendMessage("You call me when you're not even in a voice channel? I'll tear you apart!").queue();
            return;
        }

        // iau audio manager-ul
        AudioManager audioManager = commandEvent.getGuild().getAudioManager();

        // iau voice channel-ul in care se afla user-ul
        VoiceChannel userChannel = userVoiceState.getChannel();

        // conectez bot-ul la acel voice channel
        audioManager.openAudioConnection(userChannel);
    }
}
