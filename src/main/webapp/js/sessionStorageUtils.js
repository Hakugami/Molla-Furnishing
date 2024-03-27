// Function to get shopping data from sessionStorage
function getShoppingData() {
    return JSON.parse(sessionStorage.getItem('shoppingData')) || { products: [], productCounts: {} };
}

// Function to update shopping data in sessionStorage
function updateShoppingData(shoppingData) {
    sessionStorage.setItem('shoppingData', JSON.stringify(shoppingData));
}