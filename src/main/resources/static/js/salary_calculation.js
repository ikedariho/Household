/**
 * 
 */

$(function() {

	$(".Salary").keyup(function() {
		var manText = $('input[name=manSalary]').val();
		var manSalary = Number(manText) || 0; // NaNのとき0にする
		console.log(manSalary);
		var womanText = $('input[name=womanSalary]').val();
		var womanSalary = Number(womanText) || 0; // NaNのとき0にする
		console.log(womanSalary);

		var totalSalary = (manSalary + womanSalary);
		$("#totalSalary").text(totalSalary.toLocaleString());
		console.log(totalSalary);

	});

});