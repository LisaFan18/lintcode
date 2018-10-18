## 思考
题目要求返回closest target的sum。假设target = 5， 有可能第一次只能找到sum 为3， 第二次sum为7，显然此时应该保留第一次为tmp。
需要用到variable去记录可能的sum candidate，再根据math.abs(target - sum) 更新sum candidate。
note: You may assume that each input would have exactly one solution.


## summary
这个题目是3sum 的变种题， 但是自己没有轻松的完成。有两个原因：
* 没有完全吃透题目要求，题目要求是返回一个数字，closest target的sum即可。不需要像3sum那样返回triplets。
* 自己想了很久，于是看了网上其他人的solution （solution是错的），于是把自己带入另外一个黑洞。
* 吸取教训啊！一定要在自己深度思考（至少自问自答3次，至少一次write down思考，至少3天）后，直接在leetcode上看高票答案（正确性有保障）即可。
