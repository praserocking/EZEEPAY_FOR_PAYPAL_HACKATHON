<?php
class utilities{	
	public function connect_db(){
		$dbh=mysqli_connect("localhost","root","","ezeepay");
		if(mysqli_connect_errno()){
	  		echo "<script>alert('Failed to connect to MySQL');</script>";
	  	}else{
			return $dbh;
		}
	}
}
class user_functions extends utilities{
	public function getName($uname){
		$connection=self::connect_db();
		$query="SELECT name from master_user where username='$uname'";
		$result=$connection->query($query);
		$temp_array=$result->fetch_array();
		$name=$temp_array[0];
		echo $name;
	}
	public function getUserPass($uname){
		$connection=self::connect_db();
		$query="SELECT pass from master_user where username='$uname'";
		$result=$connection->query($query);
		$temp_array=$result->fetch_array();
		$pass=$temp_array[0];
		return $pass;
	}
	public function getUid($uname){
		$connection=self::connect_db();
		$query="SELECT uid from master_user where username='$uname'";
		$result=$connection->query($query);
		$temp_array=$result->fetch_array();
		$pass=$temp_array[0];
		return $pass;
	}
	public function getDevID($uid){
		$connection=self::connect_db();
		$query="SELECT devid from master_user where uid='$uid'";
		$result=$connection->query($query);
		$temp_array=$result->fetch_array();
		$pass=$temp_array[0];
		return $pass;
	}
	public function getBankDetails($uid){
		$connection=self::connect_db();
		$query="SELECT bank,db_card_no from db_cards where uid='$uid'";
		$result=$connection->query($query);
		return $result;
	}
	public function isAlreadyRegisteredUser($user){
		$conn=self::connect_db();
		$query="SELECT username from master_user where username='$user'";
		$result=$conn->query($query);
		if($result->num_rows>0)
			return 1;
		else
			return 0;
	}
	public function getUserMobile($user){
		$conn=self::connect_db();
		$query="SELECT devid from master_user where username='$user'";
		$result=$conn->query($query);
		$temp=$result->fetch_array();
		if($temp[0]=="undefined")
			return '<a href="app/regapp.apk"><button class="btn btn-link">Download Our Android App to Use our Service!</button></a><br/>';
		else
			return $temp[0];
	}
	public function isAlreadyRegisteredCardNum($cardnum){
		$conn=self::connect_db();
		$query="SELECT * from db_cards where db_card_no='$cardnum'";
		$result=$conn->query($query);
		if($result->num_rows>0)
			return 1;
		else
			return 0;
	}
	public function showUserDetails($user){
		$conn=self::connect_db();
		$query="SELECT mail,uid,devname from master_user where username='$user'";
		$result=$conn->query($query);
		$temp_array=$result->fetch_array();
		$mobile=self::getUserMobile($user);
		echo "
			<tr><td>Mail ID: </td><td>$temp_array[0]</td></tr>
			<tr><td><b>Unique ID: </b></td><td><b>$temp_array[1]</b></td></tr>
			<tr><td>Registered Device:</td><td>$mobile</td></tr>
			";
	}
	public function showUserBanks($user){
		$uid=self::getUid($user);
		$conn=self::connect_db();
		$query="select bank,db_card_no from db_cards where uid='$uid'";
		$result=$conn->query($query);
		echo "<tr><td><b>Bank Name</b></td><td><b>Card Number</b></td></tr>";
		while($temp_array=$result->fetch_array()){
			echo "
			<tr><td>$temp_array[0]</td><td>$temp_array[1]</td></tr>
			";
		}
		
	}
}
class merchant_functions extends utilities{
	public function getMName($uname){
		$connection=self::connect_db();
		$query="SELECT Mname from master_merchant where username='$uname'";
		$result=$connection->query($query);
		$temp_array=$result->fetch_array();
		$name=$temp_array[0];
		echo $name;
	}
	public function getReferURL($uid){
		$connection=self::connect_db();
		$query="SELECT referalurl from master_merchant where uid='$uname'";
		$result=$connection->query($query);
		$temp_array=$result->fetch_array();
		$url=$temp_array[0];
		echo $url;
	}
	public function isAlreadyRegisteredMerchant($user){
		$conn=self::connect_db();
		$query="SELECT username from master_merchant where username='$user'";
		$result=$conn->query($query);
		if($result->num_rows>0)
			return 1;
		else
			return 0;
	}
	public function showMerchantDetails($user){
		$conn=self::connect_db();
		$query="SELECT Mmail,uid,referalurl from master_merchant where username='$user'";
		$result=$conn->query($query);
		$temp_array=$result->fetch_array();
		echo "
			<tr><td>Mail ID: </td><td>$temp_array[0]</td></tr>
			<tr><td><b>Unique ID:</b></td><td><b>$temp_array[1]</b></td></tr>
			<tr><td>Current Referal URL </td><td>$temp_array[2]</td></tr>
			";
	}
}
class cipher {
    private $securekey, $iv;
    function __construct(){
		$textkey=md5("ezeepay");
        $this->securekey = hash('sha256',$textkey,TRUE);
        $this->iv = mcrypt_create_iv(32);
    }
    public function encrypt($input) {
        return base64_encode(mcrypt_encrypt(MCRYPT_RIJNDAEL_256, $this->securekey, $input, MCRYPT_MODE_ECB, $this->iv));
    }
    public function decrypt($input) {
        return trim(mcrypt_decrypt(MCRYPT_RIJNDAEL_256, $this->securekey, base64_decode($input), MCRYPT_MODE_ECB, $this->iv));
    }
}
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

?>