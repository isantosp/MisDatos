<?php
	
	$id = $_GET['id'];

	$empleado1 = array('id' => 3, 
					'nombre' => 'Pepito Sanchez', 
					'empresa' => 'Carteros SA de CV');
	$empleado2 = array('id' => 5, 
					'nombre' => 'Maria Casas', 
					'empresa' => 'Churreria 3 Hermanas');
					
	$empleado3 = array('id' => 6, 
					'nombre' => 'Pepe Pecas', 
					'empresa' => 'Colchones Como2');
					
	$empleados = array();
	$empleados[] = $empleado1;
	$empleados[] = $empleado2;
	$empleados[] = $empleado3;

	echo json_encode($empleados);


?>