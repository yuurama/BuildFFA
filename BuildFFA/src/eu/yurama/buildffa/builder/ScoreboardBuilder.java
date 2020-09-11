package eu.yurama.buildffa.builder;

import java.util.ArrayList;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class ScoreboardBuilder {

	private ArrayList<String> scores;
	private String title;

	public ScoreboardBuilder(String title, ArrayList<String> scores) {
		this.scores = scores;
		this.title = title;
	}

	@SuppressWarnings("unused")
	public void unshowBoard(Player player) {

		Scoreboard scoreboard = new Scoreboard();
		ScoreboardObjective obj = scoreboard.registerObjective("zagd", IScoreboardCriteria.b);
		obj.setDisplayName(this.title);
		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
		ScoreboardScore a1 = new ScoreboardScore(scoreboard, obj, "§8");
		a1.setScore(13);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardScore pa1 = new PacketPlayOutScoreboardScore(a1);
		sendPacket(removePacket, player);
	}

	public void showBoard(Player player) {
		Scoreboard scoreboard = new Scoreboard();
		ScoreboardObjective obj = scoreboard.registerObjective("zagd", IScoreboardCriteria.b);
		obj.setDisplayName(this.title);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

		int count = scores.size();
		boolean done = false;

		for (String score : scores) {
			ScoreboardScore s = new ScoreboardScore(scoreboard, obj, score);
			s.setScore(count);

			PacketPlayOutScoreboardScore ps = new PacketPlayOutScoreboardScore(s);

			if (!done) {
				done = true;
				sendPacket(removePacket, player);
				sendPacket(createPacket, player);
				sendPacket(display, player);
			}

			sendPacket(ps, player);

			count--;
		}
	}

	@SuppressWarnings("rawtypes")
	private static void sendPacket(Packet packet, Player player) {
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

	}
}