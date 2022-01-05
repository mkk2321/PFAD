const writeForm = window.document.body.querySelector('[rel="modify-form"]');

ClassicEditor.create(writeForm['content'], {
    toolbar : {
        items: [
            "heading",
            "|",
            "bold",
            "italic",
            "link",
            "bulletedList",
            "numberedList",
            "|",
            "outdent",
            "indent",
            "|",
            "imageUpload",
            "blockQuote",
            "insertTable",
            "mediaEmbed",
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
        uploadUrl: '../upload-image'
    }
});