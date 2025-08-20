const header = document.querySelector("header");
        const hambugerBtn = document.querySelector("#hamburgor-btn");
        const closeMenuBtn = document.querySelector("#close-button");

        hambugerBtn.addEventListener("click", () =>{
            header.classList.toggle("show-mobile-menu");
        })

        closeMenuBtn.addEventListener("click", () =>{
            hambugerBtn.click();
        })
        /**
 * 
 */