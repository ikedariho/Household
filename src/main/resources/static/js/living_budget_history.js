/**
 * 
 */
$(function() {
	$('[id^="pageNumber"]').on('click', function() {
		var text = $(this).text();
		$(this).val(text);
		var mae = `前へ`;
		var param;
		console.log(text);
		if (text == mae) {
			param = parseInt($('#now').text()) - 1;
			console.log('maeが=だったときの値'+param);
		} else {
			alert('アラートです');
			param = parseInt($(this).text());
			console.log('maeが=じゃないときの値'+param);
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