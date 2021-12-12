document.addEventListener("DOMContentLoaded", function () {
    let input = document.getElementById('formFileMultiple');
    let outputDivCol = document.getElementById('outputDivCol');

    input.addEventListener("change", function (e) {
        let imgToDelete = outputDivCol.querySelectorAll("#newImg")
        imgToDelete.forEach(function (el) {
            outputDivCol.removeChild(el);
        })
        for (let i = 0; i < e.target.files.length; i++) {
            let newImg = document.createElement("img");
            let container = document.createElement("div")
            let col = document.createElement("div")
            col.classList.add("col-sm-auto")
            container.classList.add("container-sm")
            container.style.maxWidth="300px"
            col.id = "newImg"
            newImg.src = URL.createObjectURL(e.target.files[i]);
            newImg.classList.add("img-fluid");
            newImg.classList.add("img-thumbnail");
            container.appendChild(newImg);
            col.appendChild(container);
            outputDivCol.appendChild(col);

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
            if (deleteChild = true) {
                form.insertBefore(notAlways, before)
                deleteChild = false
            }
        } else if (rentWay.value == "1") {
            price1.classList.remove("visually-hidden");
            price2.classList.add("visually-hidden")
            if (deleteChild = true) {
                form.insertBefore(notAlways, before)
                deleteChild = false
            }
        }


    })


})