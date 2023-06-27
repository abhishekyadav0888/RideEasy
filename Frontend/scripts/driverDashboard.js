// Function to display the selected content
function displayContent(option) {
  const content = document.querySelector('.content');

  // Clear previous content
  content.innerHTML = '';

  // Render the selected content
  switch (option) {
    case 'insert-cab':
      content.innerHTML = `
        <h2 style="text-align: center;">Insert Cab</h2>
        <form style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;" onsubmit="insertCab(event)">
          <label for="carType">Cab Type</label>
          <input type="text" id="carType" name="carType">
          <label for="perKmRate">Rate By Km</label>
          <input type="text" id="perKmRate" name="perKmRate">
          <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Insert Cab</button>
        </form>
      `;
      break;
    case 'delete-cab':
      content.innerHTML = `
        <h2 style="text-align: center;">Delete Cab</h2>
        <form style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;" onsubmit="deleteCab(event)">
          <label for="cabId">Cab ID</label>
          <input type="text" id="cabId" name="cabId">
          <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Delete Cab</button>
        </form>
      `;
      break;
    case 'view-cab':
      content.innerHTML = '<h2 style="text-align: center;">View Cab</h2>';
      // viewCab(); // Invoke the viewCab() function
      break;
    case 'view-cab-by-type':
      content.innerHTML = `
        <h2 style="text-align: center;">View Cab by Type</h2>
        <form style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;" onsubmit="viewCabByType(event)">
          <label for="carType">Enter Cab Type:</label>
          <input type="text" id="carType" />
          <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">View Cabs</button>
          </form>
        <div id="cabTable"></div>
      `;
      break;
    case 'count-cab-by-type':
      content.innerHTML = `
        <h2 style="text-align: center;">Count Cab by Type</h2>
        <form style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;" onsubmit="countCabByType(event)">
          <label for="cabType">Enter Cab Type:</label>
          <input type="text" id="carType" />
          <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Count Cabs</button>
        </form>
        <div id="countResult"></div>
      `;
      break;
    default:
      content.innerHTML = '<h2 style="text-align: center;">Welcome, Driver!</h2><p>Select an option from the sidebar to view the results.</p>';
  }
}

// ************************************************************************************************************************
// Function to insert cabs 
function insertCab(event){
  event.preventDefault();

  const carType = document.getElementById('carType').value;
  const perKmRate = document.getElementById('perKmRate').value;

  console.log(carType,perKmRate)

  const authorizationHeader = sessionStorage.getItem("authToken");

  if (!authorizationHeader) {
    console.log("Authorization token not found in session storage.");
    return;
  }

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  myHeaders.append("Authorization", `Bearer ${authorizationHeader}`);

  const cabData = {
    "cabId": 0,
    "carType": carType,
    "perKmRate": perKmRate
  };

  // Make the POST request to insert the cab
  fetch('http://localhost:8088/cabs/add', {
    method: 'POST',
    headers: myHeaders,
    body: JSON.stringify(cabData)
  })
    .then(response => {
      if (response.ok) {
        console.log('Cab inserted successfully.');

        // Display success message on the UI
        const successMessage = document.createElement('p');
        successMessage.style.textAlign = 'center';
        successMessage.style.color = 'green';
        successMessage.style.fontSize = '18px';
        successMessage.textContent = 'Cab inserted successfully.';
        const content = document.querySelector('.content');
        content.appendChild(successMessage);

        // Clear the input fields
        document.getElementById('carType').value = '';
        document.getElementById('perKmRate').value = '';
      } else {
        throw new Error('Failed to insert cab.');
      }
    })
    .catch(error => {
      console.error('An error occurred while inserting cab:', error);
    });
}


// Delete cab
function deleteCab(event) {
  event.preventDefault();

  const cabId = document.getElementById('cabId').value;

  const authorizationHeader = sessionStorage.getItem('authToken');

  if (!authorizationHeader) {
    console.log('Authorization token not found in session storage.');
    return;
  }

  const myHeaders = new Headers();
  myHeaders.append('Authorization', `Bearer ${authorizationHeader}`);

  // Make the DELETE request to delete the cab
  fetch(`http://localhost:8088/cabs/delete/${cabId}`, {
    method: 'DELETE',
    headers: myHeaders,
  })
    .then(response => {
      if (response.ok) {
        console.log('Cab deleted successfully.');

        // Display success message on the UI
        const successMessage = document.createElement('p');
        successMessage.style.textAlign = 'center';
        successMessage.style.color = 'green';
        successMessage.style.fontSize = '18px';
        successMessage.textContent = 'Cab deleted successfully.';
        const content = document.querySelector('.content');
        content.appendChild(successMessage);

        // Clear the input field
        document.getElementById('cabId').value = '';
      } else {
        throw new Error('Failed to delete cab.');
      }
    })
    .catch(error => {
      console.error('An error occurred while deleting cab:', error);
    });
}


// Function to fetch and display cabs by type
function viewCabByType(event) {
  event.preventDefault();

  const carType = document.getElementById('carType').value;

  const authorizationHeader = sessionStorage.getItem("authToken");

  if (!authorizationHeader) {
    console.log("Authorization token not found in session storage.");
    return;
  }

  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  myHeaders.append("Authorization", `Bearer ${authorizationHeader}`);

  // Make the GET request to fetch the cabs by type
  fetch(`http://localhost:8088/cabs/viewCabs/${carType}`, {
    method: 'GET',
    headers: myHeaders
  })
    .then(response => response.json())
    .then(cabs => {
      console.log("Fetched cabs:", cabs); // Log the fetched array of cabs

      // Display the fetched cabs in the cabTable element
      const cabTable = document.getElementById('cabTable');
      cabTable.innerHTML = '';

      if (cabs.length === 0) {
        cabTable.innerHTML = 'No cabs found for the specified type.';
      } else {
        const table = document.createElement('table');
        table.classList.add('center-table'); // Add CSS class for centering the table

        const tableHeader = document.createElement('thead');
        const headerRow = document.createElement('tr');
        headerRow.innerHTML = '<th>Cab ID</th><th>Cab Type</th><th>Rate per Km</th>';
        tableHeader.appendChild(headerRow);
        table.appendChild(tableHeader);

        const tableBody = document.createElement('tbody');
        cabs.forEach(cab => {
          const row = document.createElement('tr');
          row.innerHTML = `<td>${cab.cabId}</td><td>${cab.carType}</td><td>${cab.perKmRate}</td>`;
          tableBody.appendChild(row);
        });

        table.appendChild(tableBody);
        cabTable.appendChild(table);
      }

    })
    .catch(error => {
      console.error('An error occurred while fetching cabs:', error);
    });
}

// Count cab by type
function countCabByType() {
  const cabType = document.getElementById('carType').value;

  const authorizationHeader = sessionStorage.getItem('authToken');

  if (!authorizationHeader) {
    console.log('Authorization token not found in session storage.');
    return;
  }

  var myHeaders = new Headers();
  myHeaders.append('Content-Type', 'application/json');
  myHeaders.append('Authorization', `Bearer ${authorizationHeader}`);

  // Make the GET request to count cabs by type
  fetch(`http://localhost:8088/cabs/countCabs/${cabType}`, {
    method: 'GET',
    headers: myHeaders
  })
    .then(response => response.json())
    .then(count => {
      console.log('Total number of cabs for type', cabType + ':', count);
      const countResultElement = document.getElementById('countResult');
      countResultElement.style.textAlign = 'center';  // Center the text
      countResultElement.style.fontSize = '20px';  // Increase the font size
      countResultElement.style.color = 'red';  // Set the font color to red
      countResultElement.innerHTML = `<span style="color: red;">Total number of cabs for type ${cabType}: ${count}</span>`;
    })
    .catch(error => {
      console.error('An error occurred while counting cabs:', error);
    });
}








document.getElementById("logout-button").addEventListener("click", confirmLogout);

function confirmLogout() {
  if (confirm("Are you sure you want to logout?")) {
    logoutCustomer();
  }
}
function logoutCustomer() {
  sessionStorage.removeItem('authToken');
  window.location.href = 'index.html';
}
