/** @type {HTMLCanvasElement} */
const canvas = document.getElementById("myCanvas");
const ctx = canvas.getContext("2d");
//

// ctx.font = "48px serif"
// ctx.strokeText("Hello haha", 10, 50)
// const TOOLS = Object.freeze({
//   IDLE: "IDLE",
//   PEN: "PEN",
//   CLEAR_ALL: "CLEAR_ALL",
//   ERASER_STROKE: "ERASER_STROKE",
// });

// canvas.width = window.innerWidth;
// canvas.height = window.innerHeight;

// let isDraw = false;
// let isErasing = false;
// let colorInput = "#000000";
// let currTool = TOOLS.IDLE;

// let strokes = [];
// let currStroke = null;

// // -----------------------------------------------
// const penFunc = () => {
//   console.log(`pick ${currTool}`);
//   currTool = TOOLS.PEN;
//   let drawing = false;

//   colorInput = document.getElementsByName("color")[0];
// };

// // start draw
// canvas.addEventListener("mousedown", (e) => {
//   if (TOOLS.PEN !== currTool) return;
//   isDraw = true;

//   const pos = mousePos(e);
//   currStroke = {
//     color: colorInput.value,
//     size: 1,
//     points: [pos],
//   };

//   ctx.beginPath();
//   ctx.moveTo(pos.x, pos.y);

//   // throttle
//   //   ()=>{

//   //   }
// });

// // end draw
// canvas.addEventListener("mousemove", (e) => {
//   if (!isDraw) return;
//   const pos = mousePos(e);
//   currStroke.points.push(pos);
//   ctx.lineWidth = 1;
//   ctx.lineCap = "round";
//   ctx.strokeStyle = colorInput.value;
//   ctx.lineTo(pos.x, pos.y);
//   ctx.stroke();
// });

// // stop draw
// canvas.addEventListener("mouseup", () => {
//   if (!currStroke) {
//     return;
//   }
//   strokes.push(currStroke);
//   isDraw = false;
//   currStroke = null;

//   console.log(strokes);
// });

// canvas.addEventListener("mouseleave", () => (isDraw = false));

// document.querySelector(".myPen").addEventListener("click", penFunc);

// // eraser
// document.querySelector(".myEraser").addEventListener("click", () => {
//   console.info(`click ${currTool}`);
//   strokes = [];
//   ctx.clearRect(0, 0, canvas.width, canvas.height);
// });

// // eraser stroke
// canvas.addEventListener("mousedown", (e) => {
//   if (TOOLS.ERASER_STROKE !== currTool) {
//     return;
//   }
//   isErasing = true;
//   const mouse = mousePos(e);

//   const idx = strokes.findIndex((stroke) => {
//     return stroke.points.some((point) => {
//       const dx = mouse.x - point.x;
//       const dy = mouse.y - point.y;

//       const distance = Math.sqrt(dx * dx + dy * dy);

//       return distance < 10;
//     });
//   });

//   if (idx !== -1) {
//     strokes.splice(idx, 1);
//     redraw();
//   }
// });

// // eraser stroke
// document
//   .querySelector(".eraserStroke")
//   .addEventListener("click", () => (
//     currTool = TOOLS.ERASER_STROKE
// ));

// function redraw() {
//   ctx.clearRect(0, 0, canvas.width, canvas.height);

//   strokes.forEach((stroke) => {
//     ctx.beginPath();

//     ctx.strokeStyle = stroke.color;
//     ctx.lineWidth = stroke.size;
//     ctx.lineCap = "round";

//     ctx.moveTo(stroke.points[0].x, stroke.points[0].y);

//     stroke.points.forEach((point) => {
//       ctx.lineTo(point.x, point.y);
//     });

//     ctx.stroke();
//   });
// }

// const mousePos = (e) => {
//   // get canvas real size
//   const rect = canvas.getBoundingClientRect();

//   const scaleX = canvas.width / rect.width;
//   const scaleY = canvas.height / rect.height;
//   return {
//     x: (e.clientX - rect.left) * scaleX,
//     y: (e.clientY - rect.top) * scaleY,
//   };
// };
