<?php header('Content-Type:application/json');

include '../config.php';

if ($_POST) {
    $id = $_POST['id'];
    $sql = mysqli_query($db, "DELETE FROM notes WHERE id = $id");
    $query = mysqli_query($db, $sql);
    if ($sql){
        $response["error_State"] = false;
        $response["error_message"] = "Berhasil";
        echo json_encode($response);
    } else{
        $response["error"] = false;
        $response["error_message"] = "Gagal Mengirim";
        echo json_encode($response);
    }
}
else{
    $response["error_state"] = true;
    $response["error_message"] = "404";

    echo json_encode($response);
}
?>