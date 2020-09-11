package eu.yurama.buildffa.assets.impl;

public class InventorySort {

	private int slot_sword = 0;
	private int slot_rod = 1;
	private int slot_bow = 2;
	private int slot_arrow = 3;
	private int slot_sandstone = 7;
	private int slot_cobweb = 8;

	public InventorySort(String sort) {
		for (String item : sort.split(";")) {
			String[] args = item.split(":");

			switch (args[0]) {
			case "s":
				slot_sword = Integer.parseInt(args[1]);
				break;
			case "a":
				slot_rod = Integer.parseInt(args[1]);
				break;
			case "b":
				slot_bow = Integer.parseInt(args[1]);
				break;
			case "p":
				slot_arrow = Integer.parseInt(args[1]);
				break;
			case "c":
				slot_cobweb = Integer.parseInt(args[1]);
				break;
			case "sb":
				slot_sandstone = Integer.parseInt(args[1]);
				break;
			}
		}
	}

	public int getSlotSword() {
		return slot_sword;
	}

	public int getSlotRod() {
		return slot_rod;
	}

	public int getSlotBow() {
		return slot_bow;
	}

	public int getSlotArrow() {
		return slot_arrow;
	}

	public int getSlotCobweb() {
		return slot_cobweb;
	}

	public int getSlotSandstone() {
		return slot_sandstone;
	}

	@Override
	public String toString() {
		return "s:" + slot_sword + ";a" + slot_rod + ";b" + slot_bow + ";p" + slot_arrow + ";c" + slot_cobweb + ";sb"
				+ slot_sandstone;
	}

	public static String parseInventoryToString(int sword, int rod, int bow, int arrow, int cobweb, int sandstone) {
		return "s:" + sword + ";a:" + rod + ";b:" + bow + ";p:" + arrow + ";c:" + cobweb + ";sb:" + sandstone;
	}

}
