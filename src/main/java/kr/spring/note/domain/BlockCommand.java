package kr.spring.note.domain;

public class BlockCommand {
	private String block; // ������ ���
	private String be_blocked; // ���� ���� ���
	 
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getBe_blocked() {
		return be_blocked;
	}
	public void setBe_blocked(String be_blocked) {
		this.be_blocked = be_blocked;
	}
	
	@Override
	public String toString() {
		return "BlockCommand [block=" + block + ", be_blocked=" + be_blocked + "]";
	}
}
