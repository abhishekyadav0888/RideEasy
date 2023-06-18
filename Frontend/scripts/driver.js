function loginDriver(){
    window.location.href = 'driverDashboard.html';
}


// function loginDriver() {
//   const username = document.getElementById('username').value;
//   const password = document.getElementById('password').value;

//   // Make API call to authenticate the admin
//   fetch('/api/login', {
//     method: 'POST',
//     headers: {
//       'Content-Type': 'application/json'
//     },
//     body: JSON.stringify({ username, password })
//   })
//   .then(response => {
//     if (response.ok) {
//       // Authentication successful, redirect to admin dashboard
//       window.location.href = 'driverDashboard.html';
//     } else {
//       // Authentication failed, display error message
//       document.getElementById('error-message').textContent = 'Invalid username or password';
//     }
//   })
//   .catch(error => {
//     // Handle any errors that occurred during the API call
//     console.error('Error:', error);
//   });
// }


function signupDriver() {
    // Get the form inputs
    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const email = document.getElementById('email').value;
    const mobile = document.getElementById('mobile').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const license = document.getElementById('license').value;
    const rating = parseFloat(document.getElementById('rating').value);
    const role = "ROLE_DRIVER";
  
    // Create the driver object
    const driver = {
      name: name,
      address: address,
      email: email,
      mobile: mobile,
      username: username,
      password: password,
      license: license,
      rating: rating,
      role : role
    };
  
    // Send a POST request to the server to create the driver
    fetch('http://localhost:8088/drivers/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(driver)
    })
      .then(response => response.json())
      .then(data => {
        // Handle the response from the server
        if (data.success) {
          // Signup successful
          alert('Driver signup successful!');
          // Redirect to the login page or perform any other necessary actions
        } else {
          // Signup failed
          alert('Driver signup failed. Please try again.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        // Handle any errors that occurred during the request
        alert('An error occurred. Please try again later.');
      });
  }
  