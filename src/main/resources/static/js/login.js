/**
 * Aktualizacja listy, gier do których użytkownik może dołączyć
 */

var checkOptions = function(newOptions) {
	var defOption = $('<option></option>').attr("value", "new").text("Nowy");																	// option
	var $gameList = $("#gameList");
	$gameList.empty().append(defOption);
	$.each(newOptions, function(key, val) {
		var option = $('<option></option>').attr("value", key).text(value);
		$gameList.append(option);
	});
}

var pullFormOptions = function() {
	$.getJSON("status/options", function(data) {
		checkOptions(data)
	});
}

pullFormOptions();
setInterval(pullFormOptions, 3000);