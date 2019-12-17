/**
 * 
 */
$(function() {
	var i = 3;
	$("#add_category_btn").click(function() {
		if(i<=10){
			if(i<10){
				$("#category_ul").append("\<li\>項目" + i + "&nbsp;&nbsp;&nbsp;\<input type=&quot;text&quot; name=categoryNameList\>  予算 \<input type=&quot;text&quot; name=budgetList\>\<\/li\>");
			}else{
				$("#category_ul").append("\<li\>項目" + i + "&nbsp;\<input type=&quot;text&quot; name=categoryNameList\>  予算 \<input type=&quot;text&quot; name=budgetList\>\<\/li\>");
			}
			i++;
			}else{
				alert('これ以上項目を追加できません');
			}
			});
});
