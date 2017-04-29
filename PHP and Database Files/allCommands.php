<?php

$con=mysqli_connect("localhost","root","","nfc_attendance");

if (mysqli_connect_errno($con)) 
{
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// $usr = $_POST['username'];
// $pss = $_POST['password'];
// $person = $_POST['type'];

// $sql = "SELECT * FROM student where Username = \"".$usr."\" AND Password = \"".$pss."\"";

// //$sql = "SELECT * FROM student";

// //echo $sql;

// $result = mysqli_query($con,$sql);


// $row = mysqli_fetch_row($result);


// if($row)
// {
// 	echo $row[1];
// }
// else
// {
// 	echo "no";
// }

// mysqli_free_result($result);

$command = $_POST['commandID'];

switch ($command) {
	
	//Login for Student and Professor
	case "0":
			$usr = $_POST['username'];
			$pss = $_POST['password'];
			$person = $_POST['type'];


			if($person == "S")
			{
				$sql = "SELECT * FROM student where Username = \"".$usr."\" AND Password = \"".$pss."\"";

				$result = mysqli_query($con,$sql);
				$row = mysqli_fetch_row($result);

				if($row)
				{
					echo $row[1];
				}
				else
				{
					echo "no";
				}

				mysqli_free_result($result);
			}
			else
			{
				$sql = "SELECT * FROM professor where Username = \"".$usr."\" AND Password = \"".$pss."\"";

				$result = mysqli_query($con,$sql);
				$row = mysqli_fetch_row($result);

				if($row)
				{
					echo $row[1];
				}
				else
				{
					echo "no";
				}

				mysqli_free_result($result);
			}
		break;

	//get all courses of a student
	case "8":

		$studID = $_POST['studID'];

		$sql = $sql = "SELECT *FROM attendance where StudentID = \"".$studID."\"";

		$resultString = "";

		if($result = mysqli_query($con,$sql))
		{
		
			while($row = mysqli_fetch_row($result))
			{
				$currentString = $row[0];
				$resultString = $resultString."=".$currentString;
			}

			echo $resultString;

			mysqli_free_result($result);
		}
		break;

	//get all courses of a professor
	case '7':
			$profID = $_POST['profID'];

			$sql = "SELECT * FROM course WHERE ProfessorID = \"".$profID."\"";

			$resultString = "";

			if($result = mysqli_query($con,$sql))
			{
			
				while($row = mysqli_fetch_row($result))
				{
					$currentString = $row[1];
					$resultString = $resultString."=".$currentString;
				}

				echo $resultString;

				mysqli_free_result($result);
			}
		break;

	//get attendance of a student in a course
	case '5':

		$studID = $_POST['ID'];
		$courseID = $_POST['Course_ID']; 

		$sql = "SELECT * FROM attendance where StudentID = \"".$studID."\" AND CourseID = \"".$courseID."\"";

		$result = mysqli_query($con,$sql);
		$row = mysqli_fetch_row($result);

		if($row)
		{
			echo $row[2]."/".$row[3];
		}
		else
		{
			echo "NA";
		}

		mysqli_free_result($result);

		break;

	//get attendance of all students in a course
	case '6':

		$profID = $_POST['ID'];
		$courseID = $_POST['Course_ID']; 



		$sql = "SELECT * FROM attendance where CourseID = \"".$courseID."\"";

		$resultString = "";

		if($result = mysqli_query($con,$sql))
		{
		
			while($row = mysqli_fetch_row($result))
			{
				$currentString = $row[2]."/".$row[3]."  ".$row[1];
				$resultString = $resultString."=".$currentString;
			}

			echo $resultString;

			mysqli_free_result($result);
		}
		break;

	//Mark attendance of a student	
	case '4':

		$profID = $_POST['Prof_ID'];
		$courseID = $_POST['Course_ID']; 
		$studID = $_POST['Stud_ID'];

		$sql = "UPDATE attendance SET Attended = Attended + 1 WHERE StudentID = \"".$studID."\" AND CourseID = \"".$courseID."\"";

		mysqli_query($con,$sql);

		$chk = "SELECT * FROM attendance WHERE StudentID = \"".$studID."\" AND CourseID = \"".$courseID."\"";
		$chkresult = mysqli_query($con,$chk);
		$chkrow = mysqli_fetch_row($chkresult);

		if($chkrow)
		{
			$sql = "SELECT * FROM student WHERE ID = \"".$studID."\"";	
		}
		else
		{
			echo "NA - NA - NA - NA - NA";
			break;
		}
		

		$result = mysqli_query($con,$sql);
		$row = mysqli_fetch_row($result);

		if($row)
		{
			echo $row[0]."-".$row[1]."-".$row[2]."-".$row[3]."-".$row[4];
		}
		else
		{
			echo "NA";
		}

		mysqli_free_result($result);
		
		break;		

	//Increment number of classes
	case '3':
		$profID = $_POST['profID'];
		$courseID = $_POST['Course_ID']; 

		$sql = "UPDATE attendance SET Total = Total + 1 WHERE CourseID = \"".$courseID."\"";

		mysqli_query($con,$sql);

		$sql = "SELECT Total FROM attendance WHERE CourseID = \"".$courseID."\"";

		$result = mysqli_query($con,$sql);
		$row = mysqli_fetch_row($result);

		if($row)
		{
			echo "Number of Classes Increased to ".$row[0];
		}
		else
		{
			echo "NA";
		}

		mysqli_free_result($result);

	break;

	}

?>