
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
            <button type="submit">Update</button>
          </form>
        `;
            break;
        case 'insert-trip-booking':
            content.innerHTML = `
          <h2>Insert trip booking</h2>
          <form>
            <label for="fromLocation">From Location</label>
            <input type="text" id="fromLocation" name="fromLocation">
            <label for="toLocation">To Location</label>
            <input type="text" id="toLocation" name="toLocation">
            <label for="bookingDate">Booking Date</label>
            <input type="date" id="bookingDate" name="bookingDate">
            <button type="submit">Book Trip</button>
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
            <button type="submit">Update Booking</button>
          </form>
        `;
            break;
        case 'delete-trip-booking':
            content.innerHTML = `
          <h2>Delete trip booking</h2>
          <form>
            <label for="bookingId">Booking ID</label>
            <input type="text" id="bookingId" name="bookingId">
            <button type="submit">Delete Booking</button>
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
    const fromLocation = document.getElementById('fromLocation').value;
    const toLocation = document.getElementById('toLocation').value;
    const fromDateTime = document.getElementById('fromDateTime').value;
    const toDateTime = document.getElementById('toDateTime').value;
    const tripData = {
        fromLocation: fromLocation,
        toLocation: toLocation,
        fromDateTime: fromDateTime,
        toDateTime: toDateTime,
        // Add other form fields as needed
      };

    // Make the POST request to the API endpoint
    fetch('your-api-endpoint', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(tripData),
    })
        .then(response => response.json())
        .then(data => {
            // Handle the response from the API
            console.log('Trip inserted successfully:', data);
            // Add any logic to update the UI or display a success message
        })
        .catch(error => {
            // Handle any errors that occurred during the POST request
            console.error('Error inserting trip:', error);
            // Add any logic to display an error message or handle the error appropriately
        });
}

function updateTripBooking(event) {
    event.preventDefault();
    // Handle update trip booking logic here
}

function deleteTripBooking(event) {
    event.preventDefault();
    // Handle delete trip booking logic here
}

function calculateBill(event) {
    event.preventDefault();
    // Handle calculate bill logic here
}