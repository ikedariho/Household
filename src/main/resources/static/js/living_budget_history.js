/**
 * 
 */
$(function() {
	$('[id^="pageNumber"]').on('click', function(event) {
		event.preventDefault();
		var text = $(this).text();
		$(this).val(text);
		var mae = `前へ `;
		var next = `次へ `;
		var param;
		console.log(text);
		console.log(mae);
		if (String(text) === mae) {
			param = parseInt($('#now').text()) - 1;
		} else if(String(text) === next){
			param = parseInt($('#now').text()) + 1;
		}else{
			param = parseInt($(this).text());
		}
		$('<input>').attr({
			'type' : 'hidden',
			'name' : 'pageNumber',
			'value' : param
		}).appendTo($('#form'));
		console.log(param);
		$('#form').submit();
		// }
		// var page = document.createElement('input');
		// page.setAttribute('type','hidden');
		// page.setAttribute('name','pageNumber');
		// page.setAttribute('value',$(this).text());
		// document.myForm.appendChild(page);
		// }
		// );
	});

});