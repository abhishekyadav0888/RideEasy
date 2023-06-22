// Login Function
function handleLogin(event) {
  event.preventDefault();

  // Get input values from the form fields
  const username = "CUST_" + document.getElementById("driver-username").value;
  const password = document.getElementById("driver-password").value;

  var myHeaders = new Headers();
  myHeaders.append("Authorization", "Basic Q1VTVF9yYWoxMjM6cmFqMTIz");

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
  };

  fetch("http://localhost:8088/drivers/signIn", requestOptions)
    .then(response => {
      if (response.ok) {
        const authorizationHeader = response.headers.get('Authorization');
        sessionStorage.setItem("authToken", authorizationHeader);
        console.log(authorizationHeader);
        sayHello();
        window.location.href = 'driverDashboard.html';
        // Use the authorizationHeader for further requests or processing
      } else {
        throw new Error('Login failed');
      }
    })
    .catch(error => console.log('error', error));
}
// Test Login
function sayHello() {
  const authorizationHeader = sessionStorage.getItem("authToken");

  if (!authorizationHeader) {
    console.log("Authorization token not found in session storage.");
    return;
  }

  const myHeaders = new Headers();
  myHeaders.append("Authorization", `Bearer ${authorizationHeader}`);

  const requestOptions = {
    method: 'GET',
    headers: myHeaders
  };

  fetch("http://localhost:8088/drivers/hello", requestOptions)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));
}


// Logout Function
function logout() {
  sessionStorage.removeItem("authToken");
  // Redirect or perform any other actions after logging out
  window.location.href = 'index.html'; // Replace with the appropriate redirect URL
}


// Add new Customer 
function signupDriver(event) {
  event.preventDefault();

  // Get input values from the form fields
  const name = document.getElementById("name").value;
  const address = document.getElementById("address").value;
  const email = document.getElementById("email").value;
  const mobile = document.getElementById("mobile").value;
  const username = document.getElementById("driver-username").value;
  const password = document.getElementById("driver-password").value;
  const role = "ROLE_DRIVER";


  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  // Create a data object with the input values
  const raw = {
    "name": name,
    "userName": username,
    "password": password,
    "address": address,
    "mobileNumber": mobile,
    "email": email,
    "role": role
  };


  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: JSON.stringify(raw),
    redirect: 'follow'
  };

  fetch("http://localhost:8088/drivers/add", requestOptions)
    .then(response => response.text())
    .then(result => {
      console.log(result);
      // Show success message
      // alert("Signup successful, Please Sign In");
      // Redirect to another page or perform any other action
      // window.location.href = 'customerDashboard.html';
    })
    .catch(error => console.log('error', error));
}


