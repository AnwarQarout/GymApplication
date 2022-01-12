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
		
		$sql = "SELECT w.name, w.steps, w.video from membership m, workout2membership w2m, workout w
WHERE
m.name = '" .$selectedMembership."'
AND
m.id = w2m.membership_id
AND
w2m.workout_id = w.id
ORDER BY rand()
Limit 5;
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