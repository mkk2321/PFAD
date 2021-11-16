const modifyForm = window.document.body.querySelector('[rel="modify-form"]');
const passwordRegEx = new RegExp('^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$');

let isPasswordChecked = false;
let isPasswordCheckChecked = false;

modifyForm['addressButton'].addEventListener('click', () => {
    new daum.Postcode({
        oncomplete: function (data) {
            modifyForm['addressPostal'].value = data.zonecode;
            modifyForm['addressPrimary'].value = data.roadAddress;
            modifyForm['addressSecondary'].focus();
            modifyForm['addressSecondary'].select();
        }
    }).open();
});


modifyForm['password'].addEventListener('input', () => {
    const passwordMessage = window.document.body.querySelector('[rel="password-message"]');
    passwordMessage.classList.remove('good');
    passwordMessage.classList.remove('warning');
    passwordMessage.innerText = '비밀번호를 입력해주세요.';
    passwordMessage.classList.add('warning');
});

modifyForm['password'].addEventListener('focusout', () => {
    const passwordMessage = window.document.body.querySelector('[rel="password-message"]');
    passwordMessage.classList.remove('good');
    passwordMessage.classList.remove('warning');
    passwordMessage.innerText = '';
    if(!passwordRegEx.test(modifyForm['password'].value)) {
        passwordMessage.innerText = '비밀번호를 입력해주세요.';
        passwordMessage.classList.add('warning');
        isPasswordChecked = false;
    }
    if(modifyForm['password'].value === "") {
        passwordMessage.classList.remove('good');
        passwordMessage.classList.remove('warning');
        passwordMessage.innerText = '';
        isPasswordChecked = true;
    }
});

modifyForm['checkPassword'].addEventListener('focus', () => {
    const checkPasswordMessage = window.document.body.querySelector('[rel="password-check-message"]');
    checkPasswordMessage.classList.remove('good');
    checkPasswordMessage.classList.remove('warning');
    checkPasswordMessage.innerText = '';
});

modifyForm['checkPassword'].addEventListener('input', () => {
    const checkPasswordMessage = window.document.body.querySelector('[rel="password-check-message"]');
    checkPasswordMessage.classList.remove('good');
    checkPasswordMessage.classList.remove('warning');
    checkPasswordMessage.innerText = '';
    if(modifyForm['password'].value !== modifyForm['checkPassword'].value) {
        checkPasswordMessage.innerText = '비밀번호가 올바르지 않습니다.';
        checkPasswordMessage.classList.add('warning');
        isPasswordCheckChecked = false;
    } else {
        checkPasswordMessage.innerText = '';
        checkPasswordMessage.classList.remove('good');
        checkPasswordMessage.classList.remove('warning');
        isPasswordCheckChecked = true;
    }
});

modifyForm.onsubmit = () => {
    if(!isPasswordChecked) {
        alert('비밀번호가 올바르지 않습니다.');
        modifyForm['password'].focus();
        modifyForm['password'].select();
        return false;
    }

    if(!isPasswordCheckChecked) {
        alert('비밀번호가 올바르지 않습니다.');
        modifyForm['checkPassword'].focus();
        modifyForm['checkPassword'].select();
        return false;
    }

    if(!passwordRegEx.test(modifyForm['password'].value)) {
        alert('비밀번호가 올바르지 않습니다.');
        modifyForm['password'].focus();
        modifyForm['password'].select();
        return false;
    }

    if(!passwordRegEx.test(modifyForm['checkPassword'].value)) {
        alert('비밀번호가 올바르지 않습니다.');
        modifyForm['checkPassword'].focus();
        modifyForm['checkPassword'].select();
        return false;
    }
};