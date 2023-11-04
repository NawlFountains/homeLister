function showAllItems() {
    const itemsElement = document.querySelector('#items');

    fetch(`http://localhost:8080/getAllItems`)
    .then(response => {
        if (!response.ok) {
        throw new Error('Network response was not ok');
        }
        return response.json(); // Parse the response as JSON
    })
    .then(items => {
        // Update the page with the list of plans
        const headerRow = itemsElement.firstElementChild;
        console.log(headerRow);
        itemsElement.innerHTML = '<tr><td>Nombre</td><td>Cantidad</td><td></td></tr>';
        var i = 0;
        for (const item of items) {
            const row = document.createElement('tr');
            for (const value of item) {
              const cell = document.createElement('td');
              cell.textContent = value;
              row.appendChild(cell);
            }
            const delButton = document.createElement('button');
            delButton.setAttribute('onClick','deleteItem('+i+')')
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
function deleteItem(row) {
    const name = document.getElementById('items').rows[row+1].cells[0].textContent;

    const apiUrl = `http://localhost:8080/deleteItem?name=${name}`;
    fetch(apiUrl)
    .then(response => {
        if (!response.ok) {
        throw new Error('Network response was not ok');
        }
        showAllItems();
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
function registerItem() {

    // Define the data to be sent to the API
    const name = document.getElementById('name').value;
    const quantity = parseInt(document.getElementById('quantity').value);
    
    const apiUrl = `http://localhost:8080/registerItem?name=${name}&quantity=${quantity}`;

    
    fetch(apiUrl)
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    showAllItems();
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