## 思考
题目要求返回closest target的sum。假设target = 5， 有可能第一次只能找到sum为3， 第二次sum为7，显然此时应该保留第一次为sum。
需要用到variable去记录可能的sum candidate，再根据math.abs(target - sum) 更新sum candidate。
note: You may assume that each input would have exactly one solution.


## summary
这个题是3sum 的变种， 但是自己没有轻松的完成。有2个原因：
* 没有完全吃透题目要求，题目要求是返回一个数字，closest target的sum即可。不需要像3sum那样返回triplets。
* 自己想了很久，于是看了网上其他人的solution （solution是错的），于是把自己带入另外一个黑洞。

吸取教训啊！一定要在自己深度思考（至少自问自答3次，至少一次write down思考，至少3天）后，直接在leetcode上看高票答案（正确性有保障）即可。

## 坑
一直困扰我的问题是：left 和 right 指针的移动，sum就会改变，如何判断这个sum离target最近呢？
其实，答案很简单，用variable来记录。每次指针移动就会更新sum。需要有一个全局变量，来保存closest target的sum值。必须穷尽所有的triplets来得到global最优解

其次，心里一直有个很奇怪的执念：two pointers必须在某个条件下就停止移动，不要穷尽所有triplets就能得到结果，这样才能减少时间复杂度。这简直是自己给自己挖坑。题目并没有要求时间复杂度低于O(n^2)。 此时，发散思维真要命！
