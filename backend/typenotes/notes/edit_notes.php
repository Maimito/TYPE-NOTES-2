<?php header('Content-Type:application/json');

require_once "../config.php";

if ($_POST) {

    $id = $_POST['id'];
    $note_title = $_POST['note_title'];
    $note_content = $_POST['note_content'];
    $date_modified = $_POST['date_modified'];

    $sql = "UPDATE `notes` SET `note_title` = '$note_title', `note_content` = '$note_content', `date_modified` = '$date_modified' WHERE `notes`.`id` = '$id';";
    $query = mysqli_query($db, $sql);

    if($query){
        $response["error_state"] = false;
        $response["error_message"] = "Success";
        echo json_encode($response);
    } else {
        $response["error_state"] = false;
        $response["error_message"] = "Failed";
        echo json_encode($response);
    }

}

else {
    $response["error_state"] = true;
    $response["error_message"] = "Can't add notes";
    $response["error_code"] = "404";

    echo json_encode($response);
}
?>