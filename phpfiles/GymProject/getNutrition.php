<?php


	
	$selectedMembership = "";
	if(isset($_GET['selectedMembership'])) {
		$selectedMembership = $_GET['selectedMembership'];
	}
	if(!empty($selectedMembership)){
		$server_name = "localhost";
		$username = "root";
		$password = "";
		$dbname = "gym";
		
		$conn = new mysqli($server_name, $username, $password, $dbname);
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		} 
		
		$sql = "(select name, type, image from nutrition_programs where membership = '" . $selectedMembership . "' AND type = 'breakfast'
		ORDER BY RAND() LIMIT 1)
		UNION
		(select name, type, image from nutrition_programs where membership = '" . $selectedMembership . "' AND type = 'lunch'
		ORDER BY RAND() LIMIT 1)
		UNION
		(select name, type, image from nutrition_programs where membership = '" . $selectedMembership . "' AND type = 'dinner'
		ORDER BY RAND() LIMIT 1);
		";
		$result = $conn->query($sql);
		$resultarray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$resultarray[] = $row;
		}
		echo json_encode($resultarray);
		
		
		$conn->close();
		
	}
	
	




?>