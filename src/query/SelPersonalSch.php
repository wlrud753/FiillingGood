<?php


include('connect.php');

$id = $_GET['id'];

#groupname, role
if($result = $mysqli->query("select * from personalschedule where memberid = \"{$id}\"")){
	if($result->num_rows){
		for($count = 0; $count < $result->num_rows; $count++){
			$row = mysqli_fetch_array($result);
			printf("%s#%s#%s#%s#%s#%s#%d<br>", 
				$row[1], $row[2], $row[3], $row[4], $row[5], $row[6], $row[7]);
		}
		#$result->free();
	} else{
	echo "결과 없음";
	}
} else{
	echo "결과 없음";
}

$mysqli->close();