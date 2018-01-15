/**
 * Aktualizacja listy gier, do których użytkownik może dołączyć
 */
var preData = null;
var curData	= null;

var checkOptions = function(newOptions) {
	var $gamerList = $('#gamerList');
	$gamerList.children().remove();
	var option = $('<option></option>').attr("value", "new").text("Nowy");
	$gamerList.append(option);
	$.each(newOptions, function(key, val) {
		console.log("Key=" + key + " value=" + val);
		option = $('<option></option>').attr("value", key).text(val);
		$gamerList.append(option);
	});
}

var pullFormOptions = function() {
	$.getJSON("status/options", function(data) {
		console.log("Data length:" + data);
		if(data !== "test") {
			curData = JSON.stringify(data);
			if(preData !== curData) {
				checkOptions(data);
				preData = curData;
			}
		} 
	});
}

pullFormOptions();
setInterval(pullFormOptions, 3000);