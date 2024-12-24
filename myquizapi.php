<?php
	$conn = mysqli_connect("localhost","root","","my_quiz_db");
	$stmt = $conn->prepare("SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct_option` FROM `math_quiz`");
	// execute the query
	$stmt->execute();
	//binding the results to the query
	//when the prepared statemnt is executed, the values from the selected columns will be stored in these variables
	$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);
	
	//Creating an empty array
	$questions_array = array();
	//Traversing through all the questions
	while($stmt->fetch()){
	//this array will hold the values for each column in the result set for the current row
		$temp = array();
		//this line assigns the value of the question variable to the question key in the temp array
		$temp['question'] = $question;
		$temp['option1'] = $option1;
		$temp['option2'] = $option2;
		$temp['option3'] = $option3;
		$temp['option4'] = $option4;
		$temp['correct_option'] = $correct_option;
		
		//this is a common approach for collecting data from a loop, when iterating through a database result set and storing it in an array 
		array_push($questions_array, $temp);
		
	}
	
	//displaying the results in JSON format
	echo json_encode($question_array);
	?>
