/**
 * 
 */
function gfn_isNull(inputTxt) {
	if(inputTxt == null || inputTxt == 'undefined') {
		return true;
	}
	
	return false;
}


function gfn_trim(inputTxt) {
	if(inputTxt == null || inputTxt == '') {
		return '';
	}

	return inputTxt.trim();
}
 
function gfn_alert(inputTxt) {
	alert(inputTxt);
}