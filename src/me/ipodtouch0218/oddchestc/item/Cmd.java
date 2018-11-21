package me.ipodtouch0218.oddchestc.item;

public class Cmd {
	
	private String cmd;
	private double chance;
	
	public Cmd(String str, double chance) {
		this.cmd = str;
		this.chance = chance;
	}
	
	public String getCommand() {
		return cmd;
	}
	
	public double getChance() {
		return chance;
	}
}
