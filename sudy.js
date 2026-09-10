"use strict";

// const titles = document.querySelectorAll('h1')
// console.log(titles)
// titles[3].className = 'title'
// titles[3].setAttribute('id', 'fourth-title')
// titles[3].textContent =  'Hello world!'

// let h1 = document.createElement('h1')
// h1.className = 'title'
// h1.id = 'fifth-title'
// h1.textContent = 'H1 5th'

// document.body.appendChild(h1)

const inp = document.getElementsByClassName("inp");

let timer;

inp[0].addEventListener("keyup", (e) => {
  console.log("current timer", timer);
  clearTimeout(timer);
  const domain = "potterapi-fedeperin.vercel.app";
  const lang = `en`;
  const source = "books";
  const url = `https://${domain}/${lang}/${source}`;
  timer = setTimeout(async () => {
    const res = await fetch(url);
    const data = await res.json();
    console.log(data, "after 500ms");
  }, 1500);
  console.log("after timer", timer);
});

let scroll = true;
window.addEventListener("scroll", () => {
    if (!scroll){
        return
    }
    console.log('scroll check')
    scroll = false

    // after 3s set scroll value again
    setTimeout(() => {
        scroll = true;
    }, 3000)
});
