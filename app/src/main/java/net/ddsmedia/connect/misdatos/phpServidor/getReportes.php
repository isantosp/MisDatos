<?php
    
    $email = $_GET['email'];
    
    if(trim($email) != ''){
        $SQL = "SELECT * FROM reportes WHERE email = '$email' ORDER BY fecha DESC ";
		$conn = conectaDB();
		$q = $conn->prepare($SQL);
		$q->execute();
		$arrResult = $q->fetchAll();
    
        echo json_encode($arrResult);
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