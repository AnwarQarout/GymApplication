<?php


	
	$enteredUsername = "";
	$enteredPassword = "";
	if(isset($_GET['enteredUsername']) && isset($_GET['enteredPassword'])) {
		$enteredUsername = $_GET['enteredUsername'];
		$enteredPassword = $_GET['enteredPassword'];
	}
	if(!empty($enteredUsername) && !empty($enteredPassword)){
		$server_name = "localhost";
		$username = "root";
		$password = "";
		$dbname = "gym";
		
		$conn = new mysqli($server_name, $username, $password, $dbname);
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		} 
		
		$sql = "select * from manager where user_name = '" . $enteredUsername . "' AND password = '". $enteredPassword . "'" ;
		$result = $conn->query($sql);
		if ($result->num_rows > 0){
			echo "manager";
			return true;
		}
		
		$sql = "select * from employee where user_name = '" . $enteredUsername . "' AND password = '". $enteredPassword . "'" ;
		$result = $conn->query($sql);
		if ($result->num_rows > 0){
			echo "employee";
			return true;
		}
		
		$sql = "select * from member where user_name = '" . $enteredUsername . "' AND password = '". $enteredPassword . "'" ;
		$result = $conn->query($sql);
		if ($result->num_rows > 0){
			echo "user";
			return true;
		}
		
		else {
			echo "0 results";
			echo "false";
		}
		
		$conn->close();
		
	}
	
	




?>