function loginAdmin(){
    window.location.href = 'adminDashboard.html';
}
// Function to handle successful login
function handleSuccessfulLogin() {
    const loginButton = document.querySelector('.login .dropbtn');
    loginButton.textContent = 'Logout';
    loginButton.style.backgroundColor = 'red';
    // Add logout functionality here, if required
  }
  
  // Add event listener to the form submit event
  const loginForm = document.getElementById('login-form');
  loginForm.addEventListener('submit', handleLogin);
  
  // Function to handle form submission
  function handleLogin(event) {
    event.preventDefault(); // Prevent form submission
  
    // Get form values
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
  
    // Make AJAX request to API endpoint
    fetch('/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: username,
        password: password
      }),
    })
    .then(response => response.json())
    .then(data => {
      // Handle API response
      if (data.success) {
        // Call function to handle successful login
        handleSuccessfulLogin();
        // Redirect to admin page
        window.location.href = 'adminDashboard.html';
      } else {
        // Display error message or show login failed notification
        console.log('Login failed');
      }
    })
    .catch(error => {
      // Handle any errors that occur during the request
      console.log('An error occurred:', error);
    });
  }
  
  // Add further admin-specific functionality here
  