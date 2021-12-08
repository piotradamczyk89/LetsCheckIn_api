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

    let rentWay = document.querySelector("#rentWay")
    let price1 = document.querySelector("#Price1")
    let price2 = document.querySelector("#Price2")
    let notAlways = document.querySelector("#notAlways")
    let before = document.querySelector("#before");
    let form = document.querySelector("#formApartment")
    let clone = notAlways.cloneNode(true)
    let deleteChild = false

    rentWay.addEventListener("change", function (el) {

        if (rentWay.value == "3") {
            form.removeChild(notAlways);
            deleteChild = true;
        } else if (rentWay.value == "2") {
            price1.classList.add("visually-hidden");
            price2.classList.remove("visually-hidden")
            if(deleteChild=true) {
                form.insertBefore(notAlways,before)
                deleteChild=false
            }
        } else if (rentWay.value == "1") {
            price1.classList.remove("visually-hidden");
            price2.classList.add("visually-hidden")
            if(deleteChild=true) {
                form.insertBefore(notAlways,before)
                deleteChild=false
            }
        }


    })


})