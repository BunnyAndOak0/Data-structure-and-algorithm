package edu.kmust.LinkedList;

/**
 * 鍙屽悜閾捐〃
 * @author BunnyAndOak0
 */
public class DoubleLinkedListDemo {
	public static void main(String[] args) {

	}
}

//鍒涘缓涓�涓弻鍚戦摼琛ㄧ殑绫�
class DoubleLinkedList {
	//鍒濆鍖�
	private HeroNode2 head = new HeroNode2(0, "", "");
	
	//杩斿洖澶磋妭鐐�
	public HeroNode2 getHead() {
		return head;
	}
	
	//閬嶅巻鍙屽悜閾捐〃鐨勬柟娉�
	public void list() {
		//鍒ゆ柇閾捐〃鏄惁涓虹┖
		if (head.next == null) {
			System.out.println("閾捐〃涓虹┖");
			return;
		}
		
		HeroNode2 temp = head.next;
		while (true) {
			if (temp == null) {
				break;
			}
			//杈撳嚭鑺傜偣淇℃伅
			System.out.println(temp);
			//灏唗emp鍚庣Щ锛屼竴瀹氬皬蹇�
			temp = temp.next;
		}
	}
	
	//娣诲姞涓�涓妭鐐瑰埌鍙屽悜閾捐〃鐨勬渶鍚�
	public void add(HeroNode2 hersNode2) {
		//鍒涘缓涓�涓緟鍔╄妭鐐�
		HeroNode2 temp = head;
		//閬嶅巻閾捐〃锛屾壘鍒版渶鍚�
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		//閫�鍑簑hile鐨勬椂鍊欙紝temp灏辨寚鍚戠殑鏄摼琛ㄧ殑鏈�鍚�
		//灏嗘渶鍚庤繖涓妭鐐圭殑next鎸囧悜鏂扮殑鑺傜偣
		//褰㈡垚鍙屽悜閾捐〃
		temp.next = hersNode2;
		hersNode2.pre = temp;
	}
	
	//淇敼涓�涓妭鐐圭殑鍐呭
	public void update(HeroNode2 heroNode2) {
		//鍒ゆ柇鏄惁涓虹┖
		if (head.next == null) {
			System.out.println("閾捐〃涓虹┖鍟�");
			return;
		}
		//鎵惧埌闇�瑕佷慨鏀圭殑鑺傜偣锛屾牴鎹畁o缂栧彿
		//瀹氫箟涓�涓緟鍔╁彉閲�
		HeroNode2 temp = head.next;
		boolean flag = false;		//鏄惁鎵惧埌璇ヨ妭鐐�
		while (true) {
			if (temp == null) {
				break;		//閬嶅巻涓洪摼琛�
			}
			if (temp.no == heroNode2.no) {
				//鎵惧埌浜�
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) {
			temp.name = heroNode2.name;
			temp.nickname = heroNode2.nickname;
		}else {
			//娌℃湁鎵惧埌
			System.out.printf("娌℃湁鎵惧埌缂栧彿%d鐨勮妭鐐癸紝涓嶈兘淇敼", heroNode2.no);
		}
	}
	
	//从双向链表中删除一个节点
	//1.对于双向链表 可以直接找到要删除的这个节点
	//2.找到后自我删除就好
	public void del(int no) {
		//判断当前链表是否为空
		if (head.next == null) {
			System.out.println("链表为空，不能删除");
			return;
		}
		HeroNode2 temp = head.next;    //辅助变量（指针）
		boolean flag = false;  //已经到链表的最后
		
		while (true) {
			if (temp == null) {
				break;
			}
			
			if (temp.no == no) {
				//找到的待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//判断flag
		if (flag) {
			//可以删除
			temp.pre.next = temp.next;
			//如果是最后一个节点就不需要执行  否则会出现空指针异常
			if (temp.next != null) {
				temp.next.pre = temp.pre; 
			}
		}else {
			System.out.printf("要删除的%d节点不存在\n", no);
		}
	}
	
}

//瀹氫箟HeroNode2锛屾瘡涓狧eroNode瀵硅薄灏辨槸涓�涓妭鐐�
class HeroNode2 {
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;		//鎸囧悜涓嬩竴涓妭鐐� 榛樿涓簄ull
	public HeroNode2 pre;		//鎸囧悜鍓嶄竴涓妭鐐� 榛樿涓簄ull
	
	public HeroNode2(int no, String name, String nickname) {
		super();
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
	}
}