/**
 * 
 */
$(function() {
	var i = 3;
	$("#add_category_btn").click(function() {
		if(i<7){
			$("#category_ul").append("\<li\>項目" + i + "&nbsp;&nbsp;&nbsp;<input type=text name=categoryNameList\>  予算 \<input type=text name=budgetList class=budget\>\<\/li\>");
			i++;
		}else{
			alert('これ以上項目を追加できません');
		}
	});
	
	$("#calculatePocketMoney").on('click',function(){
		var numberOfPersons = 2;
		var savingsRate = 0.5;
		var manSalary = parseInt($("#manSalary").text());
		var womanSalary = parseInt($("#womanSalary").text());
		var totalSalary = manSalary+womanSalary;
		var manPocketMoney = 0;
		var womanPocketMoney = 0;
		var totalBudget = 0;
		var sabings = 0;
		var kozukai = 0;
		var manDistribution = manSalary / womanSalary;
		$(".budget").each(function(index,element){
			if(!$(this).val()){
			var budget=0;
			}else{
			var budget=parseInt($(this).val());		
			}
			totalBudget+=budget;
		});
		var extraBudget = totalSalary - totalBudget;
		console.log(extraBudget);
		var totalPocketMoney = Math.floor(extraBudget * savingsRate/1000)*1000; // 1000の位で切り捨て
		var savings = extraBudget -extraBudget*savingsRate +(extraBudget -extraBudget*savingsRate)-totalPocketMoney ;
		manPocketMoney = totalPocketMoney * manDistribution / (manDistribution + 1);
		var manPocketMoneyAfterTruncation = Math.floor(manPocketMoney/1000)*1000;
		var womanPocketMoney = Math.ceil((totalPocketMoney - manPocketMoneyAfterTruncation)/1000)*1000; 
		$("#manPocketMoney").val(manPocketMoneyAfterTruncation);
		$("#womanPocketMoney").val(womanPocketMoney);
		$("#savings").val(savings);
	});
	
	＄("＃searchLatestBudget").on('click',function(){
		$.getJSON('http://localhost:8080/registerBudget/latestBudget?userId=?',
		{
			userId:$('#userId').val()
		}		
	)
	.done(function(data){
		if(data.result){
			
			
			
			
		}
		
		
	}
			
	)
		
		
		
		
	})
	
	
	
	
	
	
	
	
});
