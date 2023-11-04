// ASume front end and backend running on same ip
const ip = window.location.hostname;
function showAllIncomes() {
    const itemsElement = document.querySelector('#incomes');

    fetch(`http://${ip}:8080/getAllIncomes`)
    .then(response => {
        if (!response.ok) {
        throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the response as JSON
    })
    .then(items => {
        // Update the page with the list of plans
        itemsElement.innerHTML = '<tr><td>Motivo</td><td>Fecha</td><td>Monto</td></tr>';
        var i = 0;
        for (const item of items) {
            const row = document.createElement('tr');
            for (const value of item) {
              const cell = document.createElement('td');
              cell.textContent = value;
              row.appendChild(cell);
            }
            const delButton = document.createElement('button');
            delButton.setAttribute('onClick','deleteIncome('+i+')')
            delButton.textContent = 'Delete';
            row.appendChild(delButton);
            itemsElement.appendChild(row);
            i++;
          }
    })
    .catch(error => {
        // Handle errors here
        console.error('There was a problem with the fetch operation:', error);
    });

}
function deleteIncome(row) {
    const name = document.getElementById('incomes').rows[row+1].cells[0].textContent;
    const date = document.getElementById('incomes').rows[row+1].cells[1].textContent;
    console.log('date is '+date);

    const apiUrl = `http://${ip}:8080/deleteIncome?cause=${name}&date=${date}`;
    fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
        throw new Error('Network response was not ok');
        }
        showAllIncomes();
        return response.json();
    })
    .then(responseData => {
        // Handle the response data from the Spring backend
        alert('Se elimino item');
        console.log(responseData);
    })
    .catch(error => {
        // Handle errors here
        console.error('There was a problem with the fetch operation:', error);
    });
}
function registerIncome() {

    // Define the data to be sent to the API
    const name = document.getElementById('motive').value;
    const quantity = parseInt(document.getElementById('quantity').value);
    
    const apiUrl = `http://${ip}:8080/registerIncome?name=${name}&quantity=${quantity}`;

    
    fetch(apiUrl)
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    showAllIncomes();
    return response.json();
  })
  .then(responseData => {
    // Handle the response data from the Spring backend
    alert('Se agrego nuevo item');
    console.log(responseData);
  })
  .catch(error => {
    // Handle errors here
    console.error('There was a problem with the fetch operation:', error);
  });
}