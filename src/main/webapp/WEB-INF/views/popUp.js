function popUp(url){
	var newwindow;
	newwindow = window.open(url, 'name', 'height=1000, width=800');
	if (window.focus) {
		newwindow.focus()
	}	
}

function disable(){
	var button = document.getElementById("test-btn");
	if(button.disable){
		button.disable = false;
	}else{
		button.disable = true;
	}
}