const registerForm = window.document.body.querySelector('[rel="register-form"]');
ClassicEditor.create(registerForm['image'], {
    toolbar : {
        items: [

            "imageUpload",
            "undo",
            "redo"
        ]
    },
    language: "en",
    image   : {
        toolbar: [
            "imageTextAlternative",
            "imageStyle:inline",
            "imageStyle:block",
            "imageStyle:side"
        ]
    },
    table   : {
        contentToolbar: [
            "tableColumn",
            "tableRow",
            "mergeTableCells"
        ]
    },
    licenseKey: '',
    simpleUpload: {
        uploadUrl: 'http://127.0.0.1/product/upload-image'
    }
});