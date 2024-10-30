
function showSection(section) {
    $('.section').hide(); 
    $('#' + section + '-section').show(); 
}

const currentDateElement = document.getElementById('current-date');

if (isNaN(currentDate.getTime())) {
    console.error("Invalid Date:", currentDate);
}

function updateDateDisplay() {
    const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
    currentDateElement.textContent = currentDate.toLocaleDateString('pl-PL', options);
    
    loadCoursesForDay(currentDate);
}

function loadCoursesForDay(date) {
    const dateString = date.toISOString().split('T')[0]; 
    console.log("Loading courses for date:", dateString); 
    $.ajax({
        url: 'dashboard',
        type: 'GET',
        data: { date: dateString },
        headers: { 'X-Requested-With': 'XMLHttpRequest' },
        success: function(response) {
            $('#courses-content').html(response);
        },
        error: function() {
            $('#courses-content').html('<p>Error loading courses.</p>');
        }
    });
}

function changeDay(delta) {
    currentDate.setDate(currentDate.getDate() + delta);
    updateDateDisplay(); 
}

$(document).ready(function() {
    updateDateDisplay(); 
});

