
function handleLogin() {
  window.location.href = 'customerDashboard.html';
}



// // Add event listener to the form submit event
// const loginForm = document.getElementById('login-form');
// loginForm.addEventListener('submit', handleLogin);

// // Function to handle form submission
// function handleLogin(event) {
//   event.preventDefault();

//   // Get input values from the form fields
//   const username = "CUST_" + document.getElementById("customer-username").value;
//   const password = document.getElementById("customer-password").value;

//   var myHeaders = new Headers();
//   myHeaders.append("Content-Type", "application/json");
//   myHeaders.append("Authorization", "Basic Q1VTVF9yYWoxMjM6cmFqMTIz");

//   var requestOptions = {
//     method: 'POST',
//     headers: myHeaders,
//     body: JSON.stringify({ username, password }),
//     redirect: 'follow'
//   };

//   // Send a POST request to the API endpoint for authentication
//   fetch("http://localhost:8088/signIn", requestOptions)
//     .then(response => response.text())
//     .then(result => console.log(result))
//     .catch(error => console.log('error', error));
// }

// function handleLogin(event) {
//   event.preventDefault();

//   // Get input values from the form fields
//   const username = "CUST_" + document.getElementById("customer-username").value;
//   const password = document.getElementById("customer-password").value;

//   // Create the query string for the login parameters
//   const queryString = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`;
//   const url = `http://localhost:8088/signIn?${queryString}`;

//   // Send a GET request to the API endpoint for authentication
//   fetch(url)
//     .then(response => response.text())
//     .then(result => {
//       console.log(result);
//       // Save the customerId in session or local storage
//       const customerId = JSON.parse(result).customerId;
//       localStorage.setItem('customerId', customerId);
//       // Redirect to the customer dashboard
//       window.location.href = 'customerDashboard.html';
//     })
//     .catch(error => console.log('error', error));
// }



function signupCustomer(event) {
  event.preventDefault();

  // Get input values from the form fields
  const name = document.getElementById("name").value;
  const address = document.getElementById("address").value;
  const email = document.getElementById("email").value;
  const mobile = document.getElementById("mobile").value;
  const username = document.getElementById("customer-username").value;
  const password = document.getElementById("customer-password").value;
  const role = "ROLE_CUSTOMER";

  
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

fetch("http://localhost:8088/customers", requestOptions)
    .then(response => response.text())
    .then(result => {
      console.log(result);
      // Show success message
      alert("Signup successful, Please Sign In");
      // Redirect to another page or perform any other action
      // window.location.href = 'customerDashboard.html';
    })
    .catch(error => console.log('error', error));
}


