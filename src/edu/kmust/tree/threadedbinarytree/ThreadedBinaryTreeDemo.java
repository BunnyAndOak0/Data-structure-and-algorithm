package edu.kmust.tree.threadedbinarytree;

/**
 * 进行线索化二叉树
* @author BunnyAndOak0
*
*/
public class ThreadedBinaryTreeDemo {
	public static void main(String[] args) {
	}
}

//定义ThreadedBinaryTree二叉树,实现了线索化功能的二叉树
class ThreadedBinaryTree {
	private HeroNode root;
	
	//为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
	//在递归进行线索化的时候，pre总是保留前一个结点
	private HeroNode pre = null; 
	

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	
	//编写对二叉树进行中序线索化的方法
	/**
	 * @param node 就是当前需要线索化的节点
	 */
	public void threadedNodes(HeroNode node) {
		//如果node == null，就不可以线索化
		if (node == null) {
			return;
		}
		//中序线索化
		//1.先线索化左子树
		threadedNodes(node.getLeft());
		//2.线索化当前节点(有难度)
		
		//处理当前节点的前驱节点
		if (node.getLeft() == null) {
			//当前节点的左指针指向前驱结点
			node.setLeft(pre);
			//修改当前节点的左指针的类型,指向前驱节点
			node.setLeftType(1);
		}
		
		//处理后继节点
		if (pre != null && pre.getRight() == null) {
			//让前驱节点的右指针指向当前节点
			pre.setRight(node);
			//修改前驱节点的右指针类型
			pre.setRightType(1) ;
		}
		//！！！每处理一个节点后，让当前节点是下一个结点的前驱结点
		pre = node;
		
		
		//3.再线索化右子树 
		threadedNodes(node.getRight());
		
	}
	
	//删除节点
	public void delNode(int no) {
		if (root != null) {
			//如果只有一个root节点，就立即判断是不是要删除的节点
			if (root.getNo() == no) {
				root = null;
			}else {
				//进行递归删除
				root.delNode(no);
			}
		}else {
			System.out.println("空树，不能删除");
		}
	}
	
	//前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		}else {
			System.out.println("二叉树为空，无法遍历...");
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		}else {
			System.out.println("二叉树为空，无法遍历...");
		}
	}
	
	//后续遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		}else {
			System.out.println("二叉树为空，无法遍历...");
		}
	}
	
	//前序遍历
	public HeroNode preOrderSearch(int no) {
		if (root !=null) {
			return root.preOrderSearch(no);
		}else {
			return null;
		}
	}
	
	//中序遍历
	public HeroNode infixOrderSearch(int no) {
		if (root != null) {
			return root.infixOrderSearch(no);
		}else {
			return null;
		}
	}
	
	//后序遍历
	public HeroNode postOrderSearch(int no) {
		if (root != null) {
			return root.postOrderSearch(no);
		}else {
			return null;
		}
	}
}

//创建HeroNode
class HeroNode{
	private int no;
	private String name;
	private HeroNode left;		//默认null
	private HeroNode right;		//默认null
	
	//说明：
	//1.如果leftType == 0表示指向的是左子树，如果为1表示指向前驱节点
	//2.如果rightType == 0表示指向的是右子树，如果为1表示指向后继节点
	private int leftType;
	private int rightType;
	
	public HeroNode(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
	
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	//递归删除节点
	// 1.如果删除的节点是叶子节点，则删除该节点
	// 2.如果删除的节点是非叶子节点，就删除该子树
	public void delNode(int no) {
		/**
		 * 思路：
		 * 1.因为我们的二叉树是单向的，所以我们判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
		 * 2.如果当前结点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null,并且就返回（结束递归删除）
		 * 3.如果当前结点的右子节点不为空，并且右子节点就是要删除的节点1，就将this.right = null,并且就返回（结束递归删除）
		 * 4.如果第2和第3步没有删除节点，那么就需要向左子树进行递归
		 * 5.如果第4步也没有删除节点，则应当向右子树进行递归删除
		 */
		
		//2.如果当前结点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null,并且就返回（结束递归删除）
		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		//3.如果当前结点的右子节点不为空，并且右子节点就是要删除的节点1，就将this.right = null,并且就返回（结束递归删除）
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		//4.如果第2和第3步没有删除节点，那么就需要向左子树进行递归
		if (this.left != null) {
			this.left.delNode(no);
		}
		//5.如果第4步也没有删除节点，则应当向右子树进行递归删除
		if (this.right != null) {
			this.right.delNode(no);
		}
	}
	
	//编写前序遍历的方法
	public void preOrder() {
		/**
		 * 先输出当前节点（初始的时候是root节点）
		 * 如果左子节点不为空，则递归继续前序遍历
		 * 如果右子节点不为空，则递归继续前序遍历
		 */
		System.out.println(this);	//先输出父节点
		//递归向左子树前序遍历
		if (this.left != null) {
			this.left.preOrder();
		}
		//递归向右子树前序遍历
		if (this.right != null) {
			this.right.preOrder();
		}
	}		
	
	//编写中序遍历的方法
	public void infixOrder() {
		//递归向左子树中序遍历
		if (this.left != null) {
			this.left.infixOrder();
		}
		
		//输出父节点
		System.out.println(this);
		
		//递归向右子树中序遍历
		
		if (this.right != null) {
			this.right.infixOrder();
		}
	}
	
	//编写后序遍历的方法
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		
		if (this.right != null) {
			this.right.postOrder();
		}
		
		System.out.println(this);
	}
	
	//前序遍历查找
	/**
	 * @param no 查找的no
	 * @return 如果找到就返回Node，如果没有找到返回null
	 */
	public HeroNode preOrderSearch(int no) {
		System.out.println("进行了前序遍历");
		//比较当前节点是不是
		if (this.no == no) {
			return this;
		}
		//1.判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
		//2.如果左递归前序查找，找到节点，则返回
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if (resNode != null) {	//说明作左子树上找到了
			return resNode;
		}
		//1.左递归前序查找，找到节点，则返回，否则继续判断，当前的结点的右子节点是否为空，如果不为空，则继续向右递归前序查找
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	
	//中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		//判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		System.out.println("执行了中序遍历");
		//如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点
		if (this.no == no) {
			return this;
		}
		//否则继续进行右递归的中序查找
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}
	
	//后序遍历查找
	public HeroNode postOrderSearch(int no) {
		//判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if (resNode != null) {		//说明左子树找到
			return resNode;
		}
		//如果左子树没有找到，则向右子树递归进行后序遍历查找
		if (this.right != null) {
			resNode = this.right.postOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		System.out.println("执行了后序遍历");
		//如果左右子树都没有找到，就比较当前节点是不是
		if (this.no == no) {
			return this;
		}
		return resNode;
	}
}