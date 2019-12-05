<?php

if(!isset($_POST['username'])){
    $arrRes = array('error' => true, 'mensaje' => 'Falta el usuario');
}else if(!isset($_POST['password'])){
    $arrRes = array('error' => true, 'mensaje' => 'Falta la contraseña');
}else{
    $user = $_POST['username'];
    $pass = $_POST['password'];
    
    $arrLogin = array('admin'   => 'admin123',
                    'user'      => 'password',
                    'root'      => 'toor',
                    'qwerty'    => '123456',
                    'usuario'   => 'contraseña'
                    );
    $login = array_search($pass, $arrLogin);
    
    if($login){
        if($login == $user){
            $arrRes = array('error' => false, 'mensaje' => 'Bienvenido '.$user);
        }else{
            $arrRes = array('error' => true, 'mensaje' => 'Datos incorrectos');
        }
    }else{
        $arrRes = array('error' => true, 'mensaje' => 'Datos incorrectos');
    }
}

echo json_encode($arrRes);
?>