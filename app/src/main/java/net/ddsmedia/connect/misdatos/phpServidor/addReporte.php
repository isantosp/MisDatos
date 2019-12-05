<?php



$nombre = $_POST['nombre'];
$email = $_POST['email'];
$telefono = $_POST['telefono'];
$reporte = $_POST['reporte'];
$geo = $_POST['geo'];


if($nombre != '' && $email != '' && $reporte != ''){
    $SQL = "INSERT INTO reportes (nombre, email, telefono, reporte, fecha, geo) VALUES ('$nombre','$email','$telefono','$reporte',NOW(),'$geo') ";
			$conn = conectaDB();

			$q = $conn->prepare($SQL);
			$q->execute();
    $id = $conn->lastInsertId();


    echo json_encode(array('error' => false, 'id' => $id));
}else{
    echo json_encode(array('error' => true, 'id' => 0));
}


	function conectaDB(){
		$DB_IP = 'SERVIDOR'; // IP, dominio, o localhost si esta la bd en el mismo
		$DB_USER = 'USUARIO_DE_BD';
		$DB_PASS = 'PASSWORD_DE_BD';
		$DB_NAME = 'NOMBRE_BD';

		$dsn = 'mysql:host=localhost;dbname='.$DB_NAME;

		try {
	    	$conn = new PDO($dsn,$DB_USER,$DB_PASS);
	    	return($conn);
    
		} catch (PDOException $e) {
        	print "  <p>Error: No puede conectarse con la base de datos.</p>\n\n";
        	print "  <p>Error: " . $e->getMessage() . "</p>\n";
        	exit();
		}

	}
	
?>