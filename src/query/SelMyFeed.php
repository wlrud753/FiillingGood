<?php


include('connect.php');

$name = $_GET['name'];
$date = $_GET['date'];
$start = $_GET['start'];
$feedmem = $_GET['feedmem'];

#groupname, role
if($result = $mysqli->query("select * from schedulefeeds
			where groupnamefk = \"{$name}\" and datefk = $date and starttimefk = $start and feedmem = \"$feedmem\"")){
	if($result->num_rows){
		for($count = 0; $count < $result->num_rows; $count++){
			$row = mysqli_fetch_array($result);
			printf("%s#%s", 
				$row[3], $row[4]);
		}
		#$result->free();
	} else{
	echo "결과 없음";
	}
} else{
	echo "결과 없음";
}

$mysqli->close();