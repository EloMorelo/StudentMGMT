
function showSection(section) {
    $('.section').hide(); 
    $('#' + section + '-section').show(); 
}

const currentDateElement = $('#current-date');
let currentDate = new Date('2024-10-22');

function changeDay(offset) {
    currentDate.setDate(currentDate.getDate() + offset);
    updateDateDisplay();
}

function updateDateDisplay() {
    const options = { day: '2-digit', month: '2-digit', year: 'numeric' };
    currentDateElement.text(currentDate.toLocaleDateString('pl-PL', options));
    loadCoursesForDay(currentDate);
}

function loadCoursesForDay(date) {
    const courseContent = $('#courses-content');
    const sampleCourses = {
        '22.10.2024': ['Math 10:00 - 12:00', 'Science 14:00 - 16:00'],
        '23.10.2024': ['History 11:00 - 13:00', 'PE 15:00 - 16:00'],
    };

    const dateString = date.toLocaleDateString('pl-PL', { day: '2-digit', month: '2-digit', year: 'numeric' });
    const courses = sampleCourses[dateString];

    if (courses) {
        const courseList = courses.map(course => `<li>${course}</li>`).join('');
        courseContent.html(`<ul>${courseList}</ul>`);
    } else {
        courseContent.html('<p>No courses scheduled for this day.</p>');
    }
}

$(document).ready(function() {
    updateDateDisplay(); 

    $('#account-settings-btn').click(function() {
        window.location.href = '${pageContext.request.contextPath}/account-settings'; 
    });
});
