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
          <form style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;">
            <label for="cabId">Cab ID</label>
            <input type="text" id="cabId" name="cabId">
            <label for="cabType">Cab Type</label>
            <input type="text" id="cabType" name="cabType">
            <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Insert Cab</button>
          </form>
        `;
            break;
        case 'delete-cab':
            content.innerHTML = `
          <h2 style="text-align: center;">Delete Cab</h2>
          <form style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;">
            <label for="cabId">Cab ID</label>
            <input type="text" id="cabId" name="cabId">
            <button type="submit" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Delete Cab</button>
          </form>
        `;
            break;
        case 'view-cab':
            content.innerHTML = '<h2 style="text-align: center;">View Cab</h2>';
            viewCab(); // Invoke the viewCab() function
            break;
        case 'view-cab-by-type':
            content.innerHTML = `
          <h2 style="text-align: center;">View Cab by Type</h2>
          <div style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;">
            <label for="cabType">Enter Cab Type:</label>
            <input type="text" id="cabType" />
            <button onclick="viewCabByType()" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">View Cabs</button>
          </div>
          <div id="cabTable"></div>
        `;
            break;
        case 'count-cab-by-type':
            content.innerHTML = `
          <h2 style="text-align: center;">Count Cab by Type</h2>
          <div style="width: 60%; gap: 10px; margin: 10px auto; display: flex; flex-direction: column;">
            <label for="cabType">Enter Cab Type:</label>
            <input type="text" id="cabType" />
            <button onclick="countCabByType()" style="font-size: 16px; background-color: #007bff; color: #fff; padding: 5px 10px; border: none; border-radius: 3px; cursor: pointer;">Count Cabs</button>
          </div>
          <div id="countResult"></div>
        `;
            break;
        default:
            content.innerHTML = '<h2 style="text-align: center;">Welcome, Driver!</h2><p>Select an option from the sidebar to view the results.</p>';
    }
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