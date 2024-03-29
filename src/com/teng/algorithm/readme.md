# 堆排序
> 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。

- 大顶堆
>每个结点的值都大于或等于其左右孩子结点的值

- 小顶堆
>每个结点的值都小于或等于其左右孩子结点的值

一般升序采用大顶堆，降序采用小顶堆

思想
- 1、将待排序序列构成一个大顶堆
- 2、此时，整个序列的最大值就是堆顶的根节点
- 3、将其与末尾元素进行交换，此时末尾就为最大值
- 4、然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。

# 霍夫曼树 Huffman Tree
基本介绍
> 给定n个权值作为n个叶子结点，构造一颗二叉树，若该树的带权路径长度(wpl)达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman Tree)

Huffman Tree是带权路径长度最短的树，权值较大的节点离根较近

Huffman Tree创建步骤
- 1）、从小到大进行排序，将每一个数据，每个数据都是一个节点，每个节点可以看成一个简单的二叉树
- 2）、取出根节点权值最小的两颗二叉树
- 3）、组成一颗新的二叉树，该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
- 4）、再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4的步骤，直到数列中，所有的数据都被处理，就得到一颗huffman tree

最佳实践
>数据压缩（生成赫夫曼编码和赫夫曼编码后的数据）

思路
- 1、Node{data(存放数据), weight(权值), left, right}
- 2、得到"字符串"对应的byte[]数组
- 3、编写一个方法，将准备构建huffman树的Node节点放到list中，形式[Node[date=97, weight=5], Node[date=32, weight=9]...]

# 二叉排序树
> 二叉排序树：BST:(Binary Sort(Search) Tree)， 对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。

二叉排序树 删除节点的三种情况

第一种情况：删除叶子节点

思路
- 1、需求先去找到要删除的结点 targetNode
- 2、找到targetNode的父节点parent(考虑是否存在父节点)
- 3、确定targetNode是parent的左子节点还是右子节点
- 4、根据前面的情况来对应删除

第一种情况没有子节点

- 左子节点 parent.left = null

- 右子节点 parent.right = null

第二种情况：删除只有一颗子树的节点 比如1

思路

（1）需求先找到要删除的结点 targetNode

（2）找到targetNode的父结点parent

（3）确定targetNode的子节点是右子节点还是左子节点
 
（4）targetNode是parent的左子节点还是右子节点

（5）如果targetNode有左子节点
    
- 如果targetNode是parent的左子节点 parent.left = target Node.left
- 如果targetNode是parent的右子节点 parent.right = targetNode.left 

（6）如果targetNode有右子节点

- 如果targetNode是parent的左子节点 parent.left = targetNode.right
- 如果targetNode是parent的右子节点 parent.left = targetNode.right

第三种情况：删除有两颗子树的节点

（1）需求先找到要删除的结点 targetNode

（2）找到targetNode的父结点parent

（3）从targetNode的右子树找到最小的结点

（4）用一个临时变量，将最小结点的值保存到temp

（5）删除该最小节点

（6）targetNode.value = temp

# 平衡二叉树

- 平衡二叉树也叫平衡二叉搜索树，可以保证查询效率。

- 特点 它是一颗空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一颗平衡二叉树。
