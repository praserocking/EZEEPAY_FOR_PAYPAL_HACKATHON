<?php
function sendGCMNotification($registrationIdsArray, $messageData ){  
	$apiKey="AIzaSyCmME04nKvjvSF-dr4uugZpkycBKrh6Iog"; 
    $headers = array("Content-Type:"."application/json","Authorization:"."key=".$apiKey);
    $data = array(
        'data' => $messageData,
        'registration_ids' => $registrationIdsArray
    );
    $ch = curl_init();
    curl_setopt( $ch, CURLOPT_HTTPHEADER, $headers ); 
    curl_setopt( $ch, CURLOPT_URL, "https://android.googleapis.com/gcm/send" );
    curl_setopt( $ch, CURLOPT_SSL_VERIFYHOST, 0 );
    curl_setopt( $ch, CURLOPT_SSL_VERIFYPEER, 0 );
    curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
    curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode($data) );
    $response = curl_exec($ch);
    curl_close($ch);
    return $response;
}
$reg=array('APA91bHy4Sr7thK4UwnbMupizT0QN_uqgTpbMaOR1pGrXiWHRCkq44BDoTLCvEbWpckNfBKDw9zJVb9o5-GHvMNSGIkihb4QZlnOAfGMW_IX2S6rl8-8fmom14dELyerT5rE9ynBUFbawuoxyG98cMyAx588Z0OJsw');
$msg=array("count"=>"1",
			"bank1"=>"ss",
			"cardnum"=>"wer",
			"user"=>"me",
			"merchant"=>"you");
$x=sendGCMNotification($reg,$msg);
echo $x;
?>