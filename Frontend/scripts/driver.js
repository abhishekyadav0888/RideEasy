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