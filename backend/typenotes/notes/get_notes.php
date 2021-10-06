<?php header('Content-Type:application/json');

include "../config.php";

    $sql = mysqli_query($db, "SELECT * FROM notes");

    while($data = mysqli_fetch_assoc($sql)){
        $arrayJson[] = $data;
    }
    if ($arrayJson != null){
        $response = $arrayJson;
    } else {
        $response = "Can't get data";
    }


    echo json_encode($response);

/* if ($_GET){

    

} else {
    $response["error_state"] = true;
    $response["error_message"] = "Can't get notes";
    $response["error_code"] = "404";

    echo json_encode($response);
}*/
?>
