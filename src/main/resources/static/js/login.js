/**
 * Aktualizacja listy, gier do których użytkownik może dołączyć
 */
var preData = null;
var curData	= null;

var checkOptions = function(newOptions) {
	$('#gameList').children('option:not(:first)').remove();
	$.each(newOptions, function(key, val) {
		console.log("Key=" + key + " value=" + val);
		option = $('<option></option>').attr("value", key).text(val);
		$("#gameList").append(option);
	});
}

var pullFormOptions = function() {
	$.getJSON("status/options", function(data) {
		curData = JSON.stringify(data);
		if(preData !== curData) {
			checkOptions(data);
			preData = curData;
		}
	});
}

pullFormOptions();
setInterval(pullFormOptions, 5000);