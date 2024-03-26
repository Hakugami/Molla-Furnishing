// Function to display dynamic notifications
function showNotification(message, type) {
    var notificationsContainer = document.getElementById('notifications');
    var popup;

    // Create a popup element based on the type
    switch (type) {
        case 'primary':
            popup = document.createElement('div');
            popup.className = 'alert alert-primary popup';
            break;
        case 'success':
            popup = document.createElement('div');
            popup.className = 'alert alert-success popup';
            break;
        case 'danger':
            popup = document.createElement('div');
            popup.className = 'alert alert-danger popup';
            break;
        case 'warning':
            popup = document.createElement('div');
            popup.className = 'alert alert-warning popup';
            break;
        default:
            // Default to primary if type is invalid or not specified
            popup = document.createElement('div');
            popup.className = 'alert alert-primary popup';
            break;
    }

    // Set the message content
    popup.textContent = message;

    // Append the popup to the notifications container
    notificationsContainer.appendChild(popup);

    // Automatically remove the popup after 3 seconds (adjust as needed)
    setTimeout(function() {
        popup.remove();
    }, 3000);
}
