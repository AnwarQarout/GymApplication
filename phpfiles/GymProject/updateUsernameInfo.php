<?php


	
	$enteredUsername = "";
	$enteredPassword = "";
	$enteredName = "";
	$enteredPhone = "";
	$enteredHeight = "";
	$enteredWeight = "";
	if(isset($_POST['enteredUsername']) && isset($_POST['enteredPassword']) && isset($_POST['enteredName']) && isset($_POST['enteredPhone']) && isset($_POST['enteredHeight']) && isset($_POST['enteredWeight'])) {
		$enteredUsername = $_POST['enteredUsername'];
		$enteredPassword = $_POST['enteredPassword'];
		$enteredName = $_POST['enteredName'];
		$enteredPhone = $_POST['enteredPhone'];
		$enteredHeight = $_POST['enteredHeight'];
		$enteredWeight = $_POST['enteredWeight'];
	}
	
	$enteredName = str_replace("-"," ",$enteredName);
	if(!empty($enteredUsername) && !empty($enteredPassword) && !empty($enteredName) && !empty($enteredPhone) && !empty($enteredHeight) && !empty($enteredWeight)){
		$server_name = "localhost";
		$username = "root";
		$password = "";
		$dbname = "gym";
		
		$conn = new mysqli($server_name, $username, $password, $dbname);
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		} 
		
		$sql = "UPDATE member SET user_name = '" . $enteredUsername . "', password = '" . $enteredPassword . "', name = '" . $enteredName . "', phone = '" . $enteredPhone . "', height = '" . $enteredHeight . "', weight = '" . $enteredWeight . "' where user_name = '" . $enteredUsername . "'";
		if ($conn->query($sql) === TRUE){
			echo "record updated successfully!";
		}
		else {
			echo "Error updating record.";
		}
	
		$conn->close();
		
	}
	
	




?>