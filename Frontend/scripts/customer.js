function loginCustomer() {
  window.location.href = 'customerDashboard.html';
}

// // Function to handle successful login
// function handleSuccessfulLogin() {
//   const loginButton = document.querySelector('.login .dropbtn');
//   loginButton.textContent = 'Logout';
//   loginButton.style.backgroundColor = 'red';
//   // Add logout functionality here, if required
// }

// // Add event listener to the form submit event
// const loginForm = document.getElementById('login-form');
// loginForm.addEventListener('submit', handleLogin);

// // Function to handle form submission
// function handleLogin(event) {
//   event.preventDefault(); // Prevent form submission

//   // Get form values
//   const username = document.getElementById('username').value;
//   const password = document.getElementById('password').value;

//   // Make AJAX request to API endpoint
//   fetch('/api/login', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({
//       username: username,
//       password: password
//     }),
//   })
//     .then(response => response.json())
//     .then(data => {
//       // Handle API response
//       if (data.success) {
//         // Call function to handle successful login
//         // handleSuccessfulLogin();
//         // Redirect to admin page
//         console.log(data)
//         window.location.href = 'customerDashboard.html';
//       } else {
//         // Display error message or show login failed notification
//         console.log('Login failed');
//       }
//     })
//     .catch(error => {
//       // Handle any errors that occur during the request
//       console.log('An error occurred:', error);
//     });
// }



function signupCustomer() {
  // Get input values from the form fields
  const name = document.getElementById("name").value;
  const address = document.getElementById("address").value;
  const email = document.getElementById("email").value;
  const mobile = document.getElementById("mobile").value;
  const username = document.getElementById("customer-username").value;
  const password = document.getElementById("customer-password").value;
  const role = "ROLE_CUSTOMER";

  // Create a data object with the input values
  const data = {
    name: name,
    address: address,
    email: email,
    mobile_number: mobile,
    user_name: username,
    password: password,
    role: role
  };

  // Send a POST request to the API endpoint
  fetch("http://localhost:8088/customers", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  })
    .then(response => {
      if (response.ok) {
        console.log("Customer created successfully!");
        window.location.href = 'customerDashboard.html';
      } else {
        throw new Error("Failed to create customer");
      }
    })
    .catch(error => {
      console.error("Error:", error);
    });

}


function checkAPI() {
  fetch("http://localhost:8088/customers")
    .then(response => response.json())
    .then(data => {
      console.log("API is working:", data);
    })
    .catch(error => {
      console.error("API is not working:", error);
    });
}

checkAPI();