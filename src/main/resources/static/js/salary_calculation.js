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

	var date = new Date();

	var yyyy = date.getFullYear();
	var mm = ("0" + (date.getMonth() + 1)).slice(-2);
	var dd = ("0" + date.getDate()).slice(-2);

	document.getElementById("deliveryTime").value = yyyy + '-' + mm + '-' + dd;

});