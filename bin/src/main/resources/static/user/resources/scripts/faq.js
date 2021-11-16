const paragraph = window.document.body.querySelectorAll(':scope> main > div.faq > section > div > p');
const div = window.document.body.querySelectorAll(':scope> main > div.faq > section > div');

for (let i = 0; i < div.length; i++) {
    paragraph[i].style.display = 'none';
    div[i].addEventListener('click', () => {
        for (let j = 0; j < div.length; j++) {
            paragraph[j].style.display = 'none';
            paragraph[i].style.display = 'block';
        }
    });
}

