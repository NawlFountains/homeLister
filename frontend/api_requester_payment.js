function showAllPayments() {
    const itemsElement = document.querySelector('#payments');

    fetch(`http://localhost:8080/getAllPayments`)
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
            delButton.setAttribute('onClick','deletePayment('+i+')')
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
function deletePayment(row) {
    const name = document.getElementById('payments').rows[row+1].cells[0].textContent;
    const date = document.getElementById('payments').rows[row+1].cells[1].textContent;

    const apiUrl = `http://localhost:8080/deletePayment?cause=${name}&date=${date}`;
    fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
        throw new Error('Network response was not ok');
        }
        showAllPayments();
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
function registerPayment() {

    // Define the data to be sent to the API
    const name = document.getElementById('motive').value;
    const quantity = parseInt(document.getElementById('quantity').value);
    
    const apiUrl = `http://localhost:8080/registerPayment?name=${name}&quantity=${quantity}`;

    
    fetch(apiUrl)
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    showAllPayments();
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