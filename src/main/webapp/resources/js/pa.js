document.addEventListener("DOMContentLoaded", function () {
    let input = document.getElementById('formFileMultiple');
    let output = document.getElementById('output');
    let outputDiv = document.getElementById('outputDiv');


    input.addEventListener("change", function (e) {
        let imgToDelete = outputDiv.querySelectorAll("#newImg")
        imgToDelete.forEach(function (el) {
            outputDiv.removeChild(el);
        })
        for (let i = 0; i < e.target.files.length; i++) {
            let clone = output.cloneNode(true);
            clone.id = "newImg"
            clone.src = URL.createObjectURL(e.target.files[i]);
            clone.onload = function () {
                URL.revokeObjectURL(clone.src) // free memory
            }
            outputDiv.appendChild(clone);

        }
    })

})