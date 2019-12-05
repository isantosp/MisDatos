<?php
	
	$id = $_GET['id'];

	$empleado = array('id' => $id, 
					'nombre' => 'Pepito Sanchez', 
					'empresa' => 'Carteros SA de CV');

	echo json_encode($empleado);


?>