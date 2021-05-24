package TriviaDB;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;
import java.util.List;

public class UserDBCommand extends Command {
    public UserDBCommand() {
        this.name = "userDB";
        this.help = "adds all users in the db";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        commandEvent.getGuild().loadMembers();

        UserDAO userDAO = new UserDAOImpl();

        List<String> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for(Member member : commandEvent.getGuild().getMembers()){
            ids.add(member.getId());
            names.add(member.getEffectiveName());
            String id = member.getId();
            String name = member.getEffectiveName();
            System.out.println(id + " " + name);
            userDAO.addUser(id, name);
        }

        System.out.println("a adaugat cumetrii");
        commandEvent.getTextChannel().sendMessage("Am adaugat cumetrii").queue();
//        for(String s : members)
//        {
//            userDAO.addUser(s);
//        }
    }
}
