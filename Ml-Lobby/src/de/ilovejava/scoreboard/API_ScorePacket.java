package de.ilovejava.scoreboard;

import java.util.ArrayList;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_12_R1.IScoreboardCriteria;
import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_12_R1.Scoreboard;
import net.minecraft.server.v1_12_R1.ScoreboardObjective;
import net.minecraft.server.v1_12_R1.ScoreboardScore;

public class API_ScorePacket {
	Player a;
	CraftPlayer cp;
	Scoreboard board;
	ScoreboardObjective obj;
	PacketPlayOutScoreboardObjective packetObjective;
	PacketPlayOutScoreboardObjective removeObjective;
	PacketPlayOutScoreboardDisplayObjective displayObjective;
	ArrayList<Packet<?>> Scores = new ArrayList<>();
	public API_ScorePacket(Player p) {
		a = p;
		setPackets();
	}
	
	public void setPackets() {
		this.board = new Scoreboard();
		this.obj = this.board.registerObjective("§bMingoland.de", IScoreboardCriteria.b);
		this.packetObjective = new PacketPlayOutScoreboardObjective(this.obj, 0);
		this.removeObjective = new PacketPlayOutScoreboardObjective(this.obj, 1);
		this.displayObjective = new PacketPlayOutScoreboardDisplayObjective(1, this.obj);
		createObjects();
	}
	
	public void createObjects() {
		this.obj.setDisplayName("§c§o§lMingoland.de");
		sendScoreBoard();
	}
	
	public void sendScoreBoard() {
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§f",16)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§b§o§lName:",15)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§f"+this.a.getName(),14)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "  ",13)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§b§o§lForum:",12)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§f§fMingoland.de",12)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "   ",10)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§b§o§lTeamspeak",9)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§fMingoland.de",8)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "    ",7)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§b§o§lServer:",6)));
		Scores.add(new PacketPlayOutScoreboardScore(getScore(this.board, this.obj, "§fLobby",5)));
		
		sendPacket(removeObjective);
		sendPacket(packetObjective);
		sendPacket(displayObjective);
		sendPackets(Scores);
	}
	
	public void sendPacket(Packet<?> pack) {
		cp = ((CraftPlayer)a);
		cp.getHandle().playerConnection.sendPacket(pack);
	}
	
	public void sendPackets(ArrayList<Packet<?>> packets) {
		for(Packet<?> p : packets) {
			cp.getHandle().playerConnection.sendPacket(p);
		}
	}
	
	public void removeScoreBoard() {
		sendPacket(removeObjective);
	}
	
	public ScoreboardScore getScore(Scoreboard board, ScoreboardObjective obj, String str, Integer size) {
		ScoreboardScore cs = new ScoreboardScore(board, obj, str);
		cs.setScore(size);
		return cs;
	}
	
	public Packet<?> getPacket(ScoreboardScore sc){
		PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(sc);
		return packet;
	}
	
}
