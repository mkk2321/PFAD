const passwordRegEx = new RegExp('^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$');
const passwordResetForm = window.document.body.querySelector('[rel="password-recover"]');

let isPasswordChecked = false;
let isPasswordCheckChecked = false;

passwordResetForm['password'].addEventListener('input', () => {
    const passwordMessage = window.document.body.querySelector('[rel="password-message"]');
    passwordMessage.classList.remove('good');
    passwordMessage.classList.remove('warning');
    passwordMessage.innerText = '특수문자, 문자, 숫자가 하나씩 포함된 8자리 이상의 비밀번호를 입력하세요';
    passwordMessage.classList.add('visible');
});

passwordResetForm['password'].addEventListener('focusout', () => {
   const passwordMessage = window.document.body.querySelector('[rel="password-message"]');
   passwordMessage.classList.remove('good');
   passwordMessage.classList.remove('warning');
   passwordMessage.innerText = '';
   if(!passwordRegEx.test(passwordResetForm['password'].value)) {
       passwordMessage.innerText = '특수문자, 문자, 숫자가 하나씩 포함된 8자리 이상의 비밀번호를 입력하세요';
       passwordMessage.classList.add('warning');
       isPasswordChecked = false;
   }else {
       passwordMessage.innerText = '';
       passwordMessage.classList.remove('good');
       passwordMessage.classList.remove('warning');
       isPasswordChecked = true;
   }
});

passwordResetForm['checkPassword'].addEventListener('focus', () => {
   const checkPasswordMessage = window.document.body.querySelector('[rel="checkPassword-message"]');
   checkPasswordMessage.classList.remove('good');
   checkPasswordMessage.classList.remove('warning');
   checkPasswordMessage.innerText = '';
});

passwordResetForm['checkPassword'].addEventListener('input', () => {
   const checkPasswordMessage = window.document.body.querySelector('[rel="checkPassword-message"]');
   checkPasswordMessage.classList.remove('good');
   checkPasswordMessage.classList.remove('warning');
   checkPasswordMessage.innerText = '';
   if(passwordResetForm['password'].value !== passwordResetForm['checkPassword'].value) {
       checkPasswordMessage.innerText = '비밀번호가 일치하지 않습니다.';
       checkPasswordMessage.classList.add('warning');
       isPasswordCheckChecked = false;
   } else {
       checkPasswordMessage.innerText = '';
       checkPasswordMessage.classList.remove('good');
       checkPasswordMessage.classList.remove('warning');
       isPasswordCheckChecked = true;
   }
});

passwordResetForm.onsubmit = () => {
    if(!isPasswordChecked) {
        alert('비밀번호가 올바르지 않습니다.');
        passwordResetForm['password'].focus();
        passwordResetForm['password'].select();
        return false;
    }

    if(!isPasswordCheckChecked) {
        alert('비밀번호가 올바르지 않습니다.');
        passwordResetForm['checkPassword'].focus();
        passwordResetForm['checkPassword'].select();
        return false;
    }

  if(!passwordRegEx.test(passwordResetForm['password'].value)) {
      alert('비밀번호가 올바르지 않습니다.');
      passwordResetForm['password'].focus();
      passwordResetForm['password'].select();
      return false;
  }

    if(!passwordRegEx.test(passwordResetForm['checkPassword'].value)) {
        alert('비밀번호가 올바르지 않습니다.');
        passwordResetForm['checkPassword'].focus();
        passwordResetForm['checkPassword'].select();
        return false;
    }
};