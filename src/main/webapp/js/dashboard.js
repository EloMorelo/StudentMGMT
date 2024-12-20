function showSection(section) {
    console.log("Switching to section:", section);
    $('.section').hide();
    $('#' + section + '-section').show();

    if (section === 'grades') {
        loadGrades();
    }
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

function loadGrades() {
    console.log("Loading grades...");
    $.ajax({
        url: 'grades',
        type: 'GET',
        headers: { 'X-Requested-With': 'XMLHttpRequest' },
        success: function(response) {
            $('#grades-content').html(response);
        },
        error: function() {
            $('#grades-content').html('<p>Error loading grades. Please try again later.</p>');
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
