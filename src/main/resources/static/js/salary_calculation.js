/**
 * 
 */

$(function() {
	calc_price();
	$(".womanSalary").on("input", function() {
		calc_price();
	});

	$(".manSalary").on("input", function() {
		calc_price();
	});

	function calc_price() {
		var manText = $('.manSalary [name=manSalary]').val();
		var manSalary = Number(manText) || 0; // NaNのとき0にする
		var womanText = $('.womanSalary [name=womanSalary]').val();
		var womanSalary = Number(manText) || 0; // NaNのとき0にする

		var totalSalary = (manSalary + womanSalary);
		$("#totalSalary").text(price.toLocaleString());
	}
	;

});/**
	 * 
	 */
