const apiEndpoint = 'http://localhost:8088';

// Function to display the selected content
function displayContent(option) {
    const content = document.querySelector('.content');

    // Clear previous content
    content.innerHTML = '';

    // Render the selected content
    switch (option) {
        case 'update-customer':
            content.innerHTML = `
              <h2>Update customer</h2>
              <form>
                  <label for="customerName">Customer Name</label>
                  <input type="text" id="customerName" name="customerName">
                  <label for="customerEmail">Email</label>
                  <input type="email" id="customerEmail" name="customerEmail">
                  <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Update</button>
              </form>
          `;
            break;
        case 'insert-trip-booking':
            content.innerHTML = `
              <h2>Insert trip booking</h2>
              <form onsubmit="insertTripBooking(event)>
                  <label for="fromLocation">From Location</label>
                  <input type="text" id="fromLocation" name="fromLocation">
                  <label for="toLocation">To Location</label>
                  <input type="text" id="toLocation" name="toLocation">
                  <label for="fromDateTime">From Booking Date</label>
                  <input type="datetime-local" id="fromDateTime" required>
                  <label for="toDateTime">To Booking Date</label>
                  <input type="datetime-local" id="toDateTime" required>
                  
                  <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Book Trip</button>
              </form>
          `;
            break;
        case 'update-trip-booking':
            content.innerHTML = `
              <h2>Update trip booking</h2>
              <form>
                  <label for="bookingId">Booking ID</label>
                  <input type="text" id="bookingId" name="bookingId">
                  <label for="newFromLocation">New From Location</label>
                  <input type="text" id="newFromLocation" name="newFromLocation">
                  <label for="newToLocation">New To Location</label>
                  <input type="text" id="newToLocation" name="newToLocation">
                  <label for="newFromDate">New Booking Date</label>
                  <input type="date" id="newFromDate" name="newFromDate">
                  <label for="newToDate">New Booking Date</label>
                  <input type="date" id="newToDate" name="newToDate">
                  <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Update Booking</button>
              </form>
          `;
            break;
        case 'delete-trip-booking':
            content.innerHTML = `
              <h2>Delete trip booking</h2>
              <form>
                  <label for="bookingId">Booking ID</label>
                  <input type="text" id="bookingId" name="bookingId">
                  <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Delete Booking</button>
              </form>
          `;
            break;
        case 'calculate-bill':
            content.innerHTML = `
              <h2>Calculate bill</h2>
              <form>
                  <label for="bookingId">Booking ID</label>
                  <input type="text" id="bookingId" name="bookingId">
                  <button type="submit">Calculate</button>
              </form>
          `;
            break;
        default:
            content.innerHTML = '<h2>Welcome, Customer!</h2><p>Select an option from the sidebar to view the results.</p>';
    }
}

// Functions for form submissions
function updateCustomer(event) {
    event.preventDefault();
    // Handle update customer logic here
}

function insertTripBooking(event) {
    event.preventDefault();
  
    const authorizationHeader = sessionStorage.getItem("authToken");
  
    if (!authorizationHeader) {
      console.log("Authorization token not found in session storage.");
      return;
    }
  
    // Extract the customer ID from the JWT token
    const decodedToken = decodeJWT(authorizationHeader);
    const customerId = decodedToken ? decodedToken.customerId : null;
  
    if (!customerId) {
      console.log("Customer ID not found in the authorization token.");
      return;
    }
  
    const fromLocation = document.getElementById('fromLocation').value;
    const toLocation = document.getElementById('toLocation').value;
    const fromDateTime = document.getElementById('fromDateTime').value;
    const toDateTime = document.getElementById('toDateTime').value;
  
    const tripData = {
        fromLocation: fromLocation,
        toLocation: toLocation,
        fromDateTime: new Date(fromDateTime).toISOString(), // Convert to ISO 8601 format
        toDateTime: new Date(toDateTime).toISOString() // Convert to ISO 8601 format
      };
  
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", `Bearer ${authorizationHeader}`);
  
    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: JSON.stringify(tripData),
      redirect: 'follow'
    };
  
    fetch(`http://localhost:8088/trip-bookings/${customerId}`, requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));
  }
  
  // Decode the JWT token and extract the payload data
  function decodeJWT(token) {
    try {
      const tokenParts = token.split('.');
      const encodedPayload = tokenParts[1];
      const decodedPayload = atob(encodedPayload);
      return JSON.parse(decodedPayload);
    } catch (error) {
      console.log('Error decoding JWT:', error);
      return null;
    }
  }
  


function updateTripBooking(event) {
    event.preventDefault();

    const bookingId = document.getElementById('bookingId').value;
    const newFromLocation = document.getElementById('newFromLocation').value;
    const newToLocation = document.getElementById('newToLocation').value;
    const newFromDate = document.getElementById('newFromDate').value;
    const newToDate = document.getElementById('newToDate').value;


    // Construct the updated trip booking object
    const updatedTripBooking = {
        fromLocation: newFromLocation,
        toLocation: newToLocation,
        bookingDate: newBookingDate,
        passengerCount: newPassengerCount,
        luggageCount: newLuggageCount
    };

    // Make a PUT request to update the trip booking
    fetch(`${apiEndpoint}/trip-bookings/${bookingId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedTripBooking)
    })
        .then(response => {
            // Handle the response
            if (response.ok) {
                // Display success message
                console.log('Trip booking updated successfully!');

                // Retrieve and display the updated trip details
                fetch(`${apiEndpoint}/trip-bookings/${bookingId}`)
                    .then(response => response.json())
                    .then(updatedTrip => {
                        const tripDetailsContainer = document.getElementById('tripDetailsContainer');
                        tripDetailsContainer.innerHTML = `
                        <h2>Updated Trip Details</h2>
                        <p>Booking ID: ${updatedTrip.bookingId}</p>
                        <p>From Location: ${updatedTrip.fromLocation}</p>
                        <p>To Location: ${updatedTrip.toLocation}</p>
                        <p>Booking Date: ${updatedTrip.bookingDate}</p>
                        <p>Passenger Count: ${updatedTrip.passengerCount}</p>
                        <p>Luggage Count: ${updatedTrip.luggageCount}</p>
                    `;
                    })
                    .catch(error => {
                        console.error('An error occurred while retrieving updated trip details:', error);
                    });

                // Optionally, you can update the UI or perform any other action

            } else {
                // Handle errors
                console.error('Failed to update trip booking.');
                // Optionally, you can display an error message or perform any other action
            }
        })
        .catch(error => {
            console.error('An error occurred while updating trip booking:', error);
            // Optionally, you can display an error message or perform any other action
        });
}



function deleteTripBooking(event) {
    event.preventDefault();

    const bookingId = document.getElementById('bookingId').value;

    // Make a DELETE request to delete the trip booking
    fetch(`${apiEndpoint}/trip-bookings/${bookingId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            // Handle the response
            if (response.ok) {
                // Display success message
                console.log('Trip booking deleted successfully!');
                // Optionally, you can update the UI or perform any other action
            } else {
                // Handle errors
                console.error('Failed to delete trip booking.');
                // Optionally, you can display an error message or perform any other action
            }
        })
        .catch(error => {
            console.error('An error occurred while deleting trip booking:', error);
            // Optionally, you can display an error message or perform any other action
        });
}


function calculateBill(event) {
    event.preventDefault();
    // Handle calculate bill logic here
}


document.getElementById("logout-button").addEventListener("click", confirmLogout);

function confirmLogout() {
    if (confirm("Are you sure you want to logout?")) {
        logoutCustomer();
    }
}
function logoutCustomer() {
    window.location.href = 'index.html';
}





// {
//     "fromLocation": "Pune",
//     "toLocation": "Dubai",
//     "fromDateTime": "2023-06-25T12:23:00",
//     "toDateTime": "2023-06-27T12:23:00"
// }